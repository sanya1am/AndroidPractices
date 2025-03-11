package com.sanya1am.consecutivepractices.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sanya1am.consecutivepractices.ui.theme.Spacing

@Composable
fun RatingBox(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodySmall,
        color = Color.White,
        modifier = Modifier
            .background(Color(0xFF4CAF50), shape = MaterialTheme.shapes.small)
            .padding(horizontal = Spacing.small, vertical = Spacing.small)
            .height(32.dp)
            .widthIn(125.dp)
    )
}