import re
import subprocess

with open('index.html', 'r', encoding='utf-8') as f:
    text = f.read()

script = re.search(r'<script>(.*?)</script>', text, re.DOTALL).group(1)

test_code = """
const localStorage = {
  store: {},
  getItem(k) { return this.store[k] || null; },
  setItem(k, v) { this.store[k] = String(v); }
};
const document = {
  getElementById(id) {
    return {
      style: {},
      classList: { add() {}, remove() {}, toggle() {} },
      appendChild() {},
      textContent: '',
      innerHTML: '',
      src: ''
    };
  },
  querySelectorAll() { return []; }
};
const window = {
  addEventListener() {},
  location: { origin: 'http://localhost', href: 'http://localhost' }
};
function showToast(msg) {}

""" + script + """

console.log("=== VERIFICA POOL DEI MESSAGGI ===");
console.log("Totale messaggi nel pool:", SUPPORT_MESSAGES.length);

console.log("\\n=== VERIFICA ALTERNANZA FOTO SQUALETTI E GATTINI ===");
for (let i = 0; i < 8; i++) {
  const p = getAlternatingPhoto(i);
  console.log("Indice " + i + ": tipo=" + p.type + " (" + p.id + ") -> " + p.badge);
}

console.log("\\n=== VERIFICA ESTRAZIONI SHUFFLE BAG ===");
for (let i = 0; i < 6; i++) {
  const m = hugMessageBag.drawNext();
  console.log("Estrazione " + (i + 1) + ": ID=" + m.id + " [" + m.categoria + "] " + m.titolo);
}
"""

with open('/tmp/test_runner.js', 'w', encoding='utf-8') as out_f:
    out_f.write(test_code)

res = subprocess.run(['node', '/tmp/test_runner.js'], capture_output=True, text=True)
print("STDOUT:\n" + res.stdout)
if res.stderr:
    print("STDERR:\n" + res.stderr)
