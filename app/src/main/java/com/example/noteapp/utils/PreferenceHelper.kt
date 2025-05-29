package com.example.noteapp.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.noteapp.ui.fragments.onboard.OnBoardFragment

class PreferenceHelper() {

    private lateinit var sharedPreferences: SharedPreferences

    fun unit(context: Context){
        sharedPreferences = context.getSharedPreferences("shared", Context.MODE_PRIVATE)
    }

    var text: String?
        get() = sharedPreferences.getString("text", "")
        set(value) = sharedPreferences.edit() { putString("text", value) }

    var isShowOnBoard: Boolean
        get() = sharedPreferences.getBoolean("board", false)
        set(value) = sharedPreferences.edit() { putBoolean("board", value) }

}