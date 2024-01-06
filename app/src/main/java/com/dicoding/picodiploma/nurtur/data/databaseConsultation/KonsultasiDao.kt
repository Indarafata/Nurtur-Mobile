package com.dicoding.picodiploma.nurtur.data.konsultasi

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface KonsultasiDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(historyDailyMood: Konsultasi)

    @Query("SELECT * from Konsultasi ORDER BY id ASC")
    fun getKonsultasi(): LiveData<List<Konsultasi>>
}