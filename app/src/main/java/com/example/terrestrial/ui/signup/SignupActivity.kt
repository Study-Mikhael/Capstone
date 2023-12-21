package com.example.terrestrial.ui.signup

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.terrestrial.R
import com.example.terrestrial.data.auth.UserRepository
import com.example.terrestrial.databinding.ActivitySignupBinding
import com.example.terrestrial.ui.ViewModelFactory
import com.example.terrestrial.ui.login.LoginActivity
import com.example.terrestrial.ui.login.LoginViewModel
import com.example.terrestrial.ui.main.MainViewModel

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private val viewModel by viewModels<SignupViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showLoading(false)

        setupAction()
        setupAnimation()

        viewModel.registrationResult.observe(this) {
            if (it) {
                showSuccessDialog(binding.emailEditText.text.toString())
            } else {
                showToast(getString(R.string.create_account_failed))
                showLoading(false)
            }
        }

        binding.tvLogin.setOnClickListener {
            val signIn = Intent(this, LoginActivity::class.java)
            startActivity(signIn)
        }
    }

    private fun setupAction() {
        binding.signupButton.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                showLoading(true)
                registerUser(name, email, password)
            } else {
                showToast(getString(R.string.not_empty_form))
            }
        }
    }

    private fun setupAnimation() {
        val nameTextView =
            ObjectAnimator.ofFloat(binding.nameTextView, View.ALPHA, 1f).setDuration(200)
        val nameEditTextLayout =
            ObjectAnimator.ofFloat(binding.nameEditTextLayout, View.ALPHA, 1f).setDuration(200)
        val emailTextView =
            ObjectAnimator.ofFloat(binding.emailTextView, View.ALPHA, 1f).setDuration(200)
        val emailEditTextLayout =
            ObjectAnimator.ofFloat(binding.emailEditTextLayout, View.ALPHA, 1f).setDuration(200)
        val passwordTextView =
            ObjectAnimator.ofFloat(binding.passwordTextView, View.ALPHA, 1f).setDuration(200)
        val passwordEditTextLayout =
            ObjectAnimator.ofFloat(binding.passwordEditTextLayout, View.ALPHA, 1f).setDuration(200)
        val registerButton =
            ObjectAnimator.ofFloat(binding.signupButton, View.ALPHA, 1f).setDuration(200)
        val tvLogin =
            ObjectAnimator.ofFloat(binding.tvquestionLogin, View.ALPHA, 1f).setDuration(200)
        val login =
            ObjectAnimator.ofFloat(binding.tvLogin, View.ALPHA, 1f).setDuration(200)

        val together = AnimatorSet().apply {playTogether(registerButton, tvLogin, login)}

        AnimatorSet().apply {
            playSequentially(
                nameTextView,
                nameEditTextLayout,
                emailTextView,
                emailEditTextLayout,
                passwordTextView,
                passwordEditTextLayout,
                registerButton,
                login,
                together
            )
            startDelay = 90
        }.start()
    }



    private fun registerUser(name: String, email: String, password: String) {
        if (isValidEmail(email)) {
            viewModel.registerUser(name, email, password)
        } else {
            showToast(getString(R.string.valid_email))
            showLoading(false)
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun showSuccessDialog(email: String) {
        if (!isFinishing && !isDestroyed) {
            AlertDialog.Builder(this).apply {
                setTitle(getString(R.string.dialog_title_success))
                setMessage(" ${getString(R.string.dialog_message_success)}")
                setPositiveButton(getString(R.string.dialog_button_next)) { _, _ ->
                    finish()
                }
                create()
                show()
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.loading.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}



