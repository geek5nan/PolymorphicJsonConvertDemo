package com.devwu.dto.nondeclare

import com.squareup.moshi.JsonClass
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class Game2 {
  @Serializable
  @SerialName("zelda")
  @JsonClass(generateAdapter = true)
  data class Zelda2(
    val title: String,
    val platform: String,
    val releaseAt: Long
  ) : Game2()

  @Serializable
  @JsonClass(generateAdapter = true)
  @SerialName("elden-ring")
  data class EldenRing2(
    val title: String,
    val platforms: List<String>,
    val releaseAt: String
  ) : Game2()
}

