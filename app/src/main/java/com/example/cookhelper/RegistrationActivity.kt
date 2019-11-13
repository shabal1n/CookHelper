package com.example.cookhelper

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.example.cookhelper.dummy.DummyContent
import com.example.cookhelper.entities.User
import com.example.cookhelper.extensions.loadImage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.theartofdev.edmodo.cropper.CropImage
import kotlinx.android.synthetic.main.activity_registration.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class RegistrationActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    private val viewModel: RegistrationViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference

        editLim.setOnClickListener {
            val list = ArrayList<DummyContent.DummyItem>()
            list.add((DummyContent.DummyItem("Sugar lim", "sdfsd", "asdasd")))
            list.add((DummyContent.DummyItem("Sugar lim", "sdfsd", "asdasd")))
            list.add((DummyContent.DummyItem("Sugar lim", "sdfsd", "asdasd")))
            list.add((DummyContent.DummyItem("Sugar lim", "sdfsd", "asdasd")))
            list.add((DummyContent.DummyItem("Sugar lim", "sdfsd", "asdasd")))
            list.add((DummyContent.DummyItem("Sugar lim", "sdfsd", "asdasd")))
            list.add((DummyContent.DummyItem("Sugar lim", "sdfsd", "asdasd")))

            val bottomSheetFragment = BottomSheetFragment(list) {

            }
            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
        }
        button_signup.setOnClickListener {
            signUpUser()
            writeNewUser()
        }

        imageAcc.setOnClickListener {
            setAccountListener()
        }

        initObservers()
    }

    private fun writeNewUser() {
            if (editName.text.toString().isEmpty()) {
                editName.error = "Please enter your name"
                editName.requestFocus()
                return
            }
            if (edit_weight.text.toString().isEmpty()) {
                edit_weight.error = "Please enter your weight"
                edit_weight.requestFocus()
                return
            }
            if (heightEdit.text.toString().isEmpty()) {
                heightEdit.error = "Please enter your height"
                heightEdit.requestFocus()
                return
            }

        val name = editName.text.toString()
        val weight = edit_weight.text.toString()
        val height = heightEdit.text.toString()

            val finalUser = User(name, weight, height)
            database.child("users").child(name).setValue(finalUser)
    }

    private fun signUpUser() {
        if (editSn.text.toString().isEmpty()) {
            editSn.error = "Please enter email"
            editSn.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(editSn.text.toString()).matches()) {
            editSn.error = "Please enter email"
            editSn.requestFocus()
            return
        }
        if (editPass.text.toString().isEmpty()) {
            editPass.error = "Please enter password"
            editSn.requestFocus()
            return
        }
        if (editName.text.toString().isEmpty()) {
            editName.error = "Please enter your name"
            editName.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(editSn.text.toString(), editPass.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, MainActivity::class.java))
                } else {

                    Toast.makeText(
                        baseContext, "Sign Up failed.Try again",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }


    private fun setAccountListener() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_DENIED
        ) {
            CropImage.activity().start(this)
        } else {
            requestCameraPermission()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            try {
                when (requestCode) {
                    CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE -> {
                        val result = CropImage.getActivityResult(data)
                        viewModel.uploadFile(result.uri)
                    }
                    CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE -> {
                        val result = CropImage.getActivityResult(data)
                        Toast.makeText(this, "Ошибка ${result.error}", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }


    private fun initObservers() {
        viewModel.downloadUriLiveData.observe(this, Observer {
            imageAcc.loadImage(it.toString(), this, R.drawable.ic_account_circle_black_24dp)
        })
    }

    private val permissions = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    private fun requestCameraPermission() {
        if (!ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) ||
            !ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) ||
            !ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)
        ) {
            ActivityCompat.requestPermissions(this, permissions, 120)
            return
        }
    }

}