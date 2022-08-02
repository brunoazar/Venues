package com.example.venueapp

data class Photo (
    val resultImg: List<ResultImg>
)

data class ResultImg(
    val prefix: String,
    val suffix: String
)