package com.sanya1am.consecutivepractices.listWithDetails.presentation.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.terrakok.modo.stack.StackNavContainer
import com.github.terrakok.modo.stack.forward
import com.sanya1am.consecutivepractices.core.coroutinesUtils.launchLoadingAndError
import com.sanya1am.consecutivepractices.listWithDetails.data.repository.MoviesRepository
import com.sanya1am.consecutivepractices.listWithDetails.domain.entity.MovieShortEntity
import com.sanya1am.consecutivepractices.listWithDetails.presentation.screens.DetailsScreen
import com.sanya1am.consecutivepractices.listWithDetails.presentation.state.MoviesListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.time.debounce
import kotlinx.coroutines.launch


class ListViewModel (
    private val repository: MoviesRepository,
    private val navigation: StackNavContainer
) : ViewModel() {

    private val mutableState = MutableMoviesListState()
    private val textChangesFlow = MutableStateFlow("")

    val viewState = mutableState as MoviesListState

    init {
        viewModelScope.launch {
            textChangesFlow
                .debounce(1000L)
                .collect { loadMovies() }
        }
    }

    private fun loadMovies() {
        mutableState.items = emptyList()
        mutableState.error = null

        viewModelScope.launchLoadingAndError(
            handleError = { mutableState.error = it.localizedMessage },
            updateLoading = { mutableState.isLoading = it}
        ) {
            mutableState.items = repository.getList(viewState.query)
        }
    }

    fun onItemClicked(id: String) {
        navigation.forward(DetailsScreen(movieId = id))
    }

    fun onQueryChanged(query: String) {
        mutableState.query = query
        viewModelScope.launch { textChangesFlow.emit(query) }
    }

    private class MutableMoviesListState: MoviesListState {
        override var items: List<MovieShortEntity> by mutableStateOf(emptyList())
        override var query by mutableStateOf("")
        override val isEmpty get() = items.isEmpty()
        override var isLoading: Boolean by mutableStateOf(false)
        override var error: String? by mutableStateOf(null)
    }
}