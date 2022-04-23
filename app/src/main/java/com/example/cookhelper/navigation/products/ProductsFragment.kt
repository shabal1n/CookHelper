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
import com.example.cookhelper.DataDAO
import com.example.cookhelper.R
import com.example.cookhelper.entities.Product
import com.example.cookhelper.navigation.add_products.AddProductsActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_products.*


class ProductsFragment : Fragment(), ProductsFragmentAdapter.OnListFragmentInteractionListener {

    private var columnCount = 1
    var mockList = ArrayList<Product>()
    private val userId: String = FirebaseAuth.getInstance().currentUser!!.uid

    //    val reference: DatabaseReference = FirebaseDatabase.getInstance().reference.child("users").child(userId).child("products")
    val data = DataDAO()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
        viewDataInitializer()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_products.layoutManager = when {
            columnCount <= 1 -> LinearLayoutManager(context)
            else -> GridLayoutManager(context, columnCount)
        }
//        mockList.add(
//            ProductsItem(
//                1,
//                "Carrot",
//                "vegetable",
//                "https://image.shutterstock.com/image-photo/carrot-isolated-on-white-background-260nw-1208616166.jpg"
//            )
//        )
//        mockList.add(
//            ProductsItem
//                (
//                2,
//                "Mushrooms",
//                "vegetable",
//                "https://thumbor.forbes.com/thumbor/960x0/https%3A%2F%2Fspecials-images.forbesimg.com%2Fdam%2Fimageserve%2F1136653496%2F960x0.jpg%3Ffit%3Dscale"
//            )
//        )
//        mockList.add(
//            ProductsItem(
//                3,
//                "Potatoes",
//                "vegetable",
//                "https://www.simplyrecipes.com/wp-content/uploads/2014/11/yukon-gold-potatoes-horiz-1200.jpg"
//            )
//        )
//        mockList.add(
//            ProductsItem(
//                4,
//                "Onion",
//                "vegetable",
//                "https://images-na.ssl-images-amazon.com/images/I/81UeYuulNjL._SX679_.jpg"
//            )
//        )
//        mockList.add(
//            ProductsItem(
//                5,
//                "Cucumber",
//                "vegetable",
//                "https://lh3.googleusercontent.com/proxy/leuNJt9x9JSTYpZgDK-inhO4uVmOWfU8jQYDs4NpP2_AuW-wZEjF_7wW62v-6C9i8qomubldgomCfbhXoUbSA_5awNyR6HbnTA5j8VF-MelSHe3i2VLigTk"
//            )
//        )
//        mockList.add(
//            ProductsItem(
//                6,
//                "Tomato",
//                "vegetable",
//                "https://grist.files.wordpress.com/2011/06/tomato1.jpg?w=425"
//            )
//        )
//        mockList.add(
//            ProductsItem(
//                7,
//                "Garlic",
//                "vegetable",
//                "https://image.shutterstock.com/image-photo/isolated-garlic-raw-on-white-260nw-625452236.jpg"
//            )
//        )
//        mockList.add(
//            ProductsItem(
//                8,
//                "Orange",
//                "fruit",
//                "https://image.shutterstock.com/image-photo/ripe-orange-isolated-on-white-260nw-606022676.jpg"
//            )
//        )
//        mockList.add(
//            ProductsItem(
//                9,
//                "Apple",
//                "fruit",
//                "https://image.shutterstock.com/image-photo/red-apple-isolated-on-white-260nw-1498042211.jpg"
//            )
//        )
        recycler_products.adapter = ProductsFragmentAdapter(mockList, this)
        add_products_button.setOnClickListener {
            activity?.let {
                val intent = Intent(it, AddProductsActivity::class.java)
                it.startActivity(intent)
            }
        }

    }

    private fun viewDataInitializer() {
        var productsTemp = ArrayList<Product>()
        var it = data.productsList.iterator()
        while (it.hasNext()) {
            productsTemp.add(it.next())
        }
        mockList = productsTemp

    }

    override fun onListFragmentInteraction(item: Product) {
        Toast.makeText(this.context, item.name, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"
    }
}
