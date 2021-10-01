package com.example.myapplicationmobtv

import android.util.Log
import androidx.lifecycle.ViewModel
import java.util.ArrayList

class SeriesViewModel : ViewModel() {
    val id = serieId.getInt("Key")

    fun parentItemList(): List<Parentitemseries> {

Log.d("Tag","id ===== ${id}")
        var itemList: MutableList<Parentitemseries> = ArrayList()
           for (i in 1..10 ) {
                val item = Parentitemseries(

                    feed?.series?.get(id)?.seasons!!

                )
itemList.add(item)

//itemList= (0..10).map { item } as MutableList<Parentitemseries>

            }
            return itemList
        }

//    private fun seriesSeasonList(): SomeData {
//        val childItemList: SomeData = DataGenerator.getData()[0].get(id)
//        Log.d("Tag","DataGenerator.getData()[0].get(id)"+DataGenerator.getData()[0].get(id))
////        Log.d("Tag","ChildItemList"+childItemList[1])
////        Log.d("Tag","ChildItemList size"+childItemList.size)
//
//        return childItemList
//    }

}