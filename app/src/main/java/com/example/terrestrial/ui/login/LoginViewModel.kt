package com.example.terrestrial.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import android.util.Log
import com.example.terrestrial.data.api.ApiService
import com.example.terrestrial.data.auth.UserModel
import com.example.terrestrial.data.auth.UserRepository

class LoginViewModel(private val repository: UserRepository) : ViewModel() {

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> get() = _loginResult

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            try {
                _isLoading.postValue(true)

                val loginRequest = ApiService.LoginRequest(email, password)
                val response = repository.login(loginRequest)

                if (response.error == false) {
                    val token = response.loginResult?.token ?: ""
                    val name = response.loginResult?.name ?: ""
                    val emailResult = response.loginResult?.email ?: ""
                    repository.saveLoginSession(UserModel(name, emailResult, token, isLogin = true))
                    _loginResult.value = true
                } else {
                    _loginResult.value = false
                }
            } catch (e: Exception) {
                _loginResult.value = false
                Log.e("TAG", "Error: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }
}
