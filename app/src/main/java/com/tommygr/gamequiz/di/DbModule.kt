package com.tommygr.gamequiz.di

import android.content.Context
import androidx.room.Room
import com.tommygr.gamequiz.data.source.local.GameQuizDb
import com.tommygr.gamequiz.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    //TODO: Remove fallbackToDestructiveMigration
    @Provides
    @Singleton
    fun provide(@ApplicationContext context: Context) = Room.databaseBuilder(context, GameQuizDb::class.java, Constants.DATABASE_NAME)
                                                                .fallbackToDestructiveMigration()
                                                                .build()

    @Provides
    @Singleton
    fun provideQuizElementDao(db: GameQuizDb) = db.quizElementDao()

    @Provides
    @Singleton
    fun provideStatisticDao(db: GameQuizDb) = db.statisticDao()

    @Provides
    @Singleton
    fun provideUserDao(db: GameQuizDb) = db.userDao()
}