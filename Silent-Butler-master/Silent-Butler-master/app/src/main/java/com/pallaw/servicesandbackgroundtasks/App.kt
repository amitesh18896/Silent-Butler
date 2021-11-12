package com.pallaw.servicesandbackgroundtasks

import android.app.Application
import androidx.annotation.NonNull
import timber.log.Timber

/**
 * Created by Pallaw Pathak on 05/05/20. - https://www.linkedin.com/in/pallaw-pathak-a6a324a1/
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()

        setupTimber()
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun log(
                    priority: Int,
                    tag: String?,
                    @NonNull msg: String,
                    t: Throwable?
                ) {
                    super.log(priority, "background_ask_example_$tag", msg, t)
                }
            })
        }
    }
}