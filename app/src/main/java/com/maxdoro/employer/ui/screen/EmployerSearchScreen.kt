package com.maxdoro.employer.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.FilterAlt
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.maxdoro.employer.R
import com.maxdoro.employer.common.SortCriteria
import com.maxdoro.employer.common.withPercentageSign
import com.maxdoro.employer.model.Employer
import com.maxdoro.employer.ui.screen.component.EmptyState
import com.maxdoro.employer.ui.screen.component.ErrorState
import com.maxdoro.employer.ui.screen.component.Loading
import com.maxdoro.employer.ui.screen.component.SearchItem
import com.maxdoro.employer.ui.screen.component.SortBottomSheet
import kotlinx.coroutines.launch

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SearchScreen(
        uiState = uiState,
        searchQuery = viewModel.searchQuery,
        onUpdateSortCriteria = {
            viewModel.updateSortCriteria(it)
        },
        shouldOpenBottomSheet = {
            viewModel.updateShowSortingSheet(it)
        },
        onSearchQueryChange = { searchQuery ->
            viewModel.updateSearchQuery(searchQuery)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    uiState: SearchUiState,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onUpdateSortCriteria: (SortCriteria) -> Unit,
    shouldOpenBottomSheet: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState()

    Scaffold(
        modifier = Modifier.background(MaterialTheme.colorScheme.background),
        topBar = {
            TopAppBar(

                title = {
                    Text(
                        text = "Maxdoro",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },
                actions = {
                    IconButton(onClick = {
                        shouldOpenBottomSheet(true)
                    }) {
                        Icon(
                            imageVector = Icons.Rounded.FilterAlt,
                            tint = MaterialTheme.colorScheme.onSurface,
                            contentDescription = null
                        )
                    }
                })
        }
    ) {

        SearchBar(
            query = searchQuery,
            onQueryChange = onSearchQueryChange,
            placeholder = {
                Text(
                    text = stringResource(R.string.search_input_placeholder),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            shape = RoundedCornerShape(8.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    tint = MaterialTheme.colorScheme.onSurface,
                    contentDescription = null
                )
            },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { onSearchQueryChange("") }) {
                        Icon(
                            imageVector = Icons.Rounded.Close,
                            tint = MaterialTheme.colorScheme.onSurface,
                            contentDescription = null
                        )
                    }
                }
            },
            onSearch = {},
            active = true,
            onActiveChange = {},
            modifier = modifier.padding(it)
        ) {
            when {
                uiState.isSearching -> {
                    Loading()
                }

                uiState.showErrorMessage -> {
                    uiState.errorMessage?.let {
                        ErrorState(
                            title = it
                        )
                    }
                }

                else -> {
                    SearchContent(uiState.searchResults)

                    if (uiState.showSortingSheet) {

                        SortBottomSheet(
                            sheetState = bottomSheetState,
                            selectedCoinSort = uiState.sortCriteria,
                            onSortCriteriaSelected = { coinSort ->
                                onUpdateSortCriteria(coinSort)

                                scope.launch {
                                    bottomSheetState.hide()
                                }.invokeOnCompletion {
                                    if (!bottomSheetState.isVisible) {
                                        shouldOpenBottomSheet(false)
                                    }
                                }
                            },
                            onDismissRequest = {
                                shouldOpenBottomSheet(false)
                            }
                        )
                    }
                }
            }

        }
    }

}

@Composable
fun SearchContent(
    searchResults: List<Employer>,
    modifier: Modifier = Modifier
) {
    if (searchResults.isNotEmpty()) {
        LazyColumn(
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        ) {
            items(
                count = searchResults.size,
            ) { index ->
                val resultItem = searchResults[index]
                SearchItem(employer = resultItem)
            }
        }
    } else {
        EmptyState(
            image = painterResource(R.drawable.icons8_search),
            title = "Welcome to Employer directory",
            subtitle = {
                Text(
                    text = "Hope you find what you looking for",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            modifier = modifier
        )
    }

}
