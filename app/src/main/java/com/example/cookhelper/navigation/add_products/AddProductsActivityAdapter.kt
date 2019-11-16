package com.example.cookhelper.navigation.add_products

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.cookhelper.R

class AddProductsActivityAdapter : RecyclerView.Adapter<AddProductsActivityAdapter.Companion.Holder>, Filterable {

    var list: MutableList<AddProductsItem>
    var listFiltered: MutableList<AddProductsItem>
    var con: Context
    lateinit var rv: View

    constructor(list: MutableList<AddProductsItem>, con: Context) : super() {
        this.list = list
        this.listFiltered = list
        this.con = con
    }


    override fun onBindViewHolder(holder: Holder, position: Int) {
        var ct: AddProductsItem = listFiltered[position]
        holder.productName.text = ct.content
        rv.setOnClickListener {
            Toast.makeText(con, holder.productName.text.toString(), Toast.LENGTH_SHORT).show()
        }

    }

    companion object {
        class Holder : RecyclerView.ViewHolder {

            var productName: TextView

            constructor(rv: View) : super(rv) {
                productName = rv.findViewById(R.id.product_name)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        rv = LayoutInflater.from(parent.context)
            .inflate(R.layout.add_new_products_list, parent, false)
        var holder: Holder = Holder(rv)
        return holder
    }

    override fun getItemCount(): Int {


        return listFiltered.size
    }

    override fun getFilter(): Filter {

        return object: Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                var charString : String = constraint.toString()
                if (charString.isEmpty()) {
                    listFiltered = list
                } else {
                    var filteredList: MutableList<AddProductsItem> = mutableListOf()
                    for(s: AddProductsItem in list) {
                        if (s.content.toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(s)
                        }
                    }
                    listFiltered = filteredList
                }
                var filterResults: FilterResults = FilterResults()
                filterResults.values = listFiltered
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                listFiltered = results!!.values as MutableList<AddProductsItem>
                notifyDataSetChanged()
            }

        }
    }

}
