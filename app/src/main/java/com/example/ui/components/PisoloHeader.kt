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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.model.PisoloData
import com.example.ui.theme.DeepRosePrimary
import com.example.ui.theme.HeartRed
import com.example.ui.theme.PastelBlueAccent
import com.example.ui.theme.PastelBlueContainer
import com.example.ui.theme.PastelBlueLight
import com.example.ui.theme.PastelPinkContainer
import com.example.ui.theme.PastelPinkLight
import com.example.ui.theme.PastelPinkPrimary
import com.example.ui.theme.WarmOutline
import com.example.ui.theme.WarmPinkSubtitle
import com.example.ui.theme.WarmTextPrimary
import com.example.ui.theme.WarmTextSecondary

@Composable
fun PisoloHeader(
    onMascotClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val mascotQuotes = PisoloData.frasiMascottePisolo
    var quoteIndex by remember { mutableIntStateOf(0) }

    // Floating animation (4s ease-in-out infinite translateY)
    val infiniteTransition = rememberInfiniteTransition(label = "shark_float")
    val offsetY by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = -8f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "shark_translate_y"
    )

    Card(
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, WarmOutline, RoundedCornerShape(28.dp))
            .testTag("pisolo_header_card")
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            PastelPinkContainer.copy(alpha = 0.65f),
                            Color.White
                        )
                    )
                )
                .padding(20.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                // Top row with App Name and Floating Mascot Duo
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "PISOLO & MICIO",
                                style = MaterialTheme.typography.displayLarge.copy(
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 0.5.sp,
                                    color = DeepRosePrimary
                                )
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = null,
                                tint = HeartRed,
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(2.dp))

                        Text(
                            text = "Pisolo 🦈 & Micio 🐱 sempre insieme per te",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = WarmPinkSubtitle,
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.Medium,
                                fontSize = 13.5.sp
                            )
                        )
                    }

                    // Mascot avatar duo con floating animation
                    Box(
                        modifier = Modifier
                            .offset(y = offsetY.dp)
                            .clickable {
                                quoteIndex = (quoteIndex + 1) % mascotQuotes.size
                                onMascotClick()
                            }
                    ) {
                        PisoloMascotAvatar(
                            size = 62.dp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Fumetto interattivo con quote dolce di Pisolo e Micio
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = PastelPinkContainer.copy(alpha = 0.85f),
                    border = androidx.compose.foundation.BorderStroke(1.dp, PastelPinkLight),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            quoteIndex = (quoteIndex + 1) % mascotQuotes.size
                        }
                        .testTag("mascot_speech_bubble")
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = "🦈🐱", fontSize = 18.sp)
                            Spacer(modifier = Modifier.width(8.dp))
                            AnimatedContent(
                                targetState = mascotQuotes[quoteIndex],
                                transitionSpec = {
                                    fadeIn(tween(300)) togetherWith fadeOut(tween(200))
                                },
                                label = "quote_transition"
                            ) { quote ->
                                Text(
                                    text = quote,
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        color = WarmTextPrimary,
                                        fontWeight = FontWeight.Medium
                                    )
                                )
                            }
                        }

                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Cambia frase dolce",
                            tint = DeepRosePrimary,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }
    }
}
