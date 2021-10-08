package com.example.myapplicationmobtv.series_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.myapplicationmobtv.MainActivity.Companion.feed
import com.example.myapplicationmobtv.Series
import com.example.myapplicationmobtv.series_screen.SeriesFragment.Companion.serieId


class SeriesViewModel : ViewModel() {


    val id = serieId.getInt("Key")

    fun getSeriesList(): MutableList<Series> {

        Log.d("Tag", "id ===== $id")
        val itemList: MutableList<Series> = ArrayList()
        for (i in 1..10) {
            val item = Series(
                feed?.series?.get(id)?.seasons

            )
            itemList.add(item)


        }

        return itemList

    }

}