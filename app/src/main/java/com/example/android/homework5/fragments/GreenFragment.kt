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
import com.example.android.homework5.R

class GreenFragment : Fragment() {

    val br: BroadcastReceiver by lazy(this::GreenBroadcastReceiver)

    companion object {
        const val ACTION = "MY_SUPER_ACTION"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_green, container, false)
    }

    override fun onStart() {
        super.onStart()
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).apply {
            addAction(ACTION)
        }
        context?.registerReceiver(br, filter)
    }

    override fun onStop() {
        super.onStop()
        context?.unregisterReceiver(br)
    }

    inner class GreenBroadcastReceiver : BroadcastReceiver() {

        private val txtEdit = view!!.findViewById(R.id.txt_edit_text) as TextView
        private val txtSwitch = view!!.findViewById(R.id.txt_switch_status) as TextView
        private val txtButton = view!!.findViewById(R.id.txt_button_status) as TextView


        override fun onReceive(context: Context?, intent: Intent?) {
            when (intent?.action) {
                ACTION -> {
                    when {
                        intent.hasExtra("EXTRA_SWITCH_STATUS") -> {
                            val boolSwitch = intent.getBooleanExtra("EXTRA_SWITCH_STATUS", false)
                            if (boolSwitch) txtSwitch.text = "Switch is on"
                            else txtSwitch.text = "Switch is off"
                        }

                        intent.hasExtra("EXTRA_EDIT_TEXT") -> {
                            val txtNewEdit = intent.getStringExtra("EXTRA_EDIT_TEXT")
                            txtEdit.text = "Text in EditText is: $txtNewEdit"
                        }
                        intent.hasExtra("EXTRA_TOUCH_BUTTON") -> {
                            val boolSwitch = intent.getBooleanExtra("EXTRA_TOUCH_BUTTON", false)
                            if (boolSwitch) txtButton.text = "Button pressed"
                            else txtButton.text = "Button released"
                        }
                    }
                }
            }
        }
    }
}