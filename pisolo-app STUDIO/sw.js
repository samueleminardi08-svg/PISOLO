importScripts("https://cdn.onesignal.com/sdks/web/v16/OneSignalSDK.sw.js");

// (install, activate, fetch handler, ecc. — non toccare)
const CACHE_NAME = 'pisolo-micio-cache-v19';
const STATIC_ASSETS = [
  './',
  './index.html',
  './script.js',
  './styles.css',
  './manifest.json',
  './assets/icons/icon.png',
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
  './assets/hugs/hug_warm_blanket_cocoa_1789076637148.jpg',
  './assets/mascots/img_01_0a586335395e.jpg',
  './assets/mascots/img_02_80bf04a43bbc.jpg',
  './assets/mascots/img_03_fa6339c60a33.jpg',
  './assets/mascots/img_04_474c0282a71d.jpg',
  './assets/mascots/img_05_44e40f9a0c35.jpg',
  './assets/mascots/img_06_7d757a1dd8a1.jpg',
  './assets/mascots/img_07_96256b3c039b.jpg',
  './assets/mascots/img_08_2c3322d80209.jpg',
  './assets/gallery/img_09_217ed04bd5bb.jpg',
  './assets/gallery/img_10_376b8a82b05b.jpg',
  './assets/gallery/img_11_d47deaf03d17.jpg',
  './assets/gallery/img_12_fc58bab3bfed.jpg',
  './assets/gallery/img_13_46d532346908.jpg',
  './assets/landscapes/img_14_71f7b0643166.jpg',
  './assets/landscapes/img_15_938af34ab049.jpg',
  './assets/landscapes/img_16_599c09a8ed3b.jpg',
  './assets/landscapes/img_17_016e937ee8b6.jpg',
  './assets/landscapes/img_18_c11de8ca4c11.jpg',
  './assets/landscapes/img_19_2ad37fb1fbd2.jpg',
  './assets/landscapes/img_20_a1294a4cb05c.jpg',
  './assets/landscapes/img_21_238c3b53e343.jpg',
  './assets/landscapes/img_22_24b7db2b4e85.jpg',
  './assets/landscapes/img_23_e58588a5599c.jpg'
];

self.addEventListener('install', (event) => {
  event.waitUntil(
    caches.open(CACHE_NAME).then((cache) => {
      return cache.addAll(STATIC_ASSETS).catch((err) => {
        console.warn('Some assets could not be pre-cached:', err);
      });
    }).then(() => self.skipWaiting())
  );
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
  if (event.request.url.includes('generativelanguage.googleapis.com')) {
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
