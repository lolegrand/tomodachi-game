package fr.iut.tomodachi_game.data.persistance

import fr.iut.tomodachi_game.data.Character
import fr.iut.tomodachi_game.data.Equipment
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

val IO_EXECUTOR: ExecutorService = Executors.newSingleThreadExecutor()

class AppRepository(private val appDao: AppDAO) {
    fun insertCharacter(character: Character) = IO_EXECUTOR.execute{appDao.insertCharacter(character) }
    fun deleteCharacter(character: Character) = IO_EXECUTOR.execute{appDao.deleteCharacter(character) }
    fun updateCharacter(character: Character) = IO_EXECUTOR.execute{appDao.updateCharacter(character) }

    fun findCharacterById(characterId: Long) = appDao.findCharacterById(characterId)
    fun getAllCharacter() = appDao.getAllCharacter()

    fun getCharacterWithEquipments(id : Long) = appDao.getCharacterWithEquipments(id)

    fun insertEquipment(equipment: Equipment) = IO_EXECUTOR.execute { appDao.insertEquipment(equipment) }
    fun deleteEquipment(equipment: Equipment) = IO_EXECUTOR.execute { appDao.deleteEquipment(equipment) }
    fun updateEquipment(equipment: Equipment) = IO_EXECUTOR.execute { appDao.updateEquipment(equipment) }

    fun findEquipmentById(id : Long) = appDao.findEquipmentById(id)
    fun getAllEquipment() =  appDao.getAllEquipment()



}

