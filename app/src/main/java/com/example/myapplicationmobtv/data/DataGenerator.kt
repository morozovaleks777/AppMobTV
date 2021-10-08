package com.example.myapplicationmobtv.data

import android.util.Log
import com.example.myapplicationmobtv.Feed
import com.example.myapplicationmobtv.FeedData
import com.example.myapplicationmobtv.MainActivity.Companion.feed


object DataGenerator {
    private val allDataList = mutableListOf<MutableList<FeedData>>()
    private var movies = feed?.movies
    private var series = feed?.series

    fun getData(): List<MutableList<FeedData>> {
        val dataList = mutableListOf<FeedData>()
        val dataList2 = mutableListOf<FeedData>()


        for (j in 0..20) {
            for (i in 1..series?.size!!) {
Log.d("Tag","series.size ${series!!.size}")
                dataList.add(
                    Feed.Serie(
                        series!!.get(i - 1).seasons,
                        (i - 1).toString(),
                        series?.get(i - 1)?.title!!,
                        series?.get(i - 1)?.shortDescription!!,
                        series!!.get(i - 1).thumbnail
                    )
                )

                dataList2.add(
                    Feed.Movie(
                        (i - 1).toString(),
                        movies?.get(i - 1)?.title!!,
                        movies?.get(i - 1)?.shortDescription!!,
                        movies!!.get(i - 1).thumbnail
                    )
                )

                allDataList.add(dataList)
                allDataList.add(dataList2)
            }
        }

        return allDataList
    }

}