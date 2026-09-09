const http = require('http');
const fs = require('fs');
const path = require('path');
const https = require('https');

const PORT = 3000;
const ROOT = path.resolve(__dirname);

const MIME_TYPES = {
  '.html': 'text/html; charset=UTF-8',
  '.js': 'application/javascript; charset=UTF-8',
  '.css': 'text/css; charset=UTF-8',
  '.json': 'application/json',
  '.webmanifest': 'application/manifest+json',
  '.png': 'image/png',
  '.jpg': 'image/jpeg',
  '.jpeg': 'image/jpeg',
  '.webp': 'image/webp',
  '.svg': 'image/svg+xml',
  '.ico': 'image/x-icon',
};

// Rich curated pools for instant offline/fallback generation
const FALLBACK_LOVE = [
  "Non importa quanto sia faticosa la giornata: quando penso a te, tutto torna a farsi quieto e luminoso.",
  "Sei il mio porto sicuro, il sorriso che cerco quando sono stanco e la persona con cui ogni istante diventa prezioso.",
  "Ricordati sempre quanto vali e quanto amore sei capace di donare. Io sarò sempre al tuo fianco a ricordartelo.",
  "Anche nei giorni più grigi, il tuo sorriso ha il potere di accendere tutto intorno.",
  "Sei la certezza più dolce che porto nel cuore ogni mattina al risveglio.",
  "Per me non esiste rifugio più rassicurante del tuo abbraccio. Qualsiasi cosa accada, ci siamo noi.",
  "Sei una persona meravigliosa, con una sensibilità rara. Non dubitare mai della tua luce.",
  "Camminare insieme a te è il regalo più bello che la vita potesse farmi. Ti amo immensamente.",
  "Quando senti il mondo fare troppo rumore, chiudi gli occhi: il mio cuore è lì a proteggere il tuo.",
  "Sei la parte più bella di ogni mio giorno, la ragione per cui ogni cosa ha più senso e colore.",
  "Qualunque tempesta ci sia fuori, nella nostra dolcezza c'è sempre un cielo sereno ad aspettarti.",
  "Non c'è niente di più bello che addormentarmi sapendo che esisti e svegliarmi volendoti ancora più bene."
];

const FALLBACK_TIPS = [
  "Bevi un bicchiere d'acqua fresca a piccoli sorsi, rilassa le spalle e fai tre respiri lenti con gli occhi socchiusi.",
  "Non pretendere di risolvere tutto adesso. Fai solo una piccola cosa alla volta, senza alcuna fretta. Va benissimo così.",
  "Stacca gli occhi dallo schermo per dieci minuti: guarda fuori dalla finestra, fai stretching dolce al collo e riposa lo sguardo.",
  "Concediti una tisana o una tazza calda profumata, stringi la tazza con entrambe le mani e assapora questo momento solo per te.",
  "Se i pensieri si accavallano, scrivili su un foglio per liberare la mente: ciò che conta davvero può aspettare domani con calma.",
  "Fai scendere dolcemente le spalle verso il basso, decontrai la mandibola e lascia andare il fiato con un sospiro liberatorio.",
  "Mettiti comoda, infilati una felpa calda o avvolgiti in una coperta morbida: il tuo corpo merita riposo e tenerezza."
];

function cleanAiOutput(text) {
  if (!text) return '';
  let cleaned = text.trim();
  // Remove markdown quotes and bolding
  cleaned = cleaned.replace(/^["'«“]|["'»”]$/g, '');
  cleaned = cleaned.replace(/\*\*/g, '');
  cleaned = cleaned.replace(/^#+\s*/gm, '');
  cleaned = cleaned.replace(/^(Ecco la frase:|Frase d'amore:|Consiglio:)/i, '').trim();
  return cleaned;
}

function callGemini(promptText, isAdvice, res) {
  const apiKey = process.env.GEMINI_API_KEY || '';
  if (!apiKey) {
    sendFallbackResponse(res, isAdvice);
    return;
  }

  const payload = JSON.stringify({
    contents: [{
      parts: [{ text: promptText }]
    }],
    generationConfig: {
      temperature: 0.9,
      maxOutputTokens: 600,
      topP: 0.95
    }
  });

  const modelsToTry = ['gemini-3.6-flash', 'gemini-3.5-flash', 'gemini-3.1-flash-lite'];

  function tryModel(index) {
    if (index >= modelsToTry.length) {
      sendFallbackResponse(res, isAdvice);
      return;
    }
    const model = modelsToTry[index];
    const options = {
      hostname: 'generativelanguage.googleapis.com',
      port: 443,
      path: `/v1beta/models/${model}:generateContent?key=${apiKey}`,
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Content-Length': Buffer.byteLength(payload)
      },
      timeout: 8000
    };

    const req = https.request(options, (geminiRes) => {
      let data = '';
      geminiRes.on('data', chunk => { data += chunk; });
      geminiRes.on('end', () => {
        try {
          const parsed = JSON.parse(data);
          if (parsed.candidates && parsed.candidates[0] && parsed.candidates[0].content && parsed.candidates[0].content.parts) {
            const raw = parsed.candidates[0].content.parts[0].text;
            const text = cleanAiOutput(raw);
            if (text && text.length > 5) {
              res.writeHead(200, { 'Content-Type': 'application/json; charset=UTF-8' });
              res.end(JSON.stringify({ success: true, text, source: 'gemini-ai', model }));
              return;
            }
          }
        } catch (e) {
          console.error('Error parsing Gemini response:', e);
        }
        if (index + 1 < modelsToTry.length) {
          tryModel(index + 1);
        } else {
          sendFallbackResponse(res, isAdvice);
        }
      });
    });

    req.on('error', (err) => {
      console.error(`Gemini request error (${model}):`, err.message);
      if (index + 1 < modelsToTry.length) {
        tryModel(index + 1);
      } else {
        sendFallbackResponse(res, isAdvice);
      }
    });

    req.on('timeout', () => {
      req.destroy();
      if (index + 1 < modelsToTry.length) {
        tryModel(index + 1);
      } else {
        sendFallbackResponse(res, isAdvice);
      }
    });

    req.write(payload);
    req.end();
  }

  tryModel(0);
}

function sendFallbackResponse(res, isAdvice) {
  const pool = isAdvice ? FALLBACK_TIPS : FALLBACK_LOVE;
  const chosen = pool[Math.floor(Math.random() * pool.length)];
  res.writeHead(200, { 'Content-Type': 'application/json; charset=UTF-8' });
  res.end(JSON.stringify({ success: true, text: chosen, source: 'curated-ai' }));
}

const server = http.createServer((req, res) => {
  res.setHeader('Access-Control-Allow-Origin', '*');
  res.setHeader('Access-Control-Allow-Methods', 'GET, POST, OPTIONS');
  res.setHeader('Access-Control-Allow-Headers', 'Content-Type');

  if (req.method === 'OPTIONS') {
    res.writeHead(200);
    res.end();
    return;
  }

  const parsedUrl = new URL(req.url, `http://${req.headers.host || 'localhost'}`);
  let reqPath = parsedUrl.pathname;

  if (reqPath === '/health' || reqPath === '/api/health') {
    res.writeHead(200, { 'Content-Type': 'application/json' });
    res.end(JSON.stringify({ status: 'ok', time: new Date().toISOString() }));
    return;
  }

  // AI Generation API
  if (reqPath === '/api/ai/generate' && req.method === 'POST') {
    let body = '';
    req.on('data', chunk => {
      body += chunk;
      if (body.length > 1e6) req.socket.destroy();
    });
    req.on('end', () => {
      try {
        const json = JSON.parse(body || '{}');
        const type = json.type || 'love'; // 'love' or 'advice'
        const mood = json.mood || 'dolce e sincero';
        const isAdvice = (type === 'advice');

        let prompt;
        if (isAdvice) {
          prompt = `Sei un assistente affettuoso e premuroso, come Pisolo 🦈 e Micio 🐱.
Genera un singolo consiglio breve, pratico, caloroso e confortante per prendersi cura di sé (tema o momento: "${mood}").
Scrivi solo il consiglio in italiano, in 2 o 3 frasi semplici, delicate e rassicuranti.
Niente titoli, niente introduzioni, niente elenchi puntati o asterischi. Solo il testo del consiglio.`;
        } else {
          prompt = `Sei un'Intelligenza Artificiale dell'Amore, creata con tutto il cuore per dedicare parole sincere, dolci e profonde a una persona speciale (ispirata all'affetto incondizionato di Pisolo 🦈 e Micio 🐱).
Genera una frase d'amore o di vicinanza inedita, profonda e romanticissima (tono o atmosfera: "${mood}").
Scrivi unicamente la frase d'amore in italiano, senza virgolette, senza asterischi, senza elenchi e senza spiegazioni (massimo 30-35 parole). Solo la frase.`;
        }

        callGemini(prompt, isAdvice, res);
      } catch (err) {
        console.error('JSON parse error in /api/ai/generate:', err);
        sendFallbackResponse(res, false);
      }
    });
    return;
  }

  // Static files handling
  if (reqPath === '/' || reqPath === '') reqPath = '/index.html';
  const filePath = path.join(ROOT, reqPath);

  if (!filePath.startsWith(ROOT)) {
    res.writeHead(403);
    res.end('Forbidden');
    return;
  }

  fs.stat(filePath, (err, stats) => {
    if (err || !stats.isFile()) {
      if (!path.extname(filePath)) {
        const indexPath = path.join(ROOT, 'index.html');
        fs.stat(indexPath, (idxErr, idxStats) => {
          if (!idxErr && idxStats.isFile()) {
            res.writeHead(200, {
              'Content-Type': 'text/html; charset=UTF-8',
              'Content-Length': idxStats.size,
              'Cache-Control': 'no-cache'
            });
            fs.createReadStream(indexPath).pipe(res);
            return;
          }
          res.writeHead(404);
          res.end('Not Found');
        });
        return;
      }
      res.writeHead(404);
      res.end('Not Found');
      return;
    }

    const ext = path.extname(filePath).toLowerCase();
    const contentType = MIME_TYPES[ext] || 'application/octet-stream';
    const isSw = (reqPath === '/sw.js');
    const isManifest = (reqPath === '/manifest.json' || reqPath === '/manifest.webmanifest');
    const headers = {
      'Content-Type': contentType,
      'Content-Length': stats.size,
      'Cache-Control': (ext === '.html' || isSw || isManifest) ? 'no-cache' : 'public, max-age=86400'
    };
    if (isSw) {
      headers['Service-Worker-Allowed'] = '/';
    }
    res.writeHead(200, headers);
    const stream = fs.createReadStream(filePath);
    stream.on('error', () => { res.end(); });
    stream.pipe(res);
  });
});

server.on('clientError', (err, socket) => {
  if (err.code === 'ECONNRESET' || !socket.writable) return;
  socket.end('HTTP/1.1 400 Bad Request\r\n\r\n');
});

server.listen(PORT, '0.0.0.0', () => {
  console.log(`Server listening on port ${PORT}`);
});
