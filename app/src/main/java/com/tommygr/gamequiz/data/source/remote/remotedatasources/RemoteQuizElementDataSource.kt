package com.tommygr.gamequiz.data.source.remote.remotedatasources

import com.tommygr.gamequiz.data.source.datamodels.QuizElementDataModel
import com.tommygr.gamequiz.data.source.remote.FirebaseAPI
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class RemoteQuizElementDataSource(private val firebaseAPI: FirebaseAPI, private val dispatcher: CoroutineDispatcher = Dispatchers.IO) {
     suspend fun getAllElements(): List<QuizElementDataModel> = withContext(dispatcher) {
        return@withContext firebaseAPI.getAll()
    }
}