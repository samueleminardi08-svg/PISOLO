const CACHE_NAME = 'pisolo-micio-cache-v10';
const STATIC_ASSETS = [
  '/',
  '/index.html',
  '/backgrounds_data.js',
  '/manifest.json',
  '/icon.svg',
  '/icon-192.png',
  '/icon-512.png',
  '/icon-180.png',
  '/apple-touch-icon.png',
  '/apple-touch-icon-precomposed.png',
  '/favicon.png',
  '/assets/shark_1.jpg',
  '/assets/kitty_1.jpg',
  '/assets/photo_featured.jpg',
  '/assets/bg_beach.jpg',
  '/assets/bg_grove.jpg',
  '/assets/bg_lake.jpg',
  '/assets/bg_lavender.jpg',
  '/assets/bg_zen_garden.jpg',
  '/assets/bg_sunset_beach.jpg',
  '/assets/bg_wildflowers.jpg',
  '/assets/bg_waterfall.jpg',
  '/assets/bg_morning_hills.jpg',
  '/assets/bg_riverbank.jpg'
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
          return caches.match('/index.html');
        }
      });
    })
  );
});
