package com.sanya1am.consecutivepractices.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.terrakok.modo.stack.StackNavContainer
import com.github.terrakok.modo.stack.back

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SimpleAppBar(
    title: String,
    onBackPressed: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            ) },
        navigationIcon = {
            IconButton(
                content = {
                    Icon(
                        Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = null
                    )
                },
                onClick = { onBackPressed.invoke() }
            )
        }
    )
}