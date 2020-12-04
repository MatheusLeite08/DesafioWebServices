package com.example.desafiowebservices.entities

data class Msg(
    val data: Data
)

data class Data(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: ArrayList<Results>
)

data class Results(
    val id: Int,
    val title: String,
    val issueNumber: Double,
    val description: String,
    val pageCount: Int,
    val dates: ArrayList<Dates>,
    val prices: ArrayList<Prices>,
    val thumbnail: Thumbnail
)

data class Thumbnail(
    val path: String,
    val extension: String
)

data class Prices(
    val type: String,
    val price: Double
)

data class Dates(
    val type: String,
    val date: String
)


