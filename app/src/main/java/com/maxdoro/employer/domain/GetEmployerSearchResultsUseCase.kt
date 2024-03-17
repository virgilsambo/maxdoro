package com.maxdoro.employer.domain

import com.maxdoro.employer.common.Result
import com.maxdoro.employer.data.remote.EmployerRepository
import com.maxdoro.employer.model.Employer
import javax.inject.Inject

class GetEmployerSearchResultsUseCase @Inject constructor(
    private val repository: EmployerRepository
) {
    suspend operator fun invoke(searchQuery: String): Result<List<Employer>> {
        return getEmployerSearchResults(searchQuery = searchQuery)
    }

    private suspend fun getEmployerSearchResults(searchQuery: String): Result<List<Employer>> {
        return repository.getEmployerSearchResults(searchQuery = searchQuery)
    }
}
