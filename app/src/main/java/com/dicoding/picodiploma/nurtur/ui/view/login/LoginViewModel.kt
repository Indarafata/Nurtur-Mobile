package com.dicoding.picodiploma.nurtur.ui.view.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.picodiploma.nurtur.data.UserRepository
import com.dicoding.picodiploma.nurtur.data.postRequest.LoginRequest
import com.dicoding.picodiploma.nurtur.data.pref.UserModel
import com.dicoding.picodiploma.nurtur.data.response.LoginResponse
import com.dicoding.picodiploma.nurtur.data.retrofit.ApiConfig
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(private val repository: UserRepository) : ViewModel() {
    fun saveSession(user: UserModel) {
        Log.d("sini", "${user.email} dan ${user.token}")
        viewModelScope.launch {
            Log.d("sini 2", "${user.email} dan ${user.token}")
            repository.saveSession(user)
        }
    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> = _isSuccess
    private val _session = MutableLiveData<UserModel>()
    val session: LiveData<UserModel> = _session
    private val _response = MutableLiveData<LoginResponse>()
    val response: LiveData<LoginResponse> = _response

//    fun postLogin(email: String, password: String) {
fun postLogin(loginRequest: LoginRequest) {
        var token: String
        val client = ApiConfig.getApiService().postLogin(loginRequest)
        _isLoading.value = true

        client.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                val responseBody = response.body()
                _response.value = response.body()
                if (response.isSuccessful && responseBody != null) {
                    token = response.body()?.data!!.token
                    Log.d("sini 3", token)

                    _session.value = UserModel(loginRequest.email, token, loginRequest.password)
                    Log.d("sini 4", session.value?.password ?: "gaada")
//                    viewModel.saveSession(UserModel(email, token))
//                    isLogin()
//                    showToast(response.message())
                    _isSuccess.value = true
                    _isLoading.value = false
                } else {
//                    showToast(response.message())
                    Log.d("sini 5", "else bang")
                    _isSuccess.value = false
                    _isLoading.value = false
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                showToast(t.message!!)
                _isSuccess.value = false
                _isLoading.value = false
            }
        })
    }
}