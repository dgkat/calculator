package com.dgkatoudis.calculator.data.responses

data class Conversion(
    val date: String,
    val info: Info,
    val query: Query,
    val result: Double,
    val success: Boolean
)