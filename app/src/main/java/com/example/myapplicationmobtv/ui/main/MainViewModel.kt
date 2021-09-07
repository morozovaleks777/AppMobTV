package com.example.myapplicationmobtv.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.myapplicationmobtv.DataGenerator
import com.example.myapplicationmobtv.ParentItem
import com.example.myapplicationmobtv.SomeData
import java.util.*

class MainViewModel : ViewModel() {

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
    private fun childItemList(): List<List<SomeData>> {
        val childItemList: List<List<SomeData>> = DataGenerator.getData()
        Log.d("Tag","ChildItemList"+childItemList[0])
        Log.d("Tag","ChildItemList"+childItemList[1])
        Log.d("Tag","ChildItemList size"+childItemList.size)
        return childItemList
    }



}