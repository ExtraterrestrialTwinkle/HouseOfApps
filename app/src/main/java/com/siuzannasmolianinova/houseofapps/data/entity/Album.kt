package com.siuzannasmolianinova.houseofapps.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Album(
    @Json(name = "userId") val userId: Long,
    @Json(name = "id") val id: Long,
    @Json(name = "title") val title: String
)
