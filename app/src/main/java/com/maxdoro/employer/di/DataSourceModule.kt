package com.maxdoro.employer.di

import com.maxdoro.employer.data.EmployerNetworkDataSource
import com.maxdoro.employer.data.EmployerNetworkDataSourceImpl
import com.maxdoro.employer.data.MaxdoroApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideEmployerNetworkDataSource(maxdoroApi: MaxdoroApi): EmployerNetworkDataSource {
        return EmployerNetworkDataSourceImpl(maxdoroApi = maxdoroApi)
    }

}
