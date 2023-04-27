package com.tommygr.gamequiz.domain.usecases

import com.tommygr.gamequiz.domain.domainmodels.StatisticDomainModel
import com.tommygr.gamequiz.domain.repositories.QuizElementRepository
import com.tommygr.gamequiz.domain.repositories.StatisticRepository
import com.tommygr.gamequiz.domain.repositories.UserRepository

class GetStatisticUseCase(private val statisticRepository: StatisticRepository) {
    suspend operator fun invoke(userId: String): StatisticDomainModel {
        return statisticRepository.getStatisticById(userId)
    }
}