package com.example.noteapp

import android.app.Application
import com.example.noteapp.utils.PreferenceHelper

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        val shared = PreferenceHelper()
        shared.unit(this)
    }
}