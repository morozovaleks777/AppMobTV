package com.example.myapplicationmobtv.ui.main

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationmobtv.DataGenerator
import com.example.myapplicationmobtv.ParentItem
import com.example.myapplicationmobtv.SomeData
import java.io.IOException
import java.nio.charset.Charset
import java.util.ArrayList

class MainViewModel : ViewModel() {

    fun ParentItemList(): List<ParentItem> {
        val itemList: MutableList<ParentItem> = ArrayList()
        val item = ParentItem(
            "Movies",
            ChildItemList()[0]
        )
        itemList.add(item)
        val item1 = ParentItem(
            "Series",
            ChildItemList()[1]
        )
        itemList.add(item1)

        return itemList
    }
    private fun ChildItemList(): List<List<SomeData>> {
        val ChildItemList: List<List<SomeData>> = DataGenerator.getData()
        Log.d("Tag","ChildItemList"+ChildItemList[0])
        Log.d("Tag","ChildItemList"+ChildItemList[1])
        Log.d("Tag","ChildItemList size"+ChildItemList.size)
        return ChildItemList
    }

}