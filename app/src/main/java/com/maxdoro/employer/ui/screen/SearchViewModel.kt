package com.maxdoro.employer.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxdoro.employer.domain.GetEmployerSearchResultsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@OptIn(FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    val getEmployerSearchResultsUseCase: GetEmployerSearchResultsUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()

    var searchQuery by mutableStateOf("")
        private set

    init {
        initialiseUiState()
    }

    fun initialiseUiState() {
        snapshotFlow { searchQuery }
            .debounce(250L)
            .onEach { query ->
                if (query.isNotBlank()) {
                    _uiState.update {
                        it.copy(isSearching = true)
                    }

                    val result = getEmployerSearchResultsUseCase(query)

                    when (result) {
                        is com.maxdoro.employer.common.Result.Error -> {
                            _uiState.update {
                                it.copy(
                                    errorMessage = result.message,
                                    isSearching = false
                                )
                            }
                        }

                        is com.maxdoro.employer.common.Result.Success -> {
                            val searchResults = result.data.toPersistentList()

                            _uiState.update {
                                it.copy(
                                    searchResults = searchResults,
                                    queryHasNoResults = searchResults.isEmpty(),
                                    isSearching = false,
                                    errorMessage = null
                                )
                            }
                        }
                    }
                } else {
                    _uiState.update {
                        it.copy(
                            searchResults = persistentListOf(),
                            queryHasNoResults = false,
                            isSearching = false,
                            errorMessage = null
                        )
                    }
                }
            }.launchIn(viewModelScope)
    }

    fun updateSearchQuery(newQuery: String) {
        searchQuery = newQuery
    }

}