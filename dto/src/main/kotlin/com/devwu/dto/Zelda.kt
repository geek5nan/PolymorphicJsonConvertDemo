package com.devwu.dto

import com.squareup.moshi.JsonClass
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("zelda")
@JsonClass(generateAdapter = true)
data class Zelda(
  val title: String,
  val platform: String,
  val releaseAt: Long
) : Game
