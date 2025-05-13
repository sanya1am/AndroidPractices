package com.sanya1am.profile.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import com.github.terrakok.modo.stack.LocalStackNavigation
import com.github.terrakok.modo.stack.forward
import com.sanya1am.profile.R
import com.sanya1am.profile.presentation.viewModel.ProfileViewModel
import com.sanya1am.uikit.ui.theme.Spacing
import com.sanya1am.uikit.ui.theme.Typography
import kotlinx.parcelize.Parcelize
import org.koin.androidx.compose.koinViewModel

@Parcelize
class ProfileScreen (
    override val screenKey: ScreenKey = generateScreenKey()
) : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content(modifier: Modifier) {
        val navigation = LocalStackNavigation.current

        val viewModel = koinViewModel<ProfileViewModel>()
        val state = viewModel.viewState

        Scaffold(
            contentWindowInsets = WindowInsets(0.dp),
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = stringResource(R.string.profile))
                    },
                    actions = {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = null,
                            Modifier
                                .padding(end = Spacing.small)
                                .clickable { navigation.forward(EditProfileScreen()) }
                        )
                    }
                )
            }
        ) { padding ->
            Column(
                Modifier
                    .padding(padding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = state.photoUri,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(vertical = Spacing.small)
                        .clip(CircleShape)
                        .size(128.dp),
                    error = painterResource(R.drawable.avatar)
                )

                Text(
                    text = state.name,
                    style = Typography.headlineLarge
                )

                Button(
                    onClick = { viewModel.onDocumentClicked() },
                    enabled = state.documentUrl.isNotBlank(),
                    modifier = Modifier
                        .padding(vertical = Spacing.small)
                ) {
                    Text(
                        text = stringResource(R.string.open_doc),
                        style = Typography.bodySmall
                    )
                }

            }
        }
    }
}

