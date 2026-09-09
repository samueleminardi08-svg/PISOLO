package com.example.ui.components

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.PhotoRepository
import com.example.model.PisoloData
import com.example.model.SupportMessage
import com.example.ui.theme.PastelPinkContainer
import com.example.ui.theme.PastelPinkLight
import com.example.ui.theme.PastelPinkPrimary
import com.example.ui.theme.WarmOutline
import com.example.ui.theme.WarmTextPrimary
import com.example.ui.theme.WarmTextSecondary
import kotlin.random.Random

@Composable
fun SupportMessagesSection(
    onSelectMessage: (SupportMessage) -> Unit,
    onNeedHugClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val photoRepo = remember { PhotoRepository.getInstance(context) }
    val heroUri by photoRepo.heroPhotoUri.collectAsState()
    val customPhotoMap by photoRepo.customPhotoMap.collectAsState()

    val messages = PisoloData.messaggiDelCuore
    var featuredIndex by remember { mutableIntStateOf(Random.nextInt(messages.size)) }
    val featuredMessage = messages.getOrNull(featuredIndex) ?: messages.first()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .testTag("support_messages_section")
    ) {
        // Main Featured Support Message Card (Hero Card from Design)
        Card(
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, WarmOutline, RoundedCornerShape(28.dp))
                .testTag("featured_support_card")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Photo container (aspect-video) with live picker and category tag
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16f / 9f)
                ) {
                    PisoloPhotoView(
                        imageUri = customPhotoMap[featuredMessage.id] ?: heroUri,
                        drawableResId = featuredMessage.drawableResId,
                        contentDescription = featuredMessage.titolo,
                        shape = RoundedCornerShape(20.dp),
                        allowChangePhoto = true,
                        onPhotoSelected = { uri ->
                            photoRepo.setMessagePhotoUri(featuredMessage.id, uri.toString())
                            photoRepo.setHeroPhotoUri(uri.toString())
                        },
                        modifier = Modifier.matchParentSize()
                    )

                    // Tag Categoria Overlay
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = Color.Black.copy(alpha = 0.45f),
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(10.dp)
                    ) {
                        Text(
                            text = "${featuredMessage.iconEmoji} ${featuredMessage.categoriaTag}",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = Color.White,
                                fontWeight = FontWeight.Medium
                            ),
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }

                    // Pulsante cambio/shuffle messaggio rapido
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = Color.Black.copy(alpha = 0.45f),
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(10.dp)
                            .clickable {
                                var next = Random.nextInt(messages.size)
                                if (messages.size > 1 && next == featuredIndex) {
                                    next = (featuredIndex + 1) % messages.size
                                }
                                featuredIndex = next
                            }
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = "Cambia messaggio casuale",
                                tint = Color.White,
                                modifier = Modifier.size(12.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Casuale",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = Color.White,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Warm Quote Text
                Text(
                    text = "“${featuredMessage.testo}”",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = WarmTextPrimary,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 22.sp,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "— ${featuredMessage.firma}",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = PastelPinkPrimary,
                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                        fontWeight = FontWeight.Medium
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Action Pill Button "Ho bisogno di un abbraccio"
                Button(
                    onClick = {
                        // Cambia messaggio casuale e apre il modal
                        var next = Random.nextInt(messages.size)
                        if (messages.size > 1 && next == featuredIndex) {
                            next = (featuredIndex + 1) % messages.size
                        }
                        featuredIndex = next
                        onNeedHugClick()
                    },
                    shape = RoundedCornerShape(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PastelPinkPrimary,
                        contentColor = Color.White
                    ),
                    contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp, pressedElevation = 4.dp),
                    modifier = Modifier
                        .testTag("need_hug_button")
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Ho bisogno di un abbraccio",
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Intestazione messaggi del cuore
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "💌", fontSize = 20.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "I messaggi per te",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = WarmTextPrimary
                        )
                    )
                    Text(
                        text = "Tocca una card per aprirla o l'icona foto per personalizzarla",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = WarmTextSecondary
                        )
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Carosello orizzontale di messaggi
        LazyRow(
            contentPadding = PaddingValues(horizontal = 2.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(messages, key = { it.id }) { msg ->
                val customUri = customPhotoMap[msg.id]
                SupportMessageCardItem(
                    message = msg.copy(imageUri = customUri),
                    onPhotoSelected = { uri ->
                        photoRepo.setMessagePhotoUri(msg.id, uri.toString())
                    },
                    onClick = {
                        onSelectMessage(msg.copy(imageUri = customUri))
                    }
                )
            }
        }
    }
}

@Composable
fun SupportMessageCardItem(
    message: SupportMessage,
    onPhotoSelected: (Uri) -> Unit,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .width(250.dp)
            .border(1.dp, WarmOutline, RoundedCornerShape(24.dp))
            .clickable { onClick() }
            .testTag("support_message_card_${message.id}")
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {
            // Immagine associata (con supporto foto personalizzata da galleria o drawable)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(125.dp)
            ) {
                PisoloPhotoView(
                    imageUri = message.imageUri,
                    drawableResId = message.drawableResId,
                    contentDescription = message.titolo,
                    shape = RoundedCornerShape(16.dp),
                    allowChangePhoto = true,
                    onPhotoSelected = onPhotoSelected,
                    modifier = Modifier.matchParentSize()
                )

                // Categoria Tag Overlay
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = Color.Black.copy(alpha = 0.5f),
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(8.dp)
                ) {
                    Text(
                        text = "${message.iconEmoji} ${message.categoriaTag}",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = Color.White,
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = message.titolo,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = WarmTextPrimary
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = message.testo,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = WarmTextSecondary,
                    lineHeight = 18.sp
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Apri messaggio",
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = PastelPinkPrimary,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = null,
                    tint = PastelPinkPrimary,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}
