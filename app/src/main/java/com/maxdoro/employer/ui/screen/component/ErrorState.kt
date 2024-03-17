package com.maxdoro.employer.ui.screen.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.maxdoro.employer.R

@Composable
fun ErrorState(
    title: String,
    modifier: Modifier = Modifier
) {
    EmptyState(
        image = painterResource(id = R.drawable.icons8_unavailable),
        title = title,
        subtitle = {
            Text(
                text = "Sorry something went wrong, please try again",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        modifier = modifier
    )
}