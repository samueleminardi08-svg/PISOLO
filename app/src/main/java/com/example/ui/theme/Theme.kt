package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = DeepRosePrimary,
    onPrimary = Color.White,
    primaryContainer = PastelPinkContainer,
    onPrimaryContainer = PastelPinkOnContainer,
    
    secondary = PastelPinkPrimary,
    onSecondary = Color.White,
    secondaryContainer = PastelPinkLight.copy(alpha = 0.5f),
    onSecondaryContainer = DeepRosePrimary,
    
    tertiary = PastelBlueAccent,
    onTertiary = Color.White,
    tertiaryContainer = PastelBlueContainer,
    onTertiaryContainer = PastelBlueOnContainer,
    
    background = SoftCreamBackground,
    onBackground = WarmTextPrimary,
    
    surface = SoftSurface,
    onSurface = WarmTextPrimary,
    surfaceVariant = SoftSurfaceVariant,
    onSurfaceVariant = WarmTextSecondary,
    
    outline = PastelPinkLight,
    outlineVariant = WarmOutline
)

private val DarkColorScheme = lightColorScheme(
    // Manteniamo una tonalità calda e dolce anche in dark mode
    primary = PastelPinkLight,
    onPrimary = Color(0xFF4A1020),
    primaryContainer = Color(0xFF5A2A38),
    onPrimaryContainer = Color(0xFFFFD9E2),
    
    secondary = PastelBlueLight,
    onSecondary = Color(0xFF00363A),
    secondaryContainer = Color(0xFF1E4950),
    onSecondaryContainer = Color(0xFFBCEBEF),
    
    background = Color(0xFF231B1E),
    onBackground = Color(0xFFF3EAEB),
    
    surface = Color(0xFF2E2428),
    onSurface = Color(0xFFF3EAEB),
    surfaceVariant = Color(0xFF3F3238),
    onSurfaceVariant = Color(0xFFD7C2C7),
    
    outline = Color(0xFF5A444C)
)

@Composable
fun PisoloTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    // Usiamo la nostra palette pastello personalizzata per mantenere la coerenza visiva
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
