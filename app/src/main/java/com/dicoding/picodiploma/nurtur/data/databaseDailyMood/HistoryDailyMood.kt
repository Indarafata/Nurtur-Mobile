package com.dicoding.picodiploma.nurtur.data.databaseDailyMood

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class HistoryDailyMood(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "userId")
    val userId: String,

    @ColumnInfo(name = "hasil")
    val hasil: String,

    @ColumnInfo(name = "day")
    val day: String
) : Parcelable