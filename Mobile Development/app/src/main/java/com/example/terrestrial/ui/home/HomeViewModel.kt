package com.example.terrestrial.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.terrestrial.R
import com.example.terrestrial.data.auth.UserRepository
import kotlinx.coroutines.launch
import com.example.terrestrial.data.auth.Result
import com.example.terrestrial.data.auth.UserModel
import com.example.terrestrial.data.response.DataItem

class HomeViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _courseList = MutableLiveData<List<DataItem>?>()
    val courseList: MutableLiveData<List<DataItem>?> get() = _courseList

    private val _recommendCourseList = MutableLiveData<List<DataItem?>>()
    val recommendCourseList: MutableLiveData<List<DataItem?>> get() = _recommendCourseList

    private val _profile = MutableLiveData<String>().apply {
        val drawableResourceId = R.drawable.default_profile
        value = drawableResourceId.toString()
    }
    val profile: LiveData<String> = _profile

//    private val _searchedCourseList = MutableLiveData<List<DataItem?>?>()
//    val searchedCourseList: LiveData<List<DataItem?>?> get() = _searchedCourseList


    fun getSession(): LiveData<UserModel> = userRepository.getLoginSession().asLiveData()

    fun getAllCourse() {
        viewModelScope.launch {
            when (val result = userRepository.getAllCourse()) {
                is Result.Success -> _courseList.value = result.data?.data as List<DataItem>?
                is Result.Error -> {/* handle error */}
                else -> {}
            }
        }
    }

    fun getRecommendCourse() {
        viewModelScope.launch {
            when (val result = userRepository.getRecommendCourse()) {
                is Result.Success -> _recommendCourseList.value = result.data?.data as List<DataItem>?
                is Result.Error -> {/* handle error */}
                else -> {}
            }
        }
    }

//    fun searchCourse(query: String) {
//        viewModelScope.launch {
//            try {
//                val response = userRepository.searchCourse(query)
//                if (!response.error!!) {
//                    _searchedCourseList.postValue(response.data)
//                } else {
//                    // Handle error jika diperlukan
//                }
//            } catch (e: Exception) {
//                // Handle exception jika diperlukan
//            }
//        }
//    }
}

