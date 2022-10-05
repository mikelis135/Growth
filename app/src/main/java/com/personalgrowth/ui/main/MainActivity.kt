package com.personalgrowth.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.personalgrowth.databinding.MainActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var mainView: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainView = MainActivityBinding.inflate(layoutInflater)
        setContentView(mainView.root)

    }
}