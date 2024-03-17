package com.maxdoro.employer.di

import com.maxdoro.employer.data.remote.EmployerNetworkDataSource
import com.maxdoro.employer.data.remote.EmployerNetworkDataSourceImpl
import com.maxdoro.employer.data.remote.MaxdoroApi
import com.maxdoro.employer.data.local.dao.EmployerDao
import com.maxdoro.employer.data.local.MaxdoroLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.shorthouse.coinwatch.data.source.local.MaxdoroLocalDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideEmployerNetworkDataSource(maxdoroApi: MaxdoroApi): EmployerNetworkDataSource {
        return EmployerNetworkDataSourceImpl(maxdoroApi = maxdoroApi)
    }

    @Provides
    @Singleton
    fun provideEmployerLocalDataSource(employerDao: EmployerDao): MaxdoroLocalDataSource {
        return MaxdoroLocalDataSourceImpl(employerDao = employerDao)
    }
}
