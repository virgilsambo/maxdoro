package com.maxdoro.employer.data.remote

import android.util.Log
import com.maxdoro.employer.common.Result
import com.maxdoro.employer.di.IoDispatcher
import com.maxdoro.employer.model.Employer
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EmployerRepositoryImpl @Inject constructor(
    private val networkDataSource: EmployerNetworkDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : EmployerRepository {

    override suspend fun getEmployerSearchResults(searchQuery: String): Result<List<Employer>> {
        return try {
            withContext(ioDispatcher) {
                val response = networkDataSource.getEmployerSearchResults(searchQuery = searchQuery)
                val body = response.body()

                if (response.isSuccessful && !body.isNullOrEmpty()) {
                    Result.Success(body)
                } else {
                    Log.e(
                        "Result",
                        "GET SearchResult unsuccessful retrofit response ${response.message()}"
                    )
                    Result.Error("Can't get search results")
                }
            }
        } catch (e: Exception) {
            Log.e("Result", "GET SearchResult error ${e.message}")
            Result.Error(" results")
        }
    }

    override suspend fun getEmployerDetail(employerId: Int): Result<Employer> {
        return Result.Error("Can't get search results")
    }

}