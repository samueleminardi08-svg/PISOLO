# SPECIFICA TECNICA E FUNZIONALE COMPLETA: PISOLO & MICIO 🦈🐱

> **Documento di Riferimento per la Ricostruzione e l'Evoluzione dell'Applicazione**  
> *Versione del documento:* 1.0  
> *Stato del progetto originale:* Web App PWA autonoma (HTML5 / Vanilla CSS / Vanilla JS) + Server Node.js leggero + Prototipo nativo Android (Jetpack Compose).  
> *Destinatario del documento:* Assistente AI (Claude / LLM avanzati) per la generazione di prompt di ricostruzione integrale, refactoring architetturale e potenziamento UX/UI.

---

## 1. Scopo e Contesto

### 1.1 Cos'è l'Applicazione
**"PISOLO & MICIO"** (titolo originale: *PISOLO & MICIO 🦈🐱 - Spazio Sicuro & App*) è un'applicazione web progressiva (PWA) e mobile creata con amore come **dono personale e spazio intimo di supporto emotivo**.
L'applicazione è progettata per essere un *"porto sicuro digitale"* (safe space) a cui la persona destinataria può accedere ogni volta che si sente giù, stanca, sopraffatta dall'ansia, in difficoltà fisica o semplicemente desiderosa di una carezza virtuale.

### 1.2 Destinatario e Contesto d'Uso
* **A chi è rivolta:** È rivolta specificamente alla fidanzata dell'autore (chiamata affettuosamente nei testi *"piccola mia"*, *"cucciola"*, *"stellina"*).
* **Chi parla nell'app:** La voce narrante e autrice è il fidanzato (*"il tuo ragazzo"*, *"chi ti vuole un bene infinito"*), affiancato da due mascotte guida:
  * **Pisolo lo squaletto (🦈):** Uno squalo peluche azzurro, morbido e protettivo, che simboleggia la forza silenziosa, la sicurezza e la difesa da ogni tempesta (*"ti faccio da scudo"*, *"nuotiamo piano insieme"*).
  * **Micio il gattino (🐱):** Un cucciolo di gatto tenero, curioso e sonnolento, che simboleggia le coccole calde, la tenerezza, il riposo e la dolcezza incondizionata (*"fusa morbidissime per sciogliere la stanchezza"*).
* **Situazioni d'uso primarie:**
  1. **Tristezza e malinconia:** Momenti di sconforto in cui mancano le energie e tutto sembra pesante.
  2. **Ansia, stress e sovraccarico mentale:** Situazioni con fiato corto, tachicardia o pensieri che si accavallano caoticamente.
  3. **Fastidi o dolori fisici:** Stanchezza corporea, emicrania, dolori mestruali, spossatezza e bisogno di rallentare senza sensi di colpa.
  4. **Momenti di lontananza:** Situazioni in cui i due partner sono distanti fisicamente o temporaneamente non possono parlare a voce.
  5. **Inizio e fine giornata:** Un pensiero dolce al risveglio (*"Buongiorno"*) o una carezza rassicurante prima di addormentarsi (*"Buonanotte"*).

### 1.3 Il Ruolo Chiave del Ragazzo e il Rapporto con l'App
L'app **non vuole sostituire la vicinanza reale del fidanzato**, ma farle da ponte:
* All'apertura dell'applicazione, compare un **promemoria d'amore caloroso** che ricorda alla ragazza che il desiderio più grande del partner è **sentirla direttamente**.
* È presente un pulsante prioritario che apre direttamente una chat WhatsApp con il fidanzato preimpostando un messaggio dolce (*"Ciao amore mio ❤️ Ho aperto l'app e volevo dirti che ho bisogno di te..."*).
* Solo se i due sono impossibilitati a sentirsi, l'app interviene per donarle conforto continuo.

### 1.4 Tono di Voce (Tone of Voice)
* **Profondamente caldo, empatico e affettuoso:** Linguaggio intimo, protettivo e rassicurante, colmo di amore e tenerezza sincera.
* **Assolutamente NON clinico:** Non vengono mai usati termini medici, psichiatrici, diagnostici o impersonali (niente formule asettiche da manuale di psicologia o da chatbot generico).
* **Nessuna pressione o imperativo:** La filosofia costante è *"Prenditi tutto il tempo che ti serve"*, *"Oggi non devi dimostrare niente a nessuno"*, *"Fai un passo per volta"*. Non c'è alcuna richiesta di produttività né giudizio.
* **Accoglienza incondizionata:** Qualsiasi emozione, compresa la vulnerabilità o la stanchezza, viene validata come legittima e degna di cura.

---

## 2. Design System

### 2.1 Palette Colori Esatta
Il design system si basa su una combinazione di toni pastello soffusi ad alto contrasto su superfici bianche traslucide (vetro satinato / glassmorphism), progettato per trasmettere calma, serenità visiva e non affaticare la vista.

| Variabile CSS | Codice Hex | Ruolo e Semantica | Note di Design |
| :--- | :--- | :--- | :--- |
| `--bg-cream` | `#FFF5F7` | **Sfondo Primario** (crema rosato chiarissimo) | Tonalità morbida, mai bianco abbagliante |
| `--surface-white` | `#FFFFFF` | **Superficie Carte** | Sfondo contenitori e modali al 95% opacity |
| `--surface-subtle` | `#FFF9FA` | **Superficie Secondaria** | Sfondo chip inattive, campi e box note |
| `--deep-rose` | `#D81B60` | **Colore Dominante Primario** | Titoli, accenti di testo, pulsanti principali |
| `--pastel-pink` | `#F06292` | **Rosa Pastello Accento** | Cuori, bottoni secondari, icone d'amore |
| `--pastel-pink-light` | `#F8BBD0` | Rosa Chiaro Delicato | Bordi schede, sfondi pillole attive |
| `--pastel-pink-container` | `#FCE4EC` | Contenitore Rosa | Sfondo banner, step respirazione 4 |
| `--pastel-pink-on-container`| `#880E4F` | Testo su Contenitore Rosa | Testo scuro su chip rosa |
| `--pastel-blue-accent` | `#0288D1` | **Accento Pisolo (Oceano)** | Dettagli dello squaletto, respirazione |
| `--pastel-blue-light` | `#BBDEFB` | Blu Pastello Morbido | Fase 2 respirazione (trattieni) |
| `--pastel-blue-container`| `#E1F5FE` | Contenitore Celeste | Fase 3 respirazione (espira) |
| `--pastel-blue-on-container`| `#01579B` | Testo su Contenitore Blu | Testi a contrasto su bottoni celesti |
| `--warm-text-primary` | `#0F172A` | **Testo Primario (Slate Dark)** | Massima leggibilità per frasi e titoli |
| `--warm-text-secondary` | `#334155` | Testo Secondario (Slate 700) | Sottotitoli, descrizioni, microcopy |
| `--warm-text-muted` | `#64748B` | Testo Smorzato (Slate 500) | Note legali, copyright, placeholder |
| `--warm-outline` | `rgba(248, 187, 208, 0.65)` | Bordo Delicato | Bordature arrotondate cards e campi |
| `--heart-red` | `#E91E63` | Rosso Cuore | Badge abbraccio, animazioni pulsanti |
| `--accent-gold` | `#F59E0B` | Oro Caldo | Stelle, premi, banner celebrativi |
| *(AI Theme)* | `#7E22CE` / `#F3E8FF` | Viola / Lavanda IA | Pannello e chip di generazione Gemini IA |
| *(Prep Step)* | `#FFE0B2` / `#E65100` | Ambra Caldo | Conto alla rovescia (5s) respirazione |

### 2.2 Tipografia
* **Font Primario:** `'Plus Jakarta Sans'` (Google Fonts), caricato nei pesi:
  * `400` (Regular) - Testo descrittivo lungo e corpo modali.
  * `500` (Medium) - Sottotitoli e microcopy.
  * `600` (Semi-Bold) - Testi dei consigli, bottoni, citazioni delicate.
  * `700` (Bold) - Titoli di sezione, pillole filtro attive, badge.
  * `800` (Extra-Bold / Black) - Titolo dell'app (`H1`), titoli delle modali, enfasi primarie.
  * `400 Italic` & `500 Italic` - Citazioni di supporto e firme affettuose.
  * *Font Fallback:* `-apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif`.
* **Font Secondario (Monospace):** Font a spaziatura fissa per l'input della chiave API privata (`font-family: monospace`).

### 2.3 Raggi di Curvatura e Ombreggiature
* **Bordi Arrotondati:**
  * `--radius-sm: 14px` (pillole, bottoni piccoli, input password).
  * `--radius-md: 20px` (bottoni primari, card interne, contenitori foto).
  * `--radius-lg: 28px` (schede principali della pagina, modali).
* **Ombre:**
  * `--shadow-sm: 0 2px 8px rgba(15, 23, 42, 0.06)`
  * `--shadow-md: 0 6px 20px rgba(15, 23, 42, 0.09)`
  * `--shadow-lg: 0 16px 36px rgba(15, 23, 42, 0.12)`

### 2.4 Animazioni e Transizioni (Micro-Interactions)
1. **`ambientFloat` (6s - 7s, ease-in-out, alternata infinita):**
   * Applicata alle mascotte fisse decorative di sfondo (Pisolo in alto a destra, Micio in basso a sinistra).
   * Movimento combinato di traslazione verticale (`-12px`) e rotazione delicata (`da -5deg a +8deg`).
2. **`pulseSoft` (2s, ease-in-out, alternata infinita):**
   * Applicata ai cuoricini pulsanti (tra cui quello della schermata di login tra i due avatar).
   * Scala da `1.0` a `1.22`.
3. **`shakeAnim` (0.35s, ease-in-out):**
   * Scuotimento orizzontale (`+-6px`) dell'input password in caso di codice errato, accompagnato da bordo rosso.
4. **`fadeIn` & `scaleUp` (0.2s - 0.3s):**
   * Apertura morbida delle modali e caricamento schede.
5. **`bounceInCongrats` & `celebrateMascotBounce` (1.2s):**
   * Entrata rimbalzante della modale di congratulazioni e salto festoso delle mascotte al completamento dei 5 gesti di cura.
6. **`floating-bubble` (2.5s, transizione verso l'alto con `opacity: 0`):**
   * Bollicine generate dinamicamente al rilascio dello sfogo nel mare (`🫧`, `🌊`, `✨`, `☁️`).
7. **Animazione Canvas Coriandoli (Confetti):**
   * Particelle generate su `#confetti-canvas` al raggiungimento di 5/5 gesti completati: cuoricini e coriandoli colorati che cadono con fisica reale di gravità e oscillazione orizzontale.

### 2.5 Elementi Decorativi a Tema Squaletto / Gattino (Kawaii)
* **Iconografia Emozionale Integrata:** Emoji universali accoppiate alle mascotte (🦈 per Pisolo, 🐱 per Micio, ❤️/💕/🌸 per l'amore).
* **Mascotte Fluttuanti Ambientali:** Riquadri trasparenti ad alta opacità (18%) posizionati sui bordi schermo esterni che fluttuano dolcemente senza interferire con i contenuti.
* **Badge e Switcher di Presenza:**
  * Header con switcher dinamico: badge interattivo con miniatura e dicitura *"Pisolo 🦈"* oppure *"Micio 🐱"*.
  * Nuvoletta di pensiero (speech bubble) che attribuisce la frase corrente alla mascotte attiva.
* **Ritratti Fotografici Alternati (8 Preset Fissi):**
  * 4 foto dedicate a Pisolo (squaletto di peluche in ambientazioni dolci: sul letto, cuscino piumoso, pronto a proteggere).
  * 4 foto dedicate a Micio (gattino morbidissimo acciambellato, con occhi curiosi, o abbracciato a Pisolo).
  * Possibilità per l'utente di caricare foto personalizzate per ciascuna delle due mascotte (salvate in base64 nel `localStorage`).

### 2.6 Layout Generale e Comportamento Responsive Mobile
* **Architettura a Colonna Centrale:** Larghezza massima fissata a `540px` (`margin: 0 auto`), ottimizzata per display smartphone, perfetta sia come applicazione a schermo intero sia all'interno di browser desktop (dove appare centrata come un'elegante app mobile).
* **Doppio Strato Ambientale Immersivo:**
  * Strato 0 (`#app-ambient-bg`): Immagine paesaggistica ad alta risoluzione (Spiaggia, Giardino Zen, Colline, ecc.) a schermo intero fisso.
  * Strato 1 (`#app-ambient-overlay`): Velatura bianca a gradiente semitrasparente (22% - 35%) che garantisce il 100% di contrasto e leggibilità per qualsiasi testo.
* **Bottom Navigation Bar Fissa:** Barra inferiore fissa con sfumatura glassmorphism, icone intuitive e supporto alle Safe Area degli schermi moderni con notch (iOS Home Bar / Android Navigation).
* **Specifiche PWA:**
  * `viewport-fit=cover`
  * `apple-mobile-web-app-capable: yes`
  * `apple-mobile-web-app-status-bar-style: black-translucent`
  * Orientamento bloccato a `portrait-primary` nel manifest.

---

## 3. Struttura e Funzionalità per Sezione

### 3.1 Schermata di Login / Accesso Privato
* **Scopo:** Creare un senso di intimità, privacy e "spazio segreto", impedendo a chiunque prenda in mano il telefono di visualizzare immediatamente i messaggi intimi e il diario.
* **Comportamento e Logica:**
  * Il tag `<body>` nasce con la classe `locked`. Quando presente, l'intero container dell'app e la barra di navigazione sono nascosti (`display: none !important`).
  * Prima ancora che avvenga il rendering completo, uno script verifica se in `localStorage` è presente `pisolo_pwa_auth === 'true'`. Se sì, rimuove subito la classe `locked`.
  * Se non autenticata, l'overlay `#login-screen` mostra gli avatar di Pisolo e Micio uniti da un cuore pulsante, il titolo "PISOLO & MICIO" e la richiesta della password.
  * **Verifica Crittografica:** La password inserita dall'utente viene hashata con SHA-256 tramite le API native del browser (`crypto.subtle.digest`) e confrontata con l'hash di sicurezza `APP_PASSWORD_HASH` (`"1bcaf6f4231908e5ef1a7495110d25279ea443479894e65d366646a9c6d49566"`).
  * Se corretta, imposta `localStorage.setItem('pisolo_pwa_auth', 'true')` e sblocca l'app con animazione toast.
  * Se errata, attiva la classe `error-shake` sull'input per 0.4s e mostra il messaggio: *"Password non corretta. Riprova con calma 🌸"*.
  * Funzione di logout presente nel footer (`logoutApp()`) per ribloccare l'app su richiesta.

### 3.2 Modale di Benvenuto e Contatto Diretto con il Ragazzo
* **Scopo:** Evitare l'isolamento emotivo della ragazza, incoraggiandola a parlare direttamente con il partner.
* **Elementi:**
  * Card con le foto circolari di Pisolo e Micio collegate da un cuore.
  * Messaggio caloroso in cui il ragazzo ribadisce che la cosa più desiderata è sentirla a voce o via messaggio.
  * **Pulsante WhatsApp Diretto (`openPartnerChat()`):**
    * Legge il numero memorizzato in `localStorage` (`partner_phone_num`).
    * Se assente, mostra un prompt nativo per impostare il numero internazionale (es. `+393401234567`).
    * Genera un link WhatsApp universale: `https://wa.me/{numero}?text={testo_precompilato}`.
    * Pulsante ingranaggio (`⚙️`) accanto al bottone WhatsApp per aggiornare il numero in qualsiasi momento (`editPartnerPhone()`).
  * **Ribbon Promemoria in Home (`.partner-welcome-ribbon`):** Bannerino sempre visibile sotto l'header con il testo *"Se hai un peso sul cuore, scrivimi sempre 💕"*; toccandolo riapre la modale di benvenuto.

### 3.3 Header Principale e Nuvoletta dei Pensieri
* **Scopo:** Identità visiva e accoglienza immediata all'avvio.
* **Elementi:**
  * Titolo principale `PISOLO & MICIO ❤️` e sottotitolo *"Il tuo porto sicuro e silenzioso con Pisolo & Micio"*.
  * Switcher avatar mascotte: tocca per alternare visivamente tra Pisolo e Micio.
  * **Nuvoletta di pensiero interattiva (`.mascot-speech-container`):**
    * Mostra a rotazione un pensiero breve e delicato accompagnato dall'emoji della mascotte.
    * Toccando la nuvoletta si attiva `nextDelicateQuote()`, che estrae la frase successiva con indicatore di refresh rotante.
    * Contiene 10 pensieri suddivisi per mascotte (5 squaletto e 5 gattino).

### 3.4 Finestra sul Luogo di Pace (10 Paesaggi Naturali a Tutto Schermo)
* **Scopo:** Favorire il rilassamento psicofisico attraverso la visualizzazione di paesaggi naturali sereni (spiagge, boschi, laghi alpini, giardini zen).
* **Funzionamento Tecnico:**
  * All'avvio dell'app, `applyAccessTranquilBackground()` seleziona a rotazione uno dei 10 sfondi disponibili salvando l'indice in `localStorage.pisolo_bg_index`.
  * Lo sfondo viene applicato a tutto schermo sull'elemento `#app-ambient-bg` e proiettato contemporaneamente nella card "Il tuo Luogo di Pace oggi".
  * Pulsante *"🔄 Cambia"*: passa ciclicamente al paesaggio successivo.
  * Pulsante *"🏖️ Tutti i 10 Luoghi"*: apre la modale di selezione rapida a griglia (`openBackgroundPickerModal()`), dove ogni paesaggio mostra nome, emoji, descrizione e miniatura cliccabile.
  * Ciascun paesaggio dispone di un attributo cromatico coerente (`themeColor`) che si armonizza con i riflessi della pagina.

### 3.5 Esercizio di Respirazione Guidata (Tecnica 4-4-4 con Pausa)
* **Scopo:** Calmare il sistema nervoso simpatico durante momenti di ansia acuta o tachicardia attraverso il respiro ritmico guidato.
* **Componenti e Logica:**
  * Accessibile dalla card in primo piano in Home o dalla modale dedicata (`openBreathingModal()`).
  * **Fase di Preparazione (5 Secondi):** Prima di avviare il ciclo, parte un countdown di 5 secondi con badge arancione *"⏳ Inizio in 5s"*, icona `🧘‍♀️` e sfondo ambrato, per dare il tempo alla ragazza di mettersi comoda.
  * **Ciclo a 4 Fasi (`BREATH_CYCLE`):**
    1. *Inspira lentamente...* (4s, colore `#F8BBD0`, scala cerchio `1.35x`, icona 🌸) - Riempire i polmoni con calma dal naso.
    2. *Trattieni...* (4s, colore `#BBDEFB`, scala cerchio `1.35x`, icona 🫧) - Trattenere dolcemente senza sforzo.
    3. *Espira con dolcezza...* (4s, colore `#E1F5FE`, scala cerchio `0.88x`, icona 🍃) - Espirare tutta l'aria dalla bocca rilasciando le spalle.
    4. *Pausa di riposo...* (2s, colore `#FCE4EC`, scala cerchio `0.88x`, icona ☁️) - Breve pausa di silenzio e stabilità.
  * **Animazione Dinamica:** Il cerchio centrale si espande e si restringe tramite CSS transform scale e transizione fluida, con timer numerico al secondo visibile al centro.
  * **Controlli:** Pulsante Pausa / Riprendi (`toggleBreathingRun()`) e pulsante di chiusura *"Sto meglio 💕"*.

### 3.6 Messaggio del Giorno e Modale Abbraccio Virtuale
* **Scopo:** Offrire parole profonde, d'amore e vicinanza, associate a un ritratto tenero delle mascotte.
* **Algoritmo Shuffle Bag (`class SupportMessageShuffleBag`):**
  * Per evitare che la ragazza legga sempre le stesse frasi o che un messaggio si ripeta a breve distanza, l'app usa l'algoritmo **Shuffle Bag (Fisher-Yates)**.
  * All'avvio, l'intero pool di 32 messaggi viene rimescolato casualmente.
  * I messaggi vengono estratti uno per uno. Quando il mazzo è esaurito, viene rimescolato automaticamente, garantendo però che il primo messaggio del nuovo mazzo **non sia identico** all'ultimo estratto dal mazzo precedente.
  * Supporta sia l'estrazione globale sia l'estrazione filtrata per categoria (`tristezza`, `ansia`, `fisico`, `generale`).
* **Card Messaggio in Home:**
  * Mostra la foto del giorno, la citazione con virgolette eleganti, la firma e il badge speciale se generata con IA.
  * Pulsante *"🔄 Altro pensiero"*: estrae un nuovo messaggio dal mazzo e sincronizza la foto.
  * Pulsante *"🦈/🐱 Alterna foto"*: cambia solo l'immagine tra gli 8 ritratti delle mascotte.
* **Modale Abbraccio Virtuale (`openHugModal()`) & Filtri per Situazione:**
  * Si apre toccando il grande bottone *"Ho bisogno di un abbraccio 🫂❤️"*.
  * Presenta 4 filtri rapidi in pillole:
    * `🫂 Tutti` (pool completo).
    * `🌧️ Tristezza` (per momenti di pianto, solitudine, abbattimento).
    * `⚡ Ansia` (per attacchi d'ansia, paura, agitazione interiore).
    * `🩹 Fastidio` (per malessere fisico, stanchezza, dolore corporeo).
  * Mostra foto grande, titolo affettuoso, corpo del messaggio e firma personalizzata.
  * Pulsante *"Grazie ❤️"* (chiude la modale con toast) e pulsante *"🔄 Altro abbraccio"*.

### 3.7 Generatore Infinito di Dediche e Consigli con Intelligenza Artificiale (Gemini)
* **Scopo:** Garantire che le parole di conforto non si esauriscano mai, permettendo all'app di generare dediche sempre nuove e personalizzate.
* **Integrazione Architetturale a Due Livelli:**
  1. **Livello Client-Side Diretto (`callGeminiDirect`):**
     * Il browser contatta direttamente le API Google Gemini (`generativelanguage.googleapis.com`) sfruttando il supporto CORS nativo di Google.
     * Utilizza la chiave API privata inserita dall'utente e memorizzata solo nel suo `localStorage`.
     * Tenta a cascata i modelli moderni: `gemini-2.5-flash`, `gemini-flash-latest`, `gemini-3.5-flash`, `gemini-3.1-flash-lite-preview`, `gemini-3.1-pro-preview`, `gemini-2.5-pro`.
     * Se tutti falliscono, interroga l'endpoint `ListModels` della chiave per individuare dinamicamente i modelli supportati.
  2. **Livello Server-Side di Backup (`serve.js`):**
     * Se la chiamata diretta dal browser fallisce o se non è impostata una chiave utente ma il server dispone di `process.env.GEMINI_API_KEY`, la richiesta viene inoltrata all'endpoint locale `/api/ai/generate`.
     * Se la rete o le API sono offline, restituisce istantaneamente una frase dal pool curato di riserva (`FALLBACK_LOVE` o `FALLBACK_TIPS`).
* **Selettore Atmosfera Dedica (6 Mood Chips):**
  * 🌸 **Dolce:** Tenerezza, coccole e romanticismo.
  * 🫂 **Conforto:** Per momenti di tristezza, pianto o ansia.
  * 🌟 **Forza:** Incoraggiamento, autostima e motivazione.
  * 🌙 **Buonanotte:** Serenità serale e sogni d'oro.
  * ☀️ **Buongiorno:** Risveglio positivo con il sorriso.
  * 🦈 **Simpatica:** Una coccola buffa e giocosa da Pisolo e Micio.
* **Modale Gestione Chiave API (`openApiKeyModal`):**
  * Campo input protetto con toggle visibilità (👁️).
  * Bottone per testare in tempo reale la validità della chiave su Google AI Studio (`testUserApiKey`).
  * Salvataggio o rimozione istantanea dal dispositivo.
  * Link diretto alla pagina ufficiale per ottenere una chiave gratuita su Google AI Studio.

### 3.8 Piccoli Gesti per Te (Consigli di Cura Personale e Tracciamento)
* **Scopo:** Guidare la ragazza verso micro-azioni concrete di auto-cura fisica e mentale, senza caricarla di compiti gravosi.
* **Meccanica dei 5 Gesti del Giorno:**
  * Dal pool complessivo di **42 consigli**, l'app seleziona ed espone **5 gesti casuali** ad ogni nuovo accesso o toccando il pulsante *"🔄 Nuovi 5 gesti"*.
  * Pulsante *"📋 Tutti (42)"*: permette di visualizzare l'intero archivio organizzato per categorie.
* **Filtri Categoria:**
  * `✨ Tutti`
  * `💤 Riposo` (sonno, rallentare, posture comode)
  * `☕ Coccole` (bevande calde, coperte, dolcezze)
  * `🕊️ Mente` (pensieri, scrittura, distacco da ansie)
  * `🍃 Sensi & Luce` (natura, luce solare, profumi, stretching)
* **Tracciamento Progressivo e Persistenza:**
  * Ciascun consiglio ha una checkbox interattiva. Quando spuntata, la card assume uno stato di completamento visivo (opacità attenuata, linea di barratura morbida).
  * Gli ID dei gesti completati vengono memorizzati in `localStorage.pisolo_checked_tips`.
  * Una barra di avanzamento dinamica (`#tips-progress-bar-fill`) mostra in tempo reale la percentuale completata (`"✨ Gesti completati: 0 su 5"` fino a `"5 su 5"`).
* **Modale di Celebrazione Finale (Completamento 5/5):**
  * Al completamento di tutti i 5 gesti estratti, scatta automaticamente la modale di trionfo affettuoso (`#modal-tips-celebration-backdrop`).
  * Animazione su canvas con pioggia di cuori e coriandoli colorati.
  * Mascotte festose con animazione di salto e badge *"✨ 5 Gesti Completati con Successo! 🎉"*.
  * Messaggio di congratulazioni intimo: *"Bravissima, piccola mia! ❤️ Prendersi cura di sé è il regalo più bello che puoi farti. Pisolo 🦈, Micio 🐱 e il tuo ragazzo sono fieri di te!"*.
  * Opzione per ringraziare o estrarre altri 5 nuovi gesti per dopo.

### 3.9 Spazio per i Tuoi Pensieri (Diario di Sfogo Privato)
* **Scopo:** Permettere alla ragazza di buttare fuori rabbia, paura, pensieri ossessivi o dolore senza alcun timore di essere giudicata o che qualcuno possa mai leggerli.
* **Principio Cardine di Privacy:**
  * **IL TESTO NON VIENE MAI SALVATO.** Non esiste salvataggio in `localStorage`, né in sessione, né cookie, né invio a server.
  * Un avviso con icona lucchetto (`🔒`) rassicura costantemente: *"Questo testo resta solo sul tuo schermo"*.
* **Funzionalità:**
  * Textarea ampia e confortevole con placeholder accogliente.
  * Il pulsante *"CANCELLA"* in alto a destra compare solo se c'è testo e consente la pulizia istantanea.
  * **Azione Rituale "Lascia Andare nel Mare" (`releaseThoughtsToSea()`):**
    * Toccando il bottone azzurro *"🌊 Lascia andare nel mare"*, l'area di testo svanisce in dissolvenza (`opacity: 0`).
    * Vengono generate a schermo **16 bolle fluttuanti** contenenti icone marine (`🫧`, `🌊`, `✨`, `☁️`) che salgono verso l'alto svanendo nel cielo.
    * Il testo viene cancellato irreversibilmente dalla memoria DOM.
    * Compare un messaggio rassicurante a comparsa per 6 secondi: *"✨ I pensieri che pesavano sono scivolati via tra le onde con Pisolo e Micio. Adesso respira con calma: sei al sicuro. 🫧"*.

### 3.10 Barra di Navigazione Inferiore (Bottom Tabs)
* Barra a 3 tab fluide con navigazione scroll verso le relative sezioni:
  1. `🏠 Home`: riporta dolcemente alla parte superiore (Header, Luogo di pace, Messaggio del giorno).
  2. `🌿 Consigli`: scivola alla sezione dei 5 piccoli gesti per te e progress bar.
  3. `✍️ Diario`: scivola allo spazio di sfogo e rilascio pensieri.
* Lo scorrimento è gestito via JavaScript con `scrollIntoView({ behavior: 'smooth' })`.

---

## 4. Contenuti Reali Integrali dell'Applicazione

Questa sezione documenta **parola per parola** ogni singolo testo attualmente presente nel codice sorgente dell'applicazione, senza sintesi o omissioni.

### 4.1 I 32 Messaggi di Supporto Integrali (`SUPPORT_MESSAGES`)

#### Messaggio 1 (ID: 1)
* **Categoria:** `tristezza`
* **Tag:** 🫂 Vicinanza sincera
* **Titolo:** Non sei mai sola
* **Testo:** *"So che ci sono momenti in cui tutto sembra pesare un po' di più e trovare le energie è faticoso. Ma tu non sei sola: io sono qui, silenziosamente al tuo fianco a sostenerti."*
* **Firma:** — Chi ti vuole un bene infinito

#### Messaggio 2 (ID: 2)
* **Categoria:** `tristezza`
* **Tag:** 🌧️ Spazio per te
* **Titolo:** Va bene piangere
* **Testo:** *"Non devi essere sempre forte, non con me. Se oggi hai solo voglia di piangere o startene rannicchiata sotto il piumone, fallo. Il mondo può aspettare fuori dalla porta."*
* **Firma:** — Sempre con te

#### Messaggio 3 (ID: 3)
* **Categoria:** `tristezza`
* **Tag:** ☁️ Nessuna fretta
* **Titolo:** Passerà anche questa
* **Testo:** *"Le giornate no arrivano senza chiedere il permesso, ma ricordati che passano anche. Non devi risolvere tutto oggi: un minuto alla volta, respirando piano."*
* **Firma:** — Il tuo porto sicuro

#### Messaggio 4 (ID: 4)
* **Categoria:** `tristezza`
* **Tag:** 🌟 La tua luce
* **Titolo:** Ricordati chi sei
* **Testo:** *"Anche quando ti sembra di non farcela, io vedo ogni giorno quanto vali, quanto cuore metti in tutto e che persona splendida sei. Non dimenticarlo mai."*
* **Firma:** — Chi crede sempre in te

#### Messaggio 5 (ID: 5)
* **Categoria:** `tristezza`
* **Tag:** 🛏️ Rifugio sicuro
* **Titolo:** Riposa la mente
* **Testo:** *"Chiudi gli occhi per qualche istante e immagina di appoggiare la testa sul mio petto. Senti il battito calmo e regolare: lì non c'è niente che possa farti del male."*
* **Firma:** — Un abbraccio stretto stretto

#### Messaggio 6 (ID: 6)
* **Categoria:** `tristezza`
* **Tag:** 🩹 Nessun giudizio
* **Titolo:** Sii gentile con te stessa
* **Testo:** *"Non arrabbiarti con te stessa se oggi non riesci a fare tutto. Quello che hai fatto è già abbastanza. Meriti dolcezza, soprattutto da te stessa."*
* **Firma:** — Con infinita tenerezza

#### Messaggio 7 (ID: 7)
* **Categoria:** `tristezza`
* **Tag:** 🕯️ Domani è un altro giorno
* **Titolo:** Una piccola luce
* **Testo:** *"Anche nella notte più buia, le stelle non smettono di brillare. Questa giornata difficile sta finendo, e domani avrai una nuova pagina tutta da scrivere, con calma."*
* **Firma:** — La tua luce nel buio

#### Messaggio 8 (ID: 8)
* **Categoria:** `tristezza`
* **Tag:** 🕊️ Pace interiore
* **Titolo:** Lascia andare il peso
* **Testo:** *"Non sei obbligata a portare sulle spalle il peso di tutto il mondo. Posalo per un attimo qui, accanto a me. Ci penso io a custodirti mentre ti riposi."*
* **Firma:** — Chi ti protegge sempre

#### Messaggio 9 (ID: 9)
* **Categoria:** `ansia`
* **Tag:** 🫁 Respira con me
* **Titolo:** Fai un respiro lento
* **Testo:** *"Sento che la mente corre veloce e il petto è stretto. Fermati un secondo. Inspira contando fino a quattro... trattieni... ed espira piano. Sei al sicuro, qui con me."*
* **Firma:** — Il tuo ancoraggio calmo

#### Messaggio 10 (ID: 10)
* **Categoria:** `ansia`
* **Tag:** 🫧 Solo questo istante
* **Titolo:** Un passo alla volta
* **Testo:** *"L'ansia ti fa guardare a cento cose che potrebbero accadere. Riporta lo sguardo a questo preciso momento: sei qui, sei viva, sei amata. Nient'altro conta adesso."*
* **Firma:** — Passo dopo passo insieme

#### Messaggio 11 (ID: 11)
* **Categoria:** `ansia`
* **Tag:** 🛡️ Sei al sicuro
* **Titolo:** Questa tempesta passerà
* **Testo:** *"Quello che provi adesso sembra gigantesco e ingestibile, lo so. Ma è solo un'onda: salirà, raggiungerà la cima e poi si ritirerà. Tu resta aggrappata a me."*
* **Firma:** — Il tuo scudo contro la tempesta

#### Messaggio 12 (ID: 12)
* **Categoria:** `ansia`
* **Tag:** 🦶 Piedi per terra
* **Titolo:** Guarda intorno a te
* **Testo:** *"Tocca qualcosa di morbido vicino a te, ascolta i suoni della stanza, bevi un sorso d'acqua. Riporta il corpo nel presente. Io sono qui con te in ogni singolo istante."*
* **Firma:** — La tua presenza costante

#### Messaggio 13 (ID: 13)
* **Categoria:** `ansia`
* **Tag:** ⏳ Niente fretta
* **Titolo:** Non devi decidere adesso
* **Testo:** *"Se senti la pressione di dover capire, scegliere, risolvere: fermati. Non c'è nessuna fretta. Le cose importanti possono aspettare che tu ti senta più serena."*
* **Firma:** — Chi rispetta i tuoi tempi

#### Messaggio 14 (ID: 14)
* **Categoria:** `ansia`
* **Tag:** 💖 Sei capace
* **Titolo:** Ce l'hai sempre fatta
* **Testo:** *"Ricorda tutte le volte che hai pensato 'non ce la farò mai', eppure sei qui, più forte di prima. Hai risorse incredibili dentro di te, anche quando non le vedi."*
* **Firma:** — Il tuo primo sostenitore

#### Messaggio 15 (ID: 15)
* **Categoria:** `ansia`
* **Tag:** 🍃 Spalle giù
* **Titolo:** Rilassa il corpo
* **Testo:** *"Fai caso alle tue spalle: sono tese e alzate? Abbassale dolcemente. Allenta la mandibola. Lascia che la pancia si distenda. Il tuo corpo può riposare adesso."*
* **Firma:** — Chi si prende cura di te

#### Messaggio 16 (ID: 16)
* **Categoria:** `ansia`
* **Tag:** 🧶 Pensieri come nuvole
* **Titolo:** I pensieri non sono fatti
* **Testo:** *"La tua mente sta creando scenari spaventosi, ma un pensiero è solo un pensiero, non è la realtà. Lasciali scorrere come nuvole nel cielo, senza trattenerli."*
* **Firma:** — Il tuo cielo sereno

#### Messaggio 17 (ID: 17)
* **Categoria:** `fisico`
* **Tag:** 🫖 Calore e dolcezza
* **Titolo:** Una coccola calda
* **Testo:** *"Mi dispiace tanto che tu stia male fisicamente. Vorrei essere lì a prepararti una borsa dell'acqua calda, una tisana e rimboccarti le coperte. Chiudi gli occhi e riposa."*
* **Firma:** — La tua cura più dolce

#### Messaggio 18 (ID: 18)
* **Categoria:** `fisico`
* **Tag:** 🛌 Ascolta il corpo
* **Titolo:** Il corpo chiede tregua
* **Testo:** *"Quando il corpo dice stop, bisogna ascoltarlo senza sensi di colpa. Stenditi comoda, cerca la posizione più dolce e lascia che il tempo faccia il suo lavoro."*
* **Firma:** — Chi veglia su di te

#### Messaggio 19 (ID: 19)
* **Categoria:** `fisico`
* **Tag:** 💆 Carezza lieve
* **Titolo:** Una carezza sulla fronte
* **Testo:** *"Immagina le mie dita che ti scostano dolcemente i capelli dalla fronte e una carezza leggera sulle tempie per sciogliere il mal di testa e le tensioni. Andrà tutto bene."*
* **Firma:** — Le mie mani per te

#### Messaggio 20 (ID: 20)
* **Categoria:** `fisico`
* **Tag:** 🍵 Piano piano
* **Titolo:** Piccole attenzioni
* **Testo:** *"Bevi un sorso d'acqua o di qualcosa di caldo, mettiti la felpa più comoda che hai e non fare sforzi. Oggi la tua unica priorità è stare meglio, niente altro."*
* **Firma:** — Il tuo infermiere personale

#### Messaggio 21 (ID: 21)
* **Categoria:** `fisico`
* **Tag:** 💤 Sonno ristoratore
* **Titolo:** Dormici su
* **Testo:** *"Il sonno è la medicina più potente e naturale. Se riesci, chiudi le tende, spegni le notifiche e regalati un pisolino senza sveglia. Io sono qui a fare la guardia."*
* **Firma:** — Buona nanna, piccola mia

#### Messaggio 22 (ID: 22)
* **Categoria:** `fisico`
* **Tag:** 🧸 Morbidezza totale
* **Titolo:** Avvolta nella tenerezza
* **Testo:** *"Stringi forte il cuscino o un peluche: immagina che sia il mio abbraccio che ti tiene al caldo e ti protegge dal dolore finché non si attenua del tutto."*
* **Firma:** — Sempre stretto a te

#### Messaggio 23 (ID: 23)
* **Categoria:** `fisico`
* **Tag:** 🌺 Pazienza col corpo
* **Titolo:** Dagli tempo
* **Testo:** *"La guarigione e il recupero non sono istantanei, richiedono pazienza e dolcezza. Non avere fretta di stare subito al cento per cento. Fai con calma."*
* **Firma:** — Con tutta la pazienza del mondo

#### Messaggio 24 (ID: 24)
* **Categoria:** `fisico`
* **Tag:** 🫧 Respiro sollievo
* **Titolo:** Espira il fastidio
* **Testo:** *"A ogni respiro che butti fuori, immagina di espellere un pochino della tensione e del dolore che senti. Piano piano, respiro dopo respiro, il corpo si distende."*
* **Firma:** — Accanto a te in ogni respiro

#### Messaggio 25 (ID: 25)
* **Categoria:** `generale`
* **Tag:** 💕 Amore puro
* **Titolo:** Ti amo immensamente
* **Testo:** *"Qualunque cosa stia succedendo nella tua giornata, volevo solo ricordarti la cosa più importante: ti amo con tutto me stesso, esattamente per come sei."*
* **Firma:** — Il tuo amore più grande

#### Messaggio 26 (ID: 26)
* **Categoria:** `generale`
* **Tag:** ☀️ Un raggio di sole
* **Titolo:** Sei il mio regalo
* **Testo:** *"Averti nella mia vita rende ogni cosa più bella e piena di senso. Anche nelle giornate grigie, pensare al tuo sorriso accende una luce calda dentro di me."*
* **Firma:** — Chi ti ringrazia ogni giorno

#### Messaggio 27 (ID: 27)
* **Categoria:** `generale`
* **Tag:** 🤝 Insieme sempre
* **Titolo:** Una squadra imbattibile
* **Testo:** *"Non dimenticare mai che noi due siamo una squadra. Qualsiasi difficoltà, dubbio o paura si affronti, non sarai mai sola a combatterla: ci sarò sempre io con te."*
* **Firma:** — Il tuo compagno di viaggio

#### Messaggio 28 (ID: 28)
* **Categoria:** `generale`
* **Tag:** 🎁 Sei speciale
* **Titolo:** La persona più bella
* **Testo:** *"Hai una sensibilità rara, un cuore grande e una dolcezza che incanta. Non permettere a una brutta giornata di farti dubitare della persona meravigliosa che sei."*
* **Firma:** — Il tuo più grande ammiratore

#### Messaggio 29 (ID: 29)
* **Categoria:** `generale`
* **Tag:** 🏠 Casa mia
* **Titolo:** Il mio posto preferito
* **Testo:** *"Il mio posto preferito nel mondo è dovunque ci sia tu. Non c'è rifugio più dolce, più sicuro e più bello delle tue braccia."*
* **Firma:** — Casa mia sei tu

#### Messaggio 30 (ID: 30)
* **Categoria:** `generale`
* **Tag:** 🌸 Piccole cose
* **Titolo:** Trova una briciola di bellezza
* **Testo:** *"Oggi cerca una sola cosa piccola che ti faccia sorridere: una tazza di tè caldo, una canzone che ami, il profumo della pioggia. Le cose belle si nascondono nei dettagli."*
* **Firma:** — Chi cerca il bello insieme a te

#### Messaggio 31 (ID: 31)
* **Categoria:** `generale`
* **Tag:** 🌙 Sogni sereni
* **Titolo:** Buonanotte tesoro
* **Testo:** *"Lascia andare tutti i pensieri della giornata appena passata. Hai fatto del tuo meglio e ora meriti solo sogni dolci e tranquilli. Io ti stringo forte forte da qui."*
* **Firma:** — Nei tuoi sogni sempre

#### Messaggio 32 (ID: 32)
* **Categoria:** `generale`
* **Tag:** 🌅 Nuovo inizio
* **Titolo:** Un buongiorno per te
* **Testo:** *"Che questa giornata ti porti momenti leggeri, persone gentili e tanti motivi per sorridere. Ricordati che porto il tuo cuore dentro al mio ovunque io vada."*
* **Firma:** — Il tuo primo pensiero al mattino

---

### 4.2 I 42 Piccoli Gesti per Te Integrali (`ALL_COMFORT_TIPS`)

| ID | Categoria | Icona | Titolo | Descrizione Completa | Note Speciali |
| :---: | :---: | :---: | :--- | :--- | :--- |
| **1** | `REST` | 🫁 | Fai un respiro profondo con Pisolo | *"Segui il ritmo calmo dell'esercizio guidato 4-4-4 per rallentare il battito."* | Apre modale respiro (`isBreath: true`) |
| **2** | `CARE` | 💧 | Bevi un sorso d'acqua fresca | *"Bevi lentamente, a piccoli sorsi. Senti la freschezza che scende e rigenera."* | - |
| **3** | `REST` | 💆 | Rilassa spalle e mandibola | *"Abbassa le spalle di due centimetri e schiudi leggermente i denti."* | - |
| **4** | `CARE` | 🫖 | Prepara una tisana calda | *"Scegli camomilla, melissa o zenzero. Tieni la tazza calda tra le mani."* | - |
| **5** | `NATURE`| 🪟 | Guarda fuori dalla finestra | *"Cerca il punto più lontano all'orizzonte o guarda le nuvole per un minuto."* | - |
| **6** | `CARE` | 🧸 | Stringi un cuscino o un peluche | *"Un abbraccio fisico, anche a un cuscino morbido, manda segnali di calma al cervello."* | - |
| **7** | `REST` | 📴 | 10 minuti senza notifiche | *"Metti il telefono a faccia in giù. Il mondo non scappa, adesso ci sei solo tu."* | - |
| **8** | `CARE` | 🧴 | Crema profumata sulle mani | *"Massaggia piano le mani con una crema dal profumo dolce che ami."* | - |
| **9** | `MIND` | ✍️ | Butta fuori un pensiero nel diario | *"Scrivi una frase nello spazio sfogo qui sotto e poi lasciala andare nel mare."* | - |
| **10** | `REST` | 🧦 | Mettiti i calzini più morbidi | *"Piedi caldi significano corpo rilassato. Cerca il paio più coccoloso che hai."* | - |
| **11** | `NATURE`| 🌿 | Annusa qualcosa di buono | *"Vaniglia, lavanda, caffè o il profumo di chi ami: l'olfatto calma l'ansia subito."* | - |
| **12** | `MIND` | 🎵 | Ascolta una canzone che ti scalda | *"Una traccia strumentale, lofi o la tua canzone del cuore a basso volume."* | - |
| **13** | `CARE` | 🚿 | Acqua tiepida sui polsi e sul viso | *"Lascia scorrere l'acqua dolce sui polsi per un minuto: abbassa la temperatura corporea."* | - |
| **14** | `REST` | 🛋️ | Cambia posizione, mettiti più comoda | *"Aggiungi un cuscino dietro la schiena, raggomitolati o allunga le gambe."* | - |
| **15** | `MIND` | 🌟 | Dì a te stessa: 'Sto facendo del mio meglio' | *"Ed è assolutamente abbastanza. Non devi dimostrare niente a nessuno oggi."* | - |
| **16** | `NATURE`| ☀️ | Cerca un raggio di luce naturale | *"Se c'è sole, chiudi gli occhi e lascia che ti scaldi le palpebre per 30 secondi."* | - |
| **17** | `CARE` | 🍫 | Un quadratino di cioccolato | *"Mangialo piano piano, lasciandolo sciogliere sulla lingua senza masticare subito."* | - |
| **18** | `REST` | 💤 | Chiudi gli occhi per 3 minuti | *"Anche senza dormire, il buio fa riposare la corteccia visiva e rilassa la testa."* | - |
| **19** | `MIND` | 🫂 | Rileggi un messaggio dolce che hai ricevuto | *"Un messaggio di chi ti vuole bene che ti ha fatto sorridere nei giorni scorsi."* | - |
| **20** | `CARE` | 🧣 | Avvolgiti in una coperta morbida | *"Fai un bozzolo caldo. Sentirti protetta fisicamente aiuta il cuore a calmarsi."* | - |
| **21** | `REST` | 🧘‍♀️ | Allenta la presa delle mani | *"Apri le dita, scuoti piano i polsi e lascia andare la tensione accumulata."* | - |
| **22** | `NATURE`| 🪴 | Guarda una pianta o un fiore | *"Osserva i dettagli delle foglie e il verde: la natura ha un ritmo calmo e paziente."* | - |
| **23** | `MIND` | ☁️ | Immagina il tuo posto del cuore | *"Una spiaggia al tramonto, un bosco fresco o il lettone: cammina lì con la mente."* | - |
| **24** | `CARE` | 🍯 | Un cucchiaino di miele o una caramella | *"La dolcezza sulla lingua rilascia endorfine e distende la gola."* | - |
| **25** | `REST` | 👣 | Cammina a piedi scalzi sul pavimento | *"Senti il contatto solido con la terra: ti aiuta a tornare con i piedi per terra."* | - |
| **26** | `MIND` | 📖 | Leggi una pagina di un libro leggero | *"Poche righe per dare alla mente una direzione diversa dai pensieri fissi."* | - |
| **27** | `REST` | 💆‍♀️ | Massaggio dolce alle tempie | *"Piccoli cerchi lenti con i polpastrelli sulle tempie per sciogliere la fronte."* | - |
| **28** | `NATURE`| 🌊 | Ascolta il suono delle onde o della pioggia | *"Metti suoni della natura in sottofondo: hanno la stessa frequenza del respiro calmo."* | - |
| **29** | `CARE` | 👕 | Infilati la felpa più larga e comoda | *"Via abiti stretti o scarpe scomode: il corpo ha bisogno di morbidezza."* | - |
| **30** | `MIND` | 🤍 | Ricorda: 'Anche questa sensazione passerà' | *"Nessun momento difficile dura per sempre. Respira e aspetta che l'onda scenda."* | - |
| **31** | `REST` | 🛌 | Stenditi con le gambe sollevate | *"Appoggia le gambe su un cuscino o contro la parete: riattiva il flusso e dà sollievo."* | - |
| **32** | `CARE` | 🍎 | Mangia un frutto fresco a piccoli morsi | *"Prenditi il tempo di sentire il sapore, la consistenza fresca e il profumo."* | - |
| **33** | `NATURE`| 💨 | Apri la finestra per cambiare aria | *"Tre grandi respiri dell'aria fresca che entra dalla finestra per rinfrescare la mente."* | - |
| **34** | `MIND` | 🧩 | Fai un cruciverba o un gioco semplice | *"Qualcosa di piccolo e senza fretta per distrarre i pensieri in modo leggero."* | - |
| **35** | `REST` | 🥱 | Concediti uno sbadiglio liberatorio | *"Apri bene la bocca e sbadiglia senza vergogna: ossigena il cervello e scioglie la faccia."* | - |
| **36** | `CARE` | 🕯️ | Accendi una candela o una luce calda | *"Spegni le luci fredde del soffitto e crea un'atmosfera dorata e protetta."* | - |
| **37** | `MIND` | 💭 | Pensa a una cosa per cui sei grata | *"Anche minuscola: un caffè buono, il piumone morbido, un sorriso ricevuto."* | - |
| **38** | `REST` | 🙆‍♀️ | Allungamento dolce del collo | *"Inclina la testa a destra per 15 secondi, poi a sinistra, senza forzare mai."* | - |
| **39** | `CARE` | 🛁 | Scalda un asciugamano sul termosifone | *"Appoggialo sul collo o sulla schiena calda: è una carezza termica rilassante."* | - |
| **40** | `NATURE`| 🌌 | Guarda il cielo per un minuto | *"Che ci siano stelle, nuvole o azzurro: il cielo è immenso e ridimensiona le paure."* | - |
| **41** | `MIND` | 🐱 | Immagina le fusa di un gattino | *"Un suono basso, ritmico e continuo che ti vibra vicino al cuore portando calma."* | - |
| **42** | `CARE` | 💌 | Mandami un cuoricino su WhatsApp | *"Basta solo un'emoji: io saprò che mi stai pensando e ti manderò tutto il mio amore."* | - |

---

### 4.3 Le Citazioni Mascotte nella Nuvoletta (`MASCOT_QUOTES`)

* **Pisolo lo squaletto (🦈):**
  1. *"Prenditi tutto il tempo che ti serve: nuotiamo piano insieme."*
  2. *"Nessuna tempesta dura per sempre. Ti proteggo io."*
  3. *"Sei il mio piccolo tesoro prezioso in mezzo al mare."*
  4. *"Appoggia la testa e respira: sono sempre qui a farti da scudo."*
  5. *"Anche nei giorni più difficili, la tua forza mi rende fiero di te."*
* **Micio il gattino (🐱):**
  1. *"Ti mando una fusa morbidissima per sciogliere la stanchezza."*
  2. *"Meriti solo coccole calde, riposo e tanta tenerezza."*
  3. *"Fai piano: oggi non devi dimostrare niente al mondo."*
  4. *"Chiudi gli occhi e immagina che ti accarezzi il viso."*
  5. *"Sei la persona più dolce che esista. Non dimenticarlo mai."*

---

### 4.4 I 10 Luoghi di Pace e Sfondi Naturali (`TRANQUIL_BACKGROUNDS`)

| ID | Nome Luogo | Emoji | Descrizione del Luogo | Colore Tema | File Immagine (`assets/`) |
| :--- | :--- | :---: | :--- | :---: | :--- |
| `spiaggia-serena` | Spiaggia Serena | 🏖️ | Spiaggia calma e mare turchese trasparente | `#EBF5FB` | `img_14_71f7b0643166.jpg` (`bg_beach.jpg`) |
| `boschetto-luce` | Boschetto di Luce | 🌲 | Bosco silenzioso con raggi di sole gentili | `#EAF8F1` | `img_15_938af34ab049.jpg` (`bg_grove.jpg`) |
| `lago-quieto` | Lago Quieto | 🏞️ | Lago alpino immobile e trasparente all'aurora | `#F2EEF8` | `img_16_599c09a8ed3b.jpg` (`bg_lake.jpg`) |
| `campi-lavanda` | Campi di Lavanda | 🪻 | Distesa fiorita provenzale al tramonto | `#F5EFFB` | `img_17_016e937ee8b6.jpg` (`bg_lavender.jpg`) |
| `giardino-zen` | Giardino Zen | 🌸 | Fiori di ciliegio, specchio d'acqua e quiete | `#FDF0F4` | `img_18_c11de8ca4c11.jpg` (`bg_zen_garden.jpg`) |
| `tramonto-mare` | Tramonto sul Mare | 🌅 | Spiaggia dorata soffice e orizzonte sereno | `#FFF3E8` | `img_19_2ad37fb1fbd2.jpg` (`bg_sunset_beach.jpg`) |
| `prato-fiorito` | Prato Fiorito | 🌼 | Dolci colline verdi con margherite campestri | `#F4FAF2` | `img_20_a1294a4cb05c.jpg` (`bg_wildflowers.jpg`) |
| `cascata-segreta` | Cascata Segreta | 💧 | Ruscello limpido tra sassi levigati e muschio | `#EDF8F7` | `img_21_238c3b53e343.jpg` (`bg_waterfall.jpg`) |
| `colline-aurora` | Colline all'Aurora | 🌄 | Vallata verde con foschia dorata mattutina | `#F7FBF4` | `img_22_24b7db2b4e85.jpg` (`bg_morning_hills.jpg`) |
| `fiume-salici` | Fiume dei Salici | 🌿 | Ninfee e salici piangenti sull'acqua cheta | `#EEF7F5` | `img_23_e58588a5599c.jpg` (`bg_riverbank.jpg`) |

---

### 4.5 Gli 8 Ritratti delle Mascotte (`ALTERNATING_MASCOT_PRESETS`)

| ID Preset | Tipo | Nome Ritratto | Tag / Badge | Descrizione | File Immagine Corrispondente |
| :--- | :---: | :--- | :--- | :--- | :--- |
| `shark_1` | 🦈 | Pisolo Morbido | `🦈 Squaletto #1` | Squaletto soffice e rassicurante | `assets/img_01_0a586335395e.jpg` |
| `kitty_1` | 🐱 | Micio Dolce | `🐱 Gattino #1` | Gattino coccoloso per le tue giornate | `assets/img_02_80bf04a43bbc.jpg` |
| `shark_2` | 🦈 | Pisolo Nuotatore | `🦈 Squaletto #2` | Pronto a farti da scudo in ogni tempesta | `assets/img_03_fa6339c60a33.jpg` |
| `kitty_2` | 🐱 | Micio Sonnecchiante | `🐱 Gattino #2` | Fusa rilassanti e nanna tranquilla | `assets/img_06_7d757a1dd8a1.jpg` |
| `shark_3` | 🦈 | Pisolo Cucciolotto | `🦈 Squaletto #3` | Tenerezza infinita per scaldarti il cuore | `assets/img_04_474c0282a71d.jpg` |
| `kitty_3` | 🐱 | Micio Curioso | `🐱 Gattino #3` | Occhietti dolci che ti sorridono | `assets/img_07_96256b3c039b.jpg` |
| `shark_4` | 🦈 | Pisolo Protettivo | `🦈 Squaletto #4` | Un abbraccio grande a pinne aperte | `assets/img_05_44e40f9a0c35.jpg` |
| `kitty_4` | 🐱 | Micio e Amico | `🐱 Gattino #4` | Coccole senza fine e fusa d'amore (Duo) | `assets/img_08_2c3322d80209.jpg` |

---

### 4.6 Frasi di Riserva Offline del Server (`FALLBACK_LOVE` & `FALLBACK_TIPS`)
Presenti in `serve.js` per garantire risposte istantanee anche senza connessione internet o in caso di indisponibilità dei server AI Google:

#### Fallback Amore (`FALLBACK_LOVE` - 12 frasi):
1. *"Non importa quanto sia faticosa la giornata: quando penso a te, tutto torna a farsi quieto e luminoso."*
2. *"Sei il mio porto sicuro, il sorriso che cerco quando sono stanco e la persona con cui ogni istante diventa prezioso."*
3. *"Ricordati sempre quanto vali e quanto amore sei capace di donare. Io sarò sempre al tuo fianco a ricordartelo."*
4. *"Anche nei giorni più grigi, il tuo sorriso ha il potere di accendere tutto intorno."*
5. *"Sei la certezza più dolce che porto nel cuore ogni mattina al risveglio."*
6. *"Per me non esiste rifugio più rassicurante del tuo abbraccio. Qualsiasi cosa accada, ci siamo noi."*
7. *"Sei una persona meravigliosa, con una sensibilità rara. Non dubitare mai della tua luce."*
8. *"Camminare insieme a te è il regalo più bello che la vita potesse farmi. Ti amo immensamente."*
9. *"Quando senti il mondo fare troppo rumore, chiudi gli occhi: il mio cuore è lì a proteggere il tuo."*
10. *"Sei la parte più bella di ogni mio giorno, la ragione per cui ogni cosa ha più senso e colore."*
11. *"Qualunque tempesta ci sia fuori, nella nostra dolcezza c'è sempre un cielo sereno ad aspettarti."*
12. *"Non c'è niente di più bello che addormentarmi sapendo che esisti e svegliarmi volendoti ancora più bene."*

#### Fallback Consigli (`FALLBACK_TIPS` - 7 consigli):
1. *"Bevi un bicchiere d'acqua fresca a piccoli sorsi, rilassa le spalle e fai tre respiri lenti con gli occhi socchiusi."*
2. *"Non pretendere di risolvere tutto adesso. Fai solo una piccola cosa alla volta, senza alcuna fretta. Va benissimo così."*
3. *"Stacca gli occhi dallo schermo per dieci minuti: guarda fuori dalla finestra, fai stretching dolce al collo e riposa lo sguardo."*
4. *"Concediti una tisana o una tazza calda profumata, stringi la tazza con entrambe le mani e assapora questo momento solo per te."*
5. *"Se i pensieri si accavallano, scrivili su un foglio per liberare la mente: ciò che conta davvero può aspettare domani con calma."*
6. *"Fai scendere dolcemente le spalle verso il basso, decontrai la mandibola e lascia andare il fiato con un sospiro liberatorio."*
7. *"Mettiti comoda, infilati una felpa calda o avvolgiti in una coperta morbida: il tuo corpo merita riposo e tenerezza."*

---

### 4.7 Microcopy Completo dell'Interfaccia Utente

* **Schermata di Login:**
  * Titolo: `PISOLO & MICIO`
  * Sottotitolo: *"Questo è il tuo spazio privato e sicuro. Inserisci la password per entrare:"*
  * Placeholder: *"Password di accesso..."*
  * Bottone Invio: *"Entra nell'app ❤️"*
  * Messaggio Errore: *"Password non corretta. Riprova con calma 🌸"*
* **Modale Promemoria d'Amore (Accoglienza):**
  * Titolo: `Piccolo promemoria d'amore`
  * Corpo: *"Ciao piccola mia. Ricordati sempre che la cosa che desidero di più è sentirti direttamente. Se c'è qualcosa che non va, se ti senti giù o se hai solo bisogno di me, scrivimi o chiamami appena puoi: per te ci sono sempre. Ho preparato quest'app con Pisolo e Micio per farti compagnia e darti una carezza nei momenti in cui siamo lontani. 💕"*
  * Bottone WhatsApp: *"💬 Scrivi o chiama il tuo ragazzo"*
  * Bottone Entra: *"Entra nell'app con Pisolo & Micio"*
* **Banner Ribbon In Home:**
  * Titolo: *"Se hai un peso sul cuore, scrivimi sempre 💕"*
  * Sottotitolo: *"Non devi tenere tutto per te: per te ci sono in ogni momento."*
* **Card Respirazione in Home:**
  * Tag: *"🫁 Esercizio di calma"*
  * Titolo: *"Senti il fiato corto o troppi pensieri?"*
  * Sottotitolo: *"60 secondi di respiro 4-4-4 guidato da Pisolo e Micio per rilassare il corpo."*
* **Sezione Luogo di Pace:**
  * Tag: *"🌿 Il tuo Luogo di Pace oggi"*
  * Bottone Cambia: *"🔄 Cambia"*
  * Bottone Modale: *"🏖️ Tutti i 10 Luoghi"*
* **Pannello Generatore IA:**
  * Titolo: *"✨ Generatore Dediche con IA"*
  * Link Modale: *"🔐 Chiave privata"*
  * Sottotitolo: *"Scegli il tono e Pisolo 🦈 & Micio 🐱 comporranno una dedica unica per te:"*
  * Loader IA: *"✨ Pisolo e Micio stanno componendo la tua dedica con il cuore... 🦈🐾"*
* **Modale Abbraccio:**
  * Tag: *"❤️ Abbraccio sincero"*
  * Categorie: `🫂 Tutti`, `🌧️ Tristezza`, `⚡ Ansia`, `🩹 Fastidio`
  * Bottone Altro: *"🔄 Altro abbraccio"*
  * Bottone Ringraziamento: *"Grazie ❤️"*
* **Modale Respirazione Guidata:**
  * Titolo: *"🦈 Respirazione Guidata (4-4-4)"*
  * Fasi indicatori: `⏳ Inizio in 5s`, `1. Inspira`, `2. Trattieni`, `3. Espira`, `4. Pausa`
  * Bottone Pausa: `⏸️ Pausa` / `▶️ Riprendi`
  * Bottone Conclusione: `Sto meglio 💕`
* **Modale Celebrazione 5 Gesti Completati:**
  * Badge: *"✨ 5 Gesti Completati con Successo! 🎉"*
  * Titolo: *"Bravissima, piccola mia! ❤️"*
  * Corpo: *"Hai completato tutti i 5 gesti di cura che Pisolo e Micio hanno preparato per te! 🎉✨"*
  * Citazione Box: *"Prendersi cura di sé, anche con piccoli passi, è il regalo più bello che puoi farti. Io e i tuoi due amici siamo fieri di te! 💕 — Pisolo 🦈, Micio 🐱 & il tuo ragazzo ❤️"*
  * Bottone Primario: *"Grazie di cuore, Pisolo & Micio! 🌸"*
  * Bottone Secondario: *"🔄 Estrai 5 nuovi gesti per dopo"*
* **Spazio Sfogo (Diario):**
  * Tag: `✍️ SPAZIO PER I TUOI PENSIERI`
  * Trigger Cancella: `CANCELLA`
  * Placeholder: *"Se hai dentro qualcosa che ti fa male, ti preoccupa o ti pesa, puoi scriverlo qui liberamente. Non verrà salvato da nessuna parte..."*
  * Nota Privacy: *"🔒 Questo testo resta solo sul tuo schermo"*
  * Bottone Mare: *"🌊 Lascia andare nel mare"*
  * Feedback Post-Rilascio: *"✨ I pensieri che pesavano sono scivolati via tra le onde con Pisolo e Micio. Adesso respira con calma: sei al sicuro. 🫧"*
* **Footer:**
  * *"Fatto per te con tutto l'affetto possibile ❤️"*
  * *"Pisolo 🦈 & Micio 🐱 • Sempre al tuo fianco"*
  * *"🔗 Link sempre autonomo e istruzioni App"*
  * *"🔒 Blocca app con password"*

---

## 5. Vincoli Tecnici e Architettura dei Dati

### 5.1 Zero Build Tools e Massima Autonomia
L'applicazione web è progettata per essere un **sistema autonomo a file statici**, eseguibile immediatamente:
* Nessun bundler obbligatorio (niente Webpack, Vite, Rollup o Babel).
* Nessun framework pesante (niente React, Angular o Vue).
* È composta da soli file standard interpretati nativamente da qualsiasi browser moderno:
  * `index.html` (Struttura semantica, modali e schede).
  * `styles.css` (Design system, palette, glassmorphism, responsive queries e animazioni).
  * `script.js` (Logica applicativa completa, gestione stato, algoritmo di rimescolamento e chiamate IA).
  * `backgrounds_data.js` (Database fotografico dei 10 sfondi naturali in alta risoluzione incorporati).
  * `manifest.json` e `sw.js` (Abilitazione Progressive Web App, icone e supporto offline).

### 5.2 Server Node.js Nativo (`serve.js`)
Il progetto include un server HTTP Node.js leggerissimo (circa 360 righe) a **zero dipendenze esterne** (utilizza unicamente i moduli nativi `http`, `https`, `fs`, `path`):
* Serve i file statici con corretti MIME types e header di cache.
* Gestisce gli endpoint API per Gemini IA:
  * `GET /api/health` e `GET /api/ai/status` (verifica stato server e disponibilità chiave).
  * `POST /api/ai/test-key` (test sicuro della chiave API senza esporla).
  * `POST /api/ai/generate` (generazione dediche o consigli con rotazione automatica dei modelli e fallback locale).

### 5.3 Prototipo Parallelo Android Nativo (`app/`)
Nel workspace è presente anche una versione nativa Android basata su **Kotlin e Jetpack Compose**:
* Package: `com.aistudio.pisolo.hugapp` (in `app/src/main/java/com/example/`).
* Componenti Compose che rispecchiano fedelmente le sezioni web: `HomeScreen`, `PisoloHeader`, `SupportMessagesSection`, `DigitalHugModal`, `ComfortTipsSection`, `VentingSection`, `BreathingExerciseDialog`, `GeminiComfortService`.
* Questo dimostra l'intenzione del progetto di esistere sia come Web App/PWA sia come app Android da installare via APK.

### 5.4 Mappa della Persistenza dei Dati (`localStorage`)

| Chiave `localStorage` | Tipo di Dato | Scopo |
| :--- | :--- | :--- |
| `pisolo_pwa_auth` | String (`'true'`) | Flag di sessione sbloccata con password |
| `partner_phone_num` | String (es. `'+39340...'`) | Numero WhatsApp del ragazzo per il contatto diretto |
| `welcome_dismissed_today` | String (Date String) | Memorizza la data per non mostrare il popup ad ogni refresh |
| `pisolo_bg_index` | Number (0 - 9) | Indice dell'ultimo sfondo naturale selezionato |
| `pisolo_checked_tips` | JSON Array (es. `[1, 5, 12]`) | ID dei gesti contrassegnati come completati |
| `user_gemini_api_key` | String (es. `'AIzaSy...'`) | Chiave privata personale di Google AI Studio |
| `active_mascot_pref` | String (`'shark'` / `'kitty'`) | Mascotte selezionata come attiva |
| `mascot_shark_selected_id` | String (`'shark_1'` .. `'shark_4'` / `'custom'`) | ID del ritratto selezionato per Pisolo |
| `mascot_kitty_selected_id` | String (`'kitty_1'` .. `'kitty_4'` / `'custom'`) | ID del ritratto selezionato per Micio |
| `custom_mascot_shark_photo`| String (Base64 Data URL) | Foto personalizzata caricata dall'utente per Pisolo |
| `custom_mascot_kitty_photo`| String (Base64 Data URL) | Foto personalizzata caricata dall'utente per Micio |
| `featured_hero_photo` | String (Base64 Data URL) | Foto personalizzata caricata per la copertina |

### 5.5 Cosa NON Deve Mai Essere Salvato (Vincolo Inviolabile)
* **Il testo dello sfogo / diario (`#vent-text-input`):**
  * **DEVE RESTARE ESCLUSIVAMENTE IN MEMORIA VOLATILE DEL BROWSER (RAM / DOM).**
  * È severamente vietato salvarlo in `localStorage`, `sessionStorage`, `IndexedDB`, o inviarlo a server remoti.
  * Alla pressione del tasto di rilascio o di cancellazione, il valore deve essere sovrascritto a stringa vuota (`""`).

---

## 6. Stato Attuale, Limiti Noti e Suggerimenti di Miglioramento

Questa sezione evidenzia le criticità tecniche, i bug scoperti analizzando il codice sorgente e le raccomandazioni progettuali per l'assistente AI (Claude) che scriverà il prompt di ricostruzione.

### 6.1 Bug e Difetti Riscontrati nel Codice Esistente

1. **Mismatch ID nel Generatore di Consigli IA (`generateAiTip`):**
   * Nel codice JavaScript (`script.js`, riga 2500) la funzione cerca l'elemento:
     `document.getElementById('tips-container-list')`
   * Nell'HTML (`index.html`, riga 315) il contenitore ha invece ID:
     `id="tips-list-container"`
   * *Effetto del bug:* La variabile `list` risulta `null` e il consiglio personalizzato creato da Gemini non viene mai visualizzato a schermo.
2. **Animazione CSS Mancante (`spinSmooth`):**
   * In `index.html` (riga 259) e in `script.js` (riga 53730) viene applicata la classe/stile inline con `animation: spinSmooth 1.5s infinite linear`.
   * In `styles.css` il selettore `@keyframes spinSmooth` **non è definito**.
   * *Effetto del bug:* L'icona di caricamento dell'IA non gira come previsto.
3. **Elementi DOM Orfani / Riferimenti a Vecchie Versioni (21 ID non trovati):**
   * `script.js` contiene chiamate `document.getElementById` a 21 elementi che non esistono più in `index.html`:
     * Vecchi tab della galleria: `tab-btn-all`, `tab-btn-sharks`, `tab-btn-kitties`, `modal-tab-all`, `modal-tab-shark`, `modal-tab-kitty`.
     * Vecchi contenitori rimossi: `mascot-gallery-content-area`, `modal-mascot-photos-backdrop`.
     * Vecchio carosello pensieri rimosso: `carousel-track`, `carousel-dots`, `carousel-counter`.
     * Vecchi pulsanti installazione rimossi: `header-btn-install`, `pwa-install-banner`.
     * Indicatori chiave IA dell'header: `header-btn-ai-key`, `header-ai-btn-text` (il bottone reale è ora nel pannello IA).
   * *Effetto del problema:* Codice morto (dead code) che genera controlli nulli e appesantisce lo script (oltre 2500 righe).
4. **File Orfani e Ridondanze nella Cartella `assets/`:**
   * La sottocartella `assets/hugs/` contiene 6 splendide illustrazioni ad alta risoluzione (es. `hug_beach_sunset_peace_...`, `hug_pisolo_micio_cuddle_...`) che non sono **mai richiamate** in nessun punto del codice sorgente.
   * La cartella `assets/` contiene sia i file con i vecchi nomi leggibili (`shark_1.jpg`, `kitty_1.jpg`, `photo_featured.jpg`, `bg_beach.jpg`, ecc.) sia i file rinominati con hash casuali (`img_01_...` fino a `img_23_...`), duplicando inutilmente decine di megabyte.
5. **Caching Incompleto nel Service Worker (`sw.js`):**
   * L'array `STATIC_ASSETS` pre-cacha le immagini e `backgrounds_data.js`, ma **omette** i file fondamentali `styles.css` e `script.js`. Pur venendo recuperati via fetch dinamica, l'omissione rende la prima apertura offline potenzialmente instabile.
6. **Discrepanza sul Respiro "4-4-4":**
   * L'interfaccia pubblicizza un respiro *"4-4-4"*, ma la configurazione `BREATH_CYCLE` include una quarta fase di *"Pausa di riposo"* di 2 secondi (4-4-4-2) preceduta da un conto alla rovescia di 5 secondi. La discrepanza tra label e comportamento reale va armonizzata.
7. **Sicurezza Password Puramente Client-Side:**
   * L'autenticazione tramite hash SHA-256 e classe `body.locked` è solo cosmetica: chiunque apra i DevTools del browser può rimuovere la classe `locked` o impostare `localStorage.setItem('pisolo_pwa_auth', 'true')` ed entrare senza conoscere la password.

---

### 6.2 Suggerimenti di Ristrutturazione e Miglioramento per il Prompt di Claude

Quando l'assistente AI scriverà il prompt per ricostruire l'app da zero, si raccomanda di includere i seguenti principi architetturali ed evolutivi:

#### A. Architettura del Codice e Modularità
* **Refactoring a Componenti Modulari:** Separare il monolite di `script.js` in moduli ES6 puliti (es. `auth.js`, `audio.js`, `breathing.js`, `messages.js`, `tips.js`, `aiService.js`, `storage.js`) oppure utilizzare un bundler ultra-veloce moderno come **Vite** con Vanilla JS o React/Vue leggero, mantenendo la build finale statica ed autonoma.
* **Rimozione di tutto il Dead Code:** Eliminare le 21 variabili orfane, le funzioni di carosello non più utilizzate e ripulire gli asset non referenziati.
* **Risoluzione Mismatch ID:** Uniformare tutti i selettori DOM tra HTML e JS.

#### B. Ottimizzazione delle Immagini e Prestazioni
* **Conversione in WebP / AVIF:** Molte immagini JPEG superano i 600-900 KB e il file `backgrounds_data.js` pesa quasi 8 MB. Convertire tutti gli asset in formato moderno WebP compresso ridurrà il peso dell'app da ~50 MB a meno di 4 MB, rendendo l'apertura istantanea anche su reti mobili deboli.
* **Integrazione Reale delle Immagini di Abbraccio:** Collegare le 6 immagini attualmente orfane in `assets/hugs/` direttamente alla modale Abbraccio Virtuale per arricchire la varietà visiva.

#### C. Rafforzamento dell'Esperienza Emotiva e Sensoriale
* **Colonna Sonora Ambientale Rilassante (Soundscapes):** Aggiungere un riproduttore audio delicato e opzionale con suoni rilassanti a loop (onde del mare calmo, pioggia leggera sui vetri, fusa del gattino, musica lofi pianoforte).
* **Feedback Aptico (Haptic Feedback):** Integrare `navigator.vibrate` sui telefoni supportati durante la respirazione (vibrazione leggera all'inizio dell'espirazione/inspirazione) e al completamento dei gesti di cura.
* **Miglioramento dell'Esercizio di Respirazione:** Rendere selezionabile il ritmo (es. Respirazione Quadrata 4-4-4-4, Tecnica Relax 4-7-8, o Calma Veloce 4-4-4).

#### D. Rafforzamento dell'Intelligenza Artificiale (Gemini)
* **System Instruction Personalizzata:** Fornire alla chiamata Gemini una System Instruction profonda che conosca i nomi dei due partner e aneddoti di tenerezza condivisi.
* **Cache Locale delle Dediche Generate:** Salvare le migliori dediche generate con IA in una sezione "I miei ricordi preferiti" per poterle rileggere offline.

#### E. Sicurezza e Privacy
* Se si desidera mantenere l'app solo per lei senza un server di autenticazione, utilizzare la **Web Cryptography API (PBKDF2/AES-GCM)** per criptare i contenuti privati (come i messaggi intimi) usando la password come chiave di decifrazione, rendendo impossibile visualizzarli semplicemente ispezionando l'HTML.
