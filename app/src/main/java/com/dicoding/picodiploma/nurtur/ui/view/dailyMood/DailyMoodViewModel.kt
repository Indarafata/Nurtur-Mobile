package com.dicoding.picodiploma.nurtur.ui.view.dailyMood

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.picodiploma.nurtur.R
import com.dicoding.picodiploma.nurtur.data.databaseDailyMood.HistoryDailyMood
import com.dicoding.picodiploma.nurtur.data.databaseDailyMood.HistoryDailyMoodRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate

class DailyMoodViewModel(private val historyDailyMoodRepository: HistoryDailyMoodRepository) :
    ViewModel() {
    fun insertDailyMood(historyDailyMood: HistoryDailyMood) {
        viewModelScope.launch(Dispatchers.IO) {
            historyDailyMoodRepository.insertDailyMood(historyDailyMood)
        }
    }

    private val _result = MutableLiveData<DataResultDaily>()
    val result: LiveData<DataResultDaily> = _result

    var userId: String = ""

    @RequiresApi(Build.VERSION_CODES.O)
    val dateToday: LocalDate = LocalDate.now()



    val getDailyMood: LiveData<List<HistoryDailyMood>> = historyDailyMoodRepository.getAllDailyHistory()
    @RequiresApi(Build.VERSION_CODES.O)
    var getDailyToday: LiveData<HistoryDailyMood> = historyDailyMoodRepository.getHistoryDailyMoodByUserIdAndDay(dateToday.toString())

    val history: List<HistoryDailyMood> = listOf(
        HistoryDailyMood(0, "", "", "")
    )

    fun checkResult(weight: Int, faceDetectionResult: String) {
        if (weight >= 40) {
            _result.value = DataResultDaily(
                R.drawable.happy_reaction,
                "Mood Baik",
                "Mood Anda sedang baik hari ini! Teruslah jaga semangat dan nikmati hari Anda."
            )
        } else if (weight >= 35 && weight < 40) {
            _result.value = DataResultDaily(
                R.drawable.flat_reaction,
                "Mood Sedang",
                "Mood Anda sedang sedang hari ini. Hati-hati dalam bertindak karena mood dapat berubah menjadi jelek seketika."
            )
        } else {
            if (faceDetectionResult == "Angry")
                _result.value = DataResultDaily(
                    R.drawable.angry_reaction,
                    "Mood Buruk",
                    "Mood Anda sedang buruk hari ini. Jika Anda merasa stres atau tertekan, sebaiknya lakukan konsultasi dengan ahlinya."
                )
            else
                _result.value = DataResultDaily(
                    R.drawable.flat_reaction,
                    "Mood Sedang",
                    "Mood Anda sedang sedang hari ini. Hati-hati dalam bertindak karena mood dapat berubah menjadi jelek seketika."
                )
        }
    }
}

data class DataResultDaily(
    var image: Int,
    var emotion: String,
    var message: String
)