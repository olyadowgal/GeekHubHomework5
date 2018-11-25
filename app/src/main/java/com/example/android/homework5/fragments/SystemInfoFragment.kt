package com.example.android.homework5.fragments

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.android.homework5.R
import android.widget.TextView



class SystemInfoFragment: Fragment() {

    val br : BroadcastReceiver by lazy(this::SystemInfoBroadcastReceiver)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_system_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).apply {
            addAction(ConnectivityManager.CONNECTIVITY_ACTION)
            addAction(Intent.ACTION_HEADSET_PLUG)
            addAction(Intent.ACTION_HEADSET_PLUG)
            addAction(Intent.ACTION_POWER_CONNECTED)
            addAction(Intent.ACTION_POWER_DISCONNECTED)
            addAction(Intent.ACTION_TIME_CHANGED)
            addAction(Intent.ACTION_TIMEZONE_CHANGED)
        }
        context?.registerReceiver(br, filter)

    }

    override fun onStop() {
        super.onStop()
        context?.unregisterReceiver(br)
    }

    inner class SystemInfoBroadcastReceiver: BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            when (intent.action) {
                ConnectivityManager.CONNECTIVITY_ACTION -> {
                    Toast.makeText(context, "wifi changed", Toast.LENGTH_LONG).show()
                }
                Intent.ACTION_HEADSET_PLUG -> {
                    updateHeadset(intent)
                }
                Intent.ACTION_POWER_CONNECTED -> {
                    val txtView = view!!.findViewById(R.id.txt_power) as TextView
                    txtView.text = "AC Adapter connected"
                }
                Intent.ACTION_POWER_DISCONNECTED -> {
                    val txtView = view!!.findViewById(R.id.txt_power) as TextView
                    txtView.text = "No connected AC adapter"
                }
                Intent.ACTION_TIME_CHANGED -> {
                    Toast.makeText(context, "Time changed", Toast.LENGTH_LONG).show()
                }
                Intent.ACTION_TIMEZONE_CHANGED -> {
                    Toast.makeText(context, "Timezone changed", Toast.LENGTH_LONG).show()
                }
            }
        }
    }


    fun updateHeadset(intent: Intent) {
        val txtView = view!!.findViewById(R.id.txt_headset) as TextView
        val state = intent.getIntExtra("state", -1)
        when (state) {
            0 -> txtView.text = "Headset is unplugged"
            1 -> txtView.text = "Headset is plugged"
            else -> txtView.text = "No data"
        }
    }

}