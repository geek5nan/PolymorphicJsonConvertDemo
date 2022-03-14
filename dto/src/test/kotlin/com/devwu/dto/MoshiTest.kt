package com.devwu.dto

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import org.junit.Test

import org.junit.Before

class MoshiTest {
  lateinit var adapter: JsonAdapter<GamingRoom>

  @Before
  fun setup() {
    // Add a polymorphic adapter to moshi.
    val moshi = Moshi.Builder()
      .add(
        PolymorphicJsonAdapterFactory.of(Game::class.java, "type")
          .withSubtype(Zelda::class.java, "zelda")
          .withSubtype(EldenRing::class.java, "elden-ring")
      )
      .build()
    adapter = moshi.adapter(GamingRoom::class.java)
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
    val jsonStr = adapter.toJson(gamingRoom)
    assert(jsonStr == """{"games":[{"type":"elden-ring","title":"艾尔登法环","platforms":["PlayStation","Xbox","PC"],"releaseAt":"2022-02-25"},{"type":"zelda","title":"塞尔达传说：旷野之息","platform":"Nintendo Switch","releaseAt":1488470400}]}""")
    println(jsonStr)
  }

  @Test
  fun testFromJson() {
    val jsonStr = """{"games":[{"type":"elden-ring","title":"艾尔登法环","platforms":["PlayStation","Xbox","PC"],"releaseAt":"2022-02-25"},{"type":"zelda","title":"塞尔达传说：旷野之息","platform":"Nintendo Switch","releaseAt":1488470400}]}"""
    val dto = adapter.fromJson(jsonStr)!!
    dto.games.forEach{
      when(it){
        is Zelda -> { assert(it.platform == "Nintendo Switch")}
        is EldenRing -> { assert(it.platforms.contains("Xbox"))}
        else -> {}
      }
    }
    println(dto)
  }
}
