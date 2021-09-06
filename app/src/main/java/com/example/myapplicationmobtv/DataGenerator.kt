package com.example.myapplicationmobtv

object DataGenerator {
    val allDataList = mutableListOf<MutableList<SomeData>>()
    var movies = feed?.movies
    var series = feed?.series

    fun getData(): List<MutableList<SomeData>> {
        val dataList = mutableListOf<SomeData>()
        val dataList2 = mutableListOf<SomeData>()

        for (j in 0..20) {
            for (i in 1..series?.size!!) {

                dataList.add(
                    Data(
                        i,
                        series?.get(i - 1)?.title!!,
                        series?.get(i - 1)?.shortDescription!!,
                        series?.get(i - 1)?.thumbnail
                    )
                )
                dataList2.add(
                    OtherData(
                        0, movies?.get(i - 1)?.title!!, movies?.get(i - 1)?.shortDescription!!,
                        movies?.get(i - 1)?.thumbnail
                    )
                )

                allDataList.add(dataList)
                allDataList.add(dataList2)
            }
        }

        return allDataList

    }
//fun getData(): List<SomeData> {
//    val dataList = mutableListOf<SomeData>()
//
//        for (j in 0..1) {
//            if (j % 2 == 0) {
//                for (i in 1..series?.size!!) {
//                dataList.add(
//                    Data(
//                        i,
//                        series?.get(i-1)?.title!!,
//                        series?.get(i-1)?.shortDescription!!,
//                        series?.get(i - 1)?.thumbnail
//                    )
//                )
//            }} else { for (i in 1..movies?.size!!) {
//                dataList.add(
//                    OtherData(
//                        i, movies?.get(i-1)?.title!!, movies?.get(i-1)?.shortDescription!!,
//                        movies?.get(i-1)?.thumbnail
//                    )
//                )
//            }}
//        }

    //   return dataList
//}


}