package com.personalgrowth.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.personalgrowth.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainFragment.newInstance())
            .commit()

    }
}