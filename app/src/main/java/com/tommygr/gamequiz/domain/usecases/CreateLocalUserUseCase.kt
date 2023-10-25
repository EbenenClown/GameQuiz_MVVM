package com.tommygr.gamequiz.domain.usecases

import com.tommygr.gamequiz.domain.domainmodels.UserDomainModel
import com.tommygr.gamequiz.domain.repositories.UserRepository
import java.util.Date
import java.util.UUID
import javax.inject.Inject

class CreateLocalUserUseCase @Inject constructor(private val userRepository: UserRepository) {
    // Default Parameter for when creating new user
    suspend operator fun invoke(userDomainModel: UserDomainModel = UserDomainModel(UUID.randomUUID().toString(), "", "", false)) {
        // There should only ever be one user in the database
        userRepository.clear()
        userRepository.saveNewUser(userDomainModel)
    }
}