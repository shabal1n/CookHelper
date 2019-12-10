package com.example.cookhelper

import android.net.Uri
import com.example.cookhelper.entities.User

object Singleton{
    const val URL = "http://127.0.0.1:8080/"

    var fcmDeviceId = ""
    var facebookToken = ""
    var user: User? = null
    var profilePhotoUri: Uri? = null
    var token = ""
}