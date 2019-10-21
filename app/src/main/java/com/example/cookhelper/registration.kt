package com.example.cookhelper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cookhelper.dummy.DummyContent
import kotlinx.android.synthetic.main.activity_registration.*

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
