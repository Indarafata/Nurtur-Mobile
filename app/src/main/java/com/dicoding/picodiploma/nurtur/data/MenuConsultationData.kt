package com.dicoding.picodiploma.nurtur.data

import com.dicoding.picodiploma.nurtur.R
import com.dicoding.picodiploma.nurtur.ui.theme.BlueArrow
import com.dicoding.picodiploma.nurtur.ui.theme.BlueKids
import com.dicoding.picodiploma.nurtur.ui.theme.PinkArrow
import com.dicoding.picodiploma.nurtur.ui.theme.PinkFamily
import com.dicoding.picodiploma.nurtur.ui.theme.PrimaryColor
import com.dicoding.picodiploma.nurtur.ui.theme.PurpleimeManagement
import com.dicoding.picodiploma.nurtur.ui.theme.YellowArrow
import com.dicoding.picodiploma.nurtur.ui.theme.YellowEmotion

object MenuConsultationData {
    val consultations = listOf(
        MenuConsultation(
            1,
            PurpleimeManagement,
            PrimaryColor,
            R.drawable.clock_consultation_menu,
            "Manajemen Waktu"
        ),
        MenuConsultation(
            2,
            YellowEmotion,
            YellowArrow,
            R.drawable.emotion_consultation_menu,
            "Emosi dan Pikiran"
        ),
        MenuConsultation(
            3,
            BlueKids,
            BlueArrow,
            R.drawable.child_consultation_menu,
            "Mengasuh \nAnak"
        ),
        MenuConsultation(
            4,
            PinkFamily,
            PinkArrow,
            R.drawable.family_consultation_menu,
            "Masalah Keluarga"
        )
    )
}