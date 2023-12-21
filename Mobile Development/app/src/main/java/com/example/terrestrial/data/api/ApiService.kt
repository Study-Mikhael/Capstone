package com.example.terrestrial.data.api

import com.example.terrestrial.data.response.CourseResponse
import com.example.terrestrial.data.response.DetailCourseResponse
import com.example.terrestrial.data.response.LoginResponse
import com.example.terrestrial.data.response.QuestionResponse
import com.example.terrestrial.data.response.SignupResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    data class SignupRequest(
        val name: String,
        val email: String,
        val password: String
    )

    @POST("users/signup")
    suspend fun signup(
        @Body request: SignupRequest
    ): SignupResponse

    data class LoginRequest(
        val email: String,
        val password: String
    )

    @POST("users/login")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse

    @GET("course")
    suspend fun getAllCourse(): CourseResponse

    @GET("course/{recommendClass}")
    suspend fun getRecommendCourse(
        @Path("recommendClass") recommendClass: String
    ): CourseResponse

    @GET("course/{id}")
    suspend fun getDetailCourse(
        @Path("id") id: String
    ): DetailCourseResponse

//    @GET("search/course")
//    fun searchUser(
//        @QueryMap params: Map<String, String>
//    ): AllCourseResponse

    @GET("question")
    suspend fun getQuestion() : QuestionResponse

//    data class AnswerRequest(
//        val desain: Int,
//        val logika : Int,
//        val data : Int
//    )
//
//    @POST("answers")
//    suspend fun submitAnswers(
//        @Body answers: AnswerRequest
//    ): PredictResponse
}