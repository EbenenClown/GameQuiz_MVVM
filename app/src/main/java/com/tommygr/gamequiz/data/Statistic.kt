package com.tommygr.gamequiz.data

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Statistic(@PrimaryKey val userId: String, val gamesWon: Int, val gamesLost: Int, val perfectGames: Int) {
    @get:Ignore
    val totalGames: Int
        get() = gamesWon + gamesLost
}