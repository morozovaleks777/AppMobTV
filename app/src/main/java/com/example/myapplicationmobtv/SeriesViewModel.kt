package com.example.myapplicationmobtv

import android.util.Log
import androidx.lifecycle.ViewModel
import java.util.ArrayList

class SeriesViewModel : ViewModel() {
    val id = serieId.getInt("key")

    fun parentItemList(): List<Parentitemseries> {
       val id = serieId.getInt("key")

        val itemList: MutableList<Parentitemseries> = ArrayList()
           for (i in 0..id) {
                val item = Parentitemseries(

                    feed?.series?.get(id)?.seasons!!
                           // series!!.get(i-1).seasons.get(0).seasonNumber
                )
              //  Log.d("Tag", " childItemList().component1()[i] ${childItemList()}")
                itemList.add(item)


            }
            return itemList
        }

    private fun seriesSeasonList(): SomeData {
        val childItemList: SomeData = DataGenerator.getData()[0].get(id)
        Log.d("Tag","DataGenerator.getData()[0].get(id)"+DataGenerator.getData()[0].get(id))
//        Log.d("Tag","ChildItemList"+childItemList[1])
//        Log.d("Tag","ChildItemList size"+childItemList.size)

        return childItemList
    }
}