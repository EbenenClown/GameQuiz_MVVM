package com.tommygr.gamequiz.util.dataGenerators

import com.tommygr.gamequiz.domain.domainmodels.QuizElementDomainModel
import kotlin.random.Random

fun getQuizDomainModelListWith150Entries(): List<QuizElementDomainModel> {
    val list = mutableListOf<QuizElementDomainModel>()
    for(i in 0..150) {
        val wasShown = Random.nextBoolean()
        val isSolved =  if (wasShown) Random.nextBoolean() else false
        list.add(quizElementDomainModel(i.toString(), isSolved = wasShown, wasShown = isSolved))
    }
    return list
}

fun quizElementDomainModel(id: String, isSolved: Boolean = false, wasShown: Boolean = false) = QuizElementDomainModel(
    id = id,
    type = 0,
    question = "Where",
    options = "options",
    difficulty = 0,
    hint = "hint",
    isSolved = isSolved,
    wasShown = wasShown
)