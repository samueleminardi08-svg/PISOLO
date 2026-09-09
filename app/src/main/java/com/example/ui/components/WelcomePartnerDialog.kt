package com.example.ui.components

import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.R
import com.example.ui.theme.DeepRosePrimary
import com.example.ui.theme.HeartRed
import com.example.ui.theme.PastelPinkContainer
import com.example.ui.theme.PastelPinkLight
import com.example.ui.theme.PastelPinkPrimary
import com.example.ui.theme.WarmOutline
import com.example.ui.theme.WarmTextPrimary
import com.example.ui.theme.WarmTextSecondary

/**
 * 6. Messaggio d'accoglienza all'apertura dell'app che invita gentilmente
 * a contattare direttamente il ragazzo, ricordando che l'app è solo un supporto per quando lui non è subito raggiungibile.
 */
@Composable
fun WelcomePartnerDialog(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
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
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .border(1.dp, PastelPinkLight, RoundedCornerShape(28.dp))
                .testTag("welcome_partner_dialog")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Duo Mascotte: Squaletto Pisolo & Gattino Micio
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .size(62.dp)
                            .shadow(4.dp, CircleShape)
                            .clip(CircleShape)
                            .background(PastelPinkLight)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img_pisolo_shark_boy),
                            contentDescription = "Pisolo",
                            modifier = Modifier.size(62.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null,
                        tint = HeartRed,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))

                    Box(
                        modifier = Modifier
                            .size(62.dp)
                            .shadow(4.dp, CircleShape)
                            .clip(CircleShape)
                            .background(PastelPinkContainer)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img_micio_cat),
                            contentDescription = "Micio",
                            modifier = Modifier.size(62.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Piccolo promemoria d'amore",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = DeepRosePrimary
                    ),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(12.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(18.dp))
                        .background(PastelPinkContainer.copy(alpha = 0.55f))
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Ciao piccola mia. Ricordati sempre che la cosa che desidero di più è sentirti direttamente. Se c'è qualcosa che non va, se ti senti giù o se hai solo bisogno di me, scrivimi o chiamami appena puoi: per te ci sono sempre.\n\nHo preparato quest'app con Pisolo e Micio per farti compagnia e darti una carezza nei momenti in cui non riesco a risponderti subito o in cui hai bisogno di ritrovare un attimo di calma.",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = WarmTextPrimary,
                            lineHeight = 22.sp
                        ),
                        textAlign = TextAlign.Start
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = onDismissRequest,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = DeepRosePrimary,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "Entra nell'app con Pisolo & Micio",
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}
