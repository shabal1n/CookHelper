package com.example.cookhelper.firebase

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.cookhelper.App
import com.example.cookhelper.base.BaseViewModel
import com.example.cookhelper.entities.Table
import com.example.cookhelper.entities.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ProfileData : BaseViewModel() {
    val profilelivedata = MutableLiveData<User>()
    val ref = FirebaseDatabase.getInstance().reference.child("products")

    fun onViewInitizialized() {
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                profilelivedata.value = dataSnapshot.getValue(User::class.java)
            }
            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("tag", "loadPost:onCancelled", databaseError.toException())
            }
        }
        ref.addListenerForSingleValueEvent(postListener)
    }
}