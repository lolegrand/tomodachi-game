package fr.iut.tomodachi_game.data.persistance

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import fr.iut.tomodachi_game.data.Character
import fr.iut.tomodachi_game.data.CharacterWithEquipment

@Dao
interface CharacterDAO {


    @Query("SELECT * FROM characters")
    fun getAllCharacter(): LiveData<List<Character>>

    @Query("SELECT * FROM characters WHERE characterId = :id")
    fun findCharacterById(id: Long): LiveData<Character>

    @Insert(onConflict = REPLACE)
    fun insertCharacter(character: Character)

    @Insert
    fun insertCharacter(vararg character: Character)

    @Update(onConflict = REPLACE)
    fun updateCharacter(character: Character)

    @Delete
    fun deleteCharacter(character: Character)

    @Transaction
    @Query("SELECT * FROM characters WHERE characterId = :id")
    fun getCharacterWithEquipments(id : Long): LiveData<CharacterWithEquipment>





}