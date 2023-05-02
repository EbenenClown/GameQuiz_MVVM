package com.tommygr.gamequiz.domain.usecases

import com.tommygr.gamequiz.domain.repositories.UserRepository

class GetUserUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(forceUpdate: Boolean = false) = userRepository.getUser(forceUpdate)
}