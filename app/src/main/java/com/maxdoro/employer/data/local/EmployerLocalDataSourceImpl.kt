package com.maxdoro.employer.data.local

import com.maxdoro.employer.data.local.model.EmployerEntity
import com.maxdoro.employer.data.local.dao.CachedEmployerDao
import com.maxdoro.employer.data.local.model.CachedEmployer
import kotlinx.coroutines.flow.Flow

class EmployerLocalDataSourceImpl(
    private val cachedEmployerDao: CachedEmployerDao
) : EmployerLocalDataSource {

    override fun getCachedEmployers(filter: String): Flow<CachedEmployer?> {
        return cachedEmployerDao.getCachedEmployer(filter)
    }

    override suspend fun updateCachedEmployers(cachedEmployer: CachedEmployer) {
        return cachedEmployerDao.updateCachedEmployers(cachedEmployer)
    }
}
