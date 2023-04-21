package com.tommygr.gamequiz.domain.usecases

interface UseCase<REQUEST, RESULT> {
    suspend fun execute(input: REQUEST, onResult:(RESULT) -> Unit)
}