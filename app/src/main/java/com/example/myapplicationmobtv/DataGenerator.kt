package com.example.myapplicationmobtv

object DataGenerator {
    private val allDataList = mutableListOf<MutableList<SomeData>>()
    private var movies = feed?.movies
    private var series = feed?.series

    fun getData(): List<MutableList<SomeData>> {
        val dataList = mutableListOf<SomeData>()
        val dataList2 = mutableListOf<SomeData>()
      // val dataList3 = mutableListOf<SomeData>()

        for (j in 0..20) {
            for (i in 1..series?.size!!) {

                dataList.add(
                    Serie(
                        series!!.get(i-1).seasons,
                        i,
                        series?.get(i - 1)?.title!!,
                        series?.get(i - 1)?.shortDescription!!,
                        series?.get(i - 1)?.thumbnail
                    )
                )

                dataList2.add(
                    Movie(
                        0, movies?.get(i - 1)?.title!!, movies?.get(i - 1)?.shortDescription!!,
                        movies?.get(i - 1)?.thumbnail
                    )
                )

//                for(a in 1..series!!.get(i).seasons.size)
//            dataList4.put("season $a",
//                Season(series!!.get(i-1).seasons.get(a-1).episodes,series!!.get(i-1).seasons.get(a-1).seasonNumber))
                allDataList.add(dataList)
                allDataList.add(dataList2)
              //  allDataList.add(dataList3)
            }
        }

        return allDataList

    }


}