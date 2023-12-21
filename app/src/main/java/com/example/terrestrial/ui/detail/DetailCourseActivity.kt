package com.example.terrestrial.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.terrestrial.R
import com.example.terrestrial.data.response.Data
import com.example.terrestrial.databinding.ActivityDetailCourseBinding
import com.example.terrestrial.ui.main.MainActivity
import com.example.terrestrial.data.auth.Result
import com.example.terrestrial.data.response.DetailCourseResponse
import com.example.terrestrial.ui.ViewModelFactory
import com.example.terrestrial.utils.Injection
import kotlinx.coroutines.launch

class DetailCourseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailCourseBinding
    private val detailCourseViewModel: DetailCourseViewModel by viewModels {
        ViewModelFactory(Injection.provideRepository(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val courseId = intent.getStringExtra(EXTRA_KURSUS_ID)

        if (courseId != null) {
            lifecycleScope.launch {
                try {
                    detailCourseViewModel.getDetail(courseId)
                } catch (e: Exception) {
                    // Handle exceptions if needed
                }
            }

            binding.back.setOnClickListener { moveToMainActivity() }
        }

        // Observe the StateFlow to handle the result
        lifecycleScope.launch {
            detailCourseViewModel.detailCourse
                .collect { result ->
                    handleResult(result)
                }
        }
    }

    private fun handleResult(result: Result<DetailCourseResponse?>) {
        when (result) {
            is Result.Success -> {
                val data = result.data?.data
                if (data != null) {
                    setData(data)
                    binding.loading.visibility = View.GONE
                } else {
                    binding.tvDesc.text = getString(R.string.null_data)
                }
            }
            is Result.Error -> {
                binding.loading.visibility = View.GONE
                binding.tvDesc.text = getString(R.string.error)
            }
            is Result.Loading -> {
                binding.loading.visibility = View.VISIBLE
            }
        }
    }

    private fun setData(data: Data) {
        Glide.with(this)
            .load(data.thumbnail)
            .into(binding.ivPic)
        binding.tvName .text = data.courseName
        binding.tvDesc.text = data.describe
        binding.tvLearn.text = data.learning
    }

    private fun moveToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    companion object {
        const val EXTRA_KURSUS_ID = "extra_kursus_id"
    }
}
