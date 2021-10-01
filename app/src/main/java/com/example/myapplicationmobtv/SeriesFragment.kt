package com.example.myapplicationmobtv


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.myapplicationmobtv.adapters.ItemArrayAdapter
import com.example.myapplicationmobtv.adapters.ItemArrayAdapter2
import com.example.myapplicationmobtv.databinding.SeriesFragmentBinding

var serieId = Bundle()

class SeriesFragment : Fragment() {

    companion object {
        fun newInstance() = SeriesFragment()
    }

    private lateinit var itemList: List<Parentitemseries>
    private lateinit var viewModel: SeriesViewModel
    private val viewBinding1: SeriesFragmentBinding by viewBinding()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.series_fragment, container, false)


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            serieId = arguments as Bundle
            Log.d("Tag", "bundle ${serieId}")
        }

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = SeriesViewModel()
         ViewModelProvider(requireActivity()).get(SeriesViewModel::class.java)
        val recyclerView2 = viewBinding1.recyclerView2
        val recyclerView1 = viewBinding1.recyclerView1

        itemList = viewModel.parentItemList()

        val itemArrayAdapter = ItemArrayAdapter(R.layout.item_data, itemList)
        val itemArrayAdapter2 = ItemArrayAdapter2(R.layout.item_data2, itemList)

        Log.d("Tag", "viewModel.parentItemList()" + viewModel.parentItemList())

        recyclerView2.layoutManager = LinearLayoutManager(context)
        recyclerView2.adapter = itemArrayAdapter2
        Log.d("Tag", "viewModel.parentItemList()" + viewModel.parentItemList())

        recyclerView1.setLayoutManager(LinearLayoutManager(context, RecyclerView.VERTICAL, false))
        recyclerView1.adapter = itemArrayAdapter

        val titleTextView = viewBinding1.titleTextView
        val textView = viewBinding1.textView
        titleTextView.text = feed?.providerName
        textView.text = feed?.lastUpdated
        Log.d("Tag", "feed?.lastUpdated + " + feed?.lastUpdated)
        titleTextView.setOnClickListener {
            Toast.makeText(context, "Roku", Toast.LENGTH_SHORT).show()
        }
    }
}
