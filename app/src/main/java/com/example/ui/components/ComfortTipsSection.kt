package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.PlayCircleFilled
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.ComfortCategory
import com.example.model.ComfortTip
import com.example.model.PisoloData
import com.example.ui.theme.PastelBlueAccent
import com.example.ui.theme.PastelBlueContainer
import com.example.ui.theme.PastelBlueLight
import com.example.ui.theme.PastelBlueOnContainer
import com.example.ui.theme.PastelPinkContainer
import com.example.ui.theme.PastelPinkLight
import com.example.ui.theme.PastelPinkOnContainer
import com.example.ui.theme.PastelPinkPrimary
import com.example.ui.theme.WarmOutline
import com.example.ui.theme.WarmTextPrimary
import com.example.ui.theme.WarmTextSecondary

@Composable
fun ComfortTipsSection(
    onStartBreathing: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedCategory by remember { mutableStateOf(ComfortCategory.ALL) }
    val allTips = PisoloData.consigliCoccole

    val filteredTips = remember(selectedCategory) {
        if (selectedCategory == ComfortCategory.ALL) {
            allTips
        } else {
            allTips.filter { it.categoria == selectedCategory }
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .testTag("comfort_tips_section")
    ) {
        // Quick Cuddles / Tips 2-Column Row (from Design HTML)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Quick Card 1: Ansia -> Respiro
            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = PastelBlueContainer),
                modifier = Modifier
                    .weight(1f)
                    .height(130.dp)
                    .border(1.dp, PastelBlueLight, RoundedCornerShape(24.dp))
                    .clickable { onStartBreathing() }
                    .testTag("quick_anxiety_card")
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "ANSIA?",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = PastelBlueAccent,
                            letterSpacing = 0.5.sp
                        )
                    )

                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Box(
                            modifier = Modifier
                                .size(width = 32.dp, height = 4.dp)
                                .clip(RoundedCornerShape(2.dp))
                                .background(PastelBlueAccent.copy(alpha = 0.3f))
                        )
                        Text(
                            text = "Prova l'esercizio di respiro.",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = PastelBlueOnContainer,
                                fontSize = 12.sp
                            )
                        )
                    }
                }
            }

            // Quick Card 2: Giù -> Tisana & Riposo
            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = PastelPinkContainer),
                modifier = Modifier
                    .weight(1f)
                    .height(130.dp)
                    .border(1.dp, PastelPinkLight, RoundedCornerShape(24.dp))
                    .clickable {
                        selectedCategory = ComfortCategory.GIU_DI_MORALE
                    }
                    .testTag("quick_sad_card")
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "GIÙ?",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = PastelPinkOnContainer,
                            letterSpacing = 0.5.sp
                        )
                    )

                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Box(
                            modifier = Modifier
                                .size(width = 32.dp, height = 4.dp)
                                .clip(RoundedCornerShape(2.dp))
                                .background(PastelPinkOnContainer.copy(alpha = 0.25f))
                        )
                        Text(
                            text = "Fatti una tisana e mettiti a letto.",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = PastelPinkOnContainer,
                                fontSize = 12.sp
                            )
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Intestazione sezione
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp)
        ) {
            Text(text = "🧸", fontSize = 20.sp)
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = "Coccole & Piccoli Consigli",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = WarmTextPrimary
                    )
                )
                Text(
                    text = "Gesti dolci per prenderti cura di te",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = WarmTextSecondary
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Filtri per Categoria (Pill Chips)
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 2.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(ComfortCategory.entries.toTypedArray()) { category ->
                val isSelected = category == selectedCategory
                FilterChip(
                    selected = isSelected,
                    onClick = { selectedCategory = category },
                    label = {
                        Text(
                            text = "${category.emoji} ${category.label}",
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                            )
                        )
                    },
                    shape = RoundedCornerShape(16.dp),
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = PastelPinkPrimary,
                        selectedLabelColor = Color.White,
                        containerColor = PastelPinkContainer.copy(alpha = 0.6f),
                        labelColor = WarmTextPrimary
                    ),
                    border = FilterChipDefaults.filterChipBorder(
                        borderColor = if (isSelected) PastelPinkPrimary else PastelPinkLight.copy(alpha = 0.5f),
                        enabled = true,
                        selected = isSelected
                    ),
                    modifier = Modifier.testTag("filter_chip_${category.name}")
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Lista dei consigli
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            filteredTips.forEach { tip ->
                ComfortTipCard(
                    tip = tip,
                    onClick = {
                        if (tip.hasBreathingAction) {
                            onStartBreathing()
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun ComfortTipCard(
    tip: ComfortTip,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (tip.hasBreathingAction) PastelBlueContainer.copy(alpha = 0.6f) else MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(
                1.dp,
                if (tip.hasBreathingAction) PastelBlueLight else WarmOutline,
                RoundedCornerShape(20.dp)
            )
            .clickable { onClick() }
            .testTag("comfort_tip_card_${tip.id}")
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icona rotonda
            Box(
                modifier = Modifier
                    .size(46.dp)
                    .clip(CircleShape)
                    .background(
                        if (tip.hasBreathingAction) PastelBlueContainer else PastelPinkContainer
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(text = tip.iconEmoji, fontSize = 22.sp)
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                if (tip.badge != null) {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = PastelBlueAccent.copy(alpha = 0.15f),
                        modifier = Modifier.padding(bottom = 4.dp)
                    ) {
                        Text(
                            text = "✨ ${tip.badge}",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = PastelBlueAccent,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }

                Text(
                    text = tip.titolo,
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = WarmTextPrimary
                    )
                )

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    text = tip.descrizione,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = WarmTextSecondary,
                        lineHeight = 18.sp
                    )
                )
            }

            if (tip.hasBreathingAction) {
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Default.PlayCircleFilled,
                    contentDescription = "Avvia esercizio",
                    tint = PastelBlueAccent,
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }
}
