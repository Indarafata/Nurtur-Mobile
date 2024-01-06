package com.dicoding.picodiploma.nurtur.ui.view.changePassword

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import com.dicoding.picodiploma.nurtur.data.postRequest.ChangePasswordRequest
import com.dicoding.picodiploma.nurtur.data.pref.UserModel
import com.dicoding.picodiploma.nurtur.databinding.ActivityChangePasswordBinding
import com.dicoding.picodiploma.nurtur.ui.view.ViewModelFactory
import com.dicoding.picodiploma.nurtur.ui.view.login.LoginViewModel
import com.dicoding.picodiploma.nurtur.ui.view.main.MainActivity
import com.dicoding.picodiploma.nurtur.ui.view.main.MainViewModel

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChangePasswordBinding
    private val viewModel: ChangePasswordViewModel = ChangePasswordViewModel()
    private val mainViewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private val loginViewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private lateinit var userId: String
    private lateinit var password: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.getSession().observe(this) { user ->
            password = user.password
            mainViewModel.getCurrent(user.token)
//            if (mainViewModel.currentUser.value?.message?.id != null) {
            mainViewModel.currentUser.observe(this){
                userId = it.message.id
            }
//            }

        }

        setupView()
        setupAction()
        playAnimation()
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

    private fun setupAction() {
        binding.loginButton.setOnClickListener {
            val oldPassword = binding.oldPasswordEditText.text.toString()
            val newPassword = binding.newPasswordEditText.text.toString()
            val confirmationNewPassword = binding.confirmationNewPasswordEditText.text.toString()

            val changePasswordRequest = ChangePasswordRequest(
                password = newPassword,
                password_confirmation = confirmationNewPassword
            )

            if (oldPassword == password) {

                mainViewModel.currentUser.observe(this){
                    val newSession = UserModel(it.message.email, it.message.token, newPassword)
                    loginViewModel.saveSession(newSession)
                }

                viewModel.postChangePassword(changePasswordRequest, userId)

                viewModel.isLoading.observe(this) {
                    showLoading(it)
                }

                viewModel.isSuccess.observe(this) {
                    showToast(it, "")

                }
            } else {
                showToast(false, "Password lama anda salah")
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressIndicatorChangePassword.visibility =
            if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showToast(isSuccess: Boolean, message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

        if (isSuccess) {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.imageView, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val title = ObjectAnimator.ofFloat(binding.titleTextView, View.ALPHA, 1f).setDuration(100)
        val message =
            ObjectAnimator.ofFloat(binding.messageTextView, View.ALPHA, 1f).setDuration(100)
        val oldPasswordTextView =
            ObjectAnimator.ofFloat(binding.oldPasswordTextView, View.ALPHA, 1f).setDuration(100)
        val oldPasswordTextLayout =
            ObjectAnimator.ofFloat(binding.oldPasswordEditTextLayout, View.ALPHA, 1f)
                .setDuration(100)
        val newPasswordTextView =
            ObjectAnimator.ofFloat(binding.passwordTextView, View.ALPHA, 1f).setDuration(100)
        val newPasswordEditTextLayout =
            ObjectAnimator.ofFloat(binding.newPasswordEditTextLayout, View.ALPHA, 1f)
                .setDuration(100)
        val confirmationNewPasswordTextView =
            ObjectAnimator.ofFloat(binding.confirmationNewPasswordTextView, View.ALPHA, 1f)
                .setDuration(100)
        val confirmationNewPasswordEditTextLayout =
            ObjectAnimator.ofFloat(binding.confirmationNewPasswordEditTextLayout, View.ALPHA, 1f)
                .setDuration(100)
        val login = ObjectAnimator.ofFloat(binding.loginButton, View.ALPHA, 1f).setDuration(100)

        AnimatorSet().apply {
            playSequentially(
                title,
                message,
                oldPasswordTextView,
                oldPasswordTextLayout,
                newPasswordTextView,
                newPasswordEditTextLayout,
                confirmationNewPasswordTextView,
                confirmationNewPasswordEditTextLayout,
                login
            )
            startDelay = 100
        }.start()
    }
}