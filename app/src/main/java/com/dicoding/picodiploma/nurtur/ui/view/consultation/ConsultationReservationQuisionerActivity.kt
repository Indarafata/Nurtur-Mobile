package com.dicoding.picodiploma.nurtur.ui.view.consultation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.nurtur.data.DailyMoodQuestionData
import com.dicoding.picodiploma.nurtur.databinding.ActivityConsultationReservationQuisionerBinding
import com.dicoding.picodiploma.nurtur.utils.DATE_APPOINMENT
import com.dicoding.picodiploma.nurtur.utils.DOKTER_APPOINMENT
import com.dicoding.picodiploma.nurtur.utils.LABEL_APPOINMENT
import com.dicoding.picodiploma.nurtur.utils.PHOTO_APPOINMENT
import com.dicoding.picodiploma.nurtur.utils.TIME_APPOINMENT

class ConsultationReservationQuisionerActivity : AppCompatActivity() {

    private lateinit var adapter: ItemQuizAppointmentAdapter
    private lateinit var recyclerView: RecyclerView

    //    private lateinit var recyclerView: ListView
    private lateinit var binding: ActivityConsultationReservationQuisionerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConsultationReservationQuisionerBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.activity_daily_mood_quiz)

        recyclerView = binding.rvQuizAppointment
        recyclerView.setHasFixedSize(true)

        recyclerView.layoutManager = LinearLayoutManager(this)
        setQuestion()

        val photo = intent.getStringExtra(PHOTO_APPOINMENT)
        val dokter = intent.getStringExtra(DOKTER_APPOINMENT)
        val label = intent.getStringExtra(LABEL_APPOINMENT)
        val tanggal = intent.getStringExtra(DATE_APPOINMENT)
        val waktu = intent.getStringExtra(TIME_APPOINMENT)

        binding.button2.setOnClickListener {
            val intent = Intent(this, ConsultationReservationPaymentActivity::class.java)
            intent.putExtra(PHOTO_APPOINMENT, photo)
            intent.putExtra(DOKTER_APPOINMENT, dokter)
            intent.putExtra(LABEL_APPOINMENT, label)
            intent.putExtra(DATE_APPOINMENT, tanggal)
            intent.putExtra(TIME_APPOINMENT, waktu)
            startActivity(intent)
        }
    }

    fun setQuestion() {
//        val ListKarakterAdapter = QuizAdapter(DailyMoodQuestionData.questions)
        adapter = ItemQuizAppointmentAdapter(
            DailyMoodQuestionData.questions,
            { question, answer ->
                // Do something with the answer
            }
        )
        recyclerView.adapter = adapter
    }
}