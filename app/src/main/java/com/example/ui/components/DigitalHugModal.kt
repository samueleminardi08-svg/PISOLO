package com.example.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.data.PhotoRepository
import com.example.model.PisoloData
import com.example.model.SupportMessage
import com.example.ui.theme.HeartRed
import com.example.ui.theme.PastelBlueAccent
import com.example.ui.theme.PastelBlueContainer
import com.example.ui.theme.PastelPinkContainer
import com.example.ui.theme.PastelPinkPrimary
import com.example.ui.theme.WarmOutline
import com.example.ui.theme.WarmTextPrimary
import com.example.ui.theme.WarmTextSecondary
import kotlin.random.Random

@Composable
fun DigitalHugModal(
    initialMessage: SupportMessage? = null,
    onDismissRequest: () -> Unit
) {
    val context = LocalContext.current
    val photoRepo = remember { PhotoRepository.getInstance(context) }
    val customPhotoMap by photoRepo.customPhotoMap.collectAsState()

    val messages = PisoloData.messaggiDelCuore
    var currentIndex by remember(initialMessage) {
        mutableIntStateOf(
            if (initialMessage != null) {
                messages.indexOfFirst { it.id == initialMessage.id }.coerceAtLeast(0)
            } else {
                Random.nextInt(messages.size)
            }
        )
    }

    val currentMessage = messages[currentIndex]
    val customPhotoUri = customPhotoMap[currentMessage.id]

    // Pulsazione dolce del cuore
    val infiniteTransition = rememberInfiniteTransition(label = "hug_pulse")
    val heartScale by infiniteTransition.animateFloat(
        initialValue = 1.0f,
        targetValue = 1.15f,
        animationSpec = infiniteRepeatable(
            animation = tween(900, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "heart_pulse_scale"
    )

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        )
    ) {
        Card(
            shape = RoundedCornerShape(32.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 24.dp)
                .border(1.dp, WarmOutline, RoundedCornerShape(32.dp))
                .testTag("digital_hug_modal")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Header bar con cuoricino e chiudi
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = PastelPinkContainer
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = null,
                                tint = HeartRed,
                                modifier = Modifier
                                    .size(16.dp)
                                    .scale(heartScale)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Abbraccio digitale",
                                style = MaterialTheme.typography.labelMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = PastelPinkPrimary
                                )
                            )
                        }
                    }

                    IconButton(
                        onClick = onDismissRequest,
                        modifier = Modifier.testTag("close_hug_modal")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Chiudi",
                            tint = WarmTextSecondary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                AnimatedContent(
                    targetState = currentMessage,
                    transitionSpec = {
                        fadeIn(tween(400)) togetherWith fadeOut(tween(250))
                    },
                    label = "message_transition"
                ) { msg ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        // Foto o illustrazione associata
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(190.dp)
                        ) {
                            PisoloPhotoView(
                                imageUri = customPhotoUri,
                                drawableResId = msg.drawableResId,
                                contentDescription = msg.titolo,
                                shape = RoundedCornerShape(24.dp),
                                allowChangePhoto = true,
                                onPhotoSelected = { uri ->
                                    photoRepo.setMessagePhotoUri(msg.id, uri.toString())
                                },
                                modifier = Modifier.matchParentSize()
                            )

                            // Badge categoria in sovraimpressione
                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = Color.Black.copy(alpha = 0.45f),
                                modifier = Modifier
                                    .align(Alignment.BottomStart)
                                    .padding(12.dp)
                            ) {
                                Text(
                                    text = "${msg.iconEmoji} ${msg.categoriaTag}",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = Color.White,
                                        fontWeight = FontWeight.Medium
                                    ),
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Titolo del messaggio
                        Text(
                            text = msg.titolo,
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold,
                                color = WarmTextPrimary,
                                textAlign = TextAlign.Center
                            ),
                            modifier = Modifier.padding(horizontal = 4.dp)
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // Testo d'affetto
                        Surface(
                            shape = RoundedCornerShape(20.dp),
                            color = PastelPinkContainer.copy(alpha = 0.5f),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = msg.testo,
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    lineHeight = 24.sp,
                                    color = WarmTextPrimary
                                ),
                                modifier = Modifier.padding(16.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Firma dolce
                        Text(
                            text = "💌 ${msg.firma}",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                                fontWeight = FontWeight.Medium,
                                color = PastelPinkPrimary
                            ),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Pulsanti Azione
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    FilledTonalButton(
                        onClick = {
                            var next = Random.nextInt(messages.size)
                            if (messages.size > 1 && next == currentIndex) {
                                next = (currentIndex + 1) % messages.size
                            }
                            currentIndex = next
                        },
                        modifier = Modifier
                            .weight(1f)
                            .testTag("another_hug_button"),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.filledTonalButtonColors(
                            containerColor = PastelBlueContainer,
                            contentColor = PastelBlueAccent
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            "Altro abbraccio",
                            style = MaterialTheme.typography.labelLarge
                        )
                    }

                    Button(
                        onClick = onDismissRequest,
                        modifier = Modifier
                            .weight(1f)
                            .testTag("hug_thanks_button"),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PastelPinkPrimary,
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            "Grazie amore ❤️",
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }
            }
        }
    }
}
