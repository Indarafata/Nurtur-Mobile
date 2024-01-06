package com.dicoding.picodiploma.nurtur.ui.view.consultation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.picodiploma.nurtur.data.konsultasi.KonsultasiRepository

class KonsultasiViewModelFactory private constructor(private val konsultasiRepository: KonsultasiRepository) :
    ViewModelProvider.Factory{

    companion object {
        @Volatile
        private var instance: KonsultasiViewModelFactory? = null

        fun getInstance(context: Context): KonsultasiViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: KonsultasiViewModelFactory(
                    KonsultasiRepository.getInstance(context)
                )
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(KonsultasiViewModel::class.java) -> {
                KonsultasiViewModel(konsultasiRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}