package com.tommygr.gamequiz.data.source.local.database

import androidx.room.*
import com.tommygr.gamequiz.data.source.datamodels.QuizElementDataModel
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizElementDao {
    @Query("SELECT * FROM quizElement")
    fun observeAllQuizElements(): Flow<List<QuizElementDataModel>>

    @Query("SELECT * FROM quizElement")
    fun getAllQuizElements(): List<QuizElementDataModel>

    @Query("SELECT * FROM quizElement WHERE isSolved=0")
    fun getAllNotSolvedElements(): List<QuizElementDataModel>

    @Query("SELECT * FROM quizElement WHERE wasShown=0")
    fun getAllNotShownElements(): List<QuizElementDataModel>

    @Query("SELECT * FROM quizElement WHERE id=:id")
    fun observeQuizElement(id: String): Flow<QuizElementDataModel>

    @Query("SELECT * FROM quizElement WHERE id=:id")
    fun getQuizElement(id: String): QuizElementDataModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertQuizElement(quizElementDataModel: QuizElementDataModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(quizElementDataModels: List<QuizElementDataModel>)

    @Update
    fun updateQuizElement(quizElementDataModel: QuizElementDataModel)

    @Delete
    fun deleteQuizElement(quizElementDataModel: QuizElementDataModel)

    @Query("DELETE FROM quizElement")
    fun clear()
}