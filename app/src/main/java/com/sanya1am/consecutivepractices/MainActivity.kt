package com.sanya1am.consecutivepractices

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.github.terrakok.modo.Modo.rememberRootScreen
import com.github.terrakok.modo.RootScreen
import com.github.terrakok.modo.stack.DefaultStackScreen
import com.github.terrakok.modo.stack.StackNavModel
import com.sanya1am.consecutivepractices.favorites.presentation.screens.MainTabScreen
import com.sanya1am.consecutivepractices.ui.theme.ConsecutivePracticesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val rootScreen: RootScreen<DefaultStackScreen> = rememberRootScreen {
                DefaultStackScreen(
                    StackNavModel(
                        MainTabScreen()
                    )
                )
            }

            ConsecutivePracticesTheme {
                Surface(color = Color.White) {
                    rootScreen.Content(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}
