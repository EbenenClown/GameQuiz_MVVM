package com.tommygr.gamequiz.data.source.datamodels.mapper

import com.google.firebase.auth.FirebaseUser
import com.tommygr.gamequiz.data.source.datamodels.QuizElementDataModel
import com.tommygr.gamequiz.data.source.datamodels.StatisticDataModel
import com.tommygr.gamequiz.data.source.datamodels.UserDataModel
import com.tommygr.gamequiz.domain.domainmodels.QuizElementDomainModel
import com.tommygr.gamequiz.domain.domainmodels.StatisticDomainModel
import com.tommygr.gamequiz.domain.domainmodels.UserDomainModel

fun QuizElementDataModel.toDomainModel(): QuizElementDomainModel = QuizElementDomainModel(id, type, question, options, difficulty, hint, isSolved, wasShown)

fun List<QuizElementDataModel>.toDomainModel(): List<QuizElementDomainModel> = map { QuizElementDomainModel(it.id, it.type, it.question, it.options, it.difficulty, it.hint, it.isSolved, it.wasShown) }

fun List<QuizElementDomainModel>.toDataModel(): List<QuizElementDataModel> = map { QuizElementDataModel(it.id, it.type, it.question, it.options, it.difficulty, it.hint, it.isSolved, it.wasShown) }

fun StatisticDataModel.toDomainModel(): StatisticDomainModel = StatisticDomainModel(userId, gamesWon, gamesLost, perfectGames)

fun StatisticDomainModel.toDataModel(): StatisticDataModel = StatisticDataModel(userId, gamesWon, gamesLost, perfectGames)

fun UserDataModel.toDomainModel(): UserDomainModel = UserDomainModel(userId, userName, email, isRegistered)

fun UserDomainModel.toDataModel(): UserDataModel = UserDataModel(userId, email)

fun FirebaseUser.toDomainModel(): UserDomainModel = UserDomainModel(uid, displayName ?: "", email ?: "", true)

fun FirebaseUser.toDataModel(): UserDataModel = UserDataModel(uid, displayName ?: "", email ?: "", true)