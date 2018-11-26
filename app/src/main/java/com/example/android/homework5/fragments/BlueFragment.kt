package com.example.android.homework5.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CompoundButton
import android.widget.RadioGroup
import android.widget.Switch
import com.example.android.homework5.R
import android.content.Intent



class BlueFragment: Fragment(), CompoundButton.OnCheckedChangeListener {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_blue, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val switch = view.findViewById(R.id.switch_blue) as Switch
        switch.setOnCheckedChangeListener(this)
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        val myIntent = Intent("MY_SUPER_ACTION")
        myIntent.putExtra("EXTRA_SWITCH_STATUS",isChecked)
        context?.sendBroadcast(myIntent)
    }


}