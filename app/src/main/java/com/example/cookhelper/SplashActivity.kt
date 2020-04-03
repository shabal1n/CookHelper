package com.example.cookhelper

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onDestroy() {
        super.onDestroy()
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out)
        iv_logo.startAnimation(AnimationUtils.loadAnimation(this,R.anim.splash_in))

        Handler().postDelayed({
            iv_logo.startAnimation(AnimationUtils.loadAnimation(this,R.anim.splash_out))
            Handler().postDelayed({
                iv_logo.visibility = View.GONE
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            },500)
        },2500)

    }

}
