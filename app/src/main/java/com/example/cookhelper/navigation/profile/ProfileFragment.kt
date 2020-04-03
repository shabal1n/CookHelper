package com.example.cookhelper.navigation.profile


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.example.cookhelper.R
import com.example.cookhelper.Singleton
import com.example.cookhelper.SplashActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment() {


    private lateinit var database: DatabaseReference
//    https://www.youtube.com/watch?v=_jU7vMw3Wcw SAVE INSTANCE STATE AND POSITION OF RECYCLERVIEW
//    private val LIST_STATE = "list_state"
//    private lateinit var savedRecyclerLayoutState: Parcelable
//    private val BUNDLE_RECYCLER_LAYOUT = "recycler_layout"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        database = FirebaseDatabase.getInstance().reference
        return inflater.inflate(R.layout.fragment_profile, container, false)

    }

    data class User (
        val name: String = "",
        val image: String = ""
    )
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getProfileData()
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

    private fun getProfileData(){
        val id = FirebaseAuth.getInstance().currentUser!!.uid
        database = FirebaseDatabase.getInstance().reference
        database.child("users").child(id).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p1: DataSnapshot) {
                profile_name.text = p1.getValue(User::class.java)!!.name
                val imageUri = p1.getValue(User::class.java)!!.image.toUri()
                photoAccountImageView.setImageURI(imageUri)
                Picasso.get().load(imageUri).into(photoAccountImageView)
            }
        })
    }

}
