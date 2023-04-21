package com.tommygr.gamequiz.domain.domainmodels

data class StatisticDomainModel(val userId: String, val gamesWon: Int, val gamesLost: Int, val perfectGames: Int) {
    val totalGames: Int
        get() = gamesWon + gamesLost
}