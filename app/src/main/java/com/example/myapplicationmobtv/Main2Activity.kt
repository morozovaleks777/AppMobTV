package com.example.myapplicationmobtv


import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import java.io.IOException
import java.nio.charset.Charset
import java.util.*

import androidx.lifecycle.ViewModelProviders

var feed: Feed?=null
class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val ParentRecyclerViewItem = findViewById<RecyclerView>(
            R.id.parent_recyclerview
        )

        val obj = getJSONFromAssets()
        val jsonAdapter: JsonAdapter<Feed> = Moshi.Builder().build().adapter(Feed::class.java)
        feed = obj?.let { jsonAdapter.fromJson(it) }

        // Initialise the Linear layout manager
        val layoutManager = LinearLayoutManager(
            this
        )

        // Pass the arguments
        // to the parentItemAdapter.
        // These arguments are passed
        // using a method ParentItemList()
        val parentItemAdapter = ParentItemAdapter(
            ParentItemList()
        )

        // Set the layout manager
        // and adapter for items
        // of the parent recyclerview
        ParentRecyclerViewItem.adapter = parentItemAdapter
        ParentRecyclerViewItem.layoutManager = layoutManager

        val titleTextView=findViewById<TextView>(R.id.titleTextView)
        val textView=findViewById<TextView>(R.id.textView)
        titleTextView.text=feed?.providerName
       textView.text=feed?.lastUpdated
       Log.d("Tag","feed?.lastUpdated + "+feed?.lastUpdated)


    }

    private fun ParentItemList(): List<ParentItem> {
        val itemList: MutableList<ParentItem> = ArrayList()
        val item = ParentItem(
            "Title 1",
            ChildItemList()[0]
        )
        itemList.add(item)
        val item1 = ParentItem(
            "Title 2",
            ChildItemList()[1]
        )
        itemList.add(item1)

        return itemList
    }

    // Method to pass the arguments
    // for the elements
    // of child RecyclerView
    private fun ChildItemList(): List<List<SomeData>> {
        val ChildItemList: List<List<SomeData>> = DataGenerator.getData()
        Log.d("Tag","ChildItemList"+ChildItemList[0])
        Log.d("Tag","ChildItemList"+ChildItemList[1])
        Log.d("Tag","ChildItemList size"+ChildItemList.size)
        return ChildItemList
    }
    private fun getJSONFromAssets(): String? {

        var json: String?
        val charset: Charset = Charsets.UTF_8
        try {
            val myFeedJSONFile =assets?.open("feed.json")
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

