package com.dicoding.picodiploma.nurtur.data.konsultasi

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Konsultasi::class], version = 1)
abstract class
KonsultasiDatabase : RoomDatabase() {
    abstract fun konsultasiDao(): KonsultasiDao

    companion object {

        @Volatile
        private var instance: KonsultasiDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): KonsultasiDatabase {
            return synchronized(this){
                instance ?: Room.databaseBuilder(context, KonsultasiDatabase::class.java, "konsultasiHistory.db")
                    .build()
            }
        }
    }
}