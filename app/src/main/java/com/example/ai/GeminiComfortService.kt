package com.example.ai

import android.util.Log
import com.example.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.TimeUnit
import kotlin.random.Random

data class AiComfortMessage(
    val quote: String,
    val consiglio: String,
    val source: String = "Pisolo & Micio AI 🦈🐱"
)

object GeminiComfortService {
    private const val TAG = "GeminiComfortService"
    private val JSON_MEDIA_TYPE = "application/json; charset=utf-8".toMediaType()

    private val client = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .build()

    // Fallback locali caldi e affettuosi
    private val fallbackQuotes = listOf(
        "Sei il mio pensiero più dolce, riposa tranquilla sapendo che per te ci sono e ci sarò sempre.",
        "Non devi essere forte ogni momento: oggi lascia fare a me, appoggia la testa e respira piano.",
        "La tua dolcezza illumina ogni mia giornata. Prenditi cura di te con tutta la calma del mondo.",
        "Anche quando fuori piove o ti senti giù, ricorda che il mio cuore è una casa calda sempre aperta per te.",
        "Chiudi gli occhi e immagina un abbraccio infinito: sei la cosa più preziosa che ho."
    )

    private val fallbackTips = listOf(
        "Bevi una tisana calda con un goccio di miele, accoccolati con una coperta morbida e concediti del tempo senza pensare a nulla.",
        "Rilassa le spalle e la mascella, metti una musica soffusa e respira profondamente per 2 minuti.",
        "Metti da parte le notifiche per un'ora: concediti il lusso di stare in silenzio e ascoltare il tuo corpo.",
        "Bevi un bicchiere d'acqua fresca e prova a guardare una foto di un bel ricordo insieme per ritrovare un sorriso dolce."
    )

    suspend fun generateComfortMessage(promptCategory: String = "dolcezza"): AiComfortMessage = withContext(Dispatchers.IO) {
        val apiKey = BuildConfig.GEMINI_API_KEY
        if (apiKey.isNullOrBlank() || apiKey == "MY_GEMINI_API_KEY") {
            Log.d(TAG, "No valid GEMINI_API_KEY in BuildConfig, using lovely fallback")
            return@withContext getRandomFallback()
        }

        val systemPrompt = """
            Sei 'Pisolo & Micio', due dolci mascotte (uno squaletto blu soffice e un gattino tenero) create con amore da un ragazzo per confortare e coccolare la sua ragazza nei momenti in cui è triste, in ansia o non sta bene.
            Genera in lingua italiana:
            1) Una frase d'amore o di conforto dolce, sincera e poetica (massimo 25 parole).
            2) Un consiglio pratico, caldo e non invasivo di cura di sé per stare meglio subito (massimo 30 parole).
            
            Rispondi ESCLUSIVAMENTE in formato JSON valido con questa struttura esatta:
            {
              "quote": "testo della frase d'amore",
              "consiglio": "testo del consiglio pratico"
            }
        """.trimIndent()

        val userPrompt = "Genera una nuova frase d'amore e un consiglio affettuoso per oggi (tema: $promptCategory)."

        val requestJson = JSONObject().apply {
            put("systemInstruction", JSONObject().apply {
                put("parts", JSONArray().apply {
                    put(JSONObject().put("text", systemPrompt))
                })
            })
            put("contents", JSONArray().apply {
                put(JSONObject().apply {
                    put("parts", JSONArray().apply {
                        put(JSONObject().put("text", userPrompt))
                    })
                })
            })
            put("generationConfig", JSONObject().apply {
                put("responseMimeType", "application/json")
                put("temperature", 0.8)
                put("maxOutputTokens", 200)
            })
        }

        val modelsToTry = listOf("gemini-3.5-flash-lite", "gemini-3.5-flash")

        for (modelName in modelsToTry) {
            try {
                val url = "https://generativelanguage.googleapis.com/v1beta/models/$modelName:generateContent?key=$apiKey"
                val requestBody = requestJson.toString().toRequestBody(JSON_MEDIA_TYPE)
                val request = Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build()

                val response = client.newCall(request).execute()
                val responseBody = response.body?.string()

                if (response.isSuccessful && !responseBody.isNullOrEmpty()) {
                    val root = JSONObject(responseBody)
                    val candidates = root.optJSONArray("candidates")
                    if (candidates != null && candidates.length() > 0) {
                        val content = candidates.getJSONObject(0).optJSONObject("content")
                        val parts = content?.optJSONArray("parts")
                        if (parts != null && parts.length() > 0) {
                            val text = parts.getJSONObject(0).optString("text")
                            val cleanText = text.trim()
                                .removePrefix("```json")
                                .removePrefix("```")
                                .removeSuffix("```")
                                .trim()
                            val jsonResult = JSONObject(cleanText)
                            val q = jsonResult.optString("quote")
                            val c = jsonResult.optString("consiglio")
                            if (q.isNotEmpty() && c.isNotEmpty()) {
                                return@withContext AiComfortMessage(
                                    quote = q,
                                    consiglio = c,
                                    source = "Generato con affetto da Pisolo & Micio AI ✨"
                                )
                            }
                        }
                    }
                } else {
                    Log.w(TAG, "Model $modelName error response: ${response.code} - $responseBody")
                }
            } catch (e: Exception) {
                Log.w(TAG, "Failed calling $modelName", e)
            }
        }

        return@withContext getRandomFallback()
    }

    fun getRandomFallback(): AiComfortMessage {
        val q = fallbackQuotes[Random.nextInt(fallbackQuotes.size)]
        val t = fallbackTips[Random.nextInt(fallbackTips.size)]
        return AiComfortMessage(
            quote = q,
            consiglio = t,
            source = "Pisolo & Micio • Sempre con te ❤️"
        )
    }
}
