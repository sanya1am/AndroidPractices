package com.sanya1am.favorites.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanya1am.api.domain.repository.IMoviesRepositoty
import com.sanya1am.favorites.presentation.state.FavoritesState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoritesViewModel (
    private val repository: IMoviesRepositoty
): ViewModel() {

    private var mutableState = MutableStateFlow(FavoritesState())
    val viewState = mutableState.asStateFlow()

    init {
        updateFavorites()
    }

    fun onUpdateClick() {
        updateFavorites()
    }

    private fun updateFavorites() {
        viewModelScope.launch {
            mutableState.update { it.copy(items = repository.getFavorites()) }
        }
    }
}