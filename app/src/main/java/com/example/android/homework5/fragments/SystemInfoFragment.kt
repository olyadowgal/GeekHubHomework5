package com.example.android.homework5.fragments

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.android.homework5.MainActivity
import com.example.android.homework5.R
import java.text.SimpleDateFormat
import java.util.*


class SystemInfoFragment : Fragment() {

    val br: BroadcastReceiver by lazy(this::SystemInfoBroadcastReceiver)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity as MainActivity)
            .setActionBarTitle("Information about this system")
        (activity as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        return inflater.inflate(R.layout.fragment_system_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateTime()
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

    fun updateTime() {
        val txtView = view!!.findViewById(R.id.txt_time) as TextView
        val tz = TimeZone.getDefault()
        val sdf = SimpleDateFormat("HH:mm:ss")
        val timeText =
            "Current time:  ${sdf.format(Date())} Current timezone: ${tz.getDisplayName(false, TimeZone.SHORT)}"
        txtView.text = timeText

    }

    fun updateNetwork() {
        val txtView = view!!.findViewById(R.id.txt_network) as TextView
        val connectivityManager = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo.isConnected) {
            txtView.text = "Internet available"
        } else txtView.text = "Internet disable"
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

    inner class SystemInfoBroadcastReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            when (intent.action) {
                ConnectivityManager.CONNECTIVITY_ACTION -> {
                    updateNetwork()
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
                    updateTime()
                }
                Intent.ACTION_TIMEZONE_CHANGED -> {
                    updateTime()
                }
            }
        }
    }

}