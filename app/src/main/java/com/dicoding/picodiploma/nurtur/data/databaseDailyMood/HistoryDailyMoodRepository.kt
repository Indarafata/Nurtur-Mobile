package com.dicoding.picodiploma.nurtur.data.databaseDailyMood

import android.content.Context
import androidx.lifecycle.LiveData

class HistoryDailyMoodRepository(private val dao: HistoryDailyMoodDao) {

    companion object {

        @Volatile
        private var instance: HistoryDailyMoodRepository? = null
        fun getInstance(context: Context): HistoryDailyMoodRepository {
            return instance ?: synchronized(this) {
                if (instance == null) {
                    val database = HistoryDailyMoodDatabase.getInstance(context)
                    instance = HistoryDailyMoodRepository(database.historyDailyMoodDao())
                }
                return instance as HistoryDailyMoodRepository
            }

        }
    }

    suspend fun insertDailyMood(historyDailyMood: HistoryDailyMood) {
        return dao.insert(historyDailyMood)
    }

    fun getDailyMoodHistory(userId: String): LiveData<List<HistoryDailyMood>> {
        return dao.getHistoryDailyMood(userId)
    }

    fun getAllDailyHistory(): LiveData<List<HistoryDailyMood>> = dao.getAllDailyHistory()


    fun getHistoryDailyMoodByUserIdAndDay(day: String): LiveData<HistoryDailyMood> {
        return dao.getHistoryDailyMoodByUserIdAndDay(day)
    }
}