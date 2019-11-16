package com.example.cookhelper.navigation.recipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cookhelper.R
import com.example.cookhelper.navigation.products.ProductsItem
import kotlinx.android.synthetic.main.fragment_recipes.*
import org.koin.android.ext.android.inject


class FragmentRecipes : Fragment() {

    lateinit var recyclerView: RecyclerView
    private val viewModel: RecipesViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recipes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()


    }

    private fun initRecycler() {
        recyclerView = rv_parent

        recyclerView.apply {
            layoutManager = LinearLayoutManager(
                context, RecyclerView.VERTICAL, false
            )
            adapter = ParentAdapter(
                    viewModel
                    .getParents()
            )
        }
    }
}

