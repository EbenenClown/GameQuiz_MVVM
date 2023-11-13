package com.tommygr.gamequiz.domain.usecases

import com.tommygr.gamequiz.domain.domainmodels.UserDomainModel
import com.tommygr.gamequiz.domain.repositories.DataStoreRepository
import com.tommygr.gamequiz.domain.repositories.UserRepository
import com.tommygr.gamequiz.util.Resource
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class RefreshUserUseCase @Inject constructor(private val userRepository: UserRepository, private val dataStoreRepository: DataStoreRepository) {

    suspend operator fun invoke(userId: String? = null): Resource<UserDomainModel> {
        return if(userId.isNullOrEmpty()) {
            val storedUserId = dataStoreRepository.getUserId().first()
            userRepository.refreshUser(storedUserId)
        } else {
            userRepository.refreshUser(userId)
        }
    }
}