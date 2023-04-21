package com.tommygr.gamequiz.data.source.datamodels

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "statistic")
data class StatisticDataModel(@PrimaryKey val userId: String, val gamesWon: Int, val gamesLost: Int, val perfectGames: Int) {
    @get:Ignore
    val totalGames: Int
        get() = gamesWon + gamesLost
}