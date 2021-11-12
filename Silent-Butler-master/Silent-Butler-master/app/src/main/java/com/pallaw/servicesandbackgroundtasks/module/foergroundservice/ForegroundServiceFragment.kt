package com.pallaw.servicesandbackgroundtasks.module.foergroundservice

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.pallaw.servicesandbackgroundtasks.R
import kotlinx.android.synthetic.main.fragment_foreground_service.*

/**
 * A simple [Fragment] subclass.
 */
class ForegroundServiceFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_foreground_service, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listOf<Button>(btn_foreground_start, btn_foreground_stop).forEach {
            it.setOnClickListener(this)
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            btn_foreground_start -> {
                startService()
            }
            btn_foreground_stop -> {
                stopService()
            }
        }
    }

    private fun stopService() {
        activity?.let {
            val serviceIntent = Intent(
                context,
                ForegroundService::class.java
            )

            it.stopService(serviceIntent)
        }
    }

    private fun startService() {
        activity?.let {
            val channelName = edt_foreground.text.toString()
            val serviceIntent = Intent(
                context,
                ForegroundService::class.java
            )

            serviceIntent.putExtra(ForegroundService.EXTRA, channelName)

            /** startForegroundService() is important to start the service from background.
             *  if we simply use startService() from background thread then the app will crash
             *  startForegroundService() is only available for api 29 that is why we use ContextCompat which checks the version internally for us
             *  Note - if we use startForegroundService() then we only get 5 seconds to call startForeground() in our service class otherwise the service will be automatically killed by the system
             */
            ContextCompat.startForegroundService(it, serviceIntent)
        }

    }

}
