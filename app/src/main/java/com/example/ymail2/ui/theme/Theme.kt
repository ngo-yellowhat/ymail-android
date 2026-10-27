package com.example.ymail2.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val YmailColorScheme = darkColorScheme(
    primary = YmailYellow,
    onPrimary = Color.Black,
    background = YmailBackground,
    onBackground = YmailTextPrimary,
    surface = YmailBackground,
    onSurface = YmailTextPrimary,
    onSurfaceVariant = YmailTextSecondary
)

@Composable
fun Ymail2Theme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = YmailColorScheme,
        typography = Typography,
        content = content
    )
}