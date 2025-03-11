package com.sanya1am.consecutivepractices.listWithDetails.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import com.github.terrakok.modo.stack.LocalStackNavigation
import com.github.terrakok.modo.stack.StackNavContainer
import com.sanya1am.consecutivepractices.listWithDetails.domain.entity.MovieFullEntity
import com.sanya1am.consecutivepractices.listWithDetails.presentation.state.MovieDetailState
import com.sanya1am.consecutivepractices.listWithDetails.presentation.viewModel.DetailsViewModel
import com.sanya1am.consecutivepractices.ui.components.EmptyDataBox
import com.sanya1am.consecutivepractices.ui.components.RatingBox
import com.sanya1am.consecutivepractices.ui.components.SimpleAppBar
import com.sanya1am.consecutivepractices.ui.theme.Spacing
import kotlinx.parcelize.Parcelize
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Parcelize
class DetailsScreen (
    override val screenKey: ScreenKey = generateScreenKey(),
    val modieId: String
) : Screen {

    @Composable
    override fun Content(modifier: Modifier) {
        val navigation = LocalStackNavigation.current

        val viewModel = koinViewModel<DetailsViewModel>() { parametersOf(navigation, modieId) }
        val state = viewModel.viewState

        MovieScreenContent(
            state,
            onBackPressed = { viewModel.back() }
        )
    }
}

@Composable
private fun MovieScreenContent(
    state: MovieDetailState,
    onBackPressed: () -> Unit,
) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = { SimpleAppBar(state.movie?.name.orEmpty(), onBackPressed) },
        containerColor = MaterialTheme.colorScheme.background
    ) {
        val movie = state.movie ?: run {
            EmptyDataBox("По запросу нет результатов")
            return@Scaffold
        }

        Column(
            modifier = Modifier
                .padding(it)
                .padding(Spacing.medium)
                .verticalScroll(scrollState)
        ) {
            Row(
                modifier = Modifier.padding(bottom = Spacing.medium)
            ) {
                Card(
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier
                        .width(140.dp)
                        .height(225.dp)
                ) {

                    AsyncImage(
                        model = movie.posterUrl,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )

                }

                Spacer(modifier = Modifier.width(Spacing.medium))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = movie.name,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(Spacing.small))

                    Text(
                        text = "Год: ${movie.year}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )

                    Spacer(modifier = Modifier.height(Spacing.small))

                    Text(
                        text = stringResource(movie.type.stringRes),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )

                    Spacer(modifier = Modifier.height(Spacing.small))

                    movie.ageRating?.let {
                        Text(
                            text = "Возраст: $it",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.secondary
                        )

                        Spacer(modifier = Modifier.height(Spacing.small))
                    }
                }
            }

            Spacer(modifier = Modifier.height(Spacing.small))

            Column {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(Spacing.small),
                    modifier = Modifier.padding(top = Spacing.small)
                ) {
                    RatingBox(text = "КиноПоиск: ${movie.rating.kp}")
                    RatingBox(text = "IMDb: ${movie.rating.imdb}")
                }

                Spacer(modifier = Modifier.height(Spacing.medium))

                movie.movieLength?.let {
                    Text(
                        text = "Длительность: $it мин",
                        style = MaterialTheme.typography.bodyMedium,
                    )

                    Spacer(modifier = Modifier.height(Spacing.small))
                }

                Text(
                    text = "Жанры: ${movie.genres.joinToString(", ")}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = Spacing.small)
                )

                Spacer(modifier = Modifier.height(Spacing.small))

                Text(
                    text = "Страны: ${movie.countries.joinToString(", ")}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.height(Spacing.medium))

            movie.description?.let {
                Card (
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(Spacing.medium)
                    )
                }
            }
        }
    }
}

