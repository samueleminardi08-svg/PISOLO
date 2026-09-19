const ONESIGNAL_API = "https://api.onesignal.com/notifications";

// Lista di messaggi affettuosi per il cron giornaliero
const MESSAGGI = [
  "Buongiorno amore, oggi vai alla grande 💛",
  "Un respiro profondo: sei più forte di quello che credi. 🌸",
  "Ti penso. Sei speciale, sempre. ❤️",
  "Prenditi tutto il tempo che ti serve: nuotiamo piano insieme oggi. 🦈💙",
  "Ti mando una fusa morbidissima per sciogliere ogni pensiero pesante. 🐱🌸",
  "Nessuna tempesta dura per sempre. Ti proteggo io, sei al sicuro. 🦈🛡️",
  "Piccolo promemoria: bevi un sorso d'acqua, rilassa le spalle e fai un respiro. 💧✨",
  "Sei il mio piccolo tesoro prezioso. Non dimenticare quanto vali. 💕",
  "Oggi non devi dimostrare niente a nessuno. Fai le cose con calma e dolcezza. 🌿",
  "Una coccola calda e un abbraccio stritolante solo per te da Pisolo & Micio! 🦈🐱",
  "Anche nei giorni più grigi, il tuo sorriso illumina tutto. Fiero di te. 🌟",
  "Fai piano oggi, un passettino alla volta. 🐾",
  "Chiudi gli occhi per cinque secondi e immagina che ti stia stringendo forte forte. 🫂❤️",
  "Meriti solo cose belle, riposo sereno e tutta la tenerezza del mondo. 🌸",
  "Se oggi è un giorno no, va bene così. Non devi essere invincibile. Io ci sono sempre. 🦈🤍",
  "Un bacino sulla fronte e una carezza leggera per iniziare bene questo momento. 😽✨",
  "Ricordati che sei amatissima e che per te ci sono sempre. Sempre. ⚓❤️"
];

async function inviaNotifica(env, testo, titolo) {
  const body = {
    app_id: env.ONESIGNAL_APP_ID,
    target_channel: "push",
    included_segments: ["Subscribed Users"],
    headings: { en: titolo || "PISOLO & Micio 🦈🐱" },
    contents: { en: testo }
  };
  const res = await fetch(ONESIGNAL_API, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      "Authorization": `Key ${env.ONESIGNAL_REST_API_KEY}`
    },
    body: JSON.stringify(body)
  });
  const data = await res.json();
  return { ok: res.ok, status: res.status, data };
}

export default {
  // (a) Endpoint on-demand protetto da token
  async fetch(request, env) {
    const cors = {
      "Access-Control-Allow-Origin": "*",
      "Access-Control-Allow-Methods": "POST, OPTIONS",
      "Access-Control-Allow-Headers": "Content-Type, X-Auth-Token"
    };
    if (request.method === "OPTIONS") return new Response(null, { headers: cors });
    if (request.method !== "POST")
      return new Response("Method not allowed", { status: 405, headers: cors });

    const token = request.headers.get("X-Auth-Token");
    if (token !== env.SEND_SECRET)
      return new Response("Unauthorized", { status: 401, headers: cors });

    let payload = {};
    try { payload = await request.json(); } catch (_) {}
    const testo = (payload.message || "").trim();
    if (!testo)
      return new Response(JSON.stringify({ error: "Messaggio vuoto" }),
        { status: 400, headers: { ...cors, "Content-Type": "application/json" } });

    const r = await inviaNotifica(env, testo, payload.title);
    return new Response(JSON.stringify(r),
      { status: r.ok ? 200 : 502, headers: { ...cors, "Content-Type": "application/json" } });
  },

  // (b) Cron giornaliero: messaggio casuale
  async scheduled(event, env, ctx) {
    const testo = MESSAGGI[Math.floor(Math.random() * MESSAGGI.length)];
    ctx.waitUntil(inviaNotifica(env, testo, "Un pensiero per te 💛"));
  }
};
