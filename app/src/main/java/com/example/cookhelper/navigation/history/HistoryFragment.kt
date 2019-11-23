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
        mockList.add(HistoryItem(1,
            "September 12, 2019",
            "123",
            HistoryType.DATE,
            "https://assets3.thrillist.com/v1/image/2797371/size/tmg-article_default_mobile.jpg"))
        mockList.add(HistoryItem(2,
            "Mushrooms",
            "Handpicked in Almaty forests",
            HistoryType.INFO,
            "https://www.walshmushrooms.com/userfiles/images/sys/blog_items/Mushroom%20Superfood.jpg"))
        mockList.add(HistoryItem(3,
            "Omelette",
            "Self-made by wife",
            HistoryType.INFO,
            "https://keyassets-p2.timeincuk.net/wp/prod/wp-content/uploads/sites/53/2018/12/masala_omelette_249703281_423012702-e1545132156627-1220x772.jpg"))
        mockList.add(HistoryItem(4,
            "Soup",
            "From true Chinese restaurant",
            HistoryType.INFO,
            "https://farm8.staticflickr.com/7852/46389717605_3e4eb41e5a_b.jpg"))
        mockList.add(HistoryItem(5,
            "September 13, 2019",
            "123",
            HistoryType.DATE,
            "https://assets3.thrillist.com/v1/image/2797371/size/tmg-article_default_mobile.jpg"))
        mockList.add(HistoryItem(6,
            "Croissant",
            "Bought in Paris yesterday",
            HistoryType.INFO,
            "https://images.squarespace-cdn.com/content/v1/5216e267e4b0cb074c79fdb3/1510517002066-N8QZ0QVE51TDSGNWWV5H/ke17ZwdGBToddI8pDm48kO6t_FIigFZlD-2ukJs68NZ7gQa3H78H3Y0txjaiv_0fDoOvxcdMmMKkDsyUqMSsMWxHk725yiiHCCLfrh8O1z5QPOohDIaIeljMHgDF5CVlOqpeNLcJ80NK65_fV7S1UdQnRCmyfmE32mt8hf8jTbpNOvskeoRv-ygqK_y0NLe3pygZMNSAPtQr-kV0SxGO-A/Croissant.JPG?format=1500w"))
        mockList.add(HistoryItem(7,
            "Omelette",
            "Self-made by wife",
            HistoryType.INFO,
            "https://keyassets-p2.timeincuk.net/wp/prod/wp-content/uploads/sites/53/2018/12/masala_omelette_249703281_423012702-e1545132156627-1220x772.jpg"))
        recyclerView.adapter = HistoryFragmentAdapter(mockList, this)
    }

    override fun onListFragmentInteraction(item: HistoryItem) {
        Toast.makeText(this.context, item.content, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"
    }
}
