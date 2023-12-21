package com.example.terrestrial.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.terrestrial.R
import com.example.terrestrial.databinding.ActivityMainBinding
import com.example.terrestrial.ui.ViewModelFactory
import com.example.terrestrial.ui.login.LoginActivity
import com.example.terrestrial.ui.recommendation.QuestionActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getSession().observe(this) { user ->
            if (user.isLogin) {
                if(user.isAnswer){
                    val navView: BottomNavigationView = binding.navView

                    val navController = findNavController(R.id.nav_host_fragment_activity_main)
                    AppBarConfiguration(
                        setOf(
                            R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
                        )
                    )

                    navView.setupWithNavController(navController)
                } else{
                    startActivity(Intent(this, QuestionActivity::class.java))
                    finish()
                }
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
