# Guida di Setup e Deploy del Cloudflare Worker

Questo Cloudflare Worker gestisce:
1. **Cron Trigger Giornaliero:** Invia automaticamente una notifica push con un pensiero affettuoso scelto a caso.
2. **Endpoint REST On-Demand:** Permette di inviare una notifica immediata dalla pagina riservata `notify.html`, protetta da `API_SECRET_TOKEN`.

---

## 1. Prerequisiti: Dati OneSignal

1. Accedi a [OneSignal Dashboard](https://dashboard.onesignal.com/).
2. Seleziona la tua app configurata per **Web Push**.
3. Vai in **Settings** > **Keys & IDs**:
   - Copia il tuo **OneSignal App ID** (es. `xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx`).
   - Copia la tua **REST API Key** (es. `os_v2_app_...`).

---

## 2. Metodo Rapido A: Deploy da Dashboard Cloudflare (Senza terminale)

1. Accedi a [Cloudflare Dashboard](https://dash.cloudflare.com/) (il piano Free include fino a 100.000 richieste al giorno e fino a 5 cron trigger gratuiti).
2. Nel menu laterale, vai su **Workers & Pages** > **Create application** > **Create Worker**.
3. Dai un nome al Worker (es. `pisolo-push-worker`) e clicca su **Deploy**.
4. Clicca su **Edit code**:
   - Cancella il codice di esempio nell'editor web.
   - Copia e incolla l'intero contenuto del file [`src/index.js`](src/index.js).
   - Clicca su **Save and deploy**.
5. Vai nelle impostazioni del Worker (**Settings**):
   - Clicca sulla scheda **Variables and Secrets** (o *Variables*).
   - Aggiungi i seguenti **Secrets** (cliccando su *Add* e selezionando *Encrypt / Secret*):
     - `ONESIGNAL_APP_ID`: incolla il tuo App ID di OneSignal.
     - `ONESIGNAL_REST_API_KEY`: incolla la tua REST API Key di OneSignal.
     - `SEND_SECRET`: inventa una password/token sicura (es. `pisolo_super_segreto_2026`). Questa è la stessa password che inserirai nella pagina `notify.html`.
   - Clicca su **Save and deploy**.
6. Configura i **Cron Triggers** (invio automatico alle 15:00 e alle 20:00):
   - Nella scheda **Settings** > **Triggers**.
   - Nella sezione **Cron Triggers**, clicca su **Add Cron Trigger**.
   - Aggiungi il primo orario (ore 15:00): inserisci `0 13 * * *` (13:00 UTC) e salva.
   - Clicca di nuovo su **Add Cron Trigger** e aggiungi il secondo orario (ore 20:00): inserisci `0 18 * * *` (18:00 UTC) e salva.
7. Copia l'URL del tuo Worker (es. `https://pisolo-push-worker.<tuo-subdomain>.workers.dev`).

---

## 3. Metodo B: Deploy tramite Wrangler (CLI)

Se hai Node.js e Wrangler installati:

```bash
cd cloudflare-worker
npx wrangler login
npx wrangler secret put ONESIGNAL_APP_ID
npx wrangler secret put ONESIGNAL_REST_API_KEY
npx wrangler secret put API_SECRET_TOKEN
npx wrangler deploy
```

---

## 4. Come Usare la Pagina di Invio Manuale (`notify.html`)

1. Apri la pagina `notify.html` nel browser (o caricala sul tuo hosting insieme all'app).
2. Clicca su **⚙️ Configurazione Connessione**:
   - Incolla l'URL del tuo Worker (es. `https://pisolo-push-worker.tuonome.workers.dev`).
   - Incolla il tuo `API_SECRET_TOKEN`.
   - Clicca su **⚡ Testa connessione worker** per verificare che sia tutto collegato.
3. Le impostazioni rimarranno salvate nel browser del tuo dispositivo.
4. Ora puoi scrivere qualsiasi messaggio o usare i pulsanti rapidi e premere **🚀 Invia Notifica Adesso**!
