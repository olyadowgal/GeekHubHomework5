package com.example.android.homework5

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.android.homework5.fragments.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.view_fragment_placeholder, MainFragment())
        transaction.commit()
    }
}
