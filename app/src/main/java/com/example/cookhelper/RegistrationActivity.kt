package com.example.cookhelper

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.InputType
import android.util.Patterns
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.annotation.GlideExtension
import com.example.cookhelper.dummy.DummyContent
import com.example.cookhelper.entities.Table
import com.example.cookhelper.entities.User
import com.example.cookhelper.extensions.loadImage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.theartofdev.edmodo.cropper.CropImage
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_registration.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class RegistrationActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private var gender = ""
    private lateinit var savePhotoUrl : String


    private val viewModel: RegistrationViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference

        setListener()

        editLim.setOnClickListener {
            val list = ArrayList<DummyContent.DummyItem>()
            list.add((DummyContent.DummyItem("1",
                "Gluten intolerance",
                "Avoid all foods and drinks containing bread, pasta, cereals, beer, baked goods, crackers, sauces, dressing and gravies, especially soy sauce")))
            list.add((DummyContent.DummyItem("2",
                "Dairy intolerance",
                "Avoid dairy products that contain lactose, such as milk and ice cream.")))
            list.add((DummyContent.DummyItem("3",
                "Caffeine intolerance",
                "People with a sensitivity to caffeine should minimize their intake by avoiding foods and beverages that contain caffeine, including coffee, soda, energy drinks, tea and chocolate.")))
            list.add((DummyContent.DummyItem("4",
                "Salicylates intolerance",
                "While completely removing salicylates from the diet is impossible, those with a salicylate intolerance should avoid foods high in salicylates like spices, coffee, raisins and oranges, as well as cosmetics and medications that contain salicylates")))
            list.add((DummyContent.DummyItem("5",
                "Amines intolerance",
                "People with an intolerance to histamine should avoid foods high in this natural chemical: fermented foods, cured meats, dried fruits, citrus fruits, avocados, aged cheeses, smoked fish, vinegar, soured foods like buttermilk" )))
            list.add((DummyContent.DummyItem("6",
                "FODMAPs intolerance",
                "There are many foods high in FODMAPs, including: apples, soft cheeses, honey, milk, artichokes, bread, beans, lentils")))
            list.add((DummyContent.DummyItem("7",
                "Sulfites intolerance",
                "Examples of foods that may contain sulfites include: dried fruit, wine,  apple cider, canned vegetables, pickled foods, condiments, potato chips, beer, tea, baked goods")))
            list.add((DummyContent.DummyItem("8",
                "Fructose intolerance",
                "The following high-fructose foods should be avoided: soda, honey, apples, apple juice and apple cider, agave nectar, certain fruits like watermelon, cherries and pears, certain vegetables like sugar snap peas")))

            val bottomSheetFragment = BottomSheetFragment(list) {

            }
            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
        }
        button_signup.setOnClickListener {
            signUpUser()
        }
        imageAcc.setOnClickListener {
            setAccountListener()
        }

        initObservers()
        editName.inputType = InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
        editSn.inputType = InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
        editPass.inputType = InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
    }


    private fun signUpUser() {
        if (editSn.text.toString().isEmpty()) {
            editSn.error = "Please enter email address"
            editSn.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(editSn.text.toString()).matches()) {
            editSn.error = "Please enter correct email address"
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

        auth.createUserWithEmailAndPassword(editSn.text.toString(), editPass.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    saveUserToFirebase ()
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
            imageAcc.loadImage(it.toString(), R.drawable.ic_account_circle_black_24dp)
            savePhotoUrl = it.toString()
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

    private fun setListener() {
        radGroup.setOnCheckedChangeListener { group, checkedId ->
            setDefaultState(female_rad_but)
            setDefaultState(male_rad_but)
            gender = when (checkedId) {
                R.id.male_rad_but -> {
                    "male"
                }
                R.id.female_rad_but -> {
                    "female"
                }
                else -> {
                    ""
                }

            }
        }
    }

    private fun setDefaultState(editText: RadioButton) {
        editText.error = null
        editText.setBackgroundResource(R.drawable.switch_choice_btn)
    }


    private fun saveUserToFirebase (){
        var name = editName.text.toString()
        var email = editSn.text.toString()
        var pass = editPass.text.toString()
        var weight = Integer.parseInt(edit_weight.text.toString())
        var height = Integer.parseInt(heightEdit.text.toString())
        var profileImageUrl = savePhotoUrl

        val finalUser = User(1, name, email, pass, profileImageUrl, gender, weight, height)
        val uid = FirebaseAuth.getInstance().uid

        database.child("users").child(uid.toString()).setValue(finalUser).addOnCompleteListener(this) { task->
            if (task.isSuccessful){
                Toast.makeText(this, "Successfully registered", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show()
            }
        }

    }

}
