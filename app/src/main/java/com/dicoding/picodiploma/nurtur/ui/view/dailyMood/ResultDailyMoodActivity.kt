package com.dicoding.picodiploma.nurtur.ui.view.dailyMood

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import com.dicoding.picodiploma.nurtur.data.databaseDailyMood.HistoryDailyMood
import com.dicoding.picodiploma.nurtur.databinding.ActivityResultDailyMoodBinding
import com.dicoding.picodiploma.nurtur.ui.view.ViewModelFactory
import com.dicoding.picodiploma.nurtur.ui.view.main.MainActivity
import com.dicoding.picodiploma.nurtur.ui.view.main.MainViewModel
import com.dicoding.picodiploma.nurtur.utils.FACE_DETECTION
import com.dicoding.picodiploma.nurtur.utils.WEIGHT_RESULT
import java.time.LocalDate

class ResultDailyMoodActivity : AppCompatActivity() {
    val resultDailyMoodViewModel by viewModels<DailyMoodViewModel> {
        DailyMoodViewModelFactory.getInstance(this)
    }

    val mainViewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private lateinit var userId: String

    private lateinit var binding: ActivityResultDailyMoodBinding
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_result_daily_mood)
        binding = ActivityResultDailyMoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val quizWeightResult = intent.getIntExtra(WEIGHT_RESULT, 0)
        val faceDetectionResult = intent.getStringExtra(FACE_DETECTION)

        mainViewModel.getSession().observe(this) { user ->
            mainViewModel.getCurrent(user.token)
            mainViewModel.currentUser.observe(this){
                userId = it.message.id

                resultDailyMoodViewModel.checkResult(quizWeightResult, faceDetectionResult ?: "")
                resultDailyMoodViewModel.result.observe(this){
                    setData(it)
                }
            }
        }

        binding.buttonResult.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setData(result: DataResultDaily) {
        val image = binding.imageResult
        val emotion = binding.emotion
        val message = binding.message


        image.setImageResource(result.image)
        emotion.setText(result.emotion)
        message.setText(result.message)

        val dateToday: LocalDate = LocalDate.now()

        resultDailyMoodViewModel.insertDailyMood(HistoryDailyMood(0, userId, result.emotion, dateToday.toString()))
    }


}