package com.example.cookhelper.navigation.recipes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cookhelper.R
import kotlinx.android.synthetic.main.fragment_recipes_child_recycler.view.*

class ChildAdapter(var children : List<RecipesItem>, private val listener: OnRecipesItemClickListener)
    : RecyclerView.Adapter<ChildAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {

        val v =  LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_recipes_child_recycler,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return children.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val child = children[position]
        Glide.with(holder.itemView.context)
            .load(children[position].image)
            .into(holder.imageView)
        holder.textView.text = child.content
        holder.itemView.setOnClickListener {
            listener.onClick(child)
        }
    }


    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val textView : TextView = itemView.child_textView
        val imageView: ImageView = itemView.child_imageView
    }


}