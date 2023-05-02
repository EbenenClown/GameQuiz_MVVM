package com.tommygr.gamequiz.domain.usecases

import com.tommygr.gamequiz.domain.domainmodels.StatisticDomainModel
import com.tommygr.gamequiz.domain.repositories.StatisticRepository

class GetStatisticUseCase(private val statisticRepository: StatisticRepository) {
    suspend operator fun invoke(forceUpdate: Boolean = false): StatisticDomainModel {
        return statisticRepository.getStatistic(forceUpdate)
    }
}