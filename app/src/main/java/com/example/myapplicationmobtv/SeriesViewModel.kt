package com.example.myapplicationmobtv

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.ArrayList

class SeriesViewModel : ViewModel() {
    private val _series = MutableLiveData<List<Parentitemseries>>()
    val series: LiveData<List<Parentitemseries>> = _series
    val id = serieId.getInt("Key")

    fun parentItemList(): List<Parentitemseries> {

        Log.d("Tag", "id ===== $id")
        val itemList: MutableList<Parentitemseries> = ArrayList()
        for (i in 1..10) {
            val item = Parentitemseries(
                feed?.series?.get(id)?.seasons!!

            )
            itemList.add(item)


        }
        //  _series.value=itemList
        return itemList
        //  return series.value as MutableList<Parentitemseries>
    }

}