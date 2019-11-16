package com.example.cookhelper.navigation.products


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cookhelper.navigation.add_products.AddProductsActivity
import com.example.cookhelper.R
import kotlinx.android.synthetic.main.fragment_products.*


class ProductsFragment : Fragment(), ProductsFragmentAdapter.OnListFragmentInteractionListener {

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        add_products_button.setOnClickListener {
            activity?.let{
                val intent = Intent (it, AddProductsActivity::class.java)
                it.startActivity(intent)
            }
        }
        recycler_products.layoutManager = when {
            columnCount <= 1 -> LinearLayoutManager(context)
            else -> GridLayoutManager(context, columnCount)
        }
        val mockList = arrayListOf<ProductsItem>()
        mockList.add(
            ProductsItem(
                "1",
                "Carrot",
                "vegetable",
                R.drawable.carrot,
                ProductsItem.ProductsType.INFO,
                "200 g."
            )
        )
        mockList.add(
            ProductsItem
                (
                "2",
                "Mushrooms",
                "vegetable",
                R.drawable.shrooms,
                ProductsItem.ProductsType.INFO,
                "200 g."
            )
        )
        mockList.add(
            ProductsItem(
                "3",
                "Potatoes",
                "vegetable",
                R.drawable.potato,
                ProductsItem.ProductsType.INFO,
                "200 g."
            )
        )
        mockList.add(
            ProductsItem(
                "4",
                "Onion",
                "vegetable",
                R.drawable.onion,
                ProductsItem.ProductsType.INFO,
                "200 g."
            )
        )
        mockList.add(
            ProductsItem(
                "5",
                "Cucumber",
                "vegetable",
                R.drawable.cucmber,
                ProductsItem.ProductsType.INFO,
                "200 g."
            )
        )
        mockList.add(
            ProductsItem(
                "6",
                "Tomato",
                "vegetable",
                R.drawable.tomato,
                ProductsItem.ProductsType.INFO,
                "200 g."
            )
        )
        mockList.add(
            ProductsItem(
                "7",
                "Garlic",
                "vegetable",
                R.drawable.garlic,
                ProductsItem.ProductsType.INFO,
                "200 g."
            )
        )
        mockList.add(
            ProductsItem(
                "7",
                "Orange",
                "fruit",
                R.drawable.orange,
                ProductsItem.ProductsType.INFO,
                "200 g."
            )
        )
        mockList.add(
            ProductsItem(
                "7",
                "Apple",
                "fruit",
                R.drawable.lagman,
                ProductsItem.ProductsType.INFO,
                "200 g."
            )
        )
        recycler_products.adapter = ProductsFragmentAdapter(mockList, this)

    }

    override fun onListFragmentInteraction(item: ProductsItem) {
        Toast.makeText(this.context, item.content, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"
    }
}
