package com.sanya1am.consecutivepractices.listWithDetails.presentation.viewModel

import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.terrakok.modo.stack.StackNavContainer
import com.github.terrakok.modo.stack.forward
import com.sanya1am.consecutivepractices.core.coroutinesUtils.launchLoadingAndError
import com.sanya1am.consecutivepractices.listWithDetails.data.repository.MoviesRepository
import com.sanya1am.consecutivepractices.listWithDetails.domain.entity.MovieShortEntity
import com.sanya1am.consecutivepractices.listWithDetails.domain.entity.MovieType
import com.sanya1am.consecutivepractices.listWithDetails.presentation.screens.DetailsScreen
import com.sanya1am.consecutivepractices.listWithDetails.presentation.state.MoviesListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject


class ListViewModel (
    private val repository: MoviesRepository,
    private val navigation: StackNavContainer
) : ViewModel() {

    private val dataStore: DataStore<Preferences> by inject(DataStore::class.java)
    private val typesKey = stringSetPreferencesKey(KEY_MOVIE_TYPES)

    private val mutableState = MutableMoviesListState()
    private val textChangesFlow = MutableStateFlow("")
    private var filterTypes: Set<MovieType> = emptySet()

    val viewState = mutableState as MoviesListState

    init {
        viewModelScope.launch {
            textChangesFlow
                .debounce(1000L)
                .collect { loadMovies() }
        }

        viewModelScope.launch {
            dataStore.data.collect {
                filterTypes = it[typesKey]
                    ?.map { MovieType.getByValue(it) }
                    ?.toSet()
                    .orEmpty()

                mutableState.selectedTypes = filterTypes
                updateBadge()
            }
        }

        mutableState.typesVariants = MovieType.entries.toSet()
    }

    private fun loadMovies() {
        mutableState.items = emptyList()
        mutableState.error = null

        viewModelScope.launchLoadingAndError(
            handleError = { mutableState.error = it.localizedMessage },
            updateLoading = { mutableState.isLoading = it}
        ) {
            val allItems = repository.getList(viewState.query)
            mutableState.items = if (viewState.selectedTypes.isEmpty()) {
                allItems
            } else {
                allItems.filter { it.type in viewState.selectedTypes }
            }
        }
    }

    fun onItemClicked(id: String) {
        navigation.forward(DetailsScreen(movieId = id))
    }

    fun onQueryChanged(query: String) {
        mutableState.query = query
        viewModelScope.launch { textChangesFlow.emit(query) }
    }

    fun onFiltersClicked() {
        mutableState.selectedTypes = filterTypes
        mutableState.showTypesDialog = true
    }

    fun onSelectionDialogDismissed() {
        mutableState.showTypesDialog = false
    }

    fun onFiltersConfirmed() {
        if (filterTypes != mutableState.selectedTypes) {
            filterTypes = mutableState.selectedTypes
            loadMovies()
            updateBadge()

            viewModelScope.launch {
                dataStore.edit {
                    it[typesKey] = filterTypes.map { it.name }.toSet()
                }
            }
        }
        onSelectionDialogDismissed()
    }

    fun onSelectedVariantChanged(variant: MovieType, isSelected: Boolean) {
        val current = viewState.selectedTypes.toMutableSet()
        if (isSelected) {
            current.add(variant)
        } else {
            current.remove(variant)
        }
        mutableState.selectedTypes = current
    }

    private fun updateBadge() {
        mutableState.hasBadge = filterTypes.isNotEmpty()
    }

    fun onItemDoubleClicked(item: MovieShortEntity) {
        viewModelScope.launch {
            repository.saveFavorite(item)
        }
    }

    private class MutableMoviesListState: MoviesListState {
        override var items: List<MovieShortEntity> by mutableStateOf(emptyList())
        override var query by mutableStateOf("")
        override val isEmpty get() = items.isEmpty()
        override var isLoading: Boolean by mutableStateOf(false)
        override var error: String? by mutableStateOf(null)
        override var hasBadge: Boolean by mutableStateOf(false)
        override var showTypesDialog: Boolean by mutableStateOf(false)
        override var selectedTypes: Set<MovieType> by mutableStateOf(emptySet())
        override var typesVariants: Set<MovieType> by mutableStateOf(emptySet())
    }

    companion object {
        private const val KEY_MOVIE_TYPES = "MOVIE_TYPES"
    }
}