package com.dicoding.picodiploma.nurtur.ui.view.consultation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.dicoding.picodiploma.nurtur.data.konsultasi.Konsultasi
import com.dicoding.picodiploma.nurtur.databinding.ActivityConsultationReservationPaymentBinding
import com.dicoding.picodiploma.nurtur.ui.view.ViewModelFactory
import com.dicoding.picodiploma.nurtur.ui.view.dailyMood.DailyMoodViewModel
import com.dicoding.picodiploma.nurtur.ui.view.dailyMood.DailyMoodViewModelFactory
import com.dicoding.picodiploma.nurtur.ui.view.main.JetNurturApp
import com.dicoding.picodiploma.nurtur.ui.view.main.MainViewModel
import com.dicoding.picodiploma.nurtur.utils.DATE_APPOINMENT
import com.dicoding.picodiploma.nurtur.utils.DOKTER_APPOINMENT
import com.dicoding.picodiploma.nurtur.utils.LABEL_APPOINMENT
import com.dicoding.picodiploma.nurtur.utils.PHOTO_APPOINMENT
import com.dicoding.picodiploma.nurtur.utils.TIME_APPOINMENT

class ConsultationReservationPaymentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConsultationReservationPaymentBinding

    private val mainViewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private val viewModel by viewModels<KonsultasiViewModel> {
        KonsultasiViewModelFactory.getInstance(this)
    }

    val resultDailyMoodViewModel by viewModels<DailyMoodViewModel> {
        DailyMoodViewModelFactory.getInstance(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConsultationReservationPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val photo = intent.getStringExtra(PHOTO_APPOINMENT)
        val dokter = intent.getStringExtra(DOKTER_APPOINMENT)
        val label = intent.getStringExtra(LABEL_APPOINMENT)
        val tanggal = intent.getStringExtra(DATE_APPOINMENT)
        val waktu = intent.getStringExtra(TIME_APPOINMENT)

        binding.button2.setOnClickListener {
            viewModel.addKonsultasi(Konsultasi(0, photo!!, dokter!!, label!!, tanggal!!, waktu!!))
            setContent { JetNurturApp(2, mainViewModel, resultDailyMoodViewModel, viewModel) }
        }
    }
}
