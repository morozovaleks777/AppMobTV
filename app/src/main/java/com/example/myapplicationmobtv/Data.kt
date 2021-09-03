package com.example.myapplicationmobtv

data class Data(
    val id: Int = 0,
    val title: String = "",
    val shortDescription: String = "",
    val thumbnail: String?=""
) : SomeData

data class OtherData(
    val id: Int = 0,
    val title: String = "",
    val shortDescription: String = "",
    val thumbnail: String?=""
) : SomeData

