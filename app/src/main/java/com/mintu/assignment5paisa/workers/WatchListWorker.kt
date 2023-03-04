package com.mintu.assignment5paisa.workers

import android.content.Context
import android.os.Handler
import android.util.Log
import androidx.work.*
import java.util.*

class WatchListWorker(context: Context, params: WorkerParameters): Worker(context,params) {
    override fun doWork(): Result {
        return  try {
            Log.d("PERIODIC_WORK: ","DOING! ${Date().time}")

            Handler().postDelayed({
                val constraints = Constraints.Builder()
                    .setRequiresCharging(false)
                    .setRequiresStorageNotLow(false)
                    .build()

                val oneTimeWork = OneTimeWorkRequestBuilder<WatchListWorker>()
                    .setConstraints(constraints)
                    .addTag("WATCHLIST_WORK")
                    .build()
                WorkManager.getInstance().enqueue(oneTimeWork)
            },5000)
            Result.success()
        }catch (e: Exception){
            Result.retry()
        }
    }
}