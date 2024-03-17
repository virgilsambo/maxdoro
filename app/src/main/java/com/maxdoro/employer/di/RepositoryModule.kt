package com.maxdoro.employer.di

import com.maxdoro.employer.data.remote.EmployerNetworkDataSource
import com.maxdoro.employer.data.remote.EmployerRepository
import com.maxdoro.employer.data.remote.EmployerRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideEmployerRepository(
        employerNetworkDataSource: EmployerNetworkDataSource,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): EmployerRepository {
        return EmployerRepositoryImpl(
            networkDataSource = employerNetworkDataSource,
            ioDispatcher = ioDispatcher
        )
    }
}
