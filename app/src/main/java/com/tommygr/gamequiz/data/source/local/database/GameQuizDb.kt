package com.tommygr.gamequiz.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tommygr.gamequiz.data.source.datamodels.QuizElementDataModel
import com.tommygr.gamequiz.data.source.datamodels.StatisticDataModel
import com.tommygr.gamequiz.data.source.datamodels.UserDataModel
import com.tommygr.gamequiz.data.source.local.database.converters.DateConverter
import com.tommygr.gamequiz.data.source.local.database.QuizElementDao
import com.tommygr.gamequiz.data.source.local.database.StatisticDao
import com.tommygr.gamequiz.data.source.local.database.UserDao

@Database(entities = [UserDataModel::class, QuizElementDataModel::class, StatisticDataModel::class], version = 1, exportSchema = true)
@TypeConverters(DateConverter::class)
abstract class GameQuizDb: RoomDatabase() {
    abstract fun quizElementDao(): QuizElementDao
    abstract fun statisticDao(): StatisticDao
    abstract fun userDao(): UserDao
}