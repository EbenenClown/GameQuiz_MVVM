package com.tommygr.gamequiz.data.di

import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tommygr.gamequiz.data.source.remote.FirebaseAPI
import com.tommygr.gamequiz.data.source.remote.remotedatasources.RemoteQuizElementDataSource
import com.tommygr.gamequiz.data.source.remote.remotedatasources.RemoteStatisticDataSource
import com.tommygr.gamequiz.data.source.remote.remotedatasources.RemoteUserDataSource
import com.tommygr.gamequiz.util.Constants
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
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun providesBaseUrl(): String {
        return Constants.BASE_URL
    }

    @Provides
    fun provideConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun provideRetrofitClient(baseUrl: String, converterFactory: Converter.Factory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideRestApiService(retrofit: Retrofit): FirebaseAPI {
        return retrofit.create(FirebaseAPI::class.java)
    }

    @Provides
    fun provideRemoteQuizElementDataSource(firebaseAPI: FirebaseAPI): RemoteQuizElementDataSource {
        return RemoteQuizElementDataSource(firebaseAPI)
    }

    @Provides
    fun provideRemoteStatisticDataSource(firebaseAPI: FirebaseAPI): RemoteStatisticDataSource {
        return RemoteStatisticDataSource(firebaseAPI)
    }

    @Provides
    fun provideRemoteUserDataSource(firebaseAuth: FirebaseAuth): RemoteUserDataSource {
        return RemoteUserDataSource(firebaseAuth)
    }
}