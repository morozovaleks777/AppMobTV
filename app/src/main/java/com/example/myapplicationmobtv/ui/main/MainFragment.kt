package com.example.myapplicationmobtv.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.myapplicationmobtv.*
import com.example.myapplicationmobtv.databinding.MainFragmentBinding
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.android.synthetic.main.main_fragment.*
import java.io.IOException
import java.nio.charset.Charset

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private val viewBinding: MainFragmentBinding by viewBinding()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(R.layout.main_fragment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val ParentRecyclerViewItem = view.findViewById<RecyclerView>(R.id.parent_recyclerview)
        val obj = getJSONFromAssets()
        val jsonAdapter: JsonAdapter<Feed> = Moshi.Builder().build().adapter(Feed::class.java)
        feed = obj?.let { jsonAdapter.fromJson(it) }

        // Initialise the Linear layout manager
        val layoutManager = LinearLayoutManager(
            context
        )

        // Pass the arguments
        // to the parentItemAdapter.
        // These arguments are passed
        // using a method ParentItemList()
        val parentItemAdapter = ParentItemAdapter(
            viewModel.ParentItemList()

        )

        // Set the layout manager
        // and adapter for items
        // of the parent recyclerview
        ParentRecyclerViewItem.adapter = parentItemAdapter
        ParentRecyclerViewItem.layoutManager = layoutManager

       // val titleTextView=view.findViewById<TextView>(R.id.titleTextView)
        val titleTextView=viewBinding.titleTextView
        val textView=viewBinding.textView
        titleTextView.text=feed?.providerName
        textView.text=feed?.lastUpdated
        Log.d("Tag","feed?.lastUpdated + "+ com.example.myapplicationmobtv.feed?.lastUpdated)



    }
//    override fun onClickItem(data: SomeData) {
//
//        when (data) {
//            is Data -> Toast.makeText(context, "Click Data ${data.id}", Toast.LENGTH_LONG).show()
//
//
//            is OtherData -> Toast.makeText(
//                context,
//                "Click Other Data${data.id}",
//                Toast.LENGTH_LONG
//            ).show()
//        }
//    }
    fun getJSONFromAssets(): String? {

        var json: String?=null
        val charset: Charset = Charsets.UTF_8
        try {
            val myFeedJSONFile =context?.assets?.open("feed.json")
            val size = myFeedJSONFile?.available()
            val buffer = size?.let { ByteArray(it) }
            myFeedJSONFile?.read(buffer)
            myFeedJSONFile?.close()
            json = buffer?.let { String(it, charset) }
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }

}