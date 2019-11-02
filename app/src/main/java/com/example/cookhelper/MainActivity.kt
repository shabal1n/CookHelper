package com.example.cookhelper

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cookhelper.navigation.BottomNavigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        val uButton = findViewById<TextView>(R.id.textreg)

        uButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, RegistrationActivity::class.java))
        }
        proceed.setOnClickListener {
            startActivity(Intent(this@MainActivity,
                BottomNavigation::class.java))
        }
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    fun updateUI(currentUser: FirebaseUser?){

    }


    fun toastMe(view: View) {
        val myToast = Toast.makeText(this, "Logging in", Toast.LENGTH_SHORT)
        myToast.show()
    }
}

