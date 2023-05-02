package com.tommygr.gamequiz.data.source.remote.remotedatasources

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.tommygr.gamequiz.data.source.datamodels.UserDataModel
import com.tommygr.gamequiz.data.source.datamodels.mapper.toDataModel
import com.tommygr.gamequiz.data.source.datamodels.mapper.toDomainModel
import com.tommygr.gamequiz.data.source.remote.FirebaseAPI
import com.tommygr.gamequiz.util.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class RemoteUserDataSource(private val firebaseAuth: FirebaseAuth, private val dispatcher: CoroutineDispatcher = Dispatchers.IO) {

     suspend fun getUser() = withContext(dispatcher) {
         return@withContext Firebase.auth.currentUser
    }

    suspend fun isUserSignedIn(): Boolean = withContext(dispatcher) {
        return@withContext Firebase.auth.currentUser != null
    }

     suspend fun registerUserWithEmailAndPassword(email: String, password: String) = withContext(dispatcher) {
        return@withContext firebaseAuth.createUserWithEmailAndPassword(email, password)
    }

    suspend fun loginUserWithEmailAndPassword(email: String, password: String) = withContext(dispatcher) {
        return@withContext firebaseAuth.signInWithEmailAndPassword(email, password)
    }
}