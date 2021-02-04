package fr.iut.tomodachi_game.data.api


import android.util.Log
import androidx.lifecycle.LiveData
import fr.iut.tomodachi_game.data.Character
import fr.iut.tomodachi_game.data.Rarity
import fr.iut.tomodachi_game.data.persistance.AppDatabase
import fr.iut.tomodachi_game.data.persistance.AppRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import java.util.concurrent.Executors
import kotlin.random.Random

class DataGenerator {

    fun toGenerateCharacters(){
        val url = "https://api.jikan.moe/"
        val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(MoshiConverterFactory.create())
                .callbackExecutor(Executors.newSingleThreadExecutor()).build()

        val service = retrofit.create(CharacterService::class.java)

        //L'application n'est limité qu'à l'anime one piece pour le moment (One Piece = 21)
        val characterRequest = service.listCharacter(21)

        characterRequest.enqueue(object : Callback<CharactersResponse> {

            override fun onResponse(
                    call: Call<CharactersResponse>,
                    response: Response<CharactersResponse>
            ) {
                Log.e("DAC", "Request granted")
                val allCharacter = response.body()
                if (allCharacter != null){

                    toSaveCharacters(allCharacter.characters as MutableList<CharacterAPI>)
                }else{
                    Log.e("DAC", "404")
                }
            }
            override fun onFailure(call: Call<CharactersResponse>, t: Throwable) {
                Log.e("DAC", "Request failed")
            }
        })

    }

    private fun toSaveCharacters(characters : MutableList<CharacterAPI>) {
        Log.e("DAC", "Body isn't Empty")

        val myRepo = AppRepository(AppDatabase.getInstance().appDAO())
        var isMain = false
        var character : CharacterAPI?

        for (i in 0..5) {
            character = characters.random()
            if (character.role == "Main") isMain = true
            myRepo.insertCharacter(Character(character.name, Rarity.COMMON, Date(System.currentTimeMillis()), character.image_url, isMain))
            Log.e("DAC", character.name)

        }
    }


    private fun toGenerateEquipment(){


    }


}