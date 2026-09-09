package com.example.ui.components

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddPhotoAlternate
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.R
import com.example.ui.theme.PastelPinkContainer
import com.example.ui.theme.PastelPinkLight
import com.example.ui.theme.PastelPinkPrimary

@Composable
fun PisoloPhotoView(
    modifier: Modifier = Modifier,
    imageUri: String? = null,
    @DrawableRes drawableResId: Int? = null,
    contentDescription: String? = null,
    shape: RoundedCornerShape = RoundedCornerShape(20.dp),
    allowChangePhoto: Boolean = true,
    onPhotoSelected: ((Uri) -> Unit)? = null
) {
    val context = LocalContext.current
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null && onPhotoSelected != null) {
            try {
                context.contentResolver.takePersistableUriPermission(
                    uri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
            } catch (_: Exception) {
                // Non-persistable URIs handled smoothly
            }
            onPhotoSelected(uri)
        }
    }

    Box(
        modifier = modifier
            .clip(shape)
            .background(PastelPinkContainer)
            .border(1.dp, PastelPinkLight.copy(alpha = 0.5f), shape),
        contentAlignment = Alignment.Center
    ) {
        when {
            // 1. Foto scelta dall'utente dalla Galleria (URI locale)
            !imageUri.isNullOrEmpty() -> {
                SubcomposeAsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(Uri.parse(imageUri))
                        .crossfade(true)
                        .build(),
                    contentDescription = contentDescription,
                    contentScale = ContentScale.Crop,
                    loading = {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(28.dp),
                                color = PastelPinkPrimary,
                                strokeWidth = 2.5.dp
                            )
                        }
                    },
                    error = {
                        // Fallback se il file URI non è più disponibile
                        if (drawableResId != null) {
                            Image(
                                painter = painterResource(id = drawableResId),
                                contentDescription = contentDescription,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        } else {
                            Image(
                                painter = painterResource(id = R.drawable.img_pisolo_hug),
                                contentDescription = contentDescription,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }

            // 2. Foto o illustrazione inclusa nel progetto (drawable)
            drawableResId != null -> {
                Image(
                    painter = painterResource(id = drawableResId),
                    contentDescription = contentDescription,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            // 3. Fallback predefinito di Pisolo
            else -> {
                Image(
                    painter = painterResource(id = R.drawable.img_pisolo_hug),
                    contentDescription = contentDescription,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        // Bottone/Badge per cambiare foto al tocco
        if (allowChangePhoto && onPhotoSelected != null) {
            Surface(
                shape = CircleShape,
                color = Color.Black.copy(alpha = 0.55f),
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
                    .clip(CircleShape)
                    .clickable {
                        photoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.CameraAlt,
                        contentDescription = "Cambia foto dalla galleria",
                        tint = Color.White,
                        modifier = Modifier.size(13.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Foto",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = Color.White,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }
    }
}
