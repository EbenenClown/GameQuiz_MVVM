package com.tommygr.gamequiz.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class QuizElement(@PrimaryKey val id: String, val type: Int, val question: String,
                       val options: List<String>,
                       val difficulty: Int,
                       val hint: String,
                       val isSolved: Boolean)
