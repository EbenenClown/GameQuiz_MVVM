package com.tommygr.gamequiz.domain.usecases

import com.tommygr.gamequiz.domain.exceptions.DomainException
import com.tommygr.gamequiz.domain.exceptions.UnknownDomainException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class UseCaseExecutor(private val coroutineScope: CoroutineScope) {
    fun<INPUT, OUTPUT> execute(useCase: UseCase<INPUT, OUTPUT>, value: INPUT, onResult:(OUTPUT) -> Unit = {}, onException: (DomainException) -> Unit = {}) {
        coroutineScope.launch {
            try {
                useCase.execute(value, onResult)
            } catch(ignore:CancellationException) {
            } catch(throwable: Throwable) {
                onException(throwable as? DomainException ?: UnknownDomainException(throwable))
            }
        }
    }
}