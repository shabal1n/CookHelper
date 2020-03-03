package com.example.cookhelper.navigation.profile


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cookhelper.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment() {


    private lateinit var database: DatabaseReference


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        database = FirebaseDatabase.getInstance().reference
        return inflater.inflate(R.layout.fragment_profile, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rateApp.setOnClickListener {
            activity?.let {
                val intent = Intent(it, RateApp::class.java)
                it.startActivity(intent)
            }
        }

        aboutApp.setOnClickListener {
            activity?.let {
                val intent = Intent(it, AboutApp::class.java)
                it.startActivity(intent)
            }
        }

        helpProfile.setOnClickListener {
            activity?.let {
                val intent = Intent(it, HelpActivity::class.java)
                it.startActivity(intent)
            }
        }
        foodGraph.setOnClickListener {
            activity?.let {
                val intent = Intent(it, GraphOfCalories::class.java)
                it.startActivity(intent)
            }
        }

        logoutBtn.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            Singleton.token = ""
            startActivity(Intent(activity, SplashActivity::class.java))
        }
    }

}
