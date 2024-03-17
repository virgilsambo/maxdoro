package com.maxdoro.employer.ui.screen.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocalConvenienceStore
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Percent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.maxdoro.employer.common.SortCriteria
import kotlinx.collections.immutable.persistentListOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortBottomSheet(
    sheetState: SheetState,
    selectedCoinSort: SortCriteria,
    onSortCriteriaSelected: (SortCriteria) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    val coinSortOptions = remember {
        persistentListOf(
            SortOption(
                icon = Icons.Rounded.LocalConvenienceStore,
                label = SortCriteria.Name.label,
                sortType = SortCriteria.Name
            ),
            SortOption(
                icon = Icons.Rounded.Percent,
                label = SortCriteria.DiscountPercentage.label,
                sortType = SortCriteria.DiscountPercentage
            ),
            SortOption(
                icon = Icons.Rounded.LocationOn,
                label = SortCriteria.Place.label,
                sortType = SortCriteria.Place
            )
        )
    }

    AppBottomSheet(
        title = "Filter/Sorting",
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        modifier = modifier
    ) {
        coinSortOptions.forEach { option ->
            SortItemOption(
                icon = option.icon,
                label = option.label,
                isSelected = option.sortType == selectedCoinSort,
                onSelected = { onSortCriteriaSelected(option.sortType) }
            )
        }
    }
}


private data class SortOption(
    val icon: ImageVector,
    val label: String,
    val sortType: SortCriteria
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBottomSheet(
    title: String,
    onDismissRequest: () -> Unit,
    sheetState: SheetState,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        dragHandle = null,
        containerColor = MaterialTheme.colorScheme.surface,
        //contentColor = MaterialTheme.colorScheme.onSurface,
        tonalElevation = 0.dp,
        sheetState = sheetState,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(Modifier.height(16.dp))

            content()
        }
    }
}