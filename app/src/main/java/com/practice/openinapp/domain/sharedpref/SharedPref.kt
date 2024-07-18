package com.practice.openinapp.domain.sharedpref

import android.content.Context
import android.content.SharedPreferences

class SharedPref(context: Context) {

    private val sharedPref: SharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val TOKEN_KEY = "TOKEN_KEY"

        @Volatile
        private var INSTANCE: SharedPref? = null

        fun getInstance(context: Context): SharedPref {
            return INSTANCE ?: synchronized(this) {
                val instance = SharedPref(context)
                INSTANCE = instance
                instance
            }
        }
    }

    fun saveToken(token: String) {
        val editor = sharedPref.edit()
        editor.putString(TOKEN_KEY, token)
        editor.apply()
    }

    fun getToken(): String? {
        return sharedPref.getString(TOKEN_KEY, null)
    }
}