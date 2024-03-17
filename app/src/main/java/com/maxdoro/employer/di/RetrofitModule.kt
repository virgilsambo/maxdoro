package com.maxdoro.employer.di

import com.maxdoro.employer.common.Constants
import com.maxdoro.employer.data.MaxdoroApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): MaxdoroApi {
        return retrofit.create(MaxdoroApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val apiInterceptor = Interceptor { chain ->
            val newRequest = chain.request()
                .newBuilder()
                .build()

            chain.proceed(newRequest)
        }

        val loggingInterceptor = HttpLoggingInterceptor()

        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(apiInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }
}
