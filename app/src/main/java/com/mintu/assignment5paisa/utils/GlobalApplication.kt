package com.mintu.assignment5paisa.utils

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.Observer
import androidx.work.*
import com.mintu.assignment5paisa.workers.WatchListWorker
import java.util.*
import java.util.concurrent.TimeUnit

class GlobalApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }

}