package com.maxdoro.employer.data.local

import com.maxdoro.employer.data.local.model.EmployerEntity
import kotlinx.coroutines.flow.Flow

interface EmployerLocalDataSource {
    fun getEmployers(): Flow<List<EmployerEntity>>
    suspend fun updateEmployers(employers: List<EmployerEntity>)
}
