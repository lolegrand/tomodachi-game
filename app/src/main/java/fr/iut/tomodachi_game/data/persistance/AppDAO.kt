package fr.iut.tomodachi_game.data.persistance

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import fr.iut.tomodachi_game.data.Character
import fr.iut.tomodachi_game.data.CharacterWithEquipment
import fr.iut.tomodachi_game.data.Equipment

@Dao
interface AppDAO {

    @Query("SELECT * FROM equipments")
    fun getAllEquipment(): LiveData<List<Equipment>>

    @Query("SELECT * FROM equipments WHERE equipmentId = :id")
    fun findEquipmentById(id: Long): LiveData<Equipment>

    @Query("SELECT * FROM equipments WHERE equipmentId = :id")
    fun findEquipmentByIdSync(id: Long): Equipment

    @Insert(onConflict = REPLACE)
    fun insertEquipment(equipment: Equipment)

    @Insert
    fun insertEquipment(vararg equipment: Equipment)

    @Update(onConflict = REPLACE)
    fun updateEquipment(equipment: Equipment)

    @Delete
    fun deleteEquipment(equipment: Equipment)

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