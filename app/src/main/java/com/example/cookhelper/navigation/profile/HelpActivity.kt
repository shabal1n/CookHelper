package com.example.cookhelper.navigation.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cookhelper.DataDAO
import com.example.cookhelper.R

class HelpActivity : AppCompatActivity() {
    val data = DataDAO()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        data.testOracle()
        setContentView(R.layout.activity_help)
    }
}
