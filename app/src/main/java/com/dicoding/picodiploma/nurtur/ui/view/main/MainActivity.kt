package com.dicoding.picodiploma.nurtur.ui.view.main

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.picodiploma.nurtur.databinding.ActivityMainBinding
import com.dicoding.picodiploma.nurtur.ui.view.ViewModelFactory
import com.dicoding.picodiploma.nurtur.ui.view.consultation.KonsultasiViewModel
import com.dicoding.picodiploma.nurtur.ui.view.consultation.KonsultasiViewModelFactory
import com.dicoding.picodiploma.nurtur.ui.view.dailyMood.DailyMoodViewModel
import com.dicoding.picodiploma.nurtur.ui.view.dailyMood.DailyMoodViewModelFactory
import com.dicoding.picodiploma.nurtur.ui.view.welcome.WelcomeActivity

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }

    val resultDailyMoodViewModel by viewModels<DailyMoodViewModel> {
        DailyMoodViewModelFactory.getInstance(this)
    }

    private val konsultasiViewModel by viewModels<KonsultasiViewModel> {
        KonsultasiViewModelFactory.getInstance(this)
    }

    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getSession().observe(this) { user ->
            viewModel.getCurrent(user.token)
            Log.d("ini main", user.token)
            if (!user.isLogin) {
//                loginViewModel.session.observe(this){}
//                loginViewModel.saveSession(viewModel.session)
                startActivity(Intent(this, WelcomeActivity::class.java))
                finish()
            }
            else{
                setContent {
                    JetNurturApp(1, viewModel, resultDailyMoodViewModel, konsultasiViewModel)
//                    ConsultationReservationScreen()
//                    RadioButtonExample()
                }
            }
        }

//        setupView()
//        setupAction()
//        playAnimation()
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

//    private fun setupAction() {
//        binding.logoutButton.setOnClickListener {
//            viewModel.logout()
//        }
//    }

//    private fun playAnimation() {
//        ObjectAnimator.ofFloat(binding.imageView, View.TRANSLATION_X, -30f, 30f).apply {
//            duration = 6000
//            repeatCount = ObjectAnimator.INFINITE
//            repeatMode = ObjectAnimator.REVERSE
//        }.start()
//
//        val name = ObjectAnimator.ofFloat(binding.nameTextView, View.ALPHA, 1f).setDuration(100)
//        val message = ObjectAnimator.ofFloat(binding.messageTextView, View.ALPHA, 1f).setDuration(100)
//        val logout = ObjectAnimator.ofFloat(binding.logoutButton, View.ALPHA, 1f).setDuration(100)
//
//        AnimatorSet().apply {
//            playSequentially(name, message, logout)
//            startDelay = 100
//        }.start()
//    }
}