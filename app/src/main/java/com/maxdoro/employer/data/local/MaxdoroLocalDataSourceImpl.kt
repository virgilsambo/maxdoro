package dev.shorthouse.coinwatch.data.source.local

import com.maxdoro.employer.data.local.dao.EmployerDao
import com.maxdoro.employer.data.local.model.EmployerEntity
import com.maxdoro.employer.data.local.MaxdoroLocalDataSource
import kotlinx.coroutines.flow.Flow

class MaxdoroLocalDataSourceImpl(
    private val employerDao: EmployerDao
) : MaxdoroLocalDataSource {

    override fun getEmployers(): Flow<List<EmployerEntity>> {
        return employerDao.getEmployers()
    }

    override suspend fun updateEmployers(employers: List<EmployerEntity>) {
        return employerDao.updateEmployers(employers)
    }
}
