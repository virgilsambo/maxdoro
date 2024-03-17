package com.maxdoro.employer.data.local

import com.maxdoro.employer.data.local.dao.EmployerDao
import com.maxdoro.employer.data.local.model.EmployerEntity
import com.maxdoro.employer.data.local.EmployerLocalDataSource
import kotlinx.coroutines.flow.Flow

class EmployerLocalDataSourceImpl(
    private val employerDao: EmployerDao
) : EmployerLocalDataSource {

    override fun getEmployers(): Flow<List<EmployerEntity>> {
        return employerDao.getEmployers()
    }

    override suspend fun updateEmployers(employers: List<EmployerEntity>) {
        return employerDao.updateEmployers(employers)
    }
}
