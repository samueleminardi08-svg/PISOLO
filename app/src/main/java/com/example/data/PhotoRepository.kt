package com.example.data

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PhotoRepository private constructor(private val context: Context) {
    private val prefs: SharedPreferences =
        context.applicationContext.getSharedPreferences("pisolo_photos_prefs", Context.MODE_PRIVATE)

    private val _heroPhotoUri = MutableStateFlow<String?>(prefs.getString("hero_photo_uri", null))
    val heroPhotoUri: StateFlow<String?> = _heroPhotoUri.asStateFlow()

    private val _customPhotoMap = MutableStateFlow<Map<Int, String>>(loadCustomPhotoMap())
    val customPhotoMap: StateFlow<Map<Int, String>> = _customPhotoMap.asStateFlow()

    private fun loadCustomPhotoMap(): Map<Int, String> {
        val map = mutableMapOf<Int, String>()
        prefs.all.forEach { (key, value) ->
            if (key.startsWith("msg_photo_") && value is String) {
                val id = key.removePrefix("msg_photo_").toIntOrNull()
                if (id != null) {
                    map[id] = value
                }
            }
        }
        return map
    }

    fun setHeroPhotoUri(uriString: String?) {
        tryTakePersistableUri(uriString)
        prefs.edit().putString("hero_photo_uri", uriString).apply()
        _heroPhotoUri.value = uriString
    }

    fun setMessagePhotoUri(messageId: Int, uriString: String?) {
        tryTakePersistableUri(uriString)
        if (uriString == null) {
            prefs.edit().remove("msg_photo_$messageId").apply()
        } else {
            prefs.edit().putString("msg_photo_$messageId", uriString).apply()
        }
        _customPhotoMap.value = loadCustomPhotoMap()
    }

    private fun tryTakePersistableUri(uriString: String?) {
        if (uriString.isNullOrEmpty()) return
        try {
            val uri = Uri.parse(uriString)
            context.contentResolver.takePersistableUriPermission(
                uri,
                Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
        } catch (_: Exception) {
            // Ignored if the URI provider doesn't support persistent grant
        }
    }

    companion object {
        @Volatile
        private var instance: PhotoRepository? = null

        fun getInstance(context: Context): PhotoRepository {
            return instance ?: synchronized(this) {
                instance ?: PhotoRepository(context.applicationContext).also { instance = it }
            }
        }
    }
}
