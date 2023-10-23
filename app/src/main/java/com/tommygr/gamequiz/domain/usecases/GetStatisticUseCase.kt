package com.tommygr.gamequiz.domain.usecases

import com.tommygr.gamequiz.domain.domainmodels.StatisticDomainModel
import com.tommygr.gamequiz.domain.repositories.StatisticRepository
import com.tommygr.gamequiz.util.Resource
import javax.inject.Inject

class GetStatisticUseCase @Inject constructor(private val statisticRepository: StatisticRepository) {
    suspend operator fun invoke(userId: String): Resource<StatisticDomainModel> = statisticRepository.getStatistic(userId)
}