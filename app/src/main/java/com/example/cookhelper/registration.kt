package com.example.cookhelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.cookhelper.dummy.DummyContent
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.android.synthetic.main.bottom_fragment.*

class registration : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
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
    }


}
