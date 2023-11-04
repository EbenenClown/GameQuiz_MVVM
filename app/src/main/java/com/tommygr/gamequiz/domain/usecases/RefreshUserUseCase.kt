package com.tommygr.gamequiz.domain.usecases

import com.tommygr.gamequiz.domain.domainmodels.UserDomainModel
import com.tommygr.gamequiz.domain.repositories.UserRepository
import com.tommygr.gamequiz.util.Resource
import javax.inject.Inject

class RefreshUserUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend operator fun invoke(userId: String): Resource<UserDomainModel> = userRepository.refreshUser(userId)
}