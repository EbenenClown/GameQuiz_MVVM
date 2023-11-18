package com.tommygr.gamequiz.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tommygr.gamequiz.GameQuizDestinationsArgs
import com.tommygr.gamequiz.domain.usecases.GetSortedQuestionsUseCase
import com.tommygr.gamequiz.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val getSortedQuestionsUseCase: GetSortedQuestionsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val gameSize: Int = savedStateHandle[GameQuizDestinationsArgs.GAME_SIZE_ARG]!!

    private val _uiState = MutableStateFlow<GameUiState>(GameUiState.Initialization)
    val uiState = _uiState.asStateFlow()

    private val _timer = MutableStateFlow(0L)
    val timer = _timer.asStateFlow()

    private val questions = mutableListOf<Question>()
    private var timerJob: Job? = null
    private var activeQuestionIndex = 0
    private var score = 0

    init {
        loadQuizElements()
    }

    private fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (true) {
                delay(1000)
                _timer.value++
            }
        }
    }

    fun pauseTimer() {
        timerJob?.cancel()
    }

    private fun stopTimer() {
        _timer.value = 0
        timerJob?.cancel()
    }

    private fun loadQuizElements() {
        viewModelScope.launch(Dispatchers.IO) {
            val quizElementsResource = getSortedQuestionsUseCase(gameSize)
            if (quizElementsResource is Resource.Success) {
                questions.addAll(quizElementsResource.data!!.map {
                    //TODO: make backend give a list of options
                    Question(it.question, listOf(it.options), it.hint)
                })
                startGame(questions)

            } else {
                _uiState.value = GameUiState.Failure(
                    errorMessage = "Something went wrong"
                )
            }
        }
    }

    private fun startGame(questions: List<Question>) {
        startTimer()

        val activeQuestion = questions[activeQuestionIndex]

        _uiState.value = GameUiState.ActiveQuestion(
            question = activeQuestion
        )

        if (timer.value >= 15000) {
            _uiState.value = GameUiState.TimeUp(
                correctAnswer = activeQuestion.correctAnswer,
                score = score
            )
        }
    }

    fun checkAnswer(userAnswer: String) {
        stopTimer()

        val correctAnswer = questions[activeQuestionIndex].correctAnswer
        if(userAnswer == correctAnswer) {
            score++
            _uiState.value = GameUiState.CorrectAnswer(
                correctAnswer = correctAnswer,
                score = score
            )
        } else {
            _uiState.value = GameUiState.IncorrectAnswer(
                correctAnswer = correctAnswer,
                score = score
            )
        }
    }

    fun nextQuestion() {
        activeQuestionIndex++

        if (activeQuestionIndex > questions.size - 1) {

        }
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }
}

sealed class GameUiState {
    data object Initialization : GameUiState()
    data class ActiveQuestion(val question: Question) : GameUiState()
    data class StoppedQuestion(val question: Question, val timerSeconds: Int) : GameUiState()
    data class CorrectAnswer(val correctAnswer: String, val score: Int) : GameUiState()
    data class IncorrectAnswer(val correctAnswer: String, val score: Int) : GameUiState()
    data class TimeUp(val correctAnswer: String, val score: Int) : GameUiState()
    data class QuizFinished(val finalScore: Int) : GameUiState()
    data class Quit(val score: Int) : GameUiState()
    data class Failure(val errorMessage: String?) : GameUiState()
}

data class Question(
    val questionText: String,
    val answerOptions: List<String>,
    val correctAnswer: String
)