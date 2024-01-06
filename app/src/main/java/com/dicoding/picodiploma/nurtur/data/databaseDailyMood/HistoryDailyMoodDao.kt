package com.dicoding.picodiploma.nurtur.data.databaseDailyMood

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HistoryDailyMoodDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(historyDailyMood: HistoryDailyMood)

    @Query("SELECT * from HistoryDailyMood WHERE userId = :userId")
    fun getHistoryDailyMood(userId: String): LiveData<List<HistoryDailyMood>>

    @Query("SELECT * FROM HistoryDailyMood WHERE day = :day")
    fun getHistoryDailyMoodByUserIdAndDay(day: String): LiveData<HistoryDailyMood>

    @Query("SELECT * FROM HistoryDailyMood ORDER BY id ASC")
    fun getAllDailyHistory(): LiveData<List<HistoryDailyMood>>

}