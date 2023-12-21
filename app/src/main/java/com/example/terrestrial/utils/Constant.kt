package com.example.terrestrial.utils

object Constant {
    enum class UserPreferences{
        UserUID, UserName, UserEmail, UserToken, UserLastLogin
    }
    const val DELAY_SPLASH_SCREEN = 2000L
    const val preferenceName = "session"
    const val BASE_URL = "https://db7482add7a47d.lhr.life/api/"
    const val OUTPUT_SIZE = 0.0

    const val KEY_AUTH_PREFERENCES = "auth"
    const val KEY_STATE_PREFERENCES = "state"
}