package com.mindvalley.personalgrowth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mindvalley.personalgrowth.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainFragment.newInstance())
            .commit()

    }
}