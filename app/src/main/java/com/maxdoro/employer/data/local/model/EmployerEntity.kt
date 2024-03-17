package com.maxdoro.employer.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EmployerEntity(
    @PrimaryKey
    val employerId: Int,
    val name: String,
    val discountPercentage: Int,
    val place: String,
)