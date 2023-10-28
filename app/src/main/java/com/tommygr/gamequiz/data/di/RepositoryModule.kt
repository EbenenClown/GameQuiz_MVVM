package com.tommygr.gamequiz.data.di

import com.tommygr.gamequiz.data.repositories.DataStoreRepositoryImpl
import com.tommygr.gamequiz.data.repositories.QuizElementRepositoryImpl
import com.tommygr.gamequiz.data.repositories.StatisticRepositoryImpl
import com.tommygr.gamequiz.data.repositories.UserRepositoryImpl
import com.tommygr.gamequiz.domain.repositories.DataStoreRepository
import com.tommygr.gamequiz.domain.repositories.QuizElementRepository
import com.tommygr.gamequiz.domain.repositories.StatisticRepository
import com.tommygr.gamequiz.domain.repositories.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun provideQuizElementRepository(quizElementRepositoryImpl: QuizElementRepositoryImpl): QuizElementRepository

    @Binds
    @Singleton
    abstract fun provideStatisticRepository(statisticRepositoryImpl: StatisticRepositoryImpl): StatisticRepository

    @Binds
    @Singleton
    abstract fun provideUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    abstract fun provideDataStoreRepository(dataStoreRepositoryImpl: DataStoreRepositoryImpl): DataStoreRepository
}