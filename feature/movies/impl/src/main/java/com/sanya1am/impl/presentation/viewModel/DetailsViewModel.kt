package com.sanya1am.impl.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.terrakok.modo.stack.StackNavContainer
import com.github.terrakok.modo.stack.back
import com.sanya1am.core.coroutinesUtils.launchLoadingAndError

import com.sanya1am.api.domain.entity.MovieFullEntity
import com.sanya1am.api.domain.repository.IMoviesRepositoty
import com.sanya1am.impl.presentation.state.MovieDetailState

class DetailsViewModel(
    private val repository: IMoviesRepositoty,
    private val navigation: StackNavContainer,
    private val id: String
) : ViewModel() {

    private val mutableState = MutableDetailsState()
    val viewState = mutableState as MovieDetailState

    init {
        viewModelScope.launchLoadingAndError(
            handleError = { mutableState.error = it.localizedMessage },
            updateLoading = { mutableState.isLoading = it }
        ) {
            mutableState.movie = repository.getById(id)
        }
    }

    fun back() {
        navigation.back()
    }

    private class MutableDetailsState() : MovieDetailState {
        override var movie: MovieFullEntity? by mutableStateOf(null)
        override var rating: Float by mutableFloatStateOf(0f)
        override var isLoading: Boolean by mutableStateOf(false)
        override var error: String? by mutableStateOf(null)
    }
}