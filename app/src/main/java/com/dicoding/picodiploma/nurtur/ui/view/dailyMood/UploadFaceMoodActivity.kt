package com.dicoding.picodiploma.nurtur.ui.view.dailyMood

import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.dicoding.picodiploma.nurtur.R
import com.dicoding.picodiploma.nurtur.data.response.MachineLearningResponse
import com.dicoding.picodiploma.nurtur.data.retrofit.ApiConfigML
import com.dicoding.picodiploma.nurtur.databinding.ActivityUploadFaceMoodBinding
import com.dicoding.picodiploma.nurtur.ui.view.ViewModelFactory
import com.dicoding.picodiploma.nurtur.ui.view.main.MainViewModel
import com.dicoding.picodiploma.nurtur.utils.getImageUri
import com.dicoding.picodiploma.nurtur.utils.uriToFile
import com.google.gson.Gson
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.HttpException

class UploadFaceMoodActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUploadFaceMoodBinding
    private var currentImageUri: Uri? = null
    val mainViewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var name: String

    companion object {
        private const val REQUIRED_PERMISSION = android.Manifest.permission.CAMERA
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Toast.makeText(this, "Permission request granted", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Permission request denied", Toast.LENGTH_LONG).show()
            }
        }

    private fun allPermissionsGranted() =
        ContextCompat.checkSelfPermission(
            this,
            REQUIRED_PERMISSION
        ) == PackageManager.PERMISSION_GRANTED

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadFaceMoodBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.activity_upload_face_mood)

        if (!allPermissionsGranted()) {
            requestPermissionLauncher.launch(REQUIRED_PERMISSION)
        }

        mainViewModel.getSession().observe(this) { user ->
            mainViewModel.getCurrent(user.token)
        }

        mainViewModel.currentUser.observe(this) {

            name = it.message.name

            Log.d("ho belum dapet", it.message.name)
        }

        binding.cameraButton.setOnClickListener {
            startCamera()
        }

        binding.submitButton.setOnClickListener {
            uploadImage()
        }
    }

    private fun startCamera() {
        currentImageUri = getImageUri(this)
        launcherIntentCamera.launch(currentImageUri)
    }

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { isSuccess ->
        if (isSuccess) {
            showImage()
        }
    }

    private fun showImage() {
        currentImageUri?.let {
            // Log.d("Image URI", "showImage: $it")
            binding.previewImageView.setImageURI(it)
        }
    }

    private fun uploadImage() {
        currentImageUri?.let { uri ->
            showLoading(true)
            val imageFile = uriToFile(uri, this)
            val requestImageFile = imageFile.asRequestBody("image/jpeg".toMediaType())
            val multipartBody = MultipartBody.Part.createFormData(
                "image",
                imageFile.name,
                requestImageFile
            )
            lifecycleScope.launch {
                try {
                    val apiService = ApiConfigML.getApiServiceML()
                    val successResponse = apiService.postMoodDetection(multipartBody)
                    if (successResponse != null) {
                        showToast(successResponse.status.message)
                        showLoading(false)
                        setContent {
                            DailyMoodQuizScreen(successResponse.data.emotion, name)
                        }
//                        finish()
                    }
                } catch (e: HttpException) {
                    val errorBody = e.response()?.errorBody()?.string()
                    val errorResponse =
                        Gson().fromJson(errorBody, MachineLearningResponse::class.java)
                    if (errorResponse != null) {
                        println("gagal bro")
                        showToast(errorResponse.status.message)
                        showLoading(false)
                    }
                }
            }
        } ?: showToast(getString(R.string.empty_image_warning))
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressIndicatorFaceDetection.visibility =
            if (isLoading) View.VISIBLE else View.GONE
    }
}