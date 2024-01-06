package com.dicoding.picodiploma.nurtur.ui.view.dailyMood

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.picodiploma.nurtur.data.databaseDailyMood.HistoryDailyMoodRepository

class DailyMoodViewModelFactory private constructor(private val dailyMoodRepository: HistoryDailyMoodRepository) :
    ViewModelProvider.Factory{

    companion object {
        @Volatile
        private var instance: DailyMoodViewModelFactory? = null

        fun getInstance(context: Context): DailyMoodViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: DailyMoodViewModelFactory(
                    HistoryDailyMoodRepository.getInstance(context)
                )
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(DailyMoodViewModel::class.java) -> {
                DailyMoodViewModel(dailyMoodRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}