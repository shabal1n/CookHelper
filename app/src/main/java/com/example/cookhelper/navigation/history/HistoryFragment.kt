package com.example.cookhelper.navigation.history


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cookhelper.R
import kotlinx.android.synthetic.main.fragment_history.*


class HistoryFragment : Fragment(), HistoryFragmentAdapter.OnListFragmentInteractionListener {


    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_history, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = when {
            columnCount <= 1 -> LinearLayoutManager(context)
            else -> GridLayoutManager(context, columnCount)
        }
        val mockList = arrayListOf<HistoryItem>()
        mockList.add(HistoryItem("1","September 12, 2019", "123", HistoryType.DATE))
        mockList.add(HistoryItem("1","Mushrooms", "Handpicked in Almaty forests", HistoryType.INFO))
        mockList.add(HistoryItem("1","Omelette", "Self-made by wife", HistoryType.INFO))
        mockList.add(HistoryItem("1","Soup", "From true Chinese restaurant", HistoryType.INFO))
        mockList.add(HistoryItem("1","September 13, 2019", "123", HistoryType.DATE))
        mockList.add(HistoryItem("1","Croissant", "Bought in Paris yesterday", HistoryType.INFO))
        mockList.add(HistoryItem("1","Omelette", "Self-made by wife", HistoryType.INFO))
        recyclerView.adapter = HistoryFragmentAdapter(mockList, this)
    }

    override fun onListFragmentInteraction(item: HistoryItem) {
        Toast.makeText(this.context, item.content, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"
    }
}
