package com.tommygr.gamequiz.data.source.remote.remotedatasources

import com.tommygr.gamequiz.data.source.datamodels.QuizElementDataModel
import com.tommygr.gamequiz.data.source.remote.FirebaseAPI
import com.tommygr.gamequiz.domain.domainmodels.QuizElementDomainModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class RemoteQuizElementDataSource @Inject constructor(private val firebaseAPI: FirebaseAPI, private val dispatcher: CoroutineDispatcher = Dispatchers.IO) {
     suspend fun getAllElements(): Response<HashMap<String, QuizElementDataModel>> = withContext(dispatcher) {
        return@withContext  firebaseAPI.getAll()
    }

    suspend fun saveElement(quizElement: QuizElementDataModel): Response<Void> {
        return firebaseAPI.saveElement(quizElement)
    }
}