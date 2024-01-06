package com.dicoding.picodiploma.nurtur.ui.view.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dicoding.picodiploma.nurtur.data.UserRepository
import com.dicoding.picodiploma.nurtur.data.pref.UserModel
import com.dicoding.picodiploma.nurtur.data.response.CurrentResponse
import com.dicoding.picodiploma.nurtur.data.response.LogoutResponse
import com.dicoding.picodiploma.nurtur.data.retrofit.ApiConfig
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val repository: UserRepository) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    private val _currentUser = MutableLiveData<CurrentResponse>()
    val currentUser: LiveData<CurrentResponse> = _currentUser
//    private val _session = MutableLiveData<UserModel>()
//    val session: LiveData<UserModel> = _session

    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }

    fun getCurrent(token: String) {
        val client = ApiConfig.getApiService().getCurrent(token)
        _isLoading.value = true

        client.enqueue(object : Callback<CurrentResponse> {
            override fun onResponse(
                call: Call<CurrentResponse>,
                response: Response<CurrentResponse>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    _isLoading.value = false
//                    _isSuccess.value = true
                    _currentUser.value = responseBody!!
//                    _session.value = UserModel(responseBody.message.email, responseBody.message.token, responseBody.message.name, responseBody.message.id)
                } else {
                    _isLoading.value = false
//                    _isSuccess.value = false
//                    _response.value = response.message()
                }
            }

            override fun onFailure(call: Call<CurrentResponse>, t: Throwable) {
                Log.d("apakah failure", "howw")
                _isLoading.value = false
//                _isSuccess.value = false
            }
        })
    }

    fun logout(userId: String, token: String) {
        viewModelScope.launch {
            val client = ApiConfig.getApiService().deleteLogout(userId, token)
            _isLoading.value = true

            client.enqueue(object : Callback<LogoutResponse> {
                override fun onResponse(
                    call: Call<LogoutResponse>,
                    response: Response<LogoutResponse>
                ) {
                    val responseBody = response.body()
                    if (response.isSuccessful && responseBody != null) {
                    } else {
                    }
                }

                override fun onFailure(call: Call<LogoutResponse>, t: Throwable) {
                }
            })

            repository.logout()
        }
    }

}