package com.example.cookhelper.navigation.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cookhelper.R
import com.example.cookhelper.extensions.loadImage
import kotlinx.android.synthetic.main.fragment_history_action_item.view.*
import kotlinx.android.synthetic.main.fragment_history_date_item.view.*
import kotlinx.coroutines.NonCancellable.children
import java.lang.RuntimeException

class HistoryFragmentAdapter(
    private val mValues: List<HistoryItem>,
    private val mListener: OnListFragmentInteractionListener?
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener
    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as HistoryItem
            mListener?.onListFragmentInteraction(item)
        }
    }


    override fun getItemViewType(position: Int) = when(mValues[position].type) {
        HistoryType.DATE -> R.layout.fragment_history_date_item
        HistoryType.INFO ->R.layout.fragment_history_action_item
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.fragment_history_date_item -> HeaderDateViewHolder(view)
            R.layout.fragment_history_action_item -> HistoryTextViewHolder(view)
            else -> throw RuntimeException("Unknown viewType: $viewType. You should modify onCreateViewHolder")
        }
    }




    override fun getItemCount(): Int = mValues.size



    inner class HistoryTextViewHolder(private val mView: View) : RecyclerView.ViewHolder(mView) {
        fun bind(historyItem: HistoryItem) {
            mView.header_history.text = historyItem.content
            mView.history_description.text = historyItem.details
            Glide.with(mView.context)
                .load(historyItem.image)
                .into(mView.history_image)

        }

        init {
            mView.setOnClickListener {
                mListener?.onListFragmentInteraction(mValues[adapterPosition])
            }
        }
    }


    inner class HeaderDateViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        fun bind(historyItem: HistoryItem) {
            mView.data_view.text = historyItem.content
        }

    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is HistoryTextViewHolder -> holder.bind(mValues[position])
            is HeaderDateViewHolder ->  holder.bind(mValues[position])
        }

    }

    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(item: HistoryItem)
    }

}