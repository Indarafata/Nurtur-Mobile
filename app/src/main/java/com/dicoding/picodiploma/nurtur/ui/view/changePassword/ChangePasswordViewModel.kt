package com.dicoding.picodiploma.nurtur.ui.view.changePassword

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.nurtur.data.postRequest.ChangePasswordRequest
import com.dicoding.picodiploma.nurtur.data.response.ChangePasswordResponse
import com.dicoding.picodiploma.nurtur.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangePasswordViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> = _isSuccess
    private val _response = MutableLiveData<String>()
    val response: LiveData<String> = _response

    //    fun postLogin(email: String, password: String) {
    fun postChangePassword(changePasswordRequest: ChangePasswordRequest, userId: String) {
        var token: String
        val client = ApiConfig.getApiService().postChangePassword(changePasswordRequest, userId)
        _isLoading.value = true

        client.enqueue(object : Callback<ChangePasswordResponse> {
            override fun onResponse(
                call: Call<ChangePasswordResponse>,
                response: Response<ChangePasswordResponse>
            ) {
                val responseBody = response.body()
//                _response.value = response.body()
                if (response.isSuccessful && responseBody != null) {
                    _response.value = response.body()?.message!!

//                    _session.value = UserModel(loginRequest.email, token, "")
//                    viewModel.saveSession(UserModel(email, token))
//                    isLogin()
//                    showToast(response.message())
                    _isSuccess.value = true
                    _isLoading.value = false
                } else {
//                    showToast(response.message())
                    _isSuccess.value = false
                    _isLoading.value = false
                }
            }

            override fun onFailure(call: Call<ChangePasswordResponse>, t: Throwable) {
//                showToast(t.message!!)
                _isSuccess.value = false
                _isLoading.value = false
            }
        })
    }
}