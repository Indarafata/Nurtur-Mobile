package com.dicoding.picodiploma.nurtur.ui.view.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel() : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> = _isSuccess
    private val _response = MutableLiveData<String?>()
    val response: LiveData<String?> = _response

//    fun postRegister(registerRequest: RegisterRequest) {
//        val client = ApiConfig.getApiService().logout("", "")
//        _isLoading.value = true
//
//        client.enqueue(object : Callback<LogoutResponse> {
//            override fun onResponse(
//                call: Call<LogoutResponse>,
//                response: Response<LogoutResponse>
//            ) {
//                val responseBody = response.body()
//                if (response.isSuccessful && responseBody != null) {
//                    _isLoading.value = false
//                    _isSuccess.value = true
//                    _response.value = responseBody.message
//                } else {
//                    _isLoading.value = false
//                    _isSuccess.value = false
//                    _response.value = response.message()
//                }
//            }
//
//            override fun onFailure(call: Call<LogoutResponse>, t: Throwable) {
//                Log.d("apakah failure", "howw")
//                _isLoading.value = false
//                _isSuccess.value = false
//            }
//        })
//    }
}