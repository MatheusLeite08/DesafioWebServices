package com.example.desafiowebservices.entities

data class MsgCharacter (
    val data: DataCharacter
)

data class DataCharacter (
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: ArrayList<ResultsCharacter>
)

data class ResultsCharacter (
    val id: Int,
    val description: String
)