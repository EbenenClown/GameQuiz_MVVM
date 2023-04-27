package com.tommygr.gamequiz.domain.domainmodels

data class QuizElementDomainModel( val id: String, val type: Int, val question: String,
                       val options: String,
                       val difficulty: Int,
                       val hint: String,
                       val isSolved: Boolean,
                       val wasShown: Boolean)
//TODO: options to array