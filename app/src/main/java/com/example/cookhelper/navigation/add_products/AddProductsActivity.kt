package com.example.cookhelper.navigation.add_products

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cookhelper.R
import com.example.cookhelper.navigation.products.ProductsItem
import com.example.cookhelper.navigation.products.ProductsType
import kotlinx.android.synthetic.main.activity_add_new_products.*

class AddProductsActivity : AppCompatActivity(), AddProductsActivityAdapter.OnListFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_products)
        add_products_recycler.layoutManager = LinearLayoutManager(this)
        val mockList = arrayListOf<ProductsItem>()
        mockList.add(
            ProductsItem(
                "1",
                "Carrot",
                "vegetable",
                ProductsType.INFO,
                "200 g."
            )
        )
        mockList.add(
            ProductsItem
                (
                "2",
                "Mushrooms",
                "vegetable",
                ProductsType.INFO,
                "200 g."
            )
        )
        mockList.add(
            ProductsItem(
                "3",
                "Potatoes",
                "vegetable",
                ProductsType.INFO,
                "200 g."
            )
        )
        mockList.add(
            ProductsItem(
                "4",
                "Onion",
                "vegetable",
                ProductsType.INFO,
                "200 g."
            )
        )
        mockList.add(
            ProductsItem(
                "5",
                "Cucumber",
                "vegetable",
                ProductsType.INFO,
                "200 g."
            )
        )
        mockList.add(
            ProductsItem(
                "6",
                "Tomato",
                "vegetable",
                ProductsType.INFO,
                "200 g."
            )
        )
        mockList.add(
            ProductsItem(
                "7",
                "Garlic",
                "vegetable",
                ProductsType.INFO,
                "200 g."
            )
        )
        mockList.add(
            ProductsItem(
                "7",
                "Orange",
                "fruit",
                ProductsType.INFO,
                "200 g."
            )
        )
        mockList.add(
            ProductsItem(
                "7",
                "Apple",
                "fruit",
                ProductsType.INFO,
                "200 g."
            )
        )
        add_products_recycler.adapter = AddProductsActivityAdapter(mockList, this)

    }



    override fun onListFragmentInteraction(item: ProductsItem) {

    }



}

