package com.dicoding.picodiploma.nurtur.data.konsultasi

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Konsultasi(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "photoUrl")
    val photo: String,

    @ColumnInfo(name = "dokter")
    val dokter: String,

    @ColumnInfo(name = "label")
    val label: String,

    @ColumnInfo(name = "tanggal")
    val tanggal: String,

    @ColumnInfo(name = "waktu")
    val waktu: String,
) : Parcelable