package com.tommygr.gamequiz.domain.usecases

import com.tommygr.gamequiz.domain.domainmodels.GameDomainModel
import com.tommygr.gamequiz.domain.domainmodels.QuizElementDomainModel
import com.tommygr.gamequiz.domain.repositories.QuizElementRepository
import com.tommygr.gamequiz.domain.repositories.StatisticRepository
import com.tommygr.gamequiz.util.Resource
import javax.inject.Inject

class GetGameWithSizeUseCase @Inject constructor(private val quizElementRepository: QuizElementRepository, private val statisticRepository: StatisticRepository) {
    suspend operator fun invoke(gameSize: Int): Resource<GameDomainModel> {
        val notShownShuffledList = quizElementRepository.getAllNotShownElements().data?.shuffled()
        val sortedList = mutableListOf<QuizElementDomainModel>()

        if(!notShownShuffledList.isNullOrEmpty()) {
            sortedList.addAll(notShownShuffledList.take(gameSize))

            if(sortedList.size < gameSize) {
                val notSolvedShuffledList = quizElementRepository.getAllNotSolvedElements().data?.shuffled()
                if(!notSolvedShuffledList.isNullOrEmpty()) {
                    sortedList.addAll(notSolvedShuffledList.take(gameSize - sortedList.size))
                } else {
                    return Resource.Error("Couldn't get not solved elements List")
                }
            }

            if(sortedList.size < gameSize) {
                val allShuffledList = quizElementRepository.getAllElements().data?.shuffled()
                if(!allShuffledList.isNullOrEmpty()) {
                    sortedList.addAll(allShuffledList.take(gameSize - sortedList.size))
                    val resetQuizElementsUseCase = ResetQuizElementsUseCase(quizElementRepository)
                    resetQuizElementsUseCase()
                } else {
                    return Resource.Error("Couldn't get all elements List")
                }
            }

            val statisticsUseCase = GetStatisticUseCase(statisticRepository)
            val statistic = statisticsUseCase().data
            if(statistic != null) {
                return Resource.Success(GameDomainModel(sortedList, statistic))
            } else {
                return Resource.Error("Couldn't get statistics")
            }
        }

        return Resource.Error("Couldn't get not Shown elements")
    }
}