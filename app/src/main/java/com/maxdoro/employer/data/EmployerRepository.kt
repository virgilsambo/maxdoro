package com.maxdoro.employer.data

import com.maxdoro.employer.common.Result
import com.maxdoro.employer.model.Employer

interface EmployerRepository {
    suspend fun getEmployerSearchResults(searchQuery: String): Result<List<Employer>>

    suspend fun getEmployerDetail(employerId: Int): Result<Employer>
}