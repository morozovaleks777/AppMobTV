package com.example.myapplicationmobtv


import android.content.ClipData.Item
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.helper.widget.Carousel
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.myapplicationmobtv.adapters.ItemArrayAdapter
import com.example.myapplicationmobtv.adapters.ItemArrayAdapter2
import com.example.myapplicationmobtv.adapters.selectedItem
import com.example.myapplicationmobtv.databinding.SeriesFragmentBinding
import com.example.myapplicationmobtv.ui.main.MainFragment
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

var serieId=Bundle()
class SeriesFragment : Fragment() {

    companion object {
        fun newInstance() = SeriesFragment()
    }
private lateinit var itemList:List<Parentitemseries>
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
        if(arguments!=null){
       serieId = arguments as Bundle
        Log.d("Tag", "bundle ${serieId}")}

        }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = SeriesViewModel()                      //ViewModelProvider(this).get(SeriesViewModel::class.java)
        val recyclerView2 = viewBinding1.recyclerView2
        val recyclerView1 = viewBinding1.recyclerView1




         itemList =viewModel.parentItemList()

        val itemArrayAdapter = ItemArrayAdapter(R.layout.item_data, itemList)
        val itemArrayAdapter2 = ItemArrayAdapter2(R.layout.item_data2, itemList)
        Log.d("Tag", "viewModel.parentItemList()" + viewModel.parentItemList())
        recyclerView2.setLayoutManager(LinearLayoutManager(context))
        recyclerView2.adapter = itemArrayAdapter2


        Log.d("Tag", "viewModel.parentItemList()" + viewModel.parentItemList())
        recyclerView1.setLayoutManager(LinearLayoutManager(context))
        recyclerView1.adapter = itemArrayAdapter





    }
}
