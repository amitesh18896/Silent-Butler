package com.pallaw.servicesandbackgroundtasks.module.jobscheduler

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.pallaw.servicesandbackgroundtasks.R
import com.pallaw.servicesandbackgroundtasks.util.TextLogger
import kotlinx.android.synthetic.main.fragment_job_scheduler.*
import kotlinx.android.synthetic.main.log_view.*

/**
 * A simple [Fragment] subclass.
 */
class JobSchedulerFragment : Fragment(), View.OnClickListener {

    private val JOB_ID: Int = 1234

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_job_scheduler, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //set click listener on all buttons at once
        listOf<Button>(btn_schedule_job, btn_cancle_schedule_job).forEach {
            it.setOnClickListener(
                this
            )
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            btn_schedule_job -> {
                scheduleJob()
            }
            btn_cancle_schedule_job -> {
                cancelScheduledJob()
            }
        }
    }

    private fun cancelScheduledJob() {
        activity?.let {
            TextLogger.log(
                txt_logs,
                "Scheduled job cancelled"
            )
            val jobScheduler = it.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
            jobScheduler.cancel(JOB_ID)
        }
    }

    private fun scheduleJob() {
        TextLogger.log(
            txt_logs,
            "scheduleJob()"
        )
        activity?.let {
            val jobScheduler = it.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler

            val componentName = ComponentName(it, MyJobService::class.java)

            val jobInfo = JobInfo.Builder(JOB_ID, componentName)
                .setRequiresCharging(true)// charging is required
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)// wifi required
                .setPersisted(true)
                .setPeriodic(15 * 60 * 1000)
                .build()

            val result = jobScheduler.schedule(jobInfo)
            if (result == JobScheduler.RESULT_SUCCESS) {
                TextLogger.log(
                    txt_logs,
                    "Job Scheduled"
                )
            } else {
                TextLogger.log(
                    txt_logs,
                    "Job Scheduled"
                )
            }
        }
    }
}
