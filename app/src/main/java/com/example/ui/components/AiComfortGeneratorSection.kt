package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ai.AiComfortMessage
import com.example.ai.GeminiComfortService
import com.example.ui.theme.DeepRosePrimary
import com.example.ui.theme.HeartRed
import com.example.ui.theme.PastelBlueAccent
import com.example.ui.theme.PastelBlueContainer
import com.example.ui.theme.PastelBlueLight
import com.example.ui.theme.PastelPinkContainer
import com.example.ui.theme.PastelPinkLight
import com.example.ui.theme.PastelPinkPrimary
import com.example.ui.theme.WarmOutline
import com.example.ui.theme.WarmTextPrimary
import com.example.ui.theme.WarmTextSecondary
import kotlinx.coroutines.launch

@Composable
fun AiComfortGeneratorSection(
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    var currentAiMessage by remember {
        mutableStateOf(GeminiComfortService.getRandomFallback())
    }
    var isLoading by remember { mutableStateOf(false) }

    fun generateNew() {
        if (isLoading) return
        isLoading = true
        coroutineScope.launch {
            try {
                currentAiMessage = GeminiComfortService.generateComfortMessage("cura e affetto dolce")
            } finally {
                isLoading = false
            }
        }
    }

    Card(
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, WarmOutline, RoundedCornerShape(28.dp))
            .testTag("ai_comfort_generator_card")
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            PastelPinkContainer.copy(alpha = 0.45f),
                            PastelBlueContainer.copy(alpha = 0.25f),
                            Color.White
                        )
                    )
                )
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Intestazione con badge AI
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(14.dp),
                    color = PastelPinkPrimary.copy(alpha = 0.15f)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = null,
                            tint = DeepRosePrimary,
                            modifier = Modifier.size(15.dp)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "AI Pisolo & Micio ✨",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = DeepRosePrimary
                            )
                        )
                    }
                }

                Text(
                    text = "🦈 + 🐱",
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = WarmTextSecondary
                    )
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Pensieri & Consigli Nuovi per Te",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = WarmTextPrimary,
                    fontSize = 17.sp
                )
            )

            Text(
                text = "Un'intelligenza artificiale dolce che genera ogni volta una frase d'amore e un consiglio su misura per il tuo cuore.",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = WarmTextSecondary,
                    lineHeight = 18.sp
                ),
                modifier = Modifier.padding(top = 4.dp, bottom = 14.dp)
            )

            // Contenuto Generato: Frase d'amore
            Surface(
                shape = RoundedCornerShape(20.dp),
                color = PastelPinkContainer.copy(alpha = 0.75f),
                border = androidx.compose.foundation.BorderStroke(1.dp, PastelPinkLight),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = null,
                            tint = HeartRed,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Frase d'amore",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = DeepRosePrimary,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "“${currentAiMessage.quote}”",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Medium,
                            color = WarmTextPrimary,
                            lineHeight = 22.sp,
                            fontStyle = FontStyle.Italic
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Contenuto Generato: Consiglio pratico
            Surface(
                shape = RoundedCornerShape(20.dp),
                color = PastelBlueContainer.copy(alpha = 0.75f),
                border = androidx.compose.foundation.BorderStroke(1.dp, PastelBlueLight),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Lightbulb,
                            contentDescription = null,
                            tint = PastelBlueAccent,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Consiglio di cura e dolcezza",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = PastelBlueAccent,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = currentAiMessage.consiglio,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = WarmTextPrimary,
                            lineHeight = 21.sp
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Pulsante per generare nuovi pensieri
            Button(
                onClick = { generateNew() },
                enabled = !isLoading,
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PastelPinkPrimary,
                    contentColor = Color.White
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("generate_ai_comfort_button")
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(18.dp),
                            color = Color.White,
                            strokeWidth = 2.dp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Pisolo e Micio stanno pensando per te...",
                            style = MaterialTheme.typography.labelLarge.copy(fontSize = 13.5.sp)
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Genera nuova frase e consiglio ✨",
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.5.sp
                            )
                        )
                    }
                }
            }
        }
    }
}
