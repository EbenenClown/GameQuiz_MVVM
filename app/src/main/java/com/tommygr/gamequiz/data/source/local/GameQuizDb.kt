package com.tommygr.gamequiz.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tommygr.gamequiz.data.QuizElement
import com.tommygr.gamequiz.data.Statistic
import com.tommygr.gamequiz.data.User

@Database(entities = [User::class, QuizElement::class, Statistic::class], version = 1, exportSchema = true)
abstract class GameQuizDb: RoomDatabase() {
    abstract fun quizElementDao(): QuizElementDao
    abstract fun statisticDao(): StatisticDao
    abstract fun userDao(): UserDataDao
}