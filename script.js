// Percorsi delle foto predefinite di Pisolo & Micio, salvate come file separati in /assets
// (prima erano incorporate come testo base64 direttamente qui, appesantendo il file di alcuni MB)
const EMBEDDED_ASSETS = {
  "shark_1": "assets/img_01_0a586335395e.jpg",
  "shark_2": "assets/img_03_fa6339c60a33.jpg",
  "shark_3": "assets/img_04_474c0282a71d.jpg",
  "shark_4": "assets/img_05_44e40f9a0c35.jpg",
  "kitty_1": "assets/img_02_80bf04a43bbc.jpg",
  "kitty_2": "assets/img_06_7d757a1dd8a1.jpg",
  "kitty_3": "assets/img_07_96256b3c039b.jpg",
  "kitty_4": "assets/img_08_2c3322d80209.jpg",
  "photo_featured": "assets/img_09_217ed04bd5bb.jpg"
};

function resolveAsset(val, defaultAsset) {
  if (!val) return defaultAsset;
  if (typeof val === 'string') {
    if (val.startsWith('data:image/')) return val;
    if (EMBEDDED_ASSETS[val]) return EMBEDDED_ASSETS[val];
    if (val.includes('shark_1') || val.includes('mascot_shark')) return EMBEDDED_ASSETS.shark_1;
    if (val.includes('shark_2')) return EMBEDDED_ASSETS.shark_2;
    if (val.includes('shark_3')) return EMBEDDED_ASSETS.shark_3;
    if (val.includes('shark_4') || val.includes('sleep')) return EMBEDDED_ASSETS.shark_4;
    if (val.includes('kitty_1') || val.includes('mascot_kitty')) return EMBEDDED_ASSETS.kitty_1;
    if (val.includes('kitty_2')) return EMBEDDED_ASSETS.kitty_2;
    if (val.includes('kitty_3')) return EMBEDDED_ASSETS.kitty_3;
    if (val.includes('kitty_4') || val.includes('cuddle') || val.includes('duo')) return EMBEDDED_ASSETS.kitty_4;
    if (val.includes('photo_featured')) return EMBEDDED_ASSETS.photo_featured;
  }
  return defaultAsset;
}

const MASCOT_SHARK = EMBEDDED_ASSETS.shark_1;
const MASCOT_KITTY = EMBEDDED_ASSETS.kitty_1;
const AUTONOMOUS_PERMANENT_URL = "https://ais-pre-cn7ojrljxfgkoj5uz546l6-282901258740.europe-west2.run.app";

// Inserisci qui la tua API Key di Gemini. 
// ATTENZIONE: La chiave è visibile a chiunque ispezioni il codice sorgente.
// Usa una chiave dedicata a questo progetto con limiti di quota impostati dal pannello Google AI Studio.
const HARDCODED_GEMINI_API_KEY = "";

// Presets foto per Squaletti (Pisolo) e Gattini (Micio)
const SHARK_PHOTOS_PRESETS = [
  {
    id: "shark_1",
    title: "Pisolo Classico",
    tag: "Originale 🦈",
    src: EMBEDDED_ASSETS.shark_1,
    desc: "Il tenero peluche azzurro fedele e protettivo"
  },
  {
    id: "shark_2",
    title: "Pisolo sul lettone",
    tag: "Coccole 🛏️",
    src: EMBEDDED_ASSETS.shark_2,
    desc: "Morbido e rilassato sulla coperta calda e soffice"
  },
  {
    id: "shark_3",
    title: "Pisolo tra le nuvole",
    tag: "Sogni ☁️",
    src: EMBEDDED_ASSETS.shark_3,
    desc: "Sorridente e dolce sul cuscino piumoso"
  },
  {
    id: "shark_4",
    title: "Pisolo che dorme",
    tag: "Relax 💤",
    src: EMBEDDED_ASSETS.shark_4,
    desc: "Occhietti chiusi che vegliano sui tuoi sogni"
  }
];

const KITTY_PHOTOS_PRESETS = [
  {
    id: "kitty_1",
    title: "Micio Dolce",
    tag: "Originale 🐱",
    src: EMBEDDED_ASSETS.kitty_1,
    desc: "Il musetto tenero dallo sguardo dolce e sincero"
  },
  {
    id: "kitty_2",
    title: "Micio acciambellato",
    tag: "Nanna 🧶",
    src: EMBEDDED_ASSETS.kitty_2,
    desc: "Appallottolato e pronto per fare le fusa"
  },
  {
    id: "kitty_3",
    title: "Micio curiosone",
    tag: "Occhioni 👀",
    src: EMBEDDED_ASSETS.kitty_3,
    desc: "Spunta curioso dal morbido cestino di lana"
  },
  {
    id: "kitty_4",
    title: "Pisolo & Micio insieme",
    tag: "Inseparabili 💕",
    src: EMBEDDED_ASSETS.kitty_4,
    desc: "Abbraccio e coccole tra i due amici inseparabili"
  }
];

let currentMascotPhotoTab = 'all';

// 8 RITRATTI MASCOTTE ALTERNATI FISSI (4 SQUALETTI + 4 GATTINI)
const ALTERNATING_MASCOT_PRESETS = [
  { type: 'shark', id: 'shark_1', title: 'Pisolo Morbido 🦈', desc: 'Squaletto soffice e rassicurante', tag: '🦈 Squaletto #1', badge: '🦈 Pisolo • Foto 1/4' },
  { type: 'kitty', id: 'kitty_1', title: 'Micio Dolce 🐱', desc: 'Gattino coccoloso per le tue giornate', tag: '🐱 Gattino #1', badge: '🐱 Micio • Foto 1/4' },
  { type: 'shark', id: 'shark_2', title: 'Pisolo Nuotatore 🦈', desc: 'Pronto a farti da scudo in ogni tempesta', tag: '🦈 Squaletto #2', badge: '🦈 Pisolo • Foto 2/4' },
  { type: 'kitty', id: 'kitty_2', title: 'Micio Sonnecchiante 🐱', desc: 'Fusa rilassanti e nanna tranquilla', tag: '🐱 Gattino #2', badge: '🐱 Micio • Foto 2/4' },
  { type: 'shark', id: 'shark_3', title: 'Pisolo Cucciolotto 🦈', desc: 'Tenerezza infinita per scaldarti il cuore', tag: '🦈 Squaletto #3', badge: '🦈 Pisolo • Foto 3/4' },
  { type: 'kitty', id: 'kitty_3', title: 'Micio Curioso 🐱', desc: 'Occhietti dolci che ti sorridono', tag: '🐱 Gattino #3', badge: '🐱 Micio • Foto 3/4' },
  { type: 'shark', id: 'shark_4', title: 'Pisolo Protettivo 🦈', desc: 'Un abbraccio grande a pinne aperte', tag: '🦈 Squaletto #4', badge: '🦈 Pisolo • Foto 4/4' },
  { type: 'kitty', id: 'kitty_4', title: 'Micio e Amico 🐱', desc: "Coccole senza fine e fusa d'amore", tag: '🐱 Gattino #4', badge: '🐱 Micio • Foto 4/4' }
];

function getAlternatingPhoto(index) {
  const safeIdx = ((index % ALTERNATING_MASCOT_PRESETS.length) + ALTERNATING_MASCOT_PRESETS.length) % ALTERNATING_MASCOT_PRESETS.length;
  const preset = ALTERNATING_MASCOT_PRESETS[safeIdx];
  const isShark = (preset.type === 'shark');
  const customPhoto = isShark
    ? localStorage.getItem('custom_mascot_shark_photo')
    : localStorage.getItem('custom_mascot_kitty_photo');
  const photoSrc = EMBEDDED_ASSETS[preset.id] || (isShark ? getActiveSharkPhoto() : getActiveKittyPhoto());
  return {
    type: preset.type,
    id: preset.id,
    src: photoSrc,
    title: preset.title,
    desc: preset.desc,
    tag: preset.tag,
    badge: isShark ? `🦈 Pisolo • ${preset.title}` : `🐱 Micio • ${preset.title}`,
    mascotName: isShark ? "Pisolo 🦈" : "Micio 🐱"
  };
}

function getActiveSharkId() {
  const savedId = localStorage.getItem('mascot_shark_selected_id');
  if (savedId) return savedId;
  const saved = localStorage.getItem('mascot_shark_selected_photo');
  if (!saved || saved.includes('shark_1') || saved.includes('mascot_shark')) return 'shark_1';
  if (saved.includes('shark_2')) return 'shark_2';
  if (saved.includes('shark_3')) return 'shark_3';
  if (saved.includes('shark_4')) return 'shark_4';
  if (saved.startsWith('data:image')) return 'custom';
  return 'shark_1';
}

function getActiveKittyId() {
  const savedId = localStorage.getItem('mascot_kitty_selected_id');
  if (savedId) return savedId;
  const saved = localStorage.getItem('mascot_kitty_selected_photo');
  if (!saved || saved.includes('kitty_1') || saved.includes('mascot_kitty')) return 'kitty_1';
  if (saved.includes('kitty_2')) return 'kitty_2';
  if (saved.includes('kitty_3')) return 'kitty_3';
  if (saved.includes('kitty_4') || saved.includes('cuddle') || saved.includes('duo')) return 'kitty_4';
  if (saved.startsWith('data:image')) return 'custom';
  return 'kitty_1';
}

function getActiveSharkPhoto() {
  const customPhoto = localStorage.getItem('custom_mascot_shark_photo');
  const activeId = getActiveSharkId();
  if (activeId === 'custom' && customPhoto) return customPhoto;
  if (EMBEDDED_ASSETS[activeId]) return EMBEDDED_ASSETS[activeId];
  const saved = localStorage.getItem('mascot_shark_selected_photo');
  return resolveAsset(saved, EMBEDDED_ASSETS.shark_1);
}

function getActiveSharkTitle() {
  const activeId = getActiveSharkId();
  const preset = SHARK_PHOTOS_PRESETS.find(p => p.id === activeId);
  if (preset) return preset.title + ' 🦈';
  return localStorage.getItem('mascot_shark_selected_title') || "Pisolo Classico 🦈";
}

function getActiveKittyPhoto() {
  const customPhoto = localStorage.getItem('custom_mascot_kitty_photo');
  const activeId = getActiveKittyId();
  if (activeId === 'custom' && customPhoto) return customPhoto;
  if (EMBEDDED_ASSETS[activeId]) return EMBEDDED_ASSETS[activeId];
  const saved = localStorage.getItem('mascot_kitty_selected_photo');
  return resolveAsset(saved, EMBEDDED_ASSETS.kitty_1);
}

function getActiveKittyTitle() {
  const activeId = getActiveKittyId();
  const preset = KITTY_PHOTOS_PRESETS.find(p => p.id === activeId);
  if (preset) return preset.title + ' 🐱';
  return localStorage.getItem('mascot_kitty_selected_title') || "Micio Dolce 🐱";
}

// 1. ALTERNANZA MASCOTTE PISOLO (Squaletto) & MICIO (Gattino)
let currentMascot = 'shark';
const MASCOT_QUOTES = {
  shark: [
    "Prenditi tutto il tempo che ti serve: nuotiamo piano insieme.",
    "Nessuna tempesta dura per sempre. Ti proteggo io.",
    "Sei il mio piccolo tesoro prezioso in mezzo al mare.",
    "Appoggia la testa e respira: sono sempre qui a farti da scudo.",
    "Anche nei giorni più difficili, la tua forza mi rende fiero di te."
  ],
  kitty: [
    "Ti mando una fusa morbidissima per sciogliere la stanchezza.",
    "Meriti solo coccole calde, riposo e tanta tenerezza.",
    "Fai piano: oggi non devi dimostrare niente al mondo.",
    "Chiudi gli occhi e immagina che ti accarezzi il viso.",
    "Sei la persona più dolce che esista. Non dimenticarlo mai."
  ]
};

// ============================================================
// GESTIONE AUTENTICAZIONE E ACCESSO CON PASSWORD
// ============================================================
// NOTA IMPORTANTE: la password non è più salvata in chiaro nel codice.
// Viene confrontata tramite hash SHA-256, quindi chi apre il "Visualizza
// sorgente" o la console del browser non la trova più leggibile.
// Attenzione però: questa resta comunque una protezione "leggera", adatta
// a scoraggiare occhi indiscreti casuali su un'app personale, non una
// vera autenticazione sicura (che richiederebbe un controllo lato server,
// impossibile per una pagina statica come questa).
const APP_PASSWORD_HASH = "1bcaf6f4231908e5ef1a7495110d25279ea443479894e65d366646a9c6d49566";

async function sha256Hex(text) {
  const encoded = new TextEncoder().encode(text);
  const digest = await crypto.subtle.digest('SHA-256', encoded);
  return Array.from(new Uint8Array(digest))
    .map(b => b.toString(16).padStart(2, '0'))
    .join('');
}

async function handleLoginSubmit(e) {
  if (e) e.preventDefault();
  const input = document.getElementById('login-password-input');
  const errEl = document.getElementById('login-error-msg');
  const val = input ? input.value.trim() : '';
  const inputHash = await sha256Hex(val.toUpperCase());

  if (inputHash === APP_PASSWORD_HASH) {
    localStorage.setItem('pisolo_pwa_auth', 'true');
    document.body.classList.remove('locked');
    if (errEl) errEl.style.display = 'none';
    applyAccessTranquilBackground();
    showToast("Accesso eseguito! Benvenuta nel tuo rifugio sicuro ❤️");
    // Mostra il pop-up di accoglienza all'entrata
    const welcomeModal = document.getElementById('welcome-modal');
    if (welcomeModal) {
      welcomeModal.style.display = 'flex';
      welcomeModal.style.opacity = '1';
    }
  } else {
    if (errEl) errEl.style.display = 'block';
    if (input) {
      input.classList.add('error-shake');
      setTimeout(() => input.classList.remove('error-shake'), 450);
      input.focus();
    }
  }
}

function togglePasswordVisibility() {
  const input = document.getElementById('login-password-input');
  const btn = document.getElementById('login-eye-btn');
  if (!input) return;
  if (input.type === 'password') {
    input.type = 'text';
    if (btn) btn.textContent = '🙈';
  } else {
    input.type = 'password';
    if (btn) btn.textContent = '👁️';
  }
}

function logoutApp() {
  localStorage.removeItem('pisolo_pwa_auth');
  location.reload();
}

function selectMascot(type) {
  currentMascot = type;
  localStorage.setItem('active_mascot_pref', type);
  updateMascotVisual();
  showToast(type === 'shark' ? "Pisolo lo squaletto ti fa compagnia 🦈" : "Micio il gattino ti fa le fusa 🐱");
}

function setActiveMascotType(type) {
  selectMascot(type);
}

function toggleActiveMascot() {
  selectMascot(currentMascot === 'shark' ? 'kitty' : 'shark');
}

function nextDelicateQuote() {
  displayCurrentMascotQuote();
  showToast("Un pensiero dolce da " + (currentMascot === 'shark' ? "Pisolo 🦈" : "Micio 🐱"));
}

function updateMascotVisual() {
  const imgEl = document.getElementById('mascot-avatar-img');
  const badgeName = document.getElementById('mascot-badge-name');
  const pillShark = document.getElementById('pill-mascot-shark');
  const pillKitty = document.getElementById('pill-mascot-kitty');
  const speechIcon = document.getElementById('speech-mascot-icon');
  const partnerIcon = document.getElementById('breath-mascot-partner');
  const welcomeShark = document.getElementById('welcome-shark-img');
  const welcomeKitty = document.getElementById('welcome-kitty-img');

  const currentSharkSrc = getActiveSharkPhoto();
  const currentKittySrc = getActiveKittyPhoto();

  if (welcomeShark) welcomeShark.src = currentSharkSrc;
  if (welcomeKitty) welcomeKitty.src = currentKittySrc;

  if (currentMascot === 'shark') {
    if (imgEl) imgEl.src = currentSharkSrc;
    if (badgeName) badgeName.textContent = 'Pisolo 🦈';
    if (pillShark) pillShark.classList.add('active');
    if (pillKitty) pillKitty.classList.remove('active');
    if (speechIcon) speechIcon.textContent = '🦈';
    if (partnerIcon) partnerIcon.textContent = '🦈';
  } else {
    if (imgEl) imgEl.src = currentKittySrc;
    if (badgeName) badgeName.textContent = 'Micio 🐱';
    if (pillKitty) pillKitty.classList.add('active');
    if (pillShark) pillShark.classList.remove('active');
    if (speechIcon) speechIcon.textContent = '🐱';
    if (partnerIcon) partnerIcon.textContent = '🐱';
  }
  displayCurrentMascotQuote();
}

function displayCurrentMascotQuote() {
  const quotes = MASCOT_QUOTES[currentMascot];
  const randQuote = quotes[Math.floor(Math.random() * quotes.length)];
  const textEl = document.getElementById('mascot-quote-display') || document.getElementById('mascot-speech-text');
  if (textEl) {
    textEl.textContent = `“${randQuote}”`;
  }
}

// ============================================================
// 2. POOL COMPLETO DEI MESSAGGI DI SUPPORTO (32 VARIANTI CALDE E SINCERE)
// Raggruppate per situazione: tristezza, ansia/stress, fastidio fisico, vicinanza generale
// ============================================================
const SUPPORT_MESSAGES = [
  {
    id: 1,
    categoria: "tristezza",
    tag: "🫂 Vicinanza sincera",
    title: "Non sei mai sola",
    titolo: "Non sei mai sola",
    testo: "So che ci sono momenti in cui tutto sembra pesare un po' di più e trovare le energie è faticoso. Ma tu non sei sola: io sono qui, silenziosamente al tuo fianco a sostenerti.",
    autore: "— Chi ti vuole un bene infinito",
    firma: "— Chi ti vuole un bene infinito"
  },
  {
    id: 2,
    categoria: "ansia",
    tag: "🌸 Dolcezza pura",
    title: "Prenditi il tuo tempo",
    titolo: "Prenditi il tuo tempo",
    testo: "Non devi essere per forza invincibile ogni singolo giorno. Puoi sentirti fragile, puoi respirare e concederti di staccare. Sei amatissima proprio così come sei.",
    autore: "— Il tuo porto sicuro",
    firma: "— Il tuo porto sicuro"
  },
  {
    id: 3,
    categoria: "tristezza",
    tag: "🛡️ Protezione e calore",
    title: "Il tuo rifugio sicuro",
    titolo: "Il tuo rifugio sicuro",
    testo: "Qualsiasi pensiero ti stia togliendo serenità in questo momento, lascialo andare per qualche minuto. Chiudi gli occhi: ci sono le mie braccia a fare da scudo a tutto.",
    autore: "— Per sempre con te",
    firma: "— Per sempre con te"
  },
  {
    id: 4,
    categoria: "generale",
    tag: "✨ Quanto vali",
    title: "La tua luce rara",
    titolo: "La tua luce rara",
    testo: "Sei la persona più speciale e generosa che conosca. Anche quando la giornata è scura, ricordati che porti una luce immensa nella mia vita.",
    autore: "— Il tuo fan numero uno",
    firma: "— Il tuo fan numero uno"
  },
  {
    id: 5,
    categoria: "ansia",
    tag: "🌊 Calma e quiete",
    title: "Un respiro alla volta",
    titolo: "Un respiro alla volta",
    testo: "Non dobbiamo scalare tutte le montagne oggi. Basta un piccolo respiro, un sorso d'acqua, una carezza nel pensiero. Piano piano tutto si sistema con Pisolo e Micio.",
    autore: "— Pisolo 🦈 & Micio 🐱",
    firma: "— Pisolo 🦈 & Micio 🐱"
  },
  {
    id: 6,
    categoria: "generale",
    tag: "☀️ Raggio di sole",
    title: "La parte più bella di me",
    titolo: "La parte più bella di me",
    testo: "Sei il primo pensiero bello della mattina e il rifugio della sera. Quando sorridi, il mondo intorno torna a brillare.",
    autore: "— Sempre al tuo fianco",
    firma: "— Sempre al tuo fianco"
  },
  {
    id: 7,
    categoria: "fisico",
    tag: "🧸 Coccole infinite",
    title: "Niente da dimostrare",
    titolo: "Niente da dimostrare",
    testo: "Oggi non devi dimostrare nulla a nessuno. Mettiti comoda, rannicchiati e ascolta il tuo corpo: meriti solo riposo, affetto e tanta tenerezza.",
    autore: "— Con tutto il cuore",
    firma: "— Con tutto il cuore"
  },
  {
    id: 8,
    categoria: "generale",
    tag: "💫 Un patto tra noi",
    title: "Io non vado da nessuna parte",
    titolo: "Io non vado da nessuna parte",
    testo: "Nessun malumore, nessuna stanchezza e nessuna distanza potrà mai cambiare quello che provo per te. Per me sei casa.",
    autore: "— Il tuo ragazzo ❤️",
    firma: "— Il tuo ragazzo ❤️"
  },
  {
    id: 9,
    categoria: "ansia",
    tag: "🍃 Lascia scorrere",
    title: "I pensieri sono nuvole",
    titolo: "I pensieri sono nuvole",
    testo: "L'ansia mente: ti fa sembrare ogni cosa un'urgenza insormontabile. Ma tu siediti comoda, lascia che il vento soffi via il rumore. Io sono qui con te.",
    autore: "— Chi ti tiene la mano",
    firma: "— Chi ti tiene la mano"
  },
  {
    id: 10,
    categoria: "fisico",
    tag: "☕ Calore rilassante",
    title: "Un momento tutto tuo",
    titolo: "Un momento tutto tuo",
    testo: "Se il corpo è indolenzito o stanco, concediti una tazza calda e una pausa senza sensi di colpa. La salute e la tua serenità vengono prima di qualunque dovere.",
    autore: "— Il tuo alleato speciale",
    firma: "— Il tuo alleato speciale"
  },
  {
    id: 11,
    categoria: "tristezza",
    tag: "🌧️ Anche la pioggia passa",
    title: "Puoi piangere se serve",
    titolo: "Puoi piangere se serve",
    testo: "Le lacrime non sono debolezza: lavano via la polvere dalle giornate pesanti. Piangi pure se ne senti il bisogno, io sarò qui ad asciugarti il viso con dolcezza.",
    autore: "— Il tuo abbraccio sicuro",
    firma: "— Il tuo abbraccio sicuro"
  },
  {
    id: 12,
    categoria: "generale",
    tag: "⭐ Il mio orgoglio",
    title: "Sei fortissima, ricordalo",
    titolo: "Sei fortissima, ricordalo",
    testo: "Hai superato ogni singolo giorno difficile che hai incontrato nella tua vita finora. Ce la farai anche questa volta, e io sarò lì a festeggiare ogni tuo piccolo passo.",
    autore: "— Chi crede in te al 100%",
    firma: "— Chi crede in te al 100%"
  },
  {
    id: 13,
    categoria: "ansia",
    tag: "⚓ Ancora sicura",
    title: "Guarda qui: siamo al sicuro",
    titolo: "Guarda qui: siamo al sicuro",
    testo: "Quando il cuore batte troppo in fretta, appoggia la mano sul petto. Fai cinque respiri lenti insieme a me. Niente di brutto può raggiungerti in questo istante.",
    autore: "— Pisolo 🦈 il tuo custode",
    firma: "— Pisolo 🦈 il tuo custode"
  },
  {
    id: 14,
    categoria: "fisico",
    tag: "🛋️ Riposo totale",
    title: "Fermati e rilassati",
    titolo: "Fermati e rilassati",
    testo: "Micio ti invita a fare come lui: trova la posizione più morbida del letto, sciogli le spalle e chiudi gli occhi. Il mondo là fuori può benissimo aspettare.",
    autore: "— Micio 🐱 tuo complice",
    firma: "— Micio 🐱 tuo complice"
  },
  {
    id: 15,
    categoria: "tristezza",
    tag: "🩹 Ti curo io",
    title: "Una carezza sul cuore",
    titolo: "Una carezza sul cuore",
    testo: "Se qualcuno o qualcosa ti ha ferita oggi, lascia che il mio affetto rimetta a posto i pezzi. Per me sei il tesoro più grande del mondo.",
    autore: "— Il tuo rifugio permanente",
    firma: "— Il tuo rifugio permanente"
  },
  {
    id: 16,
    categoria: "generale",
    tag: "💌 Amore senza fine",
    title: "Mi manchi sempre",
    titolo: "Mi manchi sempre",
    testo: "Non c'è ora del giorno in cui io non pensi a quanto sono fortunato ad averti accanto. Ti stringo fortissimo anche attraverso lo schermo.",
    autore: "— Il tuo ragazzo ❤️",
    firma: "— Il tuo ragazzo ❤️"
  },
  {
    id: 17,
    categoria: "ansia",
    tag: "🌿 Pace interiore",
    title: "Solo il presente",
    titolo: "Solo il presente",
    testo: "Non pensare a domani, non pensare alla prossima settimana. Pensa solo a questo minuto. In questo minuto stai bene, sei al calduccio e sei amata.",
    autore: "— Il tuo scudo contro l'ansia",
    firma: "— Il tuo scudo contro l'ansia"
  },
  {
    id: 18,
    categoria: "fisico",
    tag: "🫖 Tisana e coperta",
    title: "Coccola per il tuo corpo",
    titolo: "Coccola per il tuo corpo",
    testo: "Metti calzini morbidi, tira su il piumone fino al mento e lascia che il tepore sciolga ogni tensione muscolare. Fai riposare ogni fibra di te.",
    autore: "— La tua farmacia di coccole",
    firma: "— La tua farmacia di coccole"
  },
  {
    id: 19,
    categoria: "tristezza",
    tag: "💖 Senza condizioni",
    title: "Ti amo per come sei",
    titolo: "Ti amo per come sei",
    testo: "Ti amo nei giorni splendenti quando ridi a crepapelle, e ti amo esattamente allo stesso modo nei giorni grigi in cui vuoi solo startene sotto le coperte.",
    autore: "— Chi non ti lascerà mai",
    firma: "— Chi non ti lascerà mai"
  },
  {
    id: 20,
    categoria: "generale",
    tag: "🌙 Buonanotte al cuore",
    title: "Lascia andare la giornata",
    titolo: "Lascia andare la giornata",
    testo: "Qualunque cosa sia successa oggi, ormai è passata. Sei qui, sei al sicuro e domani sarà una pagina tutta nuova da scrivere insieme con calma.",
    autore: "— I tuoi sogni custoditi",
    firma: "— I tuoi sogni custoditi"
  },
  {
    id: 21,
    categoria: "tristezza",
    tag: "🛡️ Nessuna paura",
    title: "Ci penso io a farti da scudo",
    titolo: "Ci penso io a farti da scudo",
    testo: "Se le parole o le persone fuori ti hanno ferita o stancata, chiudi quella porta. Qui dentro ci siamo solo noi due, e nessuno può farti del male.",
    autore: "— Il tuo cavaliere guardiano",
    firma: "— Il tuo cavaliere guardiano"
  },
  {
    id: 22,
    categoria: "ansia",
    tag: "🌊 Mare calmo",
    title: "Nuota a riva",
    titolo: "Nuota a riva",
    testo: "Quando ti sembra di affogare nelle cose da fare, ricordati che Pisolo nuota sotto di te per sostenerti. Non puoi affondare finché ci sono io.",
    autore: "— Pisolo 🦈 il tuo custode",
    firma: "— Pisolo 🦈 il tuo custode"
  },
  {
    id: 23,
    categoria: "generale",
    tag: "🌸 Tenerezza infinita",
    title: "Una carezza per il tuo viso",
    titolo: "Una carezza per il tuo viso",
    testo: "Vorrei essere lì a sfiorarti le guance, a baciarti la fronte e a sussurrarti all'orecchio quanto sei preziosa per me. Immagina il mio bacio adesso.",
    autore: "— Il tuo amore grande",
    firma: "— Il tuo amore grande"
  },
  {
    id: 24,
    categoria: "fisico",
    tag: "🩹 Cura e pazienza",
    title: "Rispetta i tuoi ritmi",
    titolo: "Rispetta i tuoi ritmi",
    testo: "I momenti no non si risolvono forzandosi a sorridere a comando. Datti il tempo di guarire, di riposare e di ritrovare il tuo equilibrio un passetto alla volta.",
    autore: "— Chi ti aspetta sempre",
    firma: "— Chi ti aspetta sempre"
  },
  {
    id: 25,
    categoria: "ansia",
    tag: "🎈 Leggera come una piuma",
    title: "Meno severa con te stessa",
    titolo: "Meno severa con te stessa",
    testo: "Sei sempre così attenta a tutti gli altri. Per una volta, sii dolce e comprensiva con te stessa come lo saresti con la persona a cui vuoi più bene.",
    autore: "— Il tuo specchio sincero",
    firma: "— Il tuo specchio sincero"
  },
  {
    id: 26,
    categoria: "fisico",
    tag: "🍫 Dolcezza riparatrice",
    title: "Un pizzico di coccole",
    titolo: "Un pizzico di coccole",
    testo: "A volte basta un pezzetto di cioccolato, una doccia tiepida rilassante o una canzone lenta per far ripartire la giornata con un sapore diverso.",
    autore: "— Chi vuole vederti serena",
    firma: "— Chi vuole vederti serena"
  },
  {
    id: 27,
    categoria: "tristezza",
    tag: "🫂 Braccia aperte",
    title: "Stringimi forte",
    titolo: "Stringimi forte",
    testo: "Non dire nulla se non ti va. Appoggia semplicemente la testa sul mio petto, ascolta il battito regolare del mio cuore e lascia che la tempesta passi.",
    autore: "— Il tuo rifugio permanente",
    firma: "— Il tuo rifugio permanente"
  },
  {
    id: 28,
    categoria: "fisico",
    tag: "🐾 Orme morbide",
    title: "Fusa terapeutiche",
    titolo: "Fusa terapeutiche",
    testo: "Micio si raggomitola accanto a te, fa le fusa al ritmo del tuo respiro e ti ricorda che i gatti sanno sempre quando è il momento di non fare proprio nulla.",
    autore: "— Micio 🐱 tuo complice",
    firma: "— Micio 🐱 tuo complice"
  },
  {
    id: 29,
    categoria: "generale",
    tag: "✨ Sei unica",
    title: "Il mio pezzo di cielo",
    titolo: "Il mio pezzo di cielo",
    testo: "Non cambiare mai la tua sensibilità: è la cosa più preziosa e rara che esista. Anche quando il mondo sembra ruvido, la tua dolcezza vincerà sempre.",
    autore: "— Il tuo ragazzo, per sempre ❤️",
    firma: "— Il tuo ragazzo, per sempre ❤️"
  },
  {
    id: 30,
    categoria: "ansia",
    tag: "🌈 Serenità ritrovata",
    title: "La forza dentro di te",
    titolo: "La forza dentro di te",
    testo: "Dentro di te c'è una calma profonda che nessuna fretta può cancellare. Chiudi gli occhi, fai spazio al silenzio e ritrova la tua pace.",
    autore: "— Chi crede sempre in te",
    firma: "— Chi crede sempre in te"
  },
  {
    id: 31,
    categoria: "tristezza",
    tag: "🕯️ Piccolo faro",
    title: "Io non ti mollo",
    titolo: "Io non ti mollo",
    testo: "Qualsiasi tristezza ti attraversi l'anima, ricordati che io tengo acceso il faro per farti ritrovare sempre la strada di casa. Ti amo immensamente.",
    autore: "— Il tuo faro nella notte",
    firma: "— Il tuo faro nella notte"
  },
  {
    id: 32,
    categoria: "generale",
    tag: "💖 Per sempre noi",
    title: "Un abbraccio eterno",
    titolo: "Un abbraccio eterno",
    testo: "Pisolo ti protegge con le sue pinne grandi, Micio ti scalda con le sue fusa e io ti stringo con tutto l'amore che ho nel cuore. Sei meravigliosa.",
    autore: "— Pisolo 🦈, Micio 🐱 & Il tuo Amore ❤️",
    firma: "— Pisolo 🦈, Micio 🐱 & Il tuo Amore ❤️"
  }
];

// Alias per compatibilità con eventuale codice preesistente
const FEATURED_MESSAGES_POOL = SUPPORT_MESSAGES;

// SHUFFLE BAG ALGORITHM
// - Mescola l'intero pool all'avvio
// - Estrae i messaggi uno alla volta in ordine dal mazzo mescolato
// - Supporta estrazione globale o filtrata per categoria (tristezza, ansia, fisico, generale)
// - Quando il mazzo finisce, lo rimescola daccapo
// - Evita che lo stesso messaggio esca due volte di fila a cavallo del rimescolamento
class SupportMessageShuffleBag {
  constructor(items) {
    this.items = [...items];
    this.categoryBags = {};
    this.deck = [];
    this.currentIndex = 0;
    this.lastDrawnId = null;
    this.reshuffle();
  }

  reshuffle() {
    const newDeck = [...this.items];
    for (let i = newDeck.length - 1; i > 0; i--) {
      const j = Math.floor(Math.random() * (i + 1));
      [newDeck[i], newDeck[j]] = [newDeck[j], newDeck[i]];
    }
    if (this.lastDrawnId !== null && newDeck.length > 1 && newDeck[0].id === this.lastDrawnId) {
      const swapIdx = 1 + Math.floor(Math.random() * (newDeck.length - 1));
      [newDeck[0], newDeck[swapIdx]] = [newDeck[swapIdx], newDeck[0]];
    }
    this.deck = newDeck;
    this.currentIndex = 0;
  }

  drawNext(category = null) {
    if (category && category !== 'all') {
      if (!this.categoryBags[category]) {
        const filtered = this.items.filter(m => m.categoria === category);
        this.categoryBags[category] = new SupportMessageShuffleBag(filtered.length ? filtered : this.items);
      }
      return this.categoryBags[category].drawNext();
    }

    if (this.currentIndex >= this.deck.length) {
      this.reshuffle();
    }
    const message = this.deck[this.currentIndex];
    this.currentIndex++;
    this.lastDrawnId = message.id;
    return message;
  }
}

const hugMessageBag = new SupportMessageShuffleBag(SUPPORT_MESSAGES);
let activeHugCategory = 'all';

function setHugCategoryFilter(cat, btnEl) {
  activeHugCategory = cat;
  const pills = document.querySelectorAll('#modal-hug-cat-pills .filter-pill');
  pills.forEach(p => p.classList.remove('active'));
  if (btnEl) btnEl.classList.add('active');
  cycleNextHugMessage();
}

// 20 FOTO D'ABBRACCIO DI PISOLO & MICIO A ROTAZIONE CONTINUA
const HUG_IMAGES_POOL = [
  'assets/hugs/hug_storybook.jpg',
  'assets/hugs/hug_bubble.jpg',
  'assets/hugs/hug_umbrella.jpg',
  'assets/hugs/hug_lanterns.jpg',
  'assets/hugs/hug_duo_cuddle.jpg',
  'assets/hugs/hug_together_warmth.jpg',
  'assets/hugs/hug_starry_1789423893325.jpg',
  'assets/hugs/hug_cloud_1789423902447.jpg',
  'assets/hugs/hug_blanket_1789423912013.jpg',
  'assets/hugs/hug_garden_1789423919687.jpg',
  'assets/hugs/hug_tent_1789423968994.jpg',
  'assets/hugs/hug_fireplace_1789423978606.jpg',
  'assets/hugs/hug_cherryblossom_1789423987275.jpg',
  'assets/hugs/hug_pillow_1789423997048.jpg',
  'assets/hugs/hug_beach_sunset_peace_1789076615367.jpg',
  'assets/hugs/hug_gentle_garden_sun_1789076648080.jpg',
  'assets/hugs/hug_pisolo_micio_cuddle_1789076591266.jpg',
  'assets/hugs/hug_pisolo_protect_rain_1789076604075.jpg',
  'assets/hugs/hug_starry_night_dreams_1789076626440.jpg',
  'assets/hugs/hug_warm_blanket_cocoa_1789076637148.jpg'
];

let hugImageOrder = [];
let hugImageCursor = 0;

function getNextHugImage() {
  if (hugImageOrder.length === 0 || hugImageCursor >= hugImageOrder.length) {
    const indices = Array.from({ length: HUG_IMAGES_POOL.length }, (_, i) => i);
    for (let i = indices.length - 1; i > 0; i--) {
      const j = Math.floor(Math.random() * (i + 1));
      [indices[i], indices[j]] = [indices[j], indices[i]];
    }
    if (hugImageOrder.length > 0 && indices[0] === hugImageOrder[hugImageOrder.length - 1] && indices.length > 1) {
      const swap = 1 + Math.floor(Math.random() * (indices.length - 1));
      [indices[0], indices[swap]] = [indices[swap], indices[0]];
    }
    hugImageOrder = indices;
    hugImageCursor = 0;
  }
  const imgPath = HUG_IMAGES_POOL[hugImageOrder[hugImageCursor]];
  hugImageCursor++;
  return imgPath;
}

let currentFeaturedPhotoIndex = 0;

// ALTERNANZA FOTO SCHEDA MESSAGGIO DEL GIORNO
function cycleFeaturedPhotoOnly() {
  currentFeaturedPhotoIndex++;
  const photoObj = getAlternatingPhoto(currentFeaturedPhotoIndex);
  const photoImg = document.getElementById('featured-photo-img');
  const modalPhoto = document.getElementById('modal-photo-el');

  if (photoImg) {
    photoImg.style.opacity = '0.3';
    setTimeout(() => {
      photoImg.src = photoObj.src;
      photoImg.style.opacity = '1';
    }, 150);
  }
  if (modalPhoto) modalPhoto.src = photoObj.src;
  showToast(`Foto: ${photoObj.title} (${photoObj.mascotName})`);
}

function displayMessageOnCard(m) {
  if (!m) return;
  const quoteEl = document.getElementById('featured-quote-text');
  const sigEl = document.getElementById('featured-signature-text');
  const photoImg = document.getElementById('featured-photo-img');

  if (quoteEl) quoteEl.textContent = `“${m.testo}”`;
  if (sigEl) sigEl.textContent = m.firma || m.autore;

  if (photoImg) {
    const nextImg = m.image || getNextHugImage();
    photoImg.src = nextImg;
  }
}

function displayMessageInModal(m) {
  if (!m) return;
  const bodyEl = document.getElementById('modal-body-el');
  const sigEl = document.getElementById('modal-sig-el');
  const titleEl = document.getElementById('modal-title-el');
  const tagEl = document.getElementById('modal-tag-el');
  const photoEl = document.getElementById('modal-photo-el');

  if (bodyEl) bodyEl.textContent = m.testo;
  if (sigEl) sigEl.textContent = m.firma || m.autore;
  if (titleEl) titleEl.textContent = m.titolo || m.title || "Ti stringo forte forte";
  if (tagEl) tagEl.textContent = m.tag || "Abbraccio sincero";

  if (photoEl) {
    const nextImg = m.image || getNextHugImage();
    photoEl.src = nextImg;
  }
}

function shuffleFeaturedMessage() {
  const m = { ...hugMessageBag.drawNext(activeHugCategory) };
  m.image = getNextHugImage();
  displayMessageOnCard(m);
  displayMessageInModal(m);
  showToast("Nuovo pensiero e foto speciale per te 💌");
}

// CAMBIO FOTO PRINCIPALE (HERO)
function triggerPhotoUploadHero() {
  document.getElementById('upload-hero-input').click();
}

function handleHeroUpload(e) {
  const file = e.target.files[0];
  if (!file) return;
  compressImageFile(file, (b64) => {
    try {
      localStorage.setItem('featured_hero_photo', b64);
      document.getElementById('featured-photo-img').src = b64;
      document.getElementById('modal-photo-el').src = b64;
      showToast("Foto copertina personalizzata aggiornata! 📸");
    } catch (err) {
      showToast("Impossibile salvare la foto, prova con una più leggera.");
    }
  });
  e.target.value = '';
}

// ============================================================
// 3. I PENSIERI SCRITTI PER TE (CAROUSEL CON FOTO SQUALETTI E GATTINI ALTERNATI)
// ============================================================
function compressImageFile(file, callback) {
  const reader = new FileReader();
  reader.onload = (e) => {
    const img = new Image();
    img.onload = () => {
      const canvas = document.getElementById('compress-canvas');
      const ctx = canvas.getContext('2d');
      const MAX_WIDTH = 1200;
      const MAX_HEIGHT = 1200;
      let width = img.width;
      let height = img.height;
      if (width > height) {
        if (width > MAX_WIDTH) {
          height *= MAX_WIDTH / width;
          width = MAX_WIDTH;
        }
      } else {
        if (height > MAX_HEIGHT) {
          width *= MAX_HEIGHT / height;
          height = MAX_HEIGHT;
        }
      }
      canvas.width = width;
      canvas.height = height;
      ctx.drawImage(img, 0, 0, width, height);
      const dataUrl = canvas.toDataURL('image/jpeg', 0.82);
      callback(dataUrl);
    };
    img.src = e.target.result;
  };
  reader.readAsDataURL(file);
}

// 5. MODALE ABBRACCIO VIRTUALE
let isAiHugGenerating = false;
let recentAiHugs = [];

async function fetchAiHug(category) {
  const userKey = getUserApiKey();
  if (!userKey || typeof callGeminiDirect !== 'function') return null;

  const mood = category ? category + " profondo e dolce" : 'conforto, rassicurazione e dolcezza infinita';
  const avoid = recentAiHugs.length > 0 ? " Diversa da queste: " + recentAiHugs.slice(-3).join(" | ") : "";
  const prompt = `Sei Pisolo 🦈 (squaletto) e Micio 🐱 (gattino). Dai un abbraccio virtuale breve, rassicurante e unico (tema: ${mood}).${avoid} Scrivi solo la frase dell'abbraccio in italiano, senza asterischi (max 35 parole).`;

  let generatedText = '';
  try {
    const directRes = await callGeminiDirect(userKey, prompt, 120);
    if (directRes && directRes.success && directRes.text) {
      generatedText = typeof cleanGeneratedText === 'function' ? cleanGeneratedText(directRes.text) : directRes.text;
    }
  } catch (e) {
    return null;
  }

  if (generatedText) {
    recentAiHugs.push(generatedText);
    return {
      testo: generatedText,
      autore: "Pisolo 🦈 & Micio 🐱 (con IA ✨)",
      firma: "Pisolo 🦈 & Micio 🐱 (con IA ✨)",
      titolo: "Un abbraccio speciale per te",
      tag: "🫂 Abbraccio IA",
      image: getNextHugImage()
    };
  }
  return null;
}

async function openHugModal(category = null) {
  if (category) activeHugCategory = category;

  const hugImg = getNextHugImage();
  const modalPhoto = document.getElementById('modal-photo-el');
  if (modalPhoto) modalPhoto.src = hugImg;

  const modalBackdrop = document.getElementById('modal-hug-backdrop');
  if (modalBackdrop) modalBackdrop.style.display = 'flex';

  const modalBodyEl = document.getElementById('modal-body-el');
  const userKey = getUserApiKey();

  let msg = null;
  if (userKey && !isAiHugGenerating) {
    if (modalBodyEl) modalBodyEl.innerHTML = '<span style="font-size:14px; color:#7E22CE; display:flex; align-items:center; justify-content:center; gap:8px;"><span style="animation:spinSmooth 1.5s infinite linear;">✨</span> Pisolo e Micio ti stanno preparando una carezza...</span>';
    isAiHugGenerating = true;
    const aiMsg = await fetchAiHug(activeHugCategory);
    isAiHugGenerating = false;
    if (aiMsg) {
      msg = aiMsg;
    }
  }

  if (!msg) {
    msg = { ...hugMessageBag.drawNext(activeHugCategory) };
    msg.image = hugImg;
  }

  displayMessageInModal(msg);
  displayMessageOnCard(msg);
}

async function cycleNextHugMessage() {
  const hugImg = getNextHugImage();
  const modalPhoto = document.getElementById('modal-photo-el');
  if (modalPhoto) {
    modalPhoto.style.opacity = '0.3';
    setTimeout(() => {
      modalPhoto.src = hugImg;
      modalPhoto.style.opacity = '1';
    }, 150);
  }

  const modalBodyEl = document.getElementById('modal-body-el');
  const userKey = getUserApiKey();

  let msg = null;
  if (userKey && !isAiHugGenerating) {
    if (modalBodyEl) modalBodyEl.innerHTML = '<span style="font-size:14px; color:#7E22CE; display:flex; align-items:center; justify-content:center; gap:8px;"><span style="animation:spinSmooth 1.5s infinite linear;">✨</span> Stiamo preparando un nuovo abbraccio per te...</span>';
    isAiHugGenerating = true;
    const aiMsg = await fetchAiHug(activeHugCategory);
    isAiHugGenerating = false;
    if (aiMsg) {
      msg = aiMsg;
    }
  }

  if (!msg) {
    msg = { ...hugMessageBag.drawNext(activeHugCategory) };
    msg.image = hugImg;
  }

  displayMessageInModal(msg);
  displayMessageOnCard(msg);
  showToast("Nuovo abbraccio speciale con foto per te ❤️");
}
function closeHugModal(e) {
  if (e && e.target && e.target.id !== 'modal-hug-backdrop' && !e.target.classList.contains('modal-close-trigger')) return;
  document.getElementById('modal-hug-backdrop').style.display = 'none';
}

function thankYouHug() {
  for (let i = 0; i < 14; i++) {
    setTimeout(() => {
      const h = document.createElement('div');
      h.className = 'floating-heart';
      h.textContent = ['💖', '💕', '🌸', '✨', '🧸'][Math.floor(Math.random() * 5)];
      h.style.left = (Math.random() * 80 + 10) + 'vw';
      h.style.top = (Math.random() * 30 + 50) + 'vh';
      document.body.appendChild(h);
      setTimeout(() => h.remove(), 1800);
    }, i * 60);
  }
  document.getElementById('modal-hug-backdrop').style.display = 'none';
  showToast("Ti voglio un bene immenso ❤️");
}

// ============================================================
// 6. SFONDI TRANQUILLI & LEGGERI CON LUOGHI DI PACE REALI (10 VARIANTI)
// ============================================================
const TRANQUIL_BACKGROUNDS = [{ "id": "spiaggia-serena", "name": "Spiaggia Serena", "emoji": "🏖️", "image": "assets/img_14_71f7b0643166.jpg", "place": "Spiaggia calma e mare turchese trasparente", "themeColor": "#EBF5FB" }, { "id": "boschetto-luce", "name": "Boschetto di Luce", "emoji": "🌲", "image": "assets/img_15_938af34ab049.jpg", "place": "Bosco silenzioso con raggi di sole gentili", "themeColor": "#EAF8F1" }, { "id": "lago-quieto", "name": "Lago Quieto", "emoji": "🏞️", "image": "assets/img_16_599c09a8ed3b.jpg", "place": "Lago alpino immobile e trasparente all'aurora", "themeColor": "#F2EEF8" }, { "id": "campi-lavanda", "name": "Campi di Lavanda", "emoji": "🪻", "image": "assets/img_17_016e937ee8b6.jpg", "place": "Distesa fiorita provenzale al tramonto", "themeColor": "#F5EFFB" }, { "id": "giardino-zen", "name": "Giardino Zen", "emoji": "🌸", "image": "assets/img_18_c11de8ca4c11.jpg", "place": "Fiori di ciliegio, specchio d'acqua e quiete", "themeColor": "#FDF0F4" }, { "id": "tramonto-mare", "name": "Tramonto sul Mare", "emoji": "🌅", "image": "assets/img_19_2ad37fb1fbd2.jpg", "place": "Spiaggia dorata soffice e orizzonte sereno", "themeColor": "#FFF3E8" }, { "id": "prato-fiorito", "name": "Prato Fiorito", "emoji": "🌼", "image": "assets/img_20_a1294a4cb05c.jpg", "place": "Dolci colline verdi con margherite campestri", "themeColor": "#F4FAF2" }, { "id": "cascata-segreta", "name": "Cascata Segreta", "emoji": "💧", "image": "assets/img_21_238c3b53e343.jpg", "place": "Ruscello limpido tra sassi levigati e muschio", "themeColor": "#EDF8F7" }, { "id": "colline-aurora", "name": "Colline all'Aurora", "emoji": "🌄", "image": "assets/img_22_24b7db2b4e85.jpg", "place": "Vallata verde con foschia dorata mattutina", "themeColor": "#F7FBF4" }, { "id": "fiume-salici", "name": "Fiume dei Salici", "emoji": "🌿", "image": "assets/img_23_e58588a5599c.jpg", "place": "Ninfee e salici piangenti sull'acqua cheta", "themeColor": "#EEF7F5" }];

let currentBgIndex = 0;

function applyAccessTranquilBackground() {
  const storedIdx = localStorage.getItem('pisolo_bg_index');
  const lastIdx = storedIdx !== null ? parseInt(storedIdx, 10) : -1;
  let nextIdx;
  do {
    nextIdx = Math.floor(Math.random() * TRANQUIL_BACKGROUNDS.length);
  } while (nextIdx === lastIdx && TRANQUIL_BACKGROUNDS.length > 1);

  currentBgIndex = nextIdx;
  renderCurrentBackground();
}

function renderCurrentBackground() {
  const bg = TRANQUIL_BACKGROUNDS[currentBgIndex] || TRANQUIL_BACKGROUNDS[0];
  const bgLayer = document.getElementById('app-ambient-bg');
  const overlayLayer = document.getElementById('app-ambient-overlay');

  // Imposta lo sfondo visivo a tutto schermo con la foto HD del luogo e velatura morbida
  document.body.style.backgroundImage = `linear-gradient(rgba(255, 255, 255, 0.22), rgba(255, 255, 255, 0.35)), url('${bg.image}')`;
  document.body.style.backgroundSize = 'cover';
  document.body.style.backgroundPosition = 'center center';
  document.body.style.backgroundAttachment = 'fixed';
  document.body.style.backgroundRepeat = 'no-repeat';

  if (bgLayer) {
    bgLayer.style.backgroundImage = `url('${bg.image}')`;
    bgLayer.style.opacity = '1';
  }
  if (overlayLayer) {
    overlayLayer.style.opacity = '1';
  }

  // Aggiorna la finestra panoramica "Il tuo Luogo di Pace oggi" in primo piano
  const heroImg = document.getElementById('place-hero-img');
  const heroEmoji = document.getElementById('place-hero-emoji');
  const heroTitle = document.getElementById('place-hero-title');
  const heroDesc = document.getElementById('place-hero-desc');
  if (heroImg) heroImg.style.backgroundImage = `url('${bg.image}')`;
  if (heroEmoji) heroEmoji.textContent = bg.emoji;
  if (heroTitle) heroTitle.textContent = bg.name;
  if (heroDesc) heroDesc.textContent = bg.place;

  const label = document.getElementById('ambient-bg-name-label');
  if (label) {
    label.textContent = `${bg.emoji} ${bg.name}`;
  }
  const metaTheme = document.querySelector('meta[name="theme-color"]');
  if (metaTheme) metaTheme.setAttribute('content', bg.themeColor);

  updateBgPickerActiveState();
}

function cycleNextBackground() {
  currentBgIndex = (currentBgIndex + 1) % TRANQUIL_BACKGROUNDS.length;
  localStorage.setItem('pisolo_bg_index', currentBgIndex.toString());
  renderCurrentBackground();
  const bg = TRANQUIL_BACKGROUNDS[currentBgIndex];
  showToast(`Luogo di pace: ${bg.emoji} ${bg.name} ❤️`);
}

function openBackgroundPickerModal() {
  const modal = document.getElementById('modal-bg-picker-backdrop');
  if (!modal) return;
  renderBackgroundPicker();
  modal.style.display = 'flex';
}

function closeBackgroundPickerModal(e) {
  if (e && e.target !== e.currentTarget && !e.target.classList.contains('modal-close-trigger') && !e.target.classList.contains('modal-btn-primary')) {
    return;
  }
  const modal = document.getElementById('modal-bg-picker-backdrop');
  if (modal) modal.style.display = 'none';
}

function selectBackground(idx) {
  if (idx < 0 || idx >= TRANQUIL_BACKGROUNDS.length) return;
  currentBgIndex = idx;
  localStorage.setItem('pisolo_bg_index', currentBgIndex.toString());
  renderCurrentBackground();
  const bg = TRANQUIL_BACKGROUNDS[currentBgIndex];
  showToast(`Luogo di pace: ${bg.emoji} ${bg.name} ❤️`);
}

function renderBackgroundPicker() {
  const natureGrid = document.getElementById('bg-picker-nature-grid');
  if (!natureGrid) return;

  natureGrid.innerHTML = '';

  TRANQUIL_BACKGROUNDS.forEach((bg, idx) => {
    const item = document.createElement('div');
    item.className = `bg-picker-item ${idx === currentBgIndex ? 'active' : ''}`;
    item.onclick = () => selectBackground(idx);

    item.innerHTML = `
          <div class="bg-picker-thumb" style="background-image: url('${bg.image}');">
            <div class="bg-picker-thumb-overlay"></div>
            <div class="bg-picker-active-badge">In uso ✓</div>
          </div>
          <div class="bg-picker-name">${bg.emoji} ${bg.name}</div>
          <div class="bg-picker-desc">${bg.place}</div>
        `;

    natureGrid.appendChild(item);
  });
}

function updateBgPickerActiveState() {
  const allItems = document.querySelectorAll('.bg-picker-item');
  allItems.forEach((el, i) => {
    if (i === currentBgIndex) {
      el.classList.add('active');
    } else {
      el.classList.remove('active');
    }
  });
}

// ============================================================
// GESTIONE INSTALLAZIONE PWA & MODALITÀ SCHERMO INTERO NATURA
// ============================================================

// Modalità Schermo Intero PWA / Browser
function toggleAppFullscreen() {
  const isStandalone = window.matchMedia('(display-mode: standalone)').matches || window.navigator.standalone === true;
  if (isStandalone) {
    showToast("Sei già in modalità Schermo Intero PWA! 💖");
    return;
  }
  if (!document.fullscreenElement && !document.webkitFullscreenElement) {
    if (document.documentElement.requestFullscreen) {
      document.documentElement.requestFullscreen().catch(() => {
        showToast("Visualizzazione espansa attiva 🌸");
      });
    } else if (document.documentElement.webkitRequestFullscreen) {
      document.documentElement.webkitRequestFullscreen();
    } else {
      showToast("Visualizzazione a tutto schermo 🌸");
    }
  } else {
    if (document.exitFullscreen) {
      document.exitFullscreen();
    } else if (document.webkitExitFullscreen) {
      document.webkitExitFullscreen();
    }
  }
}

// ============================================================
// 7. PICCOLI GESTI PER TE (~40 CONSIGLI, DINAMICI E CONTRASSEGNABILI)
// ============================================================
const ALL_COMFORT_TIPS = [
  // SLEEP (Non riesci a dormire)
  { id: 1, categoria: "sleep", emoji: "🫁", titolo: "Fai un respiro profondo con Pisolo", descrizione: "Segui il ritmo calmo dell'esercizio guidato 4-4-4 per rallentare il battito e preparare il corpo al sonno.", isBreath: true },
  { id: 2, categoria: "sleep", emoji: "🛋️", titolo: "Trova la posizione perfetta", descrizione: "Rannicchiati sotto le coperte, abbraccia un cuscino e lascia che le spalle sprofondino nel materasso.", isBreath: false },
  { id: 3, categoria: "sleep", emoji: "🎧", titolo: "Ascolta il suono della pioggia", descrizione: "Metti una traccia di pioggia leggera o onde del mare a basso volume, e concentrati solo su quel rumore costante.", isBreath: false },
  { id: 4, categoria: "sleep", emoji: "🕯️", titolo: "Visualizza un luogo tranquillo", descrizione: "Immagina di essere su una barca che dondola lentamente su un lago calmo sotto un cielo stellato.", isBreath: false },
  { id: 5, categoria: "sleep", emoji: "🙈", titolo: "Rilassa gli occhi", descrizione: "Chiudi gli occhi e immagina uno spazio completamente nero, rilassando i muscoli della fronte e delle palpebre.", isBreath: false },
  { id: 6, categoria: "sleep", emoji: "💆‍♀️", titolo: "Rilassamento muscolare progressivo", descrizione: "Parti dai piedi: tendili per due secondi e poi rilassali completamente. Sali fino al collo e al viso.", isBreath: false },

  // ANXIETY (Ansia o agitazione)
  { id: 7, categoria: "anxiety", emoji: "💧", titolo: "Bevi un sorso d'acqua fresca", descrizione: "Sorseggia lentamente acqua fredda: aiuta a resettare il nervo vago e a calmare il battito cardiaco.", isBreath: false },
  { id: 8, categoria: "anxiety", emoji: "🫁", titolo: "Respirazione a labbra socchiuse", descrizione: "Inspira piano dal naso ed espira lentamente dalla bocca, come se dovessi soffiare su una piuma.", isBreath: true },
  { id: 9, categoria: "anxiety", emoji: "🦶", titolo: "Senti il pavimento sotto di te", descrizione: "Concentrati sul contatto dei piedi con il suolo. Conta 5 cose che puoi vedere e 4 che puoi toccare.", isBreath: false },
  { id: 10, categoria: "anxiety", emoji: "🧊", titolo: "Tocca qualcosa di freddo", descrizione: "Tieni in mano un cubetto di ghiaccio o lava i polsi e il viso con acqua fredda per interrompere l'agitazione.", isBreath: false },
  { id: 11, categoria: "anxiety", emoji: "🛡️", titolo: "Dì a te stessa: 'Passerà'", descrizione: "L'ansia è come un'onda: ora è alta, ma inevitabilmente si abbasserà. Sei al sicuro.", isBreath: false },
  { id: 12, categoria: "anxiety", emoji: "🫂", titolo: "Stringi forte un cuscino", descrizione: "Applica una leggera pressione sul petto abbracciando un cuscino o coprendoti con una coperta pesante.", isBreath: false },

  // DISTANCE (Vi mancate a distanza)
  { id: 13, categoria: "distance", emoji: "💌", titolo: "Scrivimi un messaggio dolce", descrizione: "Anche se non sono lì, leggerti mi fa sentire vicino. Raccontami una cosa bella di oggi.", isBreath: false },
  { id: 14, categoria: "distance", emoji: "📸", titolo: "Guarda una nostra foto speciale", descrizione: "Cerca nella galleria del telefono la foto di un momento in cui ridevamo insieme.", isBreath: false },
  { id: 15, categoria: "distance", emoji: "🧸", titolo: "Stringi un peluche", descrizione: "Abbraccia Pisolo o Micio immaginando che sia io a tenerti stretta, forte forte.", isBreath: false },
  { id: 16, categoria: "distance", emoji: "❤️", titolo: "Siamo sotto lo stesso cielo", descrizione: "Guarda fuori dalla finestra: anche se lontani, guardiamo le stesse stelle e la stessa luna.", isBreath: false },
  { id: 17, categoria: "distance", emoji: "🗓️", titolo: "Pensa al nostro prossimo incontro", descrizione: "Focalizzati su cosa faremo la prossima volta che ci vedremo. Il tempo passerà in fretta.", isBreath: false },
  { id: 18, categoria: "distance", emoji: "🎶", titolo: "Ascolta la 'nostra' canzone", descrizione: "Metti in play quella canzone che ti fa pensare a noi e chiudi gli occhi per un istante.", isBreath: false },

  // HEAVY_DAY (Dopo una giornata pesante)
  { id: 19, categoria: "heavy_day", emoji: "☕", titolo: "Una bevanda calda tutta per te", descrizione: "Prepara un tè o una cioccolata calda e stringi la tazza con entrambe le mani per assorbire il calore.", isBreath: false },
  { id: 20, categoria: "heavy_day", emoji: "🛁", titolo: "Fai una doccia rilassante", descrizione: "Lascia scorrere l'acqua calda sulle spalle e immagina che lavi via tutto lo stress della giornata.", isBreath: false },
  { id: 21, categoria: "heavy_day", emoji: "📱", titolo: "15 minuti di silenzio", descrizione: "Silenzia le notifiche e metti via il telefono. Per un quarto d'ora non devi essere produttiva.", isBreath: false },
  { id: 22, categoria: "heavy_day", emoji: "🧦", titolo: "Mettiti comoda", descrizione: "Togliti i vestiti da lavoro/studio, indossa il pigiama più morbido che hai o dei calzini caldi.", isBreath: false },
  { id: 23, categoria: "heavy_day", emoji: "🍫", titolo: "Concediti un piccolo premio", descrizione: "Gusta un pezzetto di cioccolato o il tuo snack preferito: te lo sei meritato oggi.", isBreath: false },
  { id: 24, categoria: "heavy_day", emoji: "🕊️", titolo: "Perdona ciò che non sei riuscita a fare", descrizione: "Hai fatto del tuo meglio. Ciò che è rimasto indietro aspetterà domani senza problemi.", isBreath: false },

  // ANGER (Quando sei arrabbiata)
  { id: 25, categoria: "anger", emoji: "✍️", titolo: "Scrivi tutto e buttalo via", descrizione: "Prendi un foglio, scrivi di getto tutto ciò che ti fa rabbia, poi accartoccialo o strappalo.", isBreath: false },
  { id: 26, categoria: "anger", emoji: "🚶‍♀️", titolo: "Fai due passi fuori", descrizione: "Esci di casa anche solo per 5 minuti. L'aria fresca sul viso aiuta a dissipare l'energia trattenuta.", isBreath: false },
  { id: 27, categoria: "anger", emoji: "🫁", titolo: "Respira profondamente", descrizione: "Usa la funzione di respiro qui nell'app. Concentrati solo sul conteggio per spezzare il pensiero.", isBreath: true },
  { id: 28, categoria: "anger", emoji: "💧", titolo: "Lavati il viso con acqua fredda", descrizione: "Un gesto netto che abbassa la temperatura corporea e dona una sensazione di reset immediato.", isBreath: false },
  { id: 29, categoria: "anger", emoji: "🛡️", titolo: "Prenditi una pausa dalla discussione", descrizione: "Se stai litigando, chiedi un momento di stop. 'Ho bisogno di 10 minuti per calmarmi, poi ne parliamo'.", isBreath: false },
  { id: 30, categoria: "anger", emoji: "🔄", titolo: "Distrai la mente con un compito fisico", descrizione: "Riordina la scrivania, piega una maglietta, fai qualcosa di manuale per scaricare la tensione.", isBreath: false },

  // LONELY (Quando ti senti sola)
  { id: 31, categoria: "lonely", emoji: "🫂", titolo: "Richiedi un abbraccio a Pisolo e Micio", descrizione: "Vai nella sezione abbracci e fatti stringere forte. Siamo qui per te, sempre.", isBreath: false },
  { id: 32, categoria: "lonely", emoji: "📞", titolo: "Ascolta un mio vocale", descrizione: "Riascolta un mio vecchio messaggio vocale in cui ridiamo insieme: la mia voce è sempre con te.", isBreath: false },
  { id: 33, categoria: "lonely", emoji: "📖", titolo: "Immergiti in una storia", descrizione: "Inizia a leggere un libro confortante o guarda la tua serie TV preferita per sentirti in compagnia.", isBreath: false },
  { id: 34, categoria: "lonely", emoji: "❤️", titolo: "Ricordati che sei amata", descrizione: "Non scordarlo mai: c'è qualcuno al mondo che pensa a te e a cui sei preziosa, anche in questo momento.", isBreath: false },
  { id: 35, categoria: "lonely", emoji: "🎶", titolo: "Metti musica allegra di sottofondo", descrizione: "Riempi il silenzio della stanza con le note di una playlist felice o cantabile.", isBreath: false },
  { id: 36, categoria: "lonely", emoji: "☀️", titolo: "Esci in un luogo affollato", descrizione: "Vai in un bar o siediti su una panchina al parco: stare tra la gente (senza dover parlare) può confortare.", isBreath: false }
];

const TIP_CATEGORIES = [
  { id: "ALL", label: "Tutte le situazioni", emoji: "✨" },
  { id: "sleep", label: "Non riesci a dormire", emoji: "💤" },
  { id: "anxiety", label: "Ansia improvvisa", emoji: "🦋" },
  { id: "distance", label: "Vi mancate a distanza", emoji: "✈️" },
  { id: "heavy_day", label: "Giornata pesante", emoji: "🔋" },
  { id: "anger", label: "Momento di rabbia", emoji: "🌪️" },
  { id: "lonely", label: "Ti senti sola", emoji: "🫂" }
];
let selectedTipCat = "ALL";
let isShowingAllTips = false;
let activeSessionTipIds = [];

function getCheckedTipIds() {
  try {
    const raw = localStorage.getItem('pisolo_checked_tips');
    return raw ? JSON.parse(raw) : [];
  } catch (e) {
    return [];
  }
}

function saveCheckedTipIds(ids) {
  try {
    localStorage.setItem('pisolo_checked_tips', JSON.stringify(ids));
  } catch (e) { }
}

function initSessionTips(forceNew = false) {
  if (!forceNew) {
    try {
      const stored = sessionStorage.getItem('pisolo_session_tip_ids');
      if (stored) {
        const parsed = JSON.parse(stored);
        if (Array.isArray(parsed) && parsed.length === 5) {
          activeSessionTipIds = parsed;
          return;
        }
      }
    } catch (e) { }
  }

  // Shuffle e seleziona esattamente 5 consigli per te
  const shuffled = [...ALL_COMFORT_TIPS].sort(() => 0.5 - Math.random());
  activeSessionTipIds = shuffled.slice(0, 5).map(t => t.id);
  try {
    sessionStorage.setItem('pisolo_session_tip_ids', JSON.stringify(activeSessionTipIds));
  } catch (e) { }
}

function shuffleComfortTips() {
  initSessionTips(true);
  renderComfortTips();
  showToast("5 nuovi piccoli gesti estratti per te 🌿✨");
}

function toggleViewAllTips() {
  isShowingAllTips = !isShowingAllTips;
  const btn = document.getElementById('btn-view-all-tips-toggle');
  if (btn) {
    btn.innerHTML = isShowingAllTips ? `<span>✨</span> Selezione (5)` : `<span>📋</span> Tutti (42)`;
  }
  renderComfortTips();
  showToast(isShowingAllTips ? "Visualizzazione completa di tutti i 42 gesti" : "Visualizzazione selezione di 5 gesti 🌸");
}

function toggleTipCheck(tipId, event) {
  if (event) event.stopPropagation();
  let checked = getCheckedTipIds();
  const idx = checked.indexOf(tipId);
  let isNowChecked = false;
  if (idx > -1) {
    checked.splice(idx, 1);
    showToast("Gesto deselezionato");
  } else {
    checked.push(tipId);
    isNowChecked = true;
    if (event && event.currentTarget) {
      const rect = event.currentTarget.getBoundingClientRect();
      const h = document.createElement('div');
      h.className = 'floating-heart';
      h.textContent = '💖';
      h.style.left = `${rect.left + 8}px`;
      h.style.top = `${rect.top}px`;
      document.body.appendChild(h);
      setTimeout(() => h.remove(), 1600);
    }
    showToast("Piccolo gesto compiuto con amore! 🌸");
  }
  saveCheckedTipIds(checked);
  renderComfortTips();

  // Se tutti i 5 gesti della sessione (o della lista) sono stati completati, attiva l'animazione di congratulazioni!
  if (isNowChecked) {
    const targetList = isShowingAllTips ? ALL_COMFORT_TIPS.map(t => t.id) : activeSessionTipIds;
    if (targetList.length > 0 && targetList.every(id => checked.includes(id))) {
      setTimeout(() => {
        triggerTipsCelebration();
      }, 350);
    }
  }
}

// ============================================================
// CELEBRAZIONE CON ANIMAZIONE CORIANDOLI & CUORI (CONGRATULAZIONI)
// ============================================================
let confettiAnimationId = null;
let confettiParticles = [];

function triggerTipsCelebration() {
  const modal = document.getElementById('modal-tips-celebration-backdrop');
  if (modal) {
    modal.style.display = 'flex';
  }

  // Haptic feedback se supportato
  if (navigator.vibrate) {
    try { navigator.vibrate([100, 60, 140, 80, 200]); } catch (e) { }
  }

  launchConfettiAnimation();
}

function closeTipsCelebrationModal(e) {
  if (e && e.target && e.target.id !== 'modal-tips-celebration-backdrop' && !e.target.classList.contains('modal-close-trigger') && e.target.tagName !== 'BUTTON') return;
  const modal = document.getElementById('modal-tips-celebration-backdrop');
  if (modal) modal.style.display = 'none';
  stopConfettiAnimation();
}

function launchConfettiAnimation() {
  const canvas = document.getElementById('confetti-canvas');
  if (!canvas) return;
  const ctx = canvas.getContext('2d');
  canvas.width = window.innerWidth;
  canvas.height = window.innerHeight;
  canvas.style.display = 'block';

  confettiParticles = [];
  const colors = ['#F8BBD0', '#D81B60', '#F48FB1', '#81D4FA', '#0288D1', '#FFE082', '#FFCA28', '#C8E6C9', '#E1BEE7'];
  const symbols = ['💖', '✨', '🌸', '🦈', '🐱', '⭐', '🎈'];

  // Crea 95 particelle festive
  for (let i = 0; i < 95; i++) {
    confettiParticles.push({
      x: Math.random() * canvas.width,
      y: Math.random() * -canvas.height,
      size: Math.random() * 9 + 6,
      color: colors[Math.floor(Math.random() * colors.length)],
      symbol: Math.random() > 0.65 ? symbols[Math.floor(Math.random() * symbols.length)] : null,
      speedY: Math.random() * 3.5 + 2.2,
      speedX: Math.random() * 3 - 1.5,
      rotation: Math.random() * 360,
      rotationSpeed: Math.random() * 6 - 3,
      opacity: 1
    });
  }

  if (confettiAnimationId) cancelAnimationFrame(confettiAnimationId);

  let frameCount = 0;
  function renderConfetti() {
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    let activeCount = 0;

    confettiParticles.forEach(p => {
      p.y += p.speedY;
      p.x += Math.sin(p.y * 0.03) + p.speedX;
      p.rotation += p.rotationSpeed;

      if (p.y < canvas.height + 40) activeCount++;

      ctx.save();
      ctx.translate(p.x, p.y);
      ctx.rotate((p.rotation * Math.PI) / 180);
      ctx.globalAlpha = p.opacity;

      if (p.symbol) {
        ctx.font = `${p.size * 1.5}px sans-serif`;
        ctx.textAlign = 'center';
        ctx.fillText(p.symbol, 0, 0);
      } else {
        ctx.fillStyle = p.color;
        ctx.fillRect(-p.size / 2, -p.size / 2, p.size, p.size * 0.7);
      }
      ctx.restore();
    });

    frameCount++;
    if (activeCount > 0 && frameCount < 400) {
      confettiAnimationId = requestAnimationFrame(renderConfetti);
    } else {
      stopConfettiAnimation();
    }
  }

  renderConfetti();
}

function stopConfettiAnimation() {
  if (confettiAnimationId) {
    cancelAnimationFrame(confettiAnimationId);
    confettiAnimationId = null;
  }
  const canvas = document.getElementById('confetti-canvas');
  if (canvas) {
    const ctx = canvas.getContext('2d');
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    canvas.style.display = 'none';
  }
}

function resetCheckedTips() {
  if (confirm("Vuoi azzerare tutti i gesti contrassegnati per ricominciare?")) {
    saveCheckedTipIds([]);
    renderComfortTips();
    showToast("Gesti azzerati: buon nuovo inizio! 🌸");
  }
}

function updateTipsProgressBar(currentList, checkedIds) {
  const textEl = document.getElementById('tips-progress-text');
  const fillEl = document.getElementById('tips-progress-bar-fill');
  if (!textEl || !fillEl) return;

  const total = currentList.length;
  const completed = currentList.filter(t => checkedIds.includes(t.id)).length;
  const pct = total > 0 ? Math.round((completed / total) * 100) : 0;

  textEl.innerHTML = `✨ Gesti completati: <strong>${completed} su ${total}</strong> (${pct}%)`;
  fillEl.style.width = `${pct}%`;
}

function renderFilterPills() {
  const container = document.getElementById('filter-pills-container');
  if (!container) return;
  container.innerHTML = '';

  TIP_CATEGORIES.forEach(cat => {
    const pill = document.createElement('div');
    pill.className = `filter-pill ${selectedTipCat === cat.id ? 'active' : ''}`;
    pill.textContent = `${cat.emoji} ${cat.label}`;
    pill.onclick = () => {
      selectedTipCat = cat.id;
      renderFilterPills();
      renderComfortTips();
    };
    container.appendChild(pill);
  });
}

function renderComfortTips() {
  const container = document.getElementById('tips-list-container');
  if (!container) return;
  container.innerHTML = '';

  if (activeSessionTipIds.length === 0) {
    initSessionTips(false);
  }

  const baseList = isShowingAllTips
    ? ALL_COMFORT_TIPS
    : ALL_COMFORT_TIPS.filter(t => activeSessionTipIds.includes(t.id));

  const list = selectedTipCat === "ALL"
    ? baseList
    : baseList.filter(t => t.categoria === selectedTipCat);

  const checkedIds = getCheckedTipIds();
  updateTipsProgressBar(list, checkedIds);

  if (list.length === 0) {
    container.innerHTML = `<div style="text-align:center; padding:20px; font-size:13px; color:var(--warm-text-secondary);">Nessun gesto in questa categoria. Prova a toccare "Tutti" o "Nuovi gesti" 🌸</div>`;
    return;
  }

  list.forEach(tip => {
    const isChecked = checkedIds.includes(tip.id);
    const card = document.createElement('div');
    card.className = `tip-card-item ${tip.isBreath ? 'is-breath-tip' : ''} ${isChecked ? 'is-completed' : ''}`;

    card.onclick = (e) => {
      if (tip.isBreath) {
        openBreathingModal();
      } else {
        toggleTipCheck(tip.id, e);
      }
    };

    card.innerHTML = `
          <button type="button" class="tip-check-btn" onclick="toggleTipCheck(${tip.id}, event)" title="${isChecked ? 'Contrassegnato come fatto' : 'Segna come fatto'}">
            ✓
          </button>
          <div class="tip-icon-avatar" style="background: ${tip.isBreath ? 'var(--pastel-blue-container)' : 'var(--pastel-pink-container)'}">
            ${tip.emoji}
          </div>
          <div class="tip-body-content">
            <div class="tip-item-title">
              <span>${tip.titolo}</span>
              <span class="tip-done-tag">FATTO ✓</span>
            </div>
            <div class="tip-item-desc">${tip.descrizione}</div>
          </div>
          ${tip.isBreath ? '<span style="font-size:16px; color:var(--pastel-blue-accent); font-weight:bold; margin-left:4px;" title="Avvia respiro">▶</span>' : ''}
        `;
    container.appendChild(card);
  });
}

// 7. RESPIRAZIONE GUIDATA 4-4-4
let breathTimerInterval = null;
let isBreathingActive = true;
let isPrepCountdown = true;
let prepSecondsLeft = 5;
const BREATH_CYCLE = [
  { name: "Inspira lentamente...", desc: "Lascia entrare l'aria dal naso con calma, riempiendo il petto senza fretta.", seconds: 4, scale: 1.35, color: '#F8BBD0', icon: '🌸', stepIdx: 0 },
  { name: "Trattieni...", desc: "Mantieni l'aria dentro dolcemente, sentendo la stabilità e la quiete.", seconds: 4, scale: 1.35, color: '#BBDEFB', icon: '🫧', stepIdx: 1 },
  { name: "Espira con dolcezza...", desc: "Lascia uscire tutta l'aria dalla bocca, sciogliendo ogni tensione fisica.", seconds: 4, scale: 0.88, color: '#E1F5FE', icon: '🍃', stepIdx: 2 },
  { name: "Pausa di riposo...", desc: "Resta un attimo in silenzio. Tutto va bene, sei al sicuro.", seconds: 2, scale: 0.88, color: '#FCE4EC', icon: '☁️', stepIdx: 3 }
];
let currentBreathIndex = 0;
let secondsLeft = 4;

function openBreathingModal() {
  document.getElementById('modal-breath-backdrop').style.display = 'flex';
  isBreathingActive = true;
  document.getElementById('breath-toggle-btn').innerHTML = '⏸️ Pausa';

  // Avviso e conto alla rovescia di 5 secondi prima di iniziare
  isPrepCountdown = true;
  prepSecondsLeft = 5;
  updatePrepUI();

  clearInterval(breathTimerInterval);
  breathTimerInterval = setInterval(() => {
    if (!isBreathingActive) return;

    if (isPrepCountdown) {
      prepSecondsLeft--;
      if (prepSecondsLeft <= 0) {
        isPrepCountdown = false;
        currentBreathIndex = 0;
        secondsLeft = BREATH_CYCLE[0].seconds;
        updateBreathPhaseUI();
      } else {
        updatePrepUI();
      }
    } else {
      secondsLeft--;
      if (secondsLeft <= 0) {
        currentBreathIndex = (currentBreathIndex + 1) % BREATH_CYCLE.length;
        secondsLeft = BREATH_CYCLE[currentBreathIndex].seconds;
        updateBreathPhaseUI();
      } else {
        document.getElementById('breath-sec-value').textContent = secondsLeft;
      }
    }
  }, 1000);
}

function updatePrepUI() {
  const circle = document.getElementById('breath-animated-circle');
  if (circle) {
    circle.style.transform = 'scale(1.02)';
    circle.style.backgroundColor = '#FFE0B2';
  }
  const icon = document.getElementById('breath-center-icon');
  if (icon) icon.textContent = '🧘‍♀️';
  const secVal = document.getElementById('breath-sec-value');
  if (secVal) secVal.textContent = prepSecondsLeft;
  const title = document.getElementById('breath-step-title');
  if (title) title.textContent = 'Preparati a respirare...';
  const desc = document.getElementById('breath-step-desc');
  if (desc) {
    desc.innerHTML = `Trova una posizione comoda e rilassa le spalle.<br><span style="font-weight:700; color:var(--deep-rose);">Inizio dell'esercizio tra ${prepSecondsLeft} secondi...</span>`;
  }

  const prepPill = document.getElementById('b-step-prep');
  if (prepPill) {
    prepPill.style.display = 'inline-block';
    prepPill.classList.add('active');
    prepPill.textContent = `⏳ Inizio in ${prepSecondsLeft}s`;
  }
  for (let i = 0; i < 4; i++) {
    const el = document.getElementById(`b-step-${i}`);
    if (el) el.classList.remove('active');
  }
}

function updateBreathPhaseUI() {
  const b = BREATH_CYCLE[currentBreathIndex];
  const circle = document.getElementById('breath-animated-circle');
  if (circle) {
    circle.style.transform = `scale(${b.scale})`;
    circle.style.backgroundColor = b.color;
  }
  const icon = document.getElementById('breath-center-icon');
  if (icon) icon.textContent = b.icon;
  const secVal = document.getElementById('breath-sec-value');
  if (secVal) secVal.textContent = secondsLeft;
  const title = document.getElementById('breath-step-title');
  if (title) title.textContent = b.name;
  const desc = document.getElementById('breath-step-desc');
  if (desc) desc.textContent = b.desc;

  const prepPill = document.getElementById('b-step-prep');
  if (prepPill) {
    prepPill.style.display = 'none';
    prepPill.classList.remove('active');
  }

  for (let i = 0; i < 4; i++) {
    const el = document.getElementById(`b-step-${i}`);
    if (el) {
      if (i === b.stepIdx) {
        el.classList.add('active');
      } else {
        el.classList.remove('active');
      }
    }
  }
}

function toggleBreathingRun() {
  isBreathingActive = !isBreathingActive;
  document.getElementById('breath-toggle-btn').innerHTML = isBreathingActive ? '⏸️ Pausa' : '▶️ Riprendi';
}

function closeBreathingModal() {
  clearInterval(breathTimerInterval);
  isPrepCountdown = false;
  document.getElementById('modal-breath-backdrop').style.display = 'none';
}

// 8. DIARIO / SPAZIO DI SFOGO
function handleVentingInput() {
  const val = document.getElementById('vent-text-input').value.trim();
  document.getElementById('vent-submit-btn').disabled = val.length === 0;
  document.getElementById('vent-clear-btn').style.display = val.length > 0 ? 'block' : 'none';
}

function clearVentingText() {
  document.getElementById('vent-text-input').value = '';
  handleVentingInput();
}

function releaseThoughtsToSea() {
  const area = document.getElementById('vent-text-input');
  area.style.opacity = '0';

  for (let i = 0; i < 16; i++) {
    setTimeout(() => {
      const b = document.createElement('div');
      b.className = 'floating-bubble';
      b.textContent = ['🫧', '🌊', '🫧', '✨', '☁️'][Math.floor(Math.random() * 5)];
      b.style.left = (Math.random() * 84 + 8) + 'vw';
      b.style.top = (Math.random() * 25 + 65) + 'vh';
      document.body.appendChild(b);
      setTimeout(() => b.remove(), 2500);
    }, i * 65);
  }

  setTimeout(() => {
    area.value = '';
    area.style.opacity = '1';
    handleVentingInput();
    const fb = document.getElementById('vent-feedback-box');
    fb.style.display = 'block';
    setTimeout(() => fb.style.display = 'none', 6000);
    showToast("I tuoi pensieri si sono sciolti tra le onde 🫧");
  }, 350);
}

// Registrazione Service Worker per supporto offline e PWA
if ('serviceWorker' in navigator) {
  window.addEventListener('load', () => {
    navigator.serviceWorker.register('./sw.js', { scope: './' })
      .then((reg) => {
        console.log('SW registrato con successo, scope:', reg.scope);
        try { reg.update(); } catch (e) { }
      })
      .catch((err) => {
        console.log('SW registration error:', err);
      });
  });
}

// 10. MODALE INIZIALE & CONTATTO DIRETTO COL RAGAZZO
function dismissWelcomeModal() {
  const overlay = document.getElementById('welcome-modal');
  overlay.style.opacity = '0';
  setTimeout(() => {
    overlay.style.display = 'none';
    if (typeof chiediPermessoNotifiche === 'function') {
      chiediPermessoNotifiche();
    }
  }, 250);
  localStorage.setItem('welcome_dismissed_today', new Date().toDateString());
}

function reopenWelcomeModal() {
  const overlay = document.getElementById('welcome-modal');
  overlay.style.display = 'flex';
  overlay.style.opacity = '1';
}

function openPartnerChat() {
  const phone = localStorage.getItem('partner_phone_num') || '';
  if (!phone) {
    editPartnerPhone();
    return;
  }
  const cleanPhone = phone.replace(/[^0-9]/g, '');
  const waUrl = `https://wa.me/${cleanPhone}?text=${encodeURIComponent("Ciao amore mio ❤️ Ho aperto l'app e volevo dirti che ho bisogno di te...")}`;
  window.open(waUrl, '_blank');
}

function editPartnerPhone() {
  const current = localStorage.getItem('partner_phone_num') || '';
  const input = prompt("Inserisci il numero WhatsApp del tuo ragazzo (incluso prefisso internazionale, es. +393401234567):", current);
  if (input !== null) {
    localStorage.setItem('partner_phone_num', input.trim());
    showToast("Numero salvato con successo! ❤️");
  }
}

// NAVIGAZIONE FLUIDA
function smoothNavTo(elementId, btn) {
  const target = document.getElementById(elementId);
  if (target) {
    target.scrollIntoView({ behavior: 'smooth', block: 'start' });
  }
  document.querySelectorAll('.nav-tab-btn').forEach(b => b.classList.remove('active'));
  if (btn) btn.classList.add('active');
}

const sectionIds = ['sec-home', 'sec-tips', 'sec-vent'];
const navButtons = document.querySelectorAll('.nav-tab-btn');

window.addEventListener('scroll', () => {
  const scrollPos = window.scrollY + 160;
  for (let i = sectionIds.length - 1; i >= 0; i--) {
    const sec = document.getElementById(sectionIds[i]);
    if (sec && sec.offsetTop <= scrollPos) {
      navButtons.forEach(b => b.classList.remove('active'));
      if (navButtons[i]) navButtons[i].classList.add('active');
      break;
    }
  }
}, { passive: true });

// Toast dolce
function showToast(msg) {
  const t = document.getElementById('toast-el');
  if (!t) return;
  t.textContent = msg;
  t.classList.add('show');
  clearTimeout(t._timer);
  t._timer = setTimeout(() => {
    t.classList.remove('show');
  }, 3200);
}

// ============================================================
// GESTIONE CHIAVE API GEMINI & GENERAZIONE DEDICHE / CONSIGLI IA
// ============================================================
function getUserApiKey() {
  return HARDCODED_GEMINI_API_KEY || localStorage.getItem('user_gemini_api_key') || '';
}

function cleanGeneratedText(t) {
  if (!t) return '';
  return t.replace(/^["'“«]+|["'”»]+$/g, '').trim();
}

// Chiamata diretta e sicura a Google Gemini da browser (CORS abilitato nativamente da Google)
async function callGeminiDirect(apiKey, prompt, maxTokens = 200) {
  // Modelli moderni e attuali supportati dall'API v1beta di Gemini
  const candidateModels = [
    'gemini-flash-latest',
    'gemini-2.5-flash',
    'gemini-1.5-flash',
    'gemini-1.5-pro'
  ];
  let lastErr = null;

  // 1. Prova prima i modelli supportati
  for (const model of candidateModels) {
    try {
      const url = `https://generativelanguage.googleapis.com/v1beta/models/${model}:generateContent?key=${encodeURIComponent(apiKey)}`;
      const payload = {
        contents: [{
          parts: [{ text: prompt }]
        }],
        generationConfig: {
          temperature: 0.9,
          maxOutputTokens: maxTokens,
          topP: 0.95
        }
      };

      const res = await fetch(url, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload)
      });

      const data = await res.json();
      if (res.ok && data.candidates && data.candidates.length > 0 && data.candidates[0].content && data.candidates[0].content.parts && data.candidates[0].content.parts.length > 0) {
        const text = data.candidates[0].content.parts[0].text;
        if (text) return { success: true, text: text.trim(), model };
      }
      if (data.error) {
        lastErr = data.error.message || JSON.stringify(data.error);
        console.warn(`Gemini direct error (${model}):`, data.error);
        // Se la chiave è errata (API_KEY_INVALID), fermati subito senza ciclare invano
        if (data.error.status === 'INVALID_ARGUMENT' && data.error.message && data.error.message.includes('API key')) {
          return { success: false, error: 'Chiave API non valida: ' + data.error.message };
        }
      }
    } catch (err) {
      lastErr = err.message;
      console.warn(`Fetch error with ${model}:`, err);
    }
  }

  // 2. Se nessuno ha funzionato, chiedi a Google l'elenco dei modelli abilitati per questa chiave specifica
  try {
    const listRes = await fetch(`https://generativelanguage.googleapis.com/v1beta/models?key=${encodeURIComponent(apiKey)}`);
    const listData = await listRes.json();
    if (listData.models && Array.isArray(listData.models)) {
      const usable = listData.models.filter(m =>
        m.supportedGenerationMethods && m.supportedGenerationMethods.includes('generateContent')
      );
      for (const mObj of usable) {
        const mName = (mObj.name || '').replace(/^models\//, '');
        if (!mName) continue;
        try {
          const url = `https://generativelanguage.googleapis.com/v1beta/models/${mName}:generateContent?key=${encodeURIComponent(apiKey)}`;
          const payload = {
            contents: [{ parts: [{ text: prompt }] }],
            generationConfig: { maxOutputTokens: maxTokens }
          };
          const res = await fetch(url, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(payload)
          });
          const data = await res.json();
          if (res.ok && data.candidates && data.candidates[0]?.content?.parts?.[0]?.text) {
            return { success: true, text: data.candidates[0].content.parts[0].text.trim(), model: mName };
          }
        } catch (e) { }
      }
    } else if (listData.error) {
      lastErr = listData.error.message || lastErr;
    }
  } catch (e) {
    console.warn('ListModels fallback failed:', e);
  }

  return { success: false, error: lastErr || 'Nessun modello Gemini compatibile ha risposto.' };
}

async function callGeminiImage(apiKey, prompt, timeoutMs = 8000) {
  if (!apiKey) return null;

  const controller = new AbortController();
  const timeoutId = setTimeout(() => controller.abort(), timeoutMs);

  try {
    const url = `https://generativelanguage.googleapis.com/v1beta/models/imagen-3.0-generate-001:predict?key=${encodeURIComponent(apiKey)}`;
    const payload = {
      instances: [{ prompt: prompt }],
      parameters: { sampleCount: 1 }
    };

    const res = await fetch(url, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(payload),
      signal: controller.signal
    });

    clearTimeout(timeoutId);
    const data = await res.json();

    if (res.ok && data.predictions && data.predictions.length > 0 && data.predictions[0].bytesBase64Encoded) {
      return "data:image/jpeg;base64," + data.predictions[0].bytesBase64Encoded;
    }
  } catch (err) {
    clearTimeout(timeoutId);
    console.warn('Gemini image generation error:', err);
  }
  return null;
}

function openApiKeyModal() {
  const modal = document.getElementById('modal-api-key-backdrop');
  const input = document.getElementById('user-gemini-key-input');
  const statusInd = document.getElementById('api-key-status-indicator');
  const resultBox = document.getElementById('api-key-test-result');

  const savedKey = getUserApiKey();
  if (input) input.value = savedKey;
  if (resultBox) {
    resultBox.style.display = 'none';
    resultBox.textContent = '';
  }
  if (statusInd) {
    if (savedKey) {
      statusInd.textContent = '✅ Salvata e attiva';
      statusInd.style.color = '#10B981';
    } else {
      statusInd.textContent = 'Non impostata';
      statusInd.style.color = '#6B7280';
    }
  }

  if (modal) {
    modal.style.display = 'flex';
    modal.classList.add('open');
  }
}

function closeApiKeyModal(e) {
  if (e && e.target !== e.currentTarget && !e.target.classList.contains('modal-close-trigger') && !e.target.classList.contains('modal-btn-primary')) {
    return;
  }
  const modal = document.getElementById('modal-api-key-backdrop');
  if (modal) {
    modal.style.display = 'none';
    modal.classList.remove('open');
  }
  updateApiKeyHeaderButton();
}

function toggleApiKeyVisibility() {
  const input = document.getElementById('user-gemini-key-input');
  if (!input) return;
  if (input.type === 'password') {
    input.type = 'text';
  } else {
    input.type = 'password';
  }
}

async function testUserApiKey() {
  const input = document.getElementById('user-gemini-key-input');
  const resultBox = document.getElementById('api-key-test-result');
  const btn = document.getElementById('btn-test-api-key');
  const key = input ? input.value.trim() : '';

  if (!key) {
    if (resultBox) {
      resultBox.style.display = 'block';
      resultBox.style.background = '#FEE2E2';
      resultBox.style.color = '#B91C1C';
      resultBox.style.border = '1px solid #FCA5A5';
      resultBox.textContent = 'Inserisci prima la tua chiave API prima di testarla 🌸';
    }
    return;
  }

  if (btn) btn.textContent = '⏳ Verifica in corso...';
  if (resultBox) {
    resultBox.style.display = 'block';
    resultBox.style.background = '#EFF6FF';
    resultBox.style.color = '#1D4ED8';
    resultBox.style.border = '1px solid #BFDBFE';
    resultBox.textContent = 'Contatto diretto con Google Gemini in corso...';
  }

  // Prova chiamata diretta a Google Gemini dal browser
  const directResult = await callGeminiDirect(key, "Rispondi solo con: OK", 10);

  if (directResult.success) {
    resultBox.style.background = '#ECFDF5';
    resultBox.style.color = '#065F46';
    resultBox.style.border = '1px solid #6EE7B7';
    resultBox.innerHTML = `✨ <strong>Fantastico!</strong> La chiave è valida e funziona alla perfezione! (${directResult.model}) 🦈🐱❤️`;
  } else {
    resultBox.style.background = '#FEE2E2';
    resultBox.style.color = '#991B1B';
    resultBox.style.border = '1px solid #FCA5A5';
    resultBox.textContent = '⚠️ Errore: ' + (directResult.error || 'Verifica di aver copiato l\'intera chiave correttamente.');
  }

  if (btn) btn.textContent = '🧪 Verifica Chiave';
}

function saveUserApiKey() {
  const input = document.getElementById('user-gemini-key-input');
  const key = input ? input.value.trim() : '';

  if (!key) {
    showToast("Inserisci la chiave prima di salvare 🌸");
    return;
  }

  localStorage.setItem('user_gemini_api_key', key);
  updateApiKeyHeaderButton();
  showToast("Chiave salvata in modo sicuro nel tuo dispositivo! 🔐✨");
  closeApiKeyModal();
}

function removeUserApiKey() {
  if (confirm("Vuoi davvero rimuovere la tua chiave API da questo dispositivo?")) {
    localStorage.removeItem('user_gemini_api_key');
    const input = document.getElementById('user-gemini-key-input');
    if (input) input.value = '';
    const statusInd = document.getElementById('api-key-status-indicator');
    if (statusInd) {
      statusInd.textContent = 'Non impostata';
      statusInd.style.color = '#6B7280';
    }
    const resultBox = document.getElementById('api-key-test-result');
    if (resultBox) resultBox.style.display = 'none';
    updateApiKeyHeaderButton();
    showToast("Chiave rimossa dal dispositivo 🗑️");
  }
}

function updateApiKeyHeaderButton() {
  const btn = document.getElementById('header-btn-ai-key');
  const txt = document.getElementById('header-ai-btn-text');
  const key = getUserApiKey();
  if (btn) {
    if (key) {
      btn.classList.add('has-key');
      if (txt) txt.textContent = 'IA Attiva ✅';
    } else {
      btn.classList.remove('has-key');
      if (txt) txt.textContent = 'Chiave IA';
    }
  }
}

// ============================================================
// GENERAZIONE DEDICA CON IA
// ============================================================
let isAiGenerating = false;

async function generateAiDedication(mood = 'dolce') {
  if (isAiGenerating) return;
  isAiGenerating = true;

  const loader = document.getElementById('ai-generating-loader');
  const quoteEl = document.getElementById('featured-quote-text');
  const signatureEl = document.getElementById('featured-signature-text');
  const badgeEl = document.getElementById('ai-quote-badge');

  if (loader) loader.style.display = 'block';

  const userKey = getUserApiKey();
  const prompt = `Sei un'Intelligenza Artificiale dell'Amore e della Dolcezza, creata con tutto il cuore per dedicare parole sincere, uniche, dolci e profonde a una persona speciale (ispirata all'affetto incondizionato di Pisolo 🦈 uno squaletto tenero e Micio 🐱 un gattino dolce).
Genera una frase d'amore, di vicinanza o di conforto inedita, profonda e romanticissima (tono o atmosfera: "${mood}").
Scrivi unicamente la frase d'amore in italiano, senza virgolette, senza asterischi, senza elenchi e senza spiegazioni (massimo 30-35 parole). Solo la frase.`;

  let generatedText = '';

  if (userKey) {
    const directRes = await callGeminiDirect(userKey, prompt, 120);
    if (directRes.success && directRes.text) {
      generatedText = cleanGeneratedText(directRes.text);
    }
  }

  if (generatedText) {
    if (quoteEl) {
      quoteEl.style.opacity = '0';
      setTimeout(() => {
        quoteEl.textContent = `“${generatedText}”`;
        quoteEl.style.opacity = '1';
      }, 180);
    }

    if (signatureEl) {
      const moodNames = {
        'dolce': 'Con tutta la dolcezza di Pisolo 🦈 e Micio 🐱',
        'conforto': 'Un caldo abbraccio per te da Pisolo 🦈, Micio 🐱 e il tuo ragazzo',
        'incoraggiante': 'Pisolo 🦈 e Micio 🐱 fanno sempre il tifo per te!',
        'buonanotte': 'Sogni d\'oro da Pisolo 🦈 e Micio 🐱 🌙',
        'buongiorno': 'Un buongiorno colmo d\'affetto da Pisolo 🦈 e Micio 🐱 ☀️',
        'divertente': 'Una coccola buffa da Pisolo 🦈 e Micio 🐱'
      };
      signatureEl.textContent = `— ${moodNames[mood] || 'Pisolo 🦈 & Micio 🐱 (con Gemini IA ✨)'}`;
    }

    if (badgeEl) {
      badgeEl.style.display = 'inline-flex';
      badgeEl.textContent = `✨ Dedica creata da Gemini IA`;
    }

    // Aggiorna anche il testo nel modale abbraccio se aperto
    displayMessageInModal({
      quote: generatedText,
      author: "Pisolo 🦈, Micio 🐱 & il tuo ragazzo ❤️"
    });

    showToast("Nuova dedica generata con affetto! 🌸✨");
  } else {
    shuffleFeaturedMessage();
    showToast("Ecco un pensiero speciale per te! 💕");
  }

  if (loader) loader.style.display = 'none';
  isAiGenerating = false;
}

// ============================================================
// GENERAZIONE GESTO DI CURA CON IA
// ============================================================
async function generateAiTip() {
  const userKey = getUserApiKey();
  const prompt = `Sei un assistente affettuoso e premuroso, come Pisolo 🦈 (uno squaletto morbidissimo) e Micio 🐱 (un gattino dolce).
Genera un singolo consiglio breve, pratico, caloroso e confortante per prendersi cura di sé (tema o momento: "calma e coccole").
Scrivi solo il consiglio in italiano, in 2 o 3 frasi semplici, delicate e rassicuranti.
Niente titoli, niente introduzioni, niente elenchi puntati o asterischi. Solo il testo del consiglio.`;

  showToast("Pisolo e Micio stanno creando un consiglio per te... 🦈🐾");

  let generatedTip = '';

  if (userKey) {
    const directRes = await callGeminiDirect(userKey, prompt, 120);
    if (directRes.success && directRes.text) {
      generatedTip = cleanGeneratedText(directRes.text);
    }
  }

  if (generatedTip) {
    const list = document.getElementById('tips-container-list');
    if (list) {
      const aiCard = document.createElement('div');
      aiCard.className = 'tip-item-card active-card';
      aiCard.style.borderColor = '#C084FC';
      aiCard.style.background = 'linear-gradient(135deg, rgba(250,245,255,0.95), rgba(253,242,248,0.95))';
      aiCard.style.animation = 'fadeInUp 0.3s ease-out';
      aiCard.innerHTML = `
            <div class="tip-checkbox-wrap" onclick="toggleTipDone(this)">
              <div class="tip-checkbox-box"></div>
            </div>
            <div class="tip-content-wrap">
              <div class="tip-pill-tag" style="background:#F3E8FF; color:#7E22CE; border:1px solid #E9D5FF;">
                <span>✨</span> Consiglio IA Personalizzato
              </div>
              <div class="tip-text-body">
                ${generatedTip}
              </div>
              <div class="tip-mascot-author">
                <span>🦈</span> Pisolo &amp; Micio per te
              </div>
            </div>
          `;
      list.prepend(aiCard);
      aiCard.scrollIntoView({ behavior: 'smooth', block: 'center' });
      showToast("Consiglio IA aggiunto in cima alla lista! 🌿✨");
    }
  } else {
    shuffleComfortTips();
  }
}

// INIZIALIZZAZIONE ALL'AVVIO
window.addEventListener('DOMContentLoaded', () => {
  // Aggiorna stato pulsante chiave IA
  updateApiKeyHeaderButton();

  // Mascotte salvata
  const savedMascot = localStorage.getItem('active_mascot_pref');
  if (savedMascot === 'kitty' || savedMascot === 'shark') {
    currentMascot = savedMascot;
  }
  updateMascotVisual();

  // Foto hero iniziale d'abbraccio Pisolo & Micio
  const savedHero = localStorage.getItem('featured_hero_photo');
  const initialHugImage = getNextHugImage();
  const heroPhotoSrc = savedHero ? resolveAsset(savedHero, initialHugImage) : initialHugImage;
  const featuredEl = document.getElementById('featured-photo-img');
  const modalPhotoEl = document.getElementById('modal-photo-el');
  if (featuredEl) featuredEl.src = heroPhotoSrc;
  if (modalPhotoEl) modalPhotoEl.src = heroPhotoSrc;

  // Modale accoglienza: mostrato solo se già autenticato (altrimenti compare dopo il login)
  const overlay = document.getElementById('welcome-modal');
  if (overlay) {
    if (localStorage.getItem('pisolo_pwa_auth') === 'true') {
      overlay.style.display = 'flex';
      overlay.style.opacity = '1';
    } else {
      overlay.style.display = 'none';
    }
  }

  // Inizializzazione sfondo tranquillo a rotazione ad ogni accesso
  applyAccessTranquilBackground();

  // Inizializzazione messaggio e suggerimenti con la foto d'abbraccio
  const initialHeroMsg = { ...hugMessageBag.drawNext() };
  initialHeroMsg.image = heroPhotoSrc;
  displayMessageOnCard(initialHeroMsg);
  displayMessageInModal(initialHeroMsg);
  renderComfortTips();

  // Shortcuts dal manifest
  const urlParams = new URLSearchParams(window.location.search);
  const action = urlParams.get('action');
  if (action === 'hug') {
    setTimeout(() => openHugModal(), 300);
  } else if (action === 'breathe') {
    setTimeout(() => openBreathingModal(), 300);
  }
});

// ============================================================
// UTILITIES MANCANTI E GESTIONE NOTIFICHE
// ============================================================
function openAutonomousLinkModal() {
  if (AUTONOMOUS_PERMANENT_URL) {
    window.open(AUTONOMOUS_PERMANENT_URL, '_blank', 'noopener,noreferrer');
  } else {
    showToast("Il link autonomo non è configurato.");
  }
}

