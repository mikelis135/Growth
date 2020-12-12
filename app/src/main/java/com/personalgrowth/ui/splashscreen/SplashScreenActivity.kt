package com.personalgrowth.ui.splashscreen

import android.animation.Animator
import android.animation.AnimatorInflater
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.personalgrowth.R
import com.personalgrowth.ui.main.MainActivity

class SplashScreenActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen_activity)

        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        val appLogo = findViewById<ImageView>(R.id.logo)
        val appText = findViewById<TextView>(R.id.mindValley)

        val flip = AnimatorInflater.loadAnimator(this, R.animator.flip)
        val fadeIn = AnimatorInflater.loadAnimator(this, R.animator.fade_in)

        flip.setTarget(appLogo)
        fadeIn.setTarget(appText)

        flip.start()
        fadeIn.start()

        flip.addListener(object : Animator.AnimatorListener {

            override fun onAnimationEnd(p0: Animator?) {
                startActivity(intent)
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            }

            override fun onAnimationRepeat(p0: Animator?) {}
            override fun onAnimationCancel(p0: Animator?) {}
            override fun onAnimationStart(p0: Animator?) {}

        })

    }

}
