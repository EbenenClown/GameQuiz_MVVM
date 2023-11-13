package com.tommygr.shared_test.datagenerators

import com.tommygr.gamequiz.data.source.datamodels.QuizElementDataModel


    fun quizElementDataModel(id: String, isSolved: Boolean = false, wasShown: Boolean = false) = QuizElementDataModel(
        id = id,
        type = 1,
        question = "question",
        options = "options",
        difficulty = 1,
        hint = "hint",
        isSolved = isSolved,
        wasShown = wasShown,
    )
