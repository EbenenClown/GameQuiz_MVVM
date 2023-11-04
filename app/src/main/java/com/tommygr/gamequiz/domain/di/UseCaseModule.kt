package com.tommygr.gamequiz.domain.di

import com.tommygr.gamequiz.domain.repositories.DataStoreRepository
import com.tommygr.gamequiz.domain.repositories.QuizElementRepository
import com.tommygr.gamequiz.domain.repositories.StatisticRepository
import com.tommygr.gamequiz.domain.repositories.UserRepository
import com.tommygr.gamequiz.domain.usecases.CreateLocalUserUseCase
import com.tommygr.gamequiz.domain.usecases.GetSortedQuestionsUseCase
import com.tommygr.gamequiz.domain.usecases.GetStatisticUseCase
import com.tommygr.gamequiz.domain.usecases.GetUserUseCase
import com.tommygr.gamequiz.domain.usecases.RefreshUserUseCase
import com.tommygr.gamequiz.domain.usecases.ResetQuizElementsUseCase
import com.tommygr.gamequiz.domain.usecases.SyncQuizElementsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideCreateLocalUserUseCase(userRepository: UserRepository, dataStoreRepository: DataStoreRepository): CreateLocalUserUseCase = CreateLocalUserUseCase(userRepository, dataStoreRepository)

    @Provides
    fun provideGetSortedQuestionsUseCase(quizElementRepository: QuizElementRepository): GetSortedQuestionsUseCase = GetSortedQuestionsUseCase(quizElementRepository)

    @Provides
    fun provideGetStatisticUseCase(statisticRepository: StatisticRepository) = GetStatisticUseCase(statisticRepository)

    @Provides
    fun provideGetUserUseCase(userRepository: UserRepository) = GetUserUseCase(userRepository)

    @Provides
    fun provideResetQuizElementsUseCase(quizElementRepository: QuizElementRepository) = ResetQuizElementsUseCase(quizElementRepository)
    @Provides
    fun provideRefreshUserUseCase(userRepository: UserRepository) = RefreshUserUseCase(userRepository)

    @Provides
    fun provideSyncQuizElementsUseCase(quizElementRepository: QuizElementRepository) = SyncQuizElementsUseCase(quizElementRepository)
}