package com.tommygr.gamequiz.domain.usecases

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers


abstract class BackgroundExecutingUseCase<REQUEST, RESULT>(private val dispatcher: CoroutineDispatcher = Dispatchers.IO): UseCase<REQUEST,RESULT> {

}