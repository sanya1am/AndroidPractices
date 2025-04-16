package com.sanya1am.consecutivepractices.listWithDetails.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.terrakok.modo.stack.StackNavContainer
import com.github.terrakok.modo.stack.back
import com.sanya1am.consecutivepractices.core.coroutinesUtils.launchLoadingAndError
import com.sanya1am.consecutivepractices.listWithDetails.data.repository.MoviesRepository
import com.sanya1am.consecutivepractices.listWithDetails.domain.entity.MovieFullEntity
import com.sanya1am.consecutivepractices.listWithDetails.presentation.state.MovieDetailState
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val repository: MoviesRepository,
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