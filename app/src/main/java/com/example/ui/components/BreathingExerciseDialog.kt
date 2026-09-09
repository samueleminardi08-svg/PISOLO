package com.example.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.ui.theme.PastelBlueAccent
import com.example.ui.theme.PastelBlueContainer
import com.example.ui.theme.PastelBlueLight
import com.example.ui.theme.PastelPinkContainer
import com.example.ui.theme.PastelPinkLight
import com.example.ui.theme.PastelPinkPrimary
import com.example.ui.theme.WarmTextPrimary
import com.example.ui.theme.WarmTextSecondary
import kotlinx.coroutines.delay

enum class BreathPhase(val title: String, val subtitle: String, val durationMs: Int, val targetScale: Float) {
    INHALE("Inspira piano...", "Riempi il pancino d'aria e pace", 4000, 1.35f),
    HOLD("Trattieni...", "Senti la calma dentro di te", 4000, 1.35f),
    EXHALE("Espira dolcemente...", "Lascia andare ogni peso e tensione", 4000, 0.85f),
    REST("Pausa serena...", "Sei al sicuro qui", 2000, 0.85f)
}

@Composable
fun BreathingExerciseDialog(
    onDismissRequest: () -> Unit
) {
    var isRunning by remember { mutableStateOf(true) }
    var currentPhase by remember { mutableStateOf(BreathPhase.INHALE) }
    var cycleCount by remember { mutableIntStateOf(1) }

    LaunchedEffect(isRunning, currentPhase) {
        if (isRunning) {
            delay(currentPhase.durationMs.toLong())
            currentPhase = when (currentPhase) {
                BreathPhase.INHALE -> BreathPhase.HOLD
                BreathPhase.HOLD -> BreathPhase.EXHALE
                BreathPhase.EXHALE -> BreathPhase.REST
                BreathPhase.REST -> {
                    cycleCount++
                    BreathPhase.INHALE
                }
            }
        }
    }

    val animatedScale by animateFloatAsState(
        targetValue = currentPhase.targetScale,
        animationSpec = tween(
            durationMillis = currentPhase.durationMs,
            easing = FastOutSlowInEasing
        ),
        label = "breath_scale"
    )

    val circleColor by animateColorAsState(
        targetValue = when (currentPhase) {
            BreathPhase.INHALE -> PastelPinkLight
            BreathPhase.HOLD -> PastelBlueLight.copy(alpha = 0.8f)
            BreathPhase.EXHALE -> PastelBlueContainer
            BreathPhase.REST -> PastelPinkContainer
        },
        animationSpec = tween(1000),
        label = "breath_color"
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
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 24.dp)
                .border(1.dp, PastelBlueLight.copy(alpha = 0.6f), RoundedCornerShape(28.dp))
                .testTag("breathing_dialog")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Header del Dialog
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "🫧",
                            fontSize = 22.sp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Respira con Pisolo",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = WarmTextPrimary
                            )
                        )
                    }

                    IconButton(
                        onClick = onDismissRequest,
                        modifier = Modifier.testTag("close_breathing_dialog")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Chiudi esercizio",
                            tint = WarmTextSecondary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Cerchio di respirazione guidato
                Box(
                    modifier = Modifier
                        .size(210.dp)
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    // Cerchio esterno con alone
                    Box(
                        modifier = Modifier
                            .size(170.dp)
                            .scale(animatedScale)
                            .clip(CircleShape)
                            .background(
                                Brush.radialGradient(
                                    colors = listOf(
                                        circleColor,
                                        circleColor.copy(alpha = 0.35f),
                                        Color.Transparent
                                    )
                                )
                            )
                    )

                    // Cerchio interno
                    Box(
                        modifier = Modifier
                            .size(110.dp)
                            .scale(animatedScale * 0.9f)
                            .clip(CircleShape)
                            .background(circleColor),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "🦈",
                            fontSize = 36.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Testo della fase attuale
                Text(
                    text = currentPhase.title,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = PastelPinkPrimary
                    ),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = currentPhase.subtitle,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = WarmTextSecondary
                    ),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(12.dp))

                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = PastelPinkContainer.copy(alpha = 0.5f),
                    modifier = Modifier.padding(horizontal = 12.dp)
                ) {
                    Text(
                        text = "Ciclo di respirazione: $cycleCount",
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = WarmTextSecondary
                        ),
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Controlli Play / Pause / Chiudi
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FilledTonalButton(
                        onClick = { isRunning = !isRunning },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.filledTonalButtonColors(
                            containerColor = PastelBlueContainer,
                            contentColor = PastelBlueAccent
                        )
                    ) {
                        Icon(
                            imageVector = if (isRunning) Icons.Default.Pause else Icons.Default.PlayArrow,
                            contentDescription = if (isRunning) "Pausa" else "Riprendi"
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(if (isRunning) "Pausa" else "Riprendi")
                    }

                    Button(
                        onClick = onDismissRequest,
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PastelPinkPrimary,
                            contentColor = Color.White
                        )
                    ) {
                        Text("Mi sento meglio ✨")
                    }
                }
            }
        }
    }
}
