package com.sanya1am.consecutivepractices.listWithDetails.presentation.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.github.terrakok.modo.stack.StackNavContainer
import com.github.terrakok.modo.stack.forward
import com.sanya1am.consecutivepractices.listWithDetails.data.repository.MoviesRepository
import com.sanya1am.consecutivepractices.listWithDetails.domain.entity.MovieShortEntity
import com.sanya1am.consecutivepractices.listWithDetails.presentation.screens.DetailsScreen
import com.sanya1am.consecutivepractices.listWithDetails.presentation.state.MoviesListState

class ListViewModel (
    private val repository: MoviesRepository,
    private val navigation: StackNavContainer
) : ViewModel() {

    private val mutableState = MutableMoviesListState()
    val viewState = mutableState as MoviesListState

    init {
        loadMovies()
    }

    private fun loadMovies() {
        mutableState.items = repository.getList(viewState.query)
    }

    fun onItemClicked(id: String) {
        navigation.forward(DetailsScreen(modieId = id))
    }

    private class MutableMoviesListState: MoviesListState {
        override var items: List<MovieShortEntity> by mutableStateOf(emptyList())
        override var query by mutableStateOf("")
        override val isEmpty get() = items.isEmpty()
    }
}