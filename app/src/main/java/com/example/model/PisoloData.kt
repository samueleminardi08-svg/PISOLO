package com.example.model

import androidx.annotation.DrawableRes
import com.example.R

/*
 * =========================================================================================
 * 💌 COME PERSONALIZZARE QUESTI MESSAGGI PER LA TUA RAGAZZA (ISTRUZIONI SEMPLICI) 💌
 * =========================================================================================
 * 1. TESTO: Modifica direttamente le frasi tra virgolette nel campo "testo".
 * 2. TITOLO: Cambia il titolo dolce (es: "Per quando ti senti sola", "Un bacio sulla fronte").
 * 3. FOTO: Per usare una tua foto personale, copiala nella cartella:
 *    app/src/main/res/drawable/ (es. tua_foto.jpg con solo lettere minuscole e trattini bassi)
 *    e poi scrivi qui: drawableResId = R.drawable.tua_foto
 *    Oppure lascia le dolci illustrazioni di Pisolo predefinite!
 * =========================================================================================
 */

data class SupportMessage(
    val id: Int,
    val titolo: String,
    val testo: String,
    val firma: String = "Con tutto il mio amore ❤️",
    @DrawableRes val drawableResId: Int? = null,
    val imageUri: String? = null,
    val iconEmoji: String = "🦈",
    val categoriaTag: String = "Per te"
)

data class ComfortTip(
    val id: String,
    val categoria: ComfortCategory,
    val titolo: String,
    val descrizione: String,
    val iconEmoji: String,
    val badge: String? = null,
    val hasBreathingAction: Boolean = false
)

enum class ComfortCategory(val label: String, val emoji: String) {
    ALL("Tutte", "🌸"),
    GIU_DI_MORALE("Giù di morale", "🌧️"),
    ANSIA_STRESS("Ansia & Stress", "🫧"),
    MALESSERE_FISICO("Malessere fisico", "🩹"),
    COCCOLE("Dolci coccole", "🧸")
}

object PisoloData {

    // =====================================================================================
    // 🌟 1. I TUOI MESSAGGI DI SUPPORTO CON FOTO (TONO DELICATO, CALMO E SINCERO)
    // =====================================================================================
    val messaggiDelCuore = listOf(
        SupportMessage(
            id = 1,
            titolo = "Chiudi gli occhi per un istante",
            testo = "So che ci sono momenti in cui tutto sembra pesare un po' di più e trovare le energie è faticoso. Chiudi gli occhi per qualche istante, lascia andare le spalle e immagina che io sia lì vicino a te a stringerti la mano con delicatezza. Non sei da sola, nemmeno per un istante.",
            firma = "Il tuo ragazzo, sempre con te",
            drawableResId = R.drawable.img_pisolo_hug,
            iconEmoji = "🫂",
            categoriaTag = "Vicinanza sincera"
        ),
        SupportMessage(
            id = 2,
            titolo = "Oggi non devi dimostrare nulla",
            testo = "Il mondo può aspettare un momento. Se il corpo o la mente ti chiedono una pausa, conceditela senza sentirti in colpa. Mettiti comoda, bevi qualcosa di caldo e lascia che la tensione scivoli via piano piano.",
            firma = "Con tutto l'affetto del mondo",
            drawableResId = R.drawable.img_pisolo_tea,
            iconEmoji = "🫖",
            categoriaTag = "Calma & Riposo"
        ),
        SupportMessage(
            id = 3,
            titolo = "Hai affrontato tante cose con coraggio",
            testo = "È del tutto normale avere giornate in cui ci si sente più fragili o smarrite. Ricordati con gentilezza di quante cose hai già superato finora. Questo momento di stanchezza passerà: concediti solo di respirare un passo alla volta.",
            firma = "Sempre al tuo fianco",
            drawableResId = R.drawable.img_pisolo_love,
            iconEmoji = "✨",
            categoriaTag = "Forza discreta"
        ),
        SupportMessage(
            id = 4,
            titolo = "Metti da parte i pensieri per stanotte",
            testo = "Per questa notte hai fatto abbastanza. Lascia le preoccupazioni fuori dalla stanza e permetti al tuo respiro di rallentare. Sono qui che ti penso con tutta la dolcezza possibile. Riposa con serenità.",
            firma = "Buonanotte con il cuore",
            drawableResId = R.drawable.img_pisolo_sleep,
            iconEmoji = "🌙",
            categoriaTag = "Serenità notturna"
        ),
        SupportMessage(
            id = 5,
            titolo = "Una carezza leggera quando non stai bene",
            testo = "Se senti malessere, stanchezza o fastidio, non pretendere troppo da te stessa. Ascolta il tuo corpo, riposati e ricordati che per qualsiasi cosa puoi sempre cercarmi.",
            firma = "Un pensiero fisso per te",
            drawableResId = R.drawable.img_pisolo_hug,
            iconEmoji = "🩹",
            categoriaTag = "Cura & Delicatezza"
        ),
        SupportMessage(
            id = 6,
            titolo = "La tua sensibilità è preziosa",
            testo = "A volte sentire le cose in modo profondo può sembrare stancante, ma la tua gentilezza e il tuo cuore sono una ricchezza rara. Prenditi cura di te stessa con lo stesso affetto sincero che doni a chi ami.",
            firma = "Con amore e stima profonda",
            drawableResId = R.drawable.img_pisolo_love,
            iconEmoji = "🌸",
            categoriaTag = "Valore & Dolcezza"
        ),
        SupportMessage(
            id = 7,
            titolo = "Un porto sicuro, sempre",
            testo = "Qualsiasi cosa tu stia provando in questo istante, ricordati che hai un porto sicuro in cui rifugiarti. Puoi piangere, sfogarti o restare in silenzio: non devi nascondere nulla.",
            firma = "Sempre qui per ascoltarti",
            drawableResId = R.drawable.img_pisolo_tea,
            iconEmoji = "🤍",
            categoriaTag = "Presenza sincera"
        ),
        SupportMessage(
            id = 8,
            titolo = "Un respiro lento alla volta",
            testo = "Le giornate no capitano e non c'è nulla di sbagliato in te. Sii paziente con te stessa e concediti una giornata tranquilla. Io ti sono vicino.",
            firma = "Con un abbraccio sincero",
            drawableResId = R.drawable.img_pisolo_sleep,
            iconEmoji = "💌",
            categoriaTag = "Pace interiore"
        )
    )

    // =====================================================================================
    // 🧸 2. CONSIGLI & SUGGERIMENTI DOLCI DIVISI PER STATO D'ANIMO
    // =====================================================================================
    val consigliCoccole = listOf(
        ComfortTip(
            id = "tip_breath",
            categoria = ComfortCategory.ANSIA_STRESS,
            titolo = "Fai un respiro guidato con Pisolo",
            descrizione = "Prenditi 60 secondi: segui il ritmo calmo del cerchio per rallentare i battiti e svuotare i pensieri.",
            iconEmoji = "🫁",
            badge = "Esercizio interattivo",
            hasBreathingAction = true
        ),
        ComfortTip(
            id = "tip_tea",
            categoria = ComfortCategory.MALESSERE_FISICO,
            titolo = "Prepara una tisana calda o camomilla",
            descrizione = "Un po' di calore tra le mani rilassa i muscoli dello stomaco e calma la mente. Aggiungi un cucchiaino di miele dolce.",
            iconEmoji = "🍵"
        ),
        ComfortTip(
            id = "tip_blanket",
            categoria = ComfortCategory.GIU_DI_MORALE,
            titolo = "Fai il 'burrito' nella coperta più morbida",
            descrizione = "Arrotolati stretta nella coperta soffice, metti i cuscini comodi e accendi una lucina soffusa o la tua candela preferita.",
            iconEmoji = "🛋️"
        ),
        ComfortTip(
            id = "tip_comfort_movie",
            categoria = ComfortCategory.GIU_DI_MORALE,
            titolo = "Guarda il tuo film o serie comfort",
            descrizione = "Qualcosa che hai già visto mille volte, leggero e che ti fa sorridere senza dover concentrarti troppo.",
            iconEmoji = "🎬"
        ),
        ComfortTip(
            id = "tip_shoulders",
            categoria = ComfortCategory.ANSIA_STRESS,
            titolo = "Rilascia le spalle e la mandibola",
            descrizione = "Senza accorgertene stai tenendo i denti stretti e le spalle alte. Fai cadere le spalle giù, socchiudi le labbra e rilassa la fronte.",
            iconEmoji = "💆‍♀️"
        ),
        ComfortTip(
            id = "tip_water",
            categoria = ComfortCategory.MALESSERE_FISICO,
            titolo = "Bevi un bicchiere d'acqua fresca",
            descrizione = "Spesso il mal di testa o la stanchezza derivano da un po' di disidratazione. Fai piccoli sorsi lenti.",
            iconEmoji = "💧"
        ),
        ComfortTip(
            id = "tip_warm_pack",
            categoria = ComfortCategory.MALESSERE_FISICO,
            titolo = "Borsa dell'acqua calda sulla pancia o collo",
            descrizione = "Il calore costante scioglie le tensioni fisiche e dà un immediato senso di sollievo e protezione.",
            iconEmoji = "🔥"
        ),
        ComfortTip(
            id = "tip_music",
            categoria = ComfortCategory.COCCOLE,
            titolo = "Metti la tua playlist acustica preferita",
            descrizione = "Note morbide a basso volume, suoni di pioggia o piano leggero per creare una bolla di pace solo per te.",
            iconEmoji = "🎵"
        ),
        ComfortTip(
            id = "tip_hands",
            categoria = ComfortCategory.COCCOLE,
            titolo = "Metti una crema profumata sulle mani",
            descrizione = "Massaggia le dita e i polsi con delicatezza; il profumo familiare e piacevole stimola endorfine rilassanti.",
            iconEmoji = "🧴"
        )
    )

    // =====================================================================================
    // 🦈🐱 3. FRASI DOLCI E SINCERE DI PISOLO E MICIO
    // =====================================================================================
    val frasiMascottePisolo = listOf(
        "Prenditi tutto il tempo che ti serve: non devi correre.",
        "Fai un respiro lento. Sei al sicuro in questo momento.",
        "Non devi affrontare tutto subito, basta un solo minuto alla volta.",
        "Se ti senti stanca, è del tutto lecito fermarsi e riposare.",
        "Ricordati che c'è qualcuno che ti vuole un bene profondo e sincero.",
        "Pisolo e Micio fanno la guardia silenziosa mentre ritrovi la calma."
    )
}
