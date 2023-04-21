package com.tommygr.gamequiz.data.source.remote

import com.tommygr.gamequiz.data.QuizElement
import com.tommygr.gamequiz.data.Statistic
import com.tommygr.gamequiz.data.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface FirebaseAPI {
    @GET("/quizelements")
    fun getAll(): List<QuizElement>

    @GET("/quizelements/{id}")
    fun getElementById(@Path("id")id: String): QuizElement

    @POST("/quizelements/")
    fun saveElement(element: QuizElement)

    @PUT("quizelements/{id}")
    fun updateElement(@Path("id")id: String, @Body element: QuizElement)

    @DELETE("quizelements/{id}")
    fun deleteElement(@Path("id")id: String)

    @GET("/statistics/{id}")
    fun getStatisticById(@Path("id")userId: String): Statistic

    @POST("/statistics/")
    fun saveStatistic(statistic: Statistic)

    @PUT("statistics/{id}")
    fun updateStatistic(@Path("id")id: String, @Body statistic: Statistic)

    @DELETE("statistics/{id}")
    fun deleteStatistic(@Path("id")id: String)

    @GET("/users/{id}")
    fun getUserById(@Path("id")id: String): User

    @POST("/users/")
    fun saveNewUser(user: User)

    @PUT("users/{id}")
    fun updateUser(@Path("id")id: String, @Body user: User)

    @DELETE("users/{id}")
    fun deleteUser(@Path("id")id: String)


}