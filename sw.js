const CACHE_NAME = 'pisolo-micio-cache-v16';
const STATIC_ASSETS = [
  './',
  './index.html',
  './script.js',
  './styles.css',
  './manifest.json',
  './icon.png',
  './assets/hugs/hug_storybook.jpg',
  './assets/hugs/hug_bubble.jpg',
  './assets/hugs/hug_umbrella.jpg',
  './assets/hugs/hug_lanterns.jpg',
  './assets/hugs/hug_duo_cuddle.jpg',
  './assets/hugs/hug_together_warmth.jpg',
  './assets/hugs/hug_starry_1789423893325.jpg',
  './assets/hugs/hug_cloud_1789423902447.jpg',
  './assets/hugs/hug_blanket_1789423912013.jpg',
  './assets/hugs/hug_garden_1789423919687.jpg',
  './assets/hugs/hug_tent_1789423968994.jpg',
  './assets/hugs/hug_fireplace_1789423978606.jpg',
  './assets/hugs/hug_cherryblossom_1789423987275.jpg',
  './assets/hugs/hug_pillow_1789423997048.jpg',
  './assets/hugs/hug_beach_sunset_peace_1789076615367.jpg',
  './assets/hugs/hug_gentle_garden_sun_1789076648080.jpg',
  './assets/hugs/hug_pisolo_micio_cuddle_1789076591266.jpg',
  './assets/hugs/hug_pisolo_protect_rain_1789076604075.jpg',
  './assets/hugs/hug_starry_night_dreams_1789076626440.jpg',
  './assets/hugs/hug_warm_blanket_cocoa_1789076637148.jpg'
];

self.addEventListener('install', (event) => {
  event.waitUntil(
    caches.open(CACHE_NAME).then((cache) => {
      return cache.addAll(STATIC_ASSETS).catch((err) => {
        console.warn('Some assets could not be pre-cached:', err);
      });
    })
  );
  self.skipWaiting();
});

self.addEventListener('activate', (event) => {
  event.waitUntil(
    caches.keys().then((keys) => {
      return Promise.all(
        keys.map((key) => {
          if (key !== CACHE_NAME) {
            return caches.delete(key);
          }
        })
      );
    })
  );
  self.clients.claim();
});

self.addEventListener('fetch', (event) => {
  // Pass-through for AI generation API
  if (event.request.url.includes('/api/')) {
    return;
  }

  event.respondWith(
    caches.match(event.request, { ignoreSearch: true }).then((cachedResponse) => {
      if (cachedResponse) {
        // Fetch in background for stale-while-revalidate on HTML/JS
        fetch(event.request).then((networkResponse) => {
          if (networkResponse && networkResponse.status === 200) {
            caches.open(CACHE_NAME).then((cache) => cache.put(event.request, networkResponse));
          }
        }).catch(() => {});
        return cachedResponse;
      }
      return fetch(event.request).catch(() => {
        if (event.request.mode === 'navigate') {
          return caches.match('./index.html');
        }
      });
    })
  );
});
