package com.example.cookhelper

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import com.example.cookhelper.BottomFragment.OnListFragmentInteractionListener
import com.example.cookhelper.dummy.DummyContent.DummyItem

import kotlinx.android.synthetic.main.bottom_fragment_list.view.*


open class BottomFragmentAdapter(
    private val mValues: List<DummyItem>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<BottomFragmentAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as DummyItem
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.bottom_fragment_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mValues[position])
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        fun bind(dummyItem: DummyItem) {
            mView.title.text = dummyItem.content
        }

        init {
            mView.setOnClickListener{
                mListener?.onListFragmentInteraction(mValues[adapterPosition])
            }
        }
    }
}
