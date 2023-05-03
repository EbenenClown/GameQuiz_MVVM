package com.tommygr.gamequiz.domain.usecases

import com.tommygr.gamequiz.domain.domainmodels.UserDomainModel
import com.tommygr.gamequiz.domain.repositories.UserRepository
import java.util.Date
import java.util.UUID
import javax.inject.Inject

class CreateLocalUserUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke() {
        val generatedId = UUID.randomUUID().toString()
        val emptyUser = UserDomainModel(userId = generatedId, email = "")
        userRepository.saveNewUser(emptyUser)
    }
}