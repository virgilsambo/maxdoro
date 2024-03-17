package com.maxdoro.employer.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.maxdoro.employer.model.Employer

@Entity
data class CachedEmployer(
    @PrimaryKey
    val filter: String,
    val employers: List<Employer>,
    val timestamp: Long
)
