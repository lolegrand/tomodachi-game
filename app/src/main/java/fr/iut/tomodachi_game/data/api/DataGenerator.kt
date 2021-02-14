package fr.iut.tomodachi_game.data.api


import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import fr.iut.tomodachi_game.R
import fr.iut.tomodachi_game.data.Character
import fr.iut.tomodachi_game.data.Equipment
import fr.iut.tomodachi_game.data.Rarity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.ByteArrayOutputStream
import java.io.File
import java.util.*
import java.util.concurrent.Executors

class DataGenerator {
    private val animId = listOf<Int>(
        25879,
        38000,
        21,
        41389,
        41120,
        19815,
        23283,
        39533,
        14467,
        11757,
        33502,
        35968,
        38481,
        38759,
        36999,
        38040,
        37105,
        37349,
        38084
    )

    private val equipmentId = listOf<Int>(
        R.drawable.item__00,
        R.drawable.item__01,
        R.drawable.item__02,
        R.drawable.item__03,
        R.drawable.item__04,
        R.drawable.item__05,
        R.drawable.item__06,
        R.drawable.item__07,
        R.drawable.item__08,
        R.drawable.item__09,
        R.drawable.item__10,
        R.drawable.item__11,
        R.drawable.item__12,
        R.drawable.item__13,
        R.drawable.item__14,
        R.drawable.item__15,
        R.drawable.item__16,
        R.drawable.item__17,
        R.drawable.item__18,
        R.drawable.item__19,
        R.drawable.item__20,
        R.drawable.item__21,
        R.drawable.item__22,
        R.drawable.item__23,
        R.drawable.item__24,
        R.drawable.item__25,
        R.drawable.item__26,
        R.drawable.item__27,
        R.drawable.item__28,
        R.drawable.item__29,
        R.drawable.item__30,
        R.drawable.item__31,
        R.drawable.item__32,
        R.drawable.item__33,
        R.drawable.item__34,
        R.drawable.item__35,
        R.drawable.item__36,
        R.drawable.item__37,
        R.drawable.item__38,
        R.drawable.item__39,
        R.drawable.item__40,
        R.drawable.item__41,
        R.drawable.item__42,
        R.drawable.item__43,
        R.drawable.item__44,
        R.drawable.item__45,
        R.drawable.item__46,
        R.drawable.item__47,
        R.drawable.item__48,
        R.drawable.item__49,
        R.drawable.item__50,
        R.drawable.item__51,
        R.drawable.item__52,
        R.drawable.item__53,
        R.drawable.item__54,
        R.drawable.item__55,
        R.drawable.item__56,
        R.drawable.item__57,
        R.drawable.item__58,
        R.drawable.item__59,
        R.drawable.item__60,
        R.drawable.item__61,
        R.drawable.item__62,
        R.drawable.item__63,
        R.drawable.item__64,
        R.drawable.item__65,
        R.drawable.item__66,
        R.drawable.item__67,
        R.drawable.item__68,
        R.drawable.item__69,
        R.drawable.item__70,
        R.drawable.item__71)

    /**
     * Fonction pour générer tout les personnage
     */
    fun toGenerateCharacters(): List<Character>{
        val url = "https://api.jikan.moe/"
        var characList = listOf<Character>()
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create()).build()

        val service = retrofit.create(CharacterService::class.java)
        val characterRequest = service.listCharacter(animId.random())

        val response = characterRequest.execute()
        val allCharacter = response.body()

        if (allCharacter != null) {
            characList = parseCharacters(allCharacter.characters)
        }

        return characList
    }

    /**
     * Fonction pour parser tout les CharacterAPI en Character et les sélectionners aléatoirement
     */
    private fun parseCharacters(characters : List<CharacterAPI>) : List<Character> {
        val characList = mutableListOf<Character>()
        var isMain = false
        var character : CharacterAPI?

        for (i in 0..4) {
            character = characters.random()
            if (character.role == "Main") {
                isMain = true
            }
            characList.add(Character(character.name, Rarity.randomRarity(), Date(System.currentTimeMillis()), character.image_url, isMain))
            Log.e("DAC", character.name)
        }

        return characList
    }

    /**
     * Fonction pour générer automatiquement des équipements aléatoires
     */
    fun toGenerateEquipments(): List<Equipment>{
        val equipmentList = mutableListOf<Equipment>()
        for (i in 0..4) {
            equipmentList.add(Equipment("Nameless", Rarity.randomRarity(), Date(System.currentTimeMillis()), equipmentId.random(), null))
        }
        return equipmentList
    }


}