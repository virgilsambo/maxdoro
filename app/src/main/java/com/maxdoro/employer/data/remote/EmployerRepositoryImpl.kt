package com.maxdoro.employer.data.remote

import android.util.Log
import com.maxdoro.employer.common.Result
import com.maxdoro.employer.data.local.EmployerLocalDataSource
import com.maxdoro.employer.data.local.model.CachedEmployer
import com.maxdoro.employer.di.IoDispatcher
import com.maxdoro.employer.model.Employer
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class EmployerRepositoryImpl @Inject constructor(
    private val networkDataSource: EmployerNetworkDataSource,
    private val localDataSource: EmployerLocalDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : EmployerRepository {

    override suspend fun getEmployerSearchResults(searchQuery: String): Result<List<Employer>> {
        return try {
            withContext(ioDispatcher) {
                val cachedResponseFlow = localDataSource.getCachedEmployers(searchQuery)

                val cachedResponse = cachedResponseFlow.firstOrNull()

                if (cachedResponse != null && isResponseValid(cachedResponse.timestamp)) {
                    return@withContext Result.Success(cachedResponse.employers)
                }

                val response = networkDataSource.getEmployerSearchResults(searchQuery = searchQuery)
                val body = response.body()

                if (response.isSuccessful && !body.isNullOrEmpty()) {
                    localDataSource.updateCachedEmployers(
                        CachedEmployer(
                            filter = searchQuery,
                            employers = body,
                            timestamp = System.currentTimeMillis()
                        )
                    )
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

    private fun isResponseValid(timestamp: Long): Boolean {
        val currentTime = System.currentTimeMillis()
        val expirationTime = TimeUnit.DAYS.toMillis(7)
        return (currentTime - timestamp) < expirationTime
    }

    override suspend fun getEmployerDetail(employerId: Int): Result<Employer> {
        return Result.Error("Can't get search results")
    }
}