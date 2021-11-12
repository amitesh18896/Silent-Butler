package com.pallaw.servicesandbackgroundtasks.module.backgroundservice

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.pallaw.servicesandbackgroundtasks.R
import kotlinx.android.synthetic.main.fragment_background_service.*
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 */
class BackgroundServiceFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_background_service, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("onViewCreated: ")

        listOf<Button>(btn_background_start, btn_background_stop).forEach {
            it.setOnClickListener(this)
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            btn_background_start -> {
                startService()
            }
            btn_background_stop -> {
                stopService()
            }
        }
    }

    private fun stopService() {
        activity?.let {
            it.stopService(Intent(it, BackgroundService::class.java))
        }
    }

    private fun startService() {
        activity?.let {
            it.startService(Intent(it, BackgroundService::class.java))
        }
    }

}
