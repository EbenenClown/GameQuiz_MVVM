package com.tommygr.gamequiz.data.source.local

import androidx.room.*
import com.tommygr.gamequiz.data.QuizElement
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizElementDao {
    @Query("SELECT * FROM quizElement")
    fun observeAllQuizElements(): Flow<List<QuizElement>>

    @Query("SELECT * FROM quizElement")
    fun getAllQuizElements(): List<QuizElement>

    @Query("SELECT * FROM quizElement WHERE id=:id")
    fun observeQuizElement(iD: String): Flow<QuizElement>

    @Query("SELECT * FROM quizElement WHERE id=:id")
    fun getQuizElement(id: String): QuizElement

    @Query("SELECT * FROM quizElement WHERE type=0")
    fun getScrambledQuizElements(): List<QuizElement>

    @Query("SELECT * FROM quizElement WHERE type=1")
    fun getPictureQuizElements(): List<QuizElement>

    @Query("SELECT * FROM quizElement WHERE isSolved=1")
    fun getNotCompletedQuizElements()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertQuizElement(quizElement: QuizElement)

    @Update
    fun updateQuizElement(quizElement: QuizElement)

    @Delete
    fun deleteQuizElement(quizElement: QuizElement)

    @Query("DELETE FROM quizElement")
    fun clear()
}