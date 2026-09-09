package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CleaningServices
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.DashedPinkOutline
import com.example.ui.theme.DeepRosePrimary
import com.example.ui.theme.PastelBlueAccent
import com.example.ui.theme.PastelBlueContainer
import com.example.ui.theme.PastelPinkContainer
import com.example.ui.theme.PastelPinkLight
import com.example.ui.theme.PastelPinkPrimary
import com.example.ui.theme.WarmOutline
import com.example.ui.theme.WarmTextMuted
import com.example.ui.theme.WarmTextPrimary
import com.example.ui.theme.WarmTextSecondary
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun VentingSection(
    modifier: Modifier = Modifier
) {
    var ventText by remember { mutableStateOf("") }
    var showClearedNotice by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Card(
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.85f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier
            .fillMaxWidth()
            .drawBehind {
                val stroke = Stroke(
                    width = 2.dp.toPx(),
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(14f, 10f), 0f)
                )
                drawRoundRect(
                    color = Color(0xFFF06292).copy(alpha = 0.45f),
                    size = size,
                    cornerRadius = CornerRadius(28.dp.toPx(), 28.dp.toPx()),
                    style = stroke
                )
            }
            .testTag("venting_section_card")
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            // Header: SFOGATI LIBERAMENTE & CANCELLA button
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "✍️", fontSize = 16.sp)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "SFOGATI LIBERAMENTE",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = PastelPinkPrimary,
                            letterSpacing = 1.2.sp,
                            fontSize = 11.sp
                        )
                    )
                }

                if (ventText.isNotEmpty()) {
                    Text(
                        text = "CANCELLA",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = DeepRosePrimary,
                            letterSpacing = 0.8.sp,
                            fontSize = 11.sp
                        ),
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .clickable {
                                ventText = ""
                                showClearedNotice = true
                                coroutineScope.launch {
                                    delay(3000)
                                    showClearedNotice = false
                                }
                            }
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                            .testTag("clear_vent_top_button")
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Campo di testo per lo sfogo (trasparente/morbido come da design)
            OutlinedTextField(
                value = ventText,
                onValueChange = { ventText = it },
                placeholder = {
                    Text(
                        text = "Scrivi cosa senti... sparisce quando chiudi.",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = PastelPinkPrimary.copy(alpha = 0.5f),
                            fontWeight = FontWeight.Medium
                        )
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .testTag("venting_text_input"),
                shape = RoundedCornerShape(18.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PastelPinkPrimary.copy(alpha = 0.6f),
                    unfocusedBorderColor = PastelPinkLight.copy(alpha = 0.4f),
                    focusedContainerColor = PastelPinkContainer.copy(alpha = 0.2f),
                    unfocusedContainerColor = PastelPinkContainer.copy(alpha = 0.1f),
                    cursorColor = PastelPinkPrimary
                )
            )

            // Bottone "Lascia andare i pensieri nel mare"
            AnimatedVisibility(
                visible = ventText.isNotBlank(),
                enter = fadeIn(tween(250)),
                exit = fadeOut(tween(200))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    FilledTonalButton(
                        onClick = {
                            ventText = ""
                            showClearedNotice = true
                            coroutineScope.launch {
                                delay(3000)
                                showClearedNotice = false
                            }
                        },
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.filledTonalButtonColors(
                            containerColor = PastelBlueContainer,
                            contentColor = PastelBlueAccent
                        ),
                        modifier = Modifier.testTag("release_vent_button")
                    ) {
                        Text(
                            text = "🌊 Lascia andare nel mare",
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }

            // Feedback dopo cancellazione
            AnimatedVisibility(
                visible = showClearedNotice,
                enter = fadeIn(tween(300)),
                exit = fadeOut(tween(300))
            ) {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = PastelPinkContainer,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "🫧", fontSize = 16.sp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Foglio pulito: pensieri lasciati andare nel mare di Pisolo ✨",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = DeepRosePrimary,
                                fontWeight = FontWeight.Medium
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Disclaimer rassicurante in corsivo
            Text(
                text = "*Niente di quello che scrivi qui viene salvato o inviato.*",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = PastelPinkPrimary.copy(alpha = 0.6f),
                    fontStyle = FontStyle.Italic,
                    fontSize = 11.sp,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
