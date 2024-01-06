package com.dicoding.picodiploma.nurtur.data.konsultasi

import android.content.Context
import androidx.lifecycle.LiveData

class KonsultasiRepository(private val dao: KonsultasiDao) {

    companion object {

        @Volatile
        private var instance: KonsultasiRepository? = null
        fun getInstance(context: Context): KonsultasiRepository {
            return instance ?: synchronized(this) {
                if (instance == null) {
                    val database = KonsultasiDatabase.getInstance(context)
                    instance = KonsultasiRepository(database.konsultasiDao())
                }
                return instance as KonsultasiRepository
            }

        }
    }

    suspend fun insertKonsultasi(historyDailyMood: Konsultasi) {
        return dao.insert(historyDailyMood)
    }

    fun getKonsultasi(): LiveData<List<Konsultasi>> {
        return dao.getKonsultasi()
    }
}