package com.example.android.homework5.fragments

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent.ACTION_DOWN
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_UP
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import com.example.android.homework5.R
import kotlinx.android.synthetic.main.fragment_blue.*


class BlueFragment : Fragment(), CompoundButton.OnCheckedChangeListener, TextWatcher, View.OnTouchListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_blue, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        switch_blue.setOnCheckedChangeListener(this)
        edit_blue.addTextChangedListener(this)
        button_blue.setOnTouchListener(this)

    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        val myIntent = Intent("MY_SUPER_ACTION")
        myIntent.putExtra("EXTRA_SWITCH_STATUS", isChecked)
        context?.sendBroadcast(myIntent)
    }

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        val myIntent = Intent("MY_SUPER_ACTION")
        when(event.action) {
            MotionEvent.ACTION_UP -> myIntent.putExtra("EXTRA_TOUCH_BUTTON", false)
            MotionEvent.ACTION_DOWN -> myIntent.putExtra("EXTRA_TOUCH_BUTTON", true)
        }
        context?.sendBroadcast(myIntent)
        return false
    }


    override fun afterTextChanged(s: Editable?) {
        val myIntent = Intent("MY_SUPER_ACTION")
        myIntent.putExtra("EXTRA_EDIT_TEXT", s.toString())
        context?.sendBroadcast(myIntent)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

}