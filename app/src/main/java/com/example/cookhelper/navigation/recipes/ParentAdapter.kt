package com.example.cookhelper.navigation.recipes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cookhelper.R
import com.example.cookhelper.entities.Recipe
import kotlinx.android.synthetic.main.fragment_recipes_parent_recycler.view.*

class ParentAdapter(
    private val listener: OnRecipesItemClickListener
) : RecyclerView.Adapter<ParentAdapter.ViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()

    private var parents: List<ParentModel>? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_recipes_parent_recycler, parent, false)
        return ViewHolder(v)
    }

    fun setItems(items: List<ParentModel>){
        parents = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return parents?.size ?: 0
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val parent = parents?.get(position)
        holder.textView.text = parent?.title
        val childLayoutManager = LinearLayoutManager(holder.recyclerView.context, RecyclerView.HORIZONTAL, false)
        childLayoutManager.initialPrefetchItemCount = 4
        holder.recyclerView.apply {
            layoutManager = childLayoutManager
            adapter = ChildAdapter(parent?.children, listener)
            setRecycledViewPool(viewPool)
        }

    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recyclerView: RecyclerView = itemView.rv_child
        val textView: TextView = itemView.textView
    }
}

interface OnRecipesItemClickListener {
    fun onClick(recipes: Recipe)
}
