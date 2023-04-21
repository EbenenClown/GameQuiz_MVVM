package com.tommygr.gamequiz.domain.exceptions

import java.lang.Exception

abstract class DomainException(open val throwable: Throwable): Exception(throwable) {
    constructor(message: String): this(Exception(message))
}