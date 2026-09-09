package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material.icons.outlined.Spa
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.SupportMessage
import com.example.ui.components.AiComfortGeneratorSection
import com.example.ui.components.BreathingExerciseDialog
import com.example.ui.components.ComfortTipsSection
import com.example.ui.components.DigitalHugModal
import com.example.ui.components.PisoloHeader
import com.example.ui.components.SupportMessagesSection
import com.example.ui.components.VentingSection
import com.example.ui.components.WelcomePartnerDialog
import com.example.ui.theme.DeepRosePrimary
import com.example.ui.theme.PastelBlueAccent
import com.example.ui.theme.PastelBlueContainer
import com.example.ui.theme.PastelBlueLight
import com.example.ui.theme.PastelBlueOnContainer
import com.example.ui.theme.PastelPinkContainer
import com.example.ui.theme.PastelPinkLight
import com.example.ui.theme.PastelPinkPrimary
import com.example.ui.theme.SoftCreamBackground
import com.example.ui.theme.WarmOutline
import com.example.ui.theme.WarmTextMuted
import com.example.ui.theme.WarmTextPrimary
import com.example.ui.theme.WarmTextSecondary
import kotlinx.coroutines.launch

data class NavItem(
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    var showWelcomeDialog by remember { mutableStateOf(true) }
    var selectedMessageForModal by remember { mutableStateOf<SupportMessage?>(null) }
    var showHugModal by remember { mutableStateOf(false) }
    var showBreathingDialog by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()

    // Sincronizzazione automatica tra posizione di scroll e indicatore barra di navigazione
    val activeNavIndex by remember {
        androidx.compose.runtime.derivedStateOf {
            val offset = scrollState.value
            when {
                offset < 220 -> 0
                offset < 680 -> 1
                offset < 1150 -> 2
                else -> 3
            }
        }
    }

    val navItems = listOf(
        NavItem("Home", Icons.Filled.Home, Icons.Outlined.Home),
        NavItem("Messaggi", Icons.Filled.Mail, Icons.Outlined.Mail),
        NavItem("Coccole", Icons.Filled.Favorite, Icons.Outlined.FavoriteBorder),
        NavItem("Sfogo", Icons.Filled.Create, Icons.Outlined.Create)
    )

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .testTag("home_screen_root"),
        containerColor = SoftCreamBackground,
        bottomBar = {
            Surface(
                color = Color.White,
                tonalElevation = 4.dp,
                shadowElevation = 8.dp,
                border = androidx.compose.foundation.BorderStroke(1.dp, WarmOutline)
            ) {
                NavigationBar(
                    containerColor = Color.White,
                    tonalElevation = 0.dp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    navItems.forEachIndexed { index, item ->
                        val isSelected = activeNavIndex == index
                        NavigationBarItem(
                            selected = isSelected,
                            onClick = {
                                coroutineScope.launch {
                                    val targetScroll = when (index) {
                                        0 -> 0
                                        1 -> 280
                                        2 -> 720
                                        else -> scrollState.maxValue
                                    }
                                    scrollState.animateScrollTo(targetScroll.coerceAtMost(scrollState.maxValue))
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                                    contentDescription = item.label,
                                    modifier = Modifier.size(22.dp)
                                )
                            },
                            label = {
                                Text(
                                    text = item.label,
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                        fontSize = 11.sp
                                    )
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = DeepRosePrimary,
                                selectedTextColor = DeepRosePrimary,
                                unselectedIconColor = WarmTextMuted,
                                unselectedTextColor = WarmTextMuted,
                                indicatorColor = PastelPinkContainer
                            ),
                            modifier = Modifier.testTag("nav_item_${item.label.lowercase()}")
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 640.dp)
                    .verticalScroll(scrollState)
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                // 1. Header con Nome "PISOLO & MICIO", saluto e mascotte alternabile
                PisoloHeader(
                    onMascotClick = { showHugModal = true }
                )

                // 5. Esercizio di respirazione in primo piano (visibile, chiaro e immediato)
                Card(
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = PastelBlueContainer.copy(alpha = 0.6f)),
                    border = androidx.compose.foundation.BorderStroke(1.dp, PastelBlueLight),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { showBreathingDialog = true }
                        .testTag("prominent_breathing_banner")
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = PastelBlueAccent.copy(alpha = 0.15f),
                                modifier = Modifier.padding(bottom = 6.dp)
                            ) {
                                Text(
                                    text = "🫁 ESERCIZIO DI CALMA",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = PastelBlueOnContainer,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 10.5.sp
                                    ),
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                )
                            }

                            Text(
                                text = "Senti il fiato corto o troppi pensieri?",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = WarmTextPrimary,
                                    fontSize = 15.5.sp
                                )
                            )

                            Spacer(modifier = Modifier.height(3.dp))

                            Text(
                                text = "60 secondi di respiro 4-4-4 guidato da Pisolo e Micio per rilassare il corpo.",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = WarmTextSecondary,
                                    lineHeight = 18.sp
                                )
                            )
                        }

                        Spacer(modifier = Modifier.width(14.dp))

                        Box(
                            modifier = Modifier
                                .size(46.dp)
                                .clip(CircleShape)
                                .background(PastelBlueAccent),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = "Avvia respirazione",
                                tint = Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }

                // 2. Sezione Messaggi di Supporto con il pulsante "Ho bisogno di un abbraccio"
                SupportMessagesSection(
                    onSelectMessage = { message ->
                        selectedMessageForModal = message
                        showHugModal = true
                    },
                    onNeedHugClick = {
                        selectedMessageForModal = null
                        showHugModal = true
                    }
                )

                // 2.bis Intelligenza Artificiale per frasi d'amore e consigli sempre nuovi
                AiComfortGeneratorSection()

                // 3. Sezione Coccole & Consigli per quando sta male (con quick cards ed esercizio di respirazione)
                ComfortTipsSection(
                    onStartBreathing = { showBreathingDialog = true }
                )

                // 4. Sezione Scrivimi / Sfogati (con stile tratteggiato)
                VentingSection()

                // Footer dolce
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Pensato con tutto il cuore per te ❤️",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = WarmTextSecondary,
                            fontWeight = FontWeight.Medium
                        ),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Pisolo App • Un abbraccio sempre in tasca 🦈🌸",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = PastelPinkPrimary,
                            fontSize = 11.sp
                        ),
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        // 6. Dialog iniziale all'apertura dell'app (Invito ad aprirsi con il ragazzo)
        if (showWelcomeDialog) {
            WelcomePartnerDialog(
                onDismissRequest = { showWelcomeDialog = false }
            )
        }

        // Dialog / Modal per l'Abbraccio Digitale
        if (showHugModal) {
            DigitalHugModal(
                initialMessage = selectedMessageForModal,
                onDismissRequest = {
                    showHugModal = false
                    selectedMessageForModal = null
                }
            )
        }

        // Dialog per l'Esercizio di Respirazione Guidato
        if (showBreathingDialog) {
            BreathingExerciseDialog(
                onDismissRequest = { showBreathingDialog = false }
            )
        }
    }
}
