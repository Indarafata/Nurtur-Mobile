package com.dicoding.picodiploma.nurtur.ui.view.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.nurtur.data.postRequest.RegisterRequest
import com.dicoding.picodiploma.nurtur.data.response.RegisterResponse
import com.dicoding.picodiploma.nurtur.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupViewModel() : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> = _isSuccess
    private val _response = MutableLiveData<String?>()
    val response: LiveData<String?> = _response

fun postRegister(registerRequest: RegisterRequest) {
    val client = ApiConfig.getApiService().postRegister(registerRequest)
        _isLoading.value = true

        client.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    _isLoading.value = false
                    _isSuccess.value = true
                    _response.value = responseBody.message
                    Log.d("ini log", "${responseBody.message} dan ${responseBody.data}")
                } else {
                    _isLoading.value = false
                    _isSuccess.value = false
                    _response.value = response.message()
                    Log.d("ini log else", "${response} dan ${responseBody?.error} dan ${response.body()} dan dan ${response.errorBody()} ")
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Log.d("apakah failure", "howw")
                _isLoading.value = false
                _isSuccess.value = false
            }
        })
    }
}