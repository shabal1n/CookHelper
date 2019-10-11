package com.example.cookhelper

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val uButton = findViewById<TextView>(R.id.textreg)

        uButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, registration::class.java))
        }
        proceed.setOnClickListener {
            startActivity(Intent(this, BottomNavigation::class.java))
        }
    }

    fun toastMe(view: View) {
        val myToast = Toast.makeText(this, "Logging in", Toast.LENGTH_SHORT)
        myToast.show()
    }
}

