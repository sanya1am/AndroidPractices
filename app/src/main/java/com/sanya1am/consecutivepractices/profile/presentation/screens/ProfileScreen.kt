package com.sanya1am.consecutivepractices.profile.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import com.sanya1am.consecutivepractices.R
import com.sanya1am.consecutivepractices.listWithDetails.presentation.screens.MovieItem
import com.sanya1am.consecutivepractices.listWithDetails.presentation.viewModel.ListViewModel
import com.sanya1am.consecutivepractices.profile.presentation.viewModel.ProfileViewModel
import kotlinx.parcelize.Parcelize
import org.koin.androidx.compose.koinViewModel

@Parcelize
class ProfileScreen(
    override val screenKey: ScreenKey = generateScreenKey()
) : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content(modifier: Modifier) {
        val viewModel = koinViewModel<ProfileViewModel>()
        val state by viewModel.viewState.collectAsState()

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = stringResource(R.string.my_movies)) },
                    actions = {
                        IconButton(onClick = { viewModel.onUpdateClick() }) {
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = null
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->
            LazyColumn(
                Modifier.padding(innerPadding)
            ) {
                items(state.items) {
                    MovieItem(item = it)
                }
            }
        }
    }
}