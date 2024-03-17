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
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.maxdoro.employer.model.Employer

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SearchScreen(
        uiState = uiState,
        searchQuery = viewModel.searchQuery,
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
    modifier: Modifier = Modifier
) {

    SearchBar(
        query = searchQuery,
        onQueryChange = onSearchQueryChange,
        placeholder = {
            Text(
                text = "zoeken",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
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
        modifier = modifier
    ) {
        when {
            uiState.isSearching -> {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.onBackground,
                    strokeWidth = 5.dp,
                    strokeCap = StrokeCap.Round
                )
            }

            uiState.showErrorMessage -> {
                uiState.errorMessage?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
            else -> {
                SearchContent(uiState.searchResults)
            }
        }

    }
}

@Composable
fun SearchContent(
    searchResults: List<Employer>,
    modifier: Modifier = Modifier
) {
    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        items(
            count = searchResults.size,
        ) { index ->
            val resultItem = searchResults[index]
            SearchItem(employer = resultItem)
        }
    }
}

@Composable
fun SearchItem(
    employer: Employer,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .heightIn(56.dp)
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(text = employer.name, color = Color.Black)
            Text(text = employer.place, color = Color.Green)
        }
    }

}