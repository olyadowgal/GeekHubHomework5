package com.example.android.homework5.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.homework5.R
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment: Fragment(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_system_info.setOnClickListener(this)
        btn_frag_com.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_system_info -> {
                fragmentManager?.apply{
                    beginTransaction()
                        .replace(R.id.view_fragment_placeholder,SystemInfoFragment())
                        .addToBackStack(null)
                        .commit()
                }

            }
        }
    }
}