package com.pallaw.servicesandbackgroundtasks.module.landing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.pallaw.servicesandbackgroundtasks.R
import kotlinx.android.synthetic.main.fragment_landing.*

/**
 * A simple [Fragment] subclass.
 */
class LandingFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_landing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val buttons = listOf(btn_job_scheduler, btn_foreground_service, btn_background_service)
        for (button in buttons) {
            button.setOnClickListener(this)
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            btn_job_scheduler -> {
                findNavController().navigate(R.id.action_landingFragment_to_jobSchedulerFragment)
            }

            btn_foreground_service -> {
                findNavController().navigate(R.id.action_landingFragment_to_foregroundServiceFragment)
            }

            btn_background_service -> {
                findNavController().navigate(R.id.action_landingFragment_to_backgroundService)
            }
        }
    }

}
