package com.example.terrestrial.data.auth

import com.example.terrestrial.data.api.ApiConfig
import com.example.terrestrial.data.api.ApiService
import com.example.terrestrial.data.response.CourseResponse
import com.example.terrestrial.data.response.DetailCourseResponse
import com.example.terrestrial.data.response.LoginResponse
import com.example.terrestrial.data.response.PredictResponse
import com.example.terrestrial.data.response.QuestionResponse
import com.example.terrestrial.data.response.SignupResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class UserRepository private constructor(
    private val apiService: ApiService,
    private val userPreferences: UserPreferences
) {

    suspend fun signup(request: ApiService.SignupRequest): SignupResponse {
        return apiService.signup(request)
    }

    suspend fun login(request: ApiService.LoginRequest): LoginResponse {
        return apiService.login(request)
    }

    suspend fun saveLoginSession(user: UserModel) {
        userPreferences.saveLoginSession(user)
    }

    suspend fun saveResultRecommendation(user: UserModel) {
        userPreferences.saveResultRecommendation(user)
    }

    fun getLoginSession(): Flow<UserModel> = userPreferences.getLoginSession()

    suspend fun clearLoginSession() {
        userPreferences.clearLoginSession()

    }

    suspend fun getAllCourse(): Result<CourseResponse?> {
        return try {
            val user = userPreferences.getLoginSession().first()
            val apiService = ApiConfig.getApiService(user.token)
            val response = apiService.getAllCourse()
            Result.Success(response)
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }


    suspend fun getRecommendCourse(): Result<CourseResponse?> {
        return try {
            val user = userPreferences.getLoginSession().first()
            val apiService = ApiConfig.getApiService(user.token)

            val recommendClass = user.resultRecommendation
            val response = apiService.getRecommendCourse(recommendClass)

            Result.Success(response)
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }

    suspend fun getDetailCourse(id: String): Result<DetailCourseResponse?> {
        return try {
            val user = userPreferences.getLoginSession().first()
            val apiService = ApiConfig.getApiService(user.token)
            val response = apiService.getDetailCourse(id)
            Result.Success(response)
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }

//    suspend fun searchCourse(query: String): AllCourseResponse {
//        val user = userPreferences.getLoginSession().first()
//        val apiService = ApiConfig.getApiService(user.token)
//        return apiService.searchUser(mapOf("query" to query))
//    }

    suspend fun getQuestion(): Result<QuestionResponse>{
        return try {
            val user = userPreferences.getLoginSession().first()
            val apiService = ApiConfig.getApiService(user.token)
            val response = apiService.getQuestion()
            Result.Success(response)
        }catch (e: Exception){
            Result.Error(e.message.toString())
        }
    }

//    suspend fun submitAnswers(desain: Int, logika: Int, data: Int): PredictResponse {
//        val answerRequest = ApiService.AnswerRequest(desain, logika, data)
//        return apiService.submitAnswers(answerRequest)
//    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(apiService: ApiService, userPreferences: UserPreferences): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(apiService, userPreferences)
            }.also { instance = it }
    }
}
