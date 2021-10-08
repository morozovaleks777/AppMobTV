package com.example.myapplicationmobtv.series_screen


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.myapplicationmobtv.R
import com.example.myapplicationmobtv.Series
import com.example.myapplicationmobtv.adapters.ItemArrayAdapter
import com.example.myapplicationmobtv.adapters.ItemArrayAdapter2
import com.example.myapplicationmobtv.databinding.SeriesFragmentBinding


class SeriesFragment : Fragment() {


    var seasonNumber= serieId.get("seasonNumber")

    private lateinit var itemList: List<Series>
    private lateinit var viewModel: SeriesViewModel
    private val viewBinding1: SeriesFragmentBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            serieId = arguments as Bundle
            Log.d("Tag", "bundle $serieId")
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.series_fragment, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = SeriesViewModel()
        val recyclerView2 = viewBinding1.recyclerView2
        val recyclerView1 = viewBinding1.recyclerView1

        itemList = viewModel.getSeriesList()

        val itemArrayAdapter = ItemArrayAdapter(R.layout.item_data, itemList)
        val itemArrayAdapter2 = ItemArrayAdapter2(R.layout.item_data2, itemList)

        Log.d("Tag", "viewModel.parentItemList()" + viewModel.getSeriesList())

        recyclerView2.layoutManager = LinearLayoutManager(context)
        recyclerView2.adapter = itemArrayAdapter2
        Log.d("Tag", "viewModel.parentItemList()" + viewModel.getSeriesList())

        recyclerView1.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView1.adapter = itemArrayAdapter


    }

    companion object {
        fun newInstance() = SeriesFragment()
        var serieId =Bundle()
    }
}
