package com.tommygr.gamequiz.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tommygr.gamequiz.data.source.datamodels.QuizElementDataModel
import com.tommygr.gamequiz.data.source.datamodels.StatisticDataModel
import com.tommygr.gamequiz.data.source.datamodels.UserDataModel
import com.tommygr.gamequiz.data.source.local.converters.DateConverter
import com.tommygr.gamequiz.data.source.local.daos.QuizElementDao
import com.tommygr.gamequiz.data.source.local.daos.StatisticDao
import com.tommygr.gamequiz.data.source.local.daos.UserDao

@Database(entities = [UserDataModel::class, QuizElementDataModel::class, StatisticDataModel::class], version = 1, exportSchema = true)
@TypeConverters(DateConverter::class)
abstract class GameQuizDb: RoomDatabase() {
    abstract fun quizElementDao(): QuizElementDao
    abstract fun statisticDao(): StatisticDao
    abstract fun userDao(): UserDao
}