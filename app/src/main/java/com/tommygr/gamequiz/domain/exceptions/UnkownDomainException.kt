package com.tommygr.gamequiz.domain.exceptions

class UnknownDomainException(throwable: Throwable): DomainException(throwable) {
    constructor(errorMessage: String): this(Throwable(errorMessage))
}