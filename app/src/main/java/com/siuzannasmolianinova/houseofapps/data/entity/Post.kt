package com.siuzannasmolianinova.houseofapps.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Post(
    @Json(name = "userId") val userId: Long,
    @Json(name = "id") val postId: Long,
    @Json(name = "title") val title: String,
    @Json(name = "body") val body: String
)
