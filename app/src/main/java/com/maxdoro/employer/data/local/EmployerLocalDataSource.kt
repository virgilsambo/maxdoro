package com.maxdoro.employer.data.local

import com.maxdoro.employer.data.local.model.CachedEmployer
import kotlinx.coroutines.flow.Flow

interface EmployerLocalDataSource {
    fun getCachedEmployers(filter: String): Flow<CachedEmployer?>
    suspend fun updateCachedEmployers(cachedEmployer: CachedEmployer)
}
