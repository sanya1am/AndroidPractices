package com.sanya1am.consecutivepractices.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sanya1am.consecutivepractices.ui.theme.Spacing

@Composable
fun FullscreenMessage(msg: String) {
    Box(Modifier.fillMaxSize().padding(Spacing.medium), contentAlignment = Alignment.Center) {
        Text(text = msg)
    }
}