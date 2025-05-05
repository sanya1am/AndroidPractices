package com.sanya1am.consecutivepractices.favorites.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanya1am.consecutivepractices.listWithDetails.data.repository.MoviesRepository
import com.sanya1am.consecutivepractices.favorites.presentation.state.FavoritesState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoritesViewModel (
    private val repository: MoviesRepository
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