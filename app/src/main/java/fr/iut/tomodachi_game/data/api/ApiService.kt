package fr.iut.tomodachi_game.data.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

class CharacterAPI(
    val image_url: String,
    val name: String,
    val role: String
)

class CharactersResponse(val  characters : List<CharacterAPI>)

interface CharacterService {
    @GET("v3/anime/{id}/characters_staff")
    fun listCharacter(@Path("id") animeId : Int): Call<CharactersResponse>
}

