package com.example.cookhelper.navigation.recipes

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cookhelper.R
import kotlinx.android.synthetic.main.fragment_recipes.*

class RecipesMainFragment : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecycler()
    }

    private fun initRecycler(){
        recyclerView = rv_parent

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@RecipesMainFragment,
                LinearLayout.VERTICAL, false)
            adapter = ParentAdapter(ParentDataFactory
                .getParents(40))
        }

    }
}