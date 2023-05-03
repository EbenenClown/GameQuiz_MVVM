package com.tommygr.gamequiz.domain.di

import com.tommygr.gamequiz.domain.repositories.QuizElementRepository
import com.tommygr.gamequiz.domain.repositories.StatisticRepository
import com.tommygr.gamequiz.domain.repositories.UserRepository
import com.tommygr.gamequiz.domain.usecases.CreateLocalUserUseCase
import com.tommygr.gamequiz.domain.usecases.GetGameWithSizeUseCase
import com.tommygr.gamequiz.domain.usecases.GetStatisticUseCase
import com.tommygr.gamequiz.domain.usecases.GetUserUseCase
import com.tommygr.gamequiz.domain.usecases.ResetQuizElementsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideCreateLocalUserUseCase(userRepository: UserRepository): CreateLocalUserUseCase = CreateLocalUserUseCase(userRepository)

    @Provides
    fun provideGetGameWithSizeUseCase(quizElementRepository: QuizElementRepository, statisticRepository: StatisticRepository): GetGameWithSizeUseCase = GetGameWithSizeUseCase(quizElementRepository, statisticRepository)

    @Provides
    fun provideGetStatisticUseCase(statisticRepository: StatisticRepository) = GetStatisticUseCase(statisticRepository)

    @Provides
    fun provideGetUserUseCase(userRepository: UserRepository) = GetUserUseCase(userRepository)

    @Provides
    fun provideResetUserUseCase(quizElementRepository: QuizElementRepository) = ResetQuizElementsUseCase(quizElementRepository)

}