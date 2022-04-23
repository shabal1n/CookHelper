package com.example.cookhelper.navigation.history


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cookhelper.DataDAO
import com.example.cookhelper.R
import com.example.cookhelper.entities.History
import kotlinx.android.synthetic.main.fragment_history.*


class HistoryFragment : Fragment(), HistoryFragmentAdapter.OnListFragmentInteractionListener {


    private var columnCount = 1
    var data = DataDAO()

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
//        data.fetchData()
        recyclerView.adapter = HistoryFragmentAdapter(data.historyList, this)
    }

    override fun onListFragmentInteraction(item: History) {
        Toast.makeText(this.context, item.recipe.name, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"
    }
}
