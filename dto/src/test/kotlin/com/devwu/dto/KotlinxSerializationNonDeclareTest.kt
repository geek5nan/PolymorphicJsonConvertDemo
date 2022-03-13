package com.devwu.dto

import com.devwu.dto.nondeclare.Game2
import com.devwu.dto.nondeclare.GamingRoom2
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Test

class KotlinxSerializationNonDeclareTest {

  @Test
  fun testToJson() {
    val game1 = Game2.EldenRing2(
      title = "艾尔登法环",
      platforms = listOf("PlayStation", "Xbox", "PC"),
      releaseAt = "2022-02-25"
    )
    val game2 = Game2.Zelda2(
      title = "塞尔达传说：旷野之息",
      platform = "Nintendo Switch",
      releaseAt = 1488470400
    )
    val gamingRoom = GamingRoom2(listOf(game1, game2))
    val jsonStr = Json.encodeToString(gamingRoom)
    assert(jsonStr == """{"games":[{"type":"elden-ring","title":"艾尔登法环","platforms":["PlayStation","Xbox","PC"],"releaseAt":"2022-02-25"},{"type":"zelda","title":"塞尔达传说：旷野之息","platform":"Nintendo Switch","releaseAt":1488470400}]}""")
    println(jsonStr)
  }

  @Test
  fun testFromJson() {
    val jsonStr =
      """{"games":[{"type":"elden-ring","title":"艾尔登法环","platforms":["PlayStation","Xbox","PC"],"releaseAt":"2022-02-25"},{"type":"zelda","title":"塞尔达传说：旷野之息","platform":"Nintendo Switch","releaseAt":1488470400}]}"""
    val gamingRoom: GamingRoom2 = Json.decodeFromString(jsonStr)
    gamingRoom.games.forEach {
      when (it) {
        is Game2.Zelda2 -> {
          assert(it.platform == "Nintendo Switch")
        }
        is Game2.EldenRing2 -> {
          assert(it.platforms.contains("Xbox"))
        }
        else -> {}
      }
    }
    println(gamingRoom)
  }
}
