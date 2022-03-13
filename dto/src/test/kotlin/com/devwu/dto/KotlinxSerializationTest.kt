package com.devwu.dto

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import org.junit.Before
import org.junit.Test

class KotlinxSerializationTest {
  lateinit var json: Json

  @Before
  fun setup() {
    // Add a polymorphic serializer to Json.
    json = Json {
      serializersModule = SerializersModule {
        polymorphic(Game::class) {
          subclass(Zelda::class)
          subclass(EldenRing::class)
        }
      }
    }
  }

  @Test
  fun testToJson() {
    val game1 = EldenRing(
      title = "艾尔登法环",
      platforms = listOf("PlayStation", "Xbox", "PC"),
      releaseAt = "2022-02-25"
    )
    val game2 = Zelda(
      title = "塞尔达传说：旷野之息",
      platform = "Nintendo Switch",
      releaseAt = 1488470400
    )
    val gamingRoom = GamingRoom(listOf(game1, game2))
    val jsonStr = json.encodeToString(gamingRoom)
    assert(jsonStr == """{"games":[{"type":"elden-ring","title":"艾尔登法环","platforms":["PlayStation","Xbox","PC"],"releaseAt":"2022-02-25"},{"type":"zelda","title":"塞尔达传说：旷野之息","platform":"Nintendo Switch","releaseAt":1488470400}]}""")
    println(jsonStr)
  }

  @Test
  fun testFromJson() {
    val jsonStr =
      """{"games":[{"type":"elden-ring","title":"艾尔登法环","platforms":["PlayStation","Xbox","PC"],"releaseAt":"2022-02-25"},{"type":"zelda","title":"塞尔达传说：旷野之息","platform":"Nintendo Switch","releaseAt":1488470400}]}"""
    val gamingRoom: GamingRoom = json.decodeFromString(jsonStr)
    gamingRoom.games.forEach {
      when (it) {
        is Zelda -> {
          assert(it.platform == "Nintendo Switch")
        }
        is EldenRing -> {
          assert(it.platforms.contains("Xbox"))
        }
        else -> {}
      }
    }
    println(gamingRoom)
  }
}
