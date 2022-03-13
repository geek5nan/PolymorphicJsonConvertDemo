package com.devwu.dto.nondeclare

import com.squareup.moshi.JsonClass
import kotlinx.serialization.Serializable

@JsonClass(generateAdapter = true)
@Serializable
data class GamingRoom2(
  val games: List<Game2>
)
