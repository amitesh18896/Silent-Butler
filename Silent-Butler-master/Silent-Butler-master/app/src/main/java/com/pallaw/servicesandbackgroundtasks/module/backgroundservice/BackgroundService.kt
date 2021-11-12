package com.pallaw.servicesandbackgroundtasks.module.backgroundservice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import timber.log.Timber

/**
 * Created by Pallaw Pathak on 06/05/20. - https://www.linkedin.com/in/pallaw-pathak-a6a324a1/
 */
class BackgroundService : Service() {
    override fun onCreate() {
        Timber.d("onCreate:")
        super.onCreate()
    }

    override fun onBind(intent: Intent?): IBinder? {
        Timber.d("onBind: ")
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Timber.d("onStartCommand: ")
        Thread(Runnable {
            for (x in 1..100) {
                Timber.d("Running background task on : ${Thread.currentThread().name} for counter $x")
                Thread.sleep(1000)
            }
        }).start()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("onDestroy: ")
    }
}