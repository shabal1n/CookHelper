package com.example.cookhelper.navigation.add_products

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cookhelper.R

class AddProductsActivityAdapter :
    RecyclerView.Adapter<AddProductsActivityAdapter.Companion.Holder>, Filterable {

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
        Glide.with(holder.itemView.context)
            .load(ct.image)
            .into(holder.productImage)
        rv.setOnClickListener {
            val builder = AlertDialog.Builder(con)

            builder.setTitle(holder.productName.text)

            builder.setMessage("Do you want to add this product?")

            builder.setPositiveButton("Yes"){ _, _ ->

                Toast.makeText(this.con,"Added product to your list",Toast.LENGTH_SHORT).show()

                // ADD PRODUCTS TO LIST
            }

            builder.setNeutralButton("No"){_,_ ->
                Toast.makeText(this.con,"Cancelled",Toast.LENGTH_SHORT).show()
            }

            val dialog: AlertDialog = builder.create()

            dialog.show()
        }

    }

    companion object {
        class Holder : RecyclerView.ViewHolder {

            var productName: TextView
            var productImage: ImageView

            constructor(rv: View) : super(rv) {
                productName = rv.findViewById(R.id.product_name)
                productImage = rv.findViewById(R.id.image_of_products)
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

        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                var charString: String = constraint.toString()
                if (charString.isEmpty()) {
                    listFiltered = list
                } else {
                    var filteredList: MutableList<AddProductsItem> = mutableListOf()
                    for (s: AddProductsItem in list) {
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