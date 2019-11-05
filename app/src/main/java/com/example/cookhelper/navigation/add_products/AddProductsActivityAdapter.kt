package com.example.cookhelper.navigation.add_products

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cookhelper.R
import com.example.cookhelper.extensions.loadImage
import com.example.cookhelper.navigation.products.ProductsItem
import com.example.cookhelper.navigation.products.ProductsType
import kotlinx.android.synthetic.main.add_new_products_list.view.*


class AddProductsActivityAdapter(
    private val mValues: List<ProductsItem>,
    private val mListener: OnListFragmentInteractionListener?
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as ProductsItem
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun getItemViewType(position: Int) = when (mValues[position].type) {
        ProductsType.INFO -> R.layout.add_new_products_list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.add_new_products_list -> AddProduct(view)
            else -> throw RuntimeException("Unknown viewType: $viewType. You should modify onCreateViewHolder")
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class AddProduct(val mView: View) : RecyclerView.ViewHolder(mView) {
        fun bind(productItem: ProductsItem) {
            mView.product_name.text = productItem.content
            mView.product_description.text = productItem.details
            mView.image_of_products.loadImage(
                mView.toString(),
                mView.context,
                R.drawable.ic_free_breakfast_black_24dp
            )

        }

        init {
            mView.setOnClickListener {
                mListener?.onListFragmentInteraction(mValues[adapterPosition])
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is AddProduct -> holder.bind(mValues[position])

        }

    }

    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(item: ProductsItem)
    }
}