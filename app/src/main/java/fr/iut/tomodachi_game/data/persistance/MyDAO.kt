package fr.iut.tomodachi_game.data.persistance

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import fr.iut.tomodachi_game.data.Character
import fr.iut.tomodachi_game.data.CharacterWithEquipments
import fr.iut.tomodachi_game.data.Equipement

@Dao
interface MyDAO {


    @Transaction
    @Query("SELECT * FROM characters")
    fun getCharacterWithEquipments() : List<CharacterWithEquipments>


    //Methode for character table
    @Query("SELECT * FROM characters")
    fun getAllCharacter(): List<Character>

    @Query("SELECT * FROM characters WHERE characterId = :id")
    fun findCharacterById(id: Long): Character

    @Insert(onConflict = REPLACE)
    fun insertCharacter(character: Character)

    @Insert
    fun insertCharacter(vararg character: Character)

    @Update(onConflict = REPLACE)
    fun updateCharacter(character: Character)

    @Delete
    fun deleteCharacter(character: Character)

    //Methode for equipment table
    @Query("SELECT * FROM equipements")
    fun getAllEquipment(): List<Equipement>

    @Query("SELECT * FROM equipements WHERE equipementId = :id")
    fun findEquipmentById(id: Long): Equipement

    @Insert(onConflict = REPLACE)
    fun insertEquipment(equipement: Equipement)

    @Insert
    fun insertEquipment(vararg equipement: Equipement)

    @Update(onConflict = REPLACE)
    fun updateEquipment(equipement: Equipement)

    @Delete
    fun deleteEquipment(equipement: Equipement)

}