package com.sanya1am.consecutivepractices.listWithDetails.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import com.github.terrakok.modo.stack.LocalStackNavigation
import com.github.terrakok.modo.stack.forward
import com.sanya1am.consecutivepractices.R
import com.sanya1am.consecutivepractices.listWithDetails.data.repository.MoviesRepository
import com.sanya1am.consecutivepractices.listWithDetails.domain.entity.MovieShortEntity
import com.sanya1am.consecutivepractices.listWithDetails.presentation.viewModel.ListViewModel
import com.sanya1am.consecutivepractices.ui.components.FullscreenLoading
import com.sanya1am.consecutivepractices.ui.components.FullscreenMessage
import com.sanya1am.consecutivepractices.ui.theme.Spacing
import kotlinx.parcelize.Parcelize
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Parcelize
class ListScreen(
    override val screenKey: ScreenKey = generateScreenKey()
) : Screen {

    @Composable
    override fun Content(modifier: Modifier) {
        val navigation = LocalStackNavigation.current
        val viewModel = koinViewModel<ListViewModel> { parametersOf(navigation) }
        val state = viewModel.viewState

        Scaffold(
            topBar = {
                Row(
                    modifier = Modifier.padding(Spacing.extraSmall),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        value = state.query,
                        onValueChange = { viewModel.onQueryChanged(it) },
                        label = { Text(stringResource(R.string.search)) },
                        modifier = Modifier.weight(1f),
                        leadingIcon = { Icon(Icons.Rounded.Search, null) },
                    )

                    BadgedBox(
                        badge = { if (state.hasBadge) Badge() },
                        Modifier.padding(Spacing.small)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = "Фильтры",
                            modifier = Modifier
                                .clickable { viewModel.onFiltersClicked() }
                        )
                    }
                }

            },
            contentWindowInsets = WindowInsets(0.dp)
        ) {
            if (state.showTypesDialog) {
                SelectionDialog(
                    onDismissRequest = { viewModel.onSelectionDialogDismissed() },
                    onConfirmation = { viewModel.onFiltersConfirmed() },
                    title = "Тип",
                    variants = state.typesVariants,
                    selectedVariants = state.selectedTypes,
                ) { variant, isSelected ->
                    viewModel.onSelectedVariantChanged(variant, isSelected)
                }
            }

            if (state.isLoading) {
                FullscreenLoading()
                return@Scaffold
            }

            state.error?.let {
                FullscreenMessage(msg = it)
                return@Scaffold
            }

            if (state.isEmpty) {
                FullscreenMessage("Нет результатов")
                return@Scaffold
            }

            LazyColumn( Modifier.padding(it) ) {
                items(state.items) { item ->
                    MovieItem(
                        item = item,
                        Modifier.pointerInput(Unit) {
                            detectTapGestures(
                                onTap = { viewModel.onItemClicked(item.id) },
                                onDoubleTap = { viewModel.onItemDoubleClicked(item) }
                            )

                        }
                    )
                }
            }
        }

    }
}

@Composable
fun MovieItem(
    item: MovieShortEntity,
    modifier: Modifier = Modifier
) {
    Row (
        modifier
            .padding(Spacing.small)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = item.previewUrl,
            contentDescription = item.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(Spacing.medium))

        Column {
            Text(
                text = item.name,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "${item.year} • ${stringResource(item.type.stringRes)}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun MovieItemPreview() {
//    MovieItem(item = MoviesData.moviesShort.first())
//}