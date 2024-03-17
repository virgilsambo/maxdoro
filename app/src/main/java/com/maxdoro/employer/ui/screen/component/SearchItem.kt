package com.maxdoro.employer.ui.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.maxdoro.employer.common.withPercentageSign
import com.maxdoro.employer.model.Employer


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
                .padding(16.dp)
        ) {
            Text(
                text = employer.name,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = employer.place,
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = employer.discountPercentage.withPercentageSign(),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}