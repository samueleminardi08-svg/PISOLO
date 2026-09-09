package com.example.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.theme.HeartRed
import com.example.ui.theme.PastelBlueAccent
import com.example.ui.theme.PastelBlueContainer
import com.example.ui.theme.PastelBlueLight
import com.example.ui.theme.PastelPinkLight
import com.example.ui.theme.PastelPinkPrimary

/**
 * Visual badge/avatar del tenero duo unico di mascotte: lo squaletto Pisolo e il gattino Micio insieme.
 */
@Composable
fun PisoloMascotAvatar(
    modifier: Modifier = Modifier,
    size: Dp = 72.dp,
    showSpeechBubble: Boolean = false,
    speechText: String = "Siamo sempre con te! 💕"
) {
    val infiniteTransition = rememberInfiniteTransition(label = "mascot_floating")
    val offsetY by infiniteTransition.animateFloat(
        initialValue = -4f,
        targetValue = 4f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "floating_offset"
    )

    val rotationAngle by infiniteTransition.animateFloat(
        initialValue = -3f,
        targetValue = 3f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "floating_rotation"
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .offset(y = offsetY.dp)
            .testTag("pisolo_mascot_avatar")
    ) {
        Box(
            modifier = Modifier
                .size(size)
                .shadow(8.dp, CircleShape, spotColor = PastelPinkPrimary.copy(alpha = 0.3f))
                .clip(CircleShape)
                .background(PastelPinkLight.copy(alpha = 0.5f)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_pisolo_and_micio),
                contentDescription = "Pisolo lo squaletto e Micio il gattino insieme",
                modifier = Modifier
                    .size(size)
                    .clip(CircleShape)
            )

            // Piccolo cuoricino fluttuante
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = null,
                tint = HeartRed.copy(alpha = 0.9f),
                modifier = Modifier
                    .size(18.dp)
                    .align(Alignment.TopEnd)
                    .offset(x = (-2).dp, y = 2.dp)
            )
        }

        if (showSpeechBubble) {
            Spacer(modifier = Modifier.width(10.dp))
            Surface(
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp, bottomEnd = 16.dp, bottomStart = 4.dp),
                color = MaterialTheme.colorScheme.surface,
                shadowElevation = 2.dp,
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Text(
                    text = speechText,
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                )
            }
        }
    }
}

/**
 * Doodle kawaii personalizzato disegnato su Canvas con lo squaletto Pisolo
 */
@Composable
fun KawaiiSharkDoodle(
    modifier: Modifier = Modifier,
    primaryColor: Color = PastelBlueLight,
    accentColor: Color = PastelPinkPrimary
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // Corpo dello squaletto morbido
        val bodyPath = Path().apply {
            moveTo(w * 0.2f, h * 0.5f)
            // Schiena e pinna dorsale
            cubicTo(w * 0.25f, h * 0.25f, w * 0.45f, h * 0.2f, w * 0.55f, h * 0.35f)
            // Pinna superiore
            lineTo(w * 0.6f, h * 0.15f)
            lineTo(w * 0.65f, h * 0.35f)
            // Verso la coda
            cubicTo(w * 0.75f, h * 0.35f, w * 0.85f, h * 0.4f, w * 0.9f, h * 0.5f)
            // Coda
            lineTo(w * 0.98f, h * 0.38f)
            lineTo(w * 0.93f, h * 0.52f)
            lineTo(w * 0.98f, h * 0.66f)
            // Pancia morbida
            cubicTo(w * 0.8f, h * 0.75f, w * 0.45f, h * 0.8f, w * 0.25f, h * 0.65f)
            close()
        }

        // Riempi corpo
        drawPath(path = bodyPath, color = primaryColor, style = Fill)
        drawPath(path = bodyPath, color = primaryColor.copy(alpha = 0.8f), style = Stroke(width = 3f))

        // Pancia chiara
        val bellyPath = Path().apply {
            moveTo(w * 0.25f, h * 0.55f)
            cubicTo(w * 0.4f, h * 0.75f, w * 0.7f, h * 0.7f, w * 0.85f, h * 0.55f)
            cubicTo(w * 0.65f, h * 0.62f, w * 0.4f, h * 0.62f, w * 0.25f, h * 0.55f)
            close()
        }
        drawPath(path = bellyPath, color = Color.White.copy(alpha = 0.85f), style = Fill)

        // Occhietto dolce
        drawCircle(
            color = Color(0xFF332A2E),
            radius = w * 0.035f,
            center = Offset(w * 0.35f, h * 0.45f)
        )
        // Riflesso occhio
        drawCircle(
            color = Color.White,
            radius = w * 0.012f,
            center = Offset(w * 0.34f, h * 0.44f)
        )

        // Guancetta arrossata (blush)
        drawCircle(
            color = accentColor.copy(alpha = 0.6f),
            radius = w * 0.04f,
            center = Offset(w * 0.36f, h * 0.54f)
        )

        // Sorrisino dolce
        val smilePath = Path().apply {
            moveTo(w * 0.28f, h * 0.52f)
            quadraticTo(w * 0.31f, h * 0.56f, w * 0.34f, h * 0.52f)
        }
        drawPath(path = smilePath, color = Color(0xFF332A2E), style = Stroke(width = 2.5f))
    }
}
