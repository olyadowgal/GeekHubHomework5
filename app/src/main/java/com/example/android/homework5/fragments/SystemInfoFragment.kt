package com.example.android.homework5.fragments

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.homework5.R

class SystemInfoFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_system_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

//    inner class SystemInfoBroadcastReceiver: BroadcastReceiver() {
//
//        override fun onReceive(context: Context, intent: Intent) {
//            when (intent.action) {
//
//            }
//        }
//
//    }

}