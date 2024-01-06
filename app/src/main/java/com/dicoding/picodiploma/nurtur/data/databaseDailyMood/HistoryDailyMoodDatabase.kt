package com.dicoding.picodiploma.nurtur.data.databaseDailyMood

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [HistoryDailyMood::class], version = 1)
abstract class HistoryDailyMoodDatabase : RoomDatabase() {
    abstract fun historyDailyMoodDao(): HistoryDailyMoodDao

    companion object {

        @Volatile
        private var instance: HistoryDailyMoodDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): HistoryDailyMoodDatabase {
            return synchronized(this){
                instance ?: Room.databaseBuilder(context, HistoryDailyMoodDatabase::class.java, "historyDailyMood.db")
                    .build()
            }
        }
    }
}