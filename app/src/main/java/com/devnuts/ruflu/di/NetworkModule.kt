package com.devnuts.ruflu.di

import com.devnuts.ruflu.util.ServerAPI
import com.devnuts.ruflu.worker.AddCookiesInterceptor
import com.devnuts.ruflu.worker.ReceivedCookiesInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideMenuApiService(
        retrofit: Retrofit
    ): ServerAPI = retrofit.create(ServerAPI::class.java)
}


// Retrofit Builder
@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        converter: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(converter)
            .baseUrl("http://10.0.2.2:3000/api/")
            .build()
    }
}

// Factory
@Module
@InstallIn(SingletonComponent::class)
object ConvertFactoryModule {
    @Provides
    fun provideConvertFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }
}

// Client
@Module
@InstallIn(SingletonComponent::class)
object HttpClientModule {
    @Provides
    fun provideHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient
            .Builder()
            .connectTimeout(3, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(AddCookiesInterceptor())
            .addInterceptor(ReceivedCookiesInterceptor())
            .build()
    }
}