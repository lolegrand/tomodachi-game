package fr.iut.tomodachi_game.data.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Classe permettant la requête à l'api jikan
 */
class CharacterAPI(
    val image_url: String,
    val name: String,
    val role: String
)

/**
 * Classe qui comporte tout les CharacterAPI de la requête
 */
class CharactersResponse(val  characters : List<CharacterAPI>)


/**
 * Interface utilisé par retrofite pour faire la requête à l'api
 */
interface CharacterService {
    @GET("v3/anime/{id}/characters_staff")
    fun listCharacter(@Path("id") animeId : Int): Call<CharactersResponse>
}

