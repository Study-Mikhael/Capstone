package com.example.terrestrial.ui.recommendation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.terrestrial.data.auth.UserRepository
import com.example.terrestrial.data.response.QuestionItem
import com.example.terrestrial.data.auth.Result
import com.example.terrestrial.data.auth.UserModel
import com.example.terrestrial.ml.TFLiteModel
import kotlinx.coroutines.launch

class QuestionViewModel(private val repository: UserRepository) : ViewModel() {

    private val _questions = MutableLiveData<List<QuestionItem?>?>()
    val questions: LiveData<List<QuestionItem?>?> get() = _questions

    private val _result = MutableLiveData<String?>()
    val result: LiveData<String?> get() = _result

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun getQuestions() {
        viewModelScope.launch {
            when (val result = repository.getQuestion()) {
                is Result.Success -> _questions.value = result.data.data
                is Result.Error -> {/* handle error */}
                else -> {}
            }
        }
    }

//    fun processModelWithAnswers(
//        desain: Int,
//        logika: Int,
//        data: Int,
//        questionActivity: QuestionActivity
//    ) {
//        viewModelScope.launch {
//            try {
//                val response = repository.submitAnswers(desain, logika, data)
//                _modelResult.value = response.prediction
//            } catch (e: Exception) {
//                // Handle error if needed
//            }
//        }
//    }

//    fun processAnswers(desain: Int, logika: Int, data: Int) {
//        val result = determineResult(desain, logika, data)
//    }

    fun processAnswers(desain: Int, logika: Int, data: Int): String {
        val recommendations = listOf(
            Triple(7, 0, 0), Triple(6, 1, 0), Triple(6, 0, 1), Triple(5, 1, 1),
            Triple(4, 2, 1), Triple(4, 1, 2), Triple(4, 3, 0), Triple(4, 0, 3),
            Triple(3, 3, 1), Triple(3, 1, 3), Triple(3, 2, 2)
        )

        val result = when {
            recommendations.any { (d, l, da) -> d == desain && l == logika && da == data } -> "Frontend"
            recommendations.any { (l, d, da) -> l == logika && d == desain && da == data } -> "Backend"
            else -> "DataScience"
        }

        _result.postValue(result)

        viewModelScope.launch {
            val userModel = UserModel(isAnswer = true, resultRecommendation = result)
            repository.saveResultRecommendation(userModel)
        }

        return result
    }

}