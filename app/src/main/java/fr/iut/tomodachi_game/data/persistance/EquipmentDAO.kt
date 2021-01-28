package fr.iut.tomodachi_game.data.persistance

import androidx.lifecycle.LiveData
import androidx.room.*
import fr.iut.tomodachi_game.data.Equipment

@Dao
interface EquipmentDAO {


    @Query("SELECT * FROM equipements")
    fun getAllEquipment(): LiveData<List<Equipment>>

    @Query("SELECT * FROM equipements WHERE equipmentId = :id")
    fun findEquipmentById(id: Long): LiveData<Equipment>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEquipment(equipement: Equipment)

    @Insert
    fun insertEquipment(vararg equipement: Equipment)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateEquipment(equipement: Equipment)

    @Delete
    fun deleteEquipment(equipement: Equipment)


}