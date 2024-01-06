package com.dicoding.picodiploma.nurtur.ui.view.consultation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.picodiploma.nurtur.data.konsultasi.Konsultasi
import com.dicoding.picodiploma.nurtur.data.konsultasi.KonsultasiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class KonsultasiViewModel(private val repository: KonsultasiRepository): ViewModel() {

    fun addKonsultasi(konsultasi: Konsultasi){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertKonsultasi(konsultasi)
        }
    }
    val konsultasi: List<Konsultasi> = listOf(Konsultasi(0, "", "", "", "", ""))
    var getKonsultasi: LiveData<List<Konsultasi>> = repository.getKonsultasi()
}