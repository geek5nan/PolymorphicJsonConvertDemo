package com.devwu.dto

import com.squareup.moshi.JsonClass
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@JsonClass(generateAdapter = true)
@SerialName("elden-ring")
data class EldenRing(
  val title: String,
  val platforms: List<String>,
  val releaseAt: String
) : Game
