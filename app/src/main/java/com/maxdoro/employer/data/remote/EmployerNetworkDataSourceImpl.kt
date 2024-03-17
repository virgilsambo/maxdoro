package com.maxdoro.employer.data.remote

import com.maxdoro.employer.model.Employer
import retrofit2.Response
import javax.inject.Inject

class EmployerNetworkDataSourceImpl @Inject constructor(
    private val maxdoroApi: MaxdoroApi
): EmployerNetworkDataSource {
    override suspend fun getEmployerSearchResults(searchQuery: String): Response<List<Employer>> {
        return maxdoroApi.getEmployerSearchResults(searchQuery = searchQuery, maxRows = 100)
    }

    override suspend fun getEmployerDetail(employerId: Int): Response<Employer> {
        return maxdoroApi.getEmployerDetail(employerId = employerId)
    }
}