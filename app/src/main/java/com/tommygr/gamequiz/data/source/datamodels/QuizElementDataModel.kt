package com.tommygr.gamequiz.data.source.datamodels

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quizelement")
data class QuizElementDataModel(@PrimaryKey val id: String, val type: Int, val question: String,
                                val options: String,
                                val difficulty: Int,
                                val hint: String,
                                val isSolved: Boolean,
                                val wasShown: Boolean)
//TODO: options to array