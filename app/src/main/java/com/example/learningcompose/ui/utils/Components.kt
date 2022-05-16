package com.example.learningcompose.ui.utils

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@Composable
fun TextDefault(text: String) {
    Text(
        text = text,
        fontSize = MaterialTheme.typography.headlineMedium.fontSize,
        fontWeight = FontWeight.Bold,
        color = Color.White
    )
}