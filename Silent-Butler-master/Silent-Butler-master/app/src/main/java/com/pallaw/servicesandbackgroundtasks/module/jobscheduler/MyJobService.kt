package com.pallaw.servicesandbackgroundtasks.module.jobscheduler

import android.app.job.JobParameters
import android.app.job.JobService
import timber.log.Timber

/**
 * Created by Pallaw Pathak on 05/05/20. - https://www.linkedin.com/in/pallaw-pathak-a6a324a1/
 */
class MyJobService : JobService() {
    private var isJobCancelled: Boolean = false

    override fun onStartJob(params: JobParameters): Boolean {
        Timber.d("onStartJob()")
        startBackgroundTask(params)
        return true
    }

    private fun startBackgroundTask(params: JobParameters) {
        Timber.d("Starting background task")
        Thread(Runnable {
            for (x in 1..10) {
                if (isJobCancelled) {
                    return@Runnable
                }
                Timber.d("Doing background task : $x")
                Thread.sleep(1000)
            }

            jobFinished(params, false)

        }).start()


    }

    override fun onStopJob(params: JobParameters): Boolean {
        Timber.d("Job cancelled before completion")
        isJobCancelled = true
        return false
    }
}