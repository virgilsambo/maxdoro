package com.maxdoro.employer.data

import com.maxdoro.employer.common.Constants.QUERY_PARAM_EMPLOYER_ID
import com.maxdoro.employer.common.Constants.QUERY_PARAM_FILTER
import com.maxdoro.employer.common.Constants.QUERY_PARAM_MAX_ROWS
import com.maxdoro.employer.model.Employer
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MaxdoroApi {

    @GET("employers")
    suspend fun getEmployerSearchResults(
        @Query(QUERY_PARAM_FILTER) searchQuery:String,
        @Query(QUERY_PARAM_MAX_ROWS) maxRows:Int
        ): Response<List<Employer>>

    @GET("employers/{employerId}/")
    suspend fun getEmployerDetail(
        @Path(QUERY_PARAM_EMPLOYER_ID) employerId:Int
    ): Response<Employer>
}