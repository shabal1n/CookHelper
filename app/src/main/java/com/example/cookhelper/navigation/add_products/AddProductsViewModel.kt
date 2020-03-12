//import androidx.lifecycle.Observer
//import com.example.cookhelper.navigation.add_products.AddProductsActivity
//import com.example.cookhelper.navigation.add_products.AddProductsActivityAdapter
//import com.example.cookhelper.navigation.add_products.AddProductsItem
//import org.koin.android.ext.android.inject
//import kotlin.reflect.KProperty
//
//package com.example.cookhelper.navigation.add_products
//
//import android.util.Log
//import androidx.lifecycle.MutableLiveData
//import com.example.cookhelper.base.BaseViewModel
//import com.example.cookhelper.extensions.getData
//import com.google.firebase.database.DataSnapshot
//import com.google.firebase.database.DatabaseError
//import com.google.firebase.database.FirebaseDatabase
//import com.google.firebase.database.ValueEventListener
//
//class AddProductsViewModel : BaseViewModel() {
//    val productsdata = MutableLiveData<List<AddProductsItem>>()
//    val ref = FirebaseDatabase.getInstance().reference.child("products")
//
//    fun onViewInitializedProducts(){
//        val postListener = object: ValueEventListener{
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                val products = ArrayList<AddProductsItem>()
//                dataSnapshot.children.forEach {
//                    products.add(it.getValue(AddProductsItem::class.java)!!)
//                }
//                productsdata.value = products
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {
//                Log.w("tag", "loadPost:onCancelled", databaseError.toException())
//            }
//        }
//        ref.addListenerForSingleValueEvent(postListener)
//    }
//}
//
//private fun initObserver() {
//    viewModel.productsdata.observe(this, Observer {
//        updateProfileUI(it)
//    })
//}
//
//private fun updateProfileUI(product: List<AddProductsItem>) {
//    adapter.list = product.toMutableList()
//    adapter.notifyDataSetChanged()
//}
//
//initObserver()
//viewModel.onViewInitializedProducts()
//
//
//private var firebaseView: AddProductsViewModel by viewModel()
//val repository = AddProductsViewModel()
//private val viewModel: AddProductsViewModel by inject()
//
//
//
//private operator fun Any.setValue(
//    addProductsActivity: AddProductsActivity,
//    property: KProperty<*>,
//    addProductsViewModel: AddProductsViewModel
//) {
//
//}