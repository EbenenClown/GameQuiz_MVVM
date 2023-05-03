package com.tommygr.gamequiz.domain.usecases

import com.tommygr.gamequiz.domain.repositories.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(forceUpdate: Boolean = false) = userRepository.getUser(forceUpdate)
}