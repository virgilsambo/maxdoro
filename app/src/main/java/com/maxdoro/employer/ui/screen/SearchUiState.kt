package com.maxdoro.employer.ui.screen

import com.maxdoro.employer.common.SortCriteria
import com.maxdoro.employer.model.Employer

data class SearchUiState(
    val searchResults: List<Employer> = listOf(),
    val queryHasNoResults: Boolean = false,
    val errorMessage: String? = null,
    val isSearching: Boolean = false,
    val sortCriteria: SortCriteria = SortCriteria.Name,
    val showSortingSheet: Boolean = false
) {
    val showErrorMessage: Boolean
        get() = errorMessage != null
}