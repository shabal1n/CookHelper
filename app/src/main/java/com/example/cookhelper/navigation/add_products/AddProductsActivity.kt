package com.example.cookhelper.navigation.add_products

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cookhelper.R
import com.example.cookhelper.firebase.ProfileData


class AddProductsActivity : AppCompatActivity() {

    private lateinit var viewModel: ProfileData
    lateinit var recycler: RecyclerView
    var list: MutableList<AddProductsItem> = mutableListOf()
    val repository = ProductsAddItemRepository()
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var adapter: AddProductsActivityAdapter
    lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_products)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        recycler = findViewById(R.id.add_products_recycler)
        layoutManager = LinearLayoutManager(this)
        recycler.layoutManager = layoutManager
        recycler.setHasFixedSize(true)
        var count = 0
        for (product: AddProductsItem in repository.products) {
            list.add(product)
            count++
        }
        adapter = AddProductsActivityAdapter(list, this)
        recycler.adapter = adapter

        viewModel.onViewInitizialized()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_products_add, menu)
        var item: MenuItem = menu!!.findItem(R.id.action_search)
        searchView = MenuItemCompat.getActionView(item) as SearchView
        MenuItemCompat.setOnActionExpandListener(
            item,
            object : MenuItemCompat.OnActionExpandListener {
                override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                    toolbar.setBackgroundColor(Color.WHITE)
                    (searchView.findViewById(androidx.appcompat.R.id.search_src_text) as EditText).setHintTextColor(
                        Color.BLACK
                    )
                    return true
                }

                override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                    toolbar.setBackgroundColor(resources.getColor(R.color.CornflowerBlue))
                    searchView.setQuery("", false)
                    return true
                }
            })
        searchView.maxWidth = Int.MAX_VALUE
        searchName(searchView)
        return true
    }

    private fun searchName(searchView: SearchView) {

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }
        })

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item!!.itemId == R.id.action_search) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (!searchView.isIconified) {
            searchView.isIconified = true
            return
        }
        super.onBackPressed()
    }


}






