package com.devwu.dto

import com.squareup.moshi.JsonClass
import kotlinx.serialization.Serializable

@JsonClass(generateAdapter = true)
@Serializable
data class GamingRoom(
  val games: List<Game>
)
