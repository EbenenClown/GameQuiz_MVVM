package com.tommygr.gamequiz.data.source.remote

import com.tommygr.gamequiz.data.source.datamodels.QuizElementDataModel
import com.tommygr.gamequiz.data.source.datamodels.StatisticDataModel
import com.tommygr.gamequiz.data.source.datamodels.UserDataModel
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface FirebaseAPI {
    @GET("/quizelements")
    fun getAll(): List<QuizElementDataModel>

    @GET("/quizelements/{id}")
    fun getElementById(@Path("id")id: String): QuizElementDataModel

    @POST("/quizelements/")
    fun saveELementList(quizElementDataModels: List<QuizElementDataModel>)

    @POST("/quizelements/")
    fun saveElement(element: QuizElementDataModel)

    @PUT("quizelements/{id}")
    fun updateElement(@Path("id")id: String, @Body element: QuizElementDataModel)

    @DELETE("quizelements/{id}")
    fun deleteElement(@Path("id")id: String)

    @GET("/statistics/{id}")
    fun getStatisticById(@Path("id")userId: String): StatisticDataModel

    @POST("/statistics/")
    fun saveStatistic(statisticDataModel: StatisticDataModel)

    @PUT("statistics/{id}")
    fun updateStatistic(@Path("id")id: String, @Body statisticDataModel: StatisticDataModel)

    @DELETE("statistics/{id}")
    fun deleteStatistic(@Path("id")id: String)

    @GET("/users/{id}")
    fun getUserById(@Path("id")id: String): UserDataModel

    @POST("/users/")
    fun saveNewUser(userDataModel: UserDataModel)

    @PUT("users/{id}")
    fun updateUser(@Path("id")id: String, @Body userDataModel: UserDataModel)

    @DELETE("users/{id}")
    fun deleteUser(@Path("id")id: String)



}