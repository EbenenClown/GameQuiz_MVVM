package com.tommygr.gamequiz.data.source.remote

import com.tommygr.gamequiz.data.source.datamodels.QuizElementDataModel
import com.tommygr.gamequiz.data.source.datamodels.StatisticDataModel
import com.tommygr.gamequiz.data.source.datamodels.UserDataModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface FirebaseAPI {
    @GET("/quizelements.json")
    @Headers("Accepts: application/json")
    suspend fun getAll(): Response<HashMap<String,QuizElementDataModel>>

    @POST("/quizelements/.json")
    suspend fun saveElement(@Body quizElementDataModel: QuizElementDataModel): Response<Void>

    @GET("/statistics/{id}.json")
    suspend fun getStatisticById(@Path("id")userId: String): Response<HashMap<String, StatisticDataModel>>

    @POST("/statistics/.json")
    suspend fun saveStatistic(statisticDataModel: StatisticDataModel)

    @PUT("statistics/{id}.json")
    suspend fun updateStatistic(@Path("id")id: String, @Body statisticDataModel: StatisticDataModel)

    @DELETE("statistics/{id}.json")
    suspend fun deleteStatistic(@Path("id")id: String)
}