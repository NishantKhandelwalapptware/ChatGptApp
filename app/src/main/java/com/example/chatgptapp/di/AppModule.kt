package com.example.chatgptapp.di

import com.example.chatgptapp.network.ApiService
import com.example.chatgptapp.util.Constants
import com.example.chatgptapp.util.Constants.CONNECTION_TIMEOUT
import com.example.chatgptapp.util.Constants.READ_TIMEOUT
import com.example.chatgptapp.util.Constants.WRITE_TIMEOUT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [LoggingInterceptorModule::class])
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkhttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}