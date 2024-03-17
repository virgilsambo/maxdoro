package com.maxdoro.employer.data.remote

import com.maxdoro.employer.model.Employer
import retrofit2.Response

interface EmployerNetworkDataSource {

    suspend fun getEmployerSearchResults(searchQuery: String): Response<List<Employer>>

    suspend fun getEmployerDetail(employerId: Int): Response<Employer>
}