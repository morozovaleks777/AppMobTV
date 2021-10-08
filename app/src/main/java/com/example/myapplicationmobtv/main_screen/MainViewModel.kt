package com.example.myapplicationmobtv.main_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.myapplicationmobtv.FeedData
import com.example.myapplicationmobtv.data.DataGenerator
import com.example.myapplicationmobtv.ParentItem



class MainViewModel : ViewModel(){

    fun parentItemList(): List<ParentItem> {
        val itemList: MutableList<ParentItem> = ArrayList()
        val item = ParentItem(
            "Movies",
            childItemList()[0]
        )
        itemList.add(item)
        val item1 = ParentItem(
            "Series",
            childItemList()[1]
        )
        itemList.add(item1)

        return itemList
    }
    private fun childItemList(): List<List<FeedData>> {
        val childItemList: List<List<FeedData>> = DataGenerator.getData()
        Log.d("Tag","ChildItemList"+childItemList[0])
        Log.d("Tag","ChildItemList"+childItemList[1])
        Log.d("Tag","ChildItemList size"+childItemList.size)
        return childItemList
    }




}