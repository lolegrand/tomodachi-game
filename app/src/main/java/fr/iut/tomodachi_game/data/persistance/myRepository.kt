package fr.iut.tomodachi_game.data.persistance

import fr.iut.tomodachi_game.data.Character
import fr.iut.tomodachi_game.data.Equipment
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

val IO_EXECUTOR: ExecutorService = Executors.newSingleThreadExecutor()

class CharacterRepository(private val characterDao: CharacterDAO) {
    fun insert(character: Character) = IO_EXECUTOR.execute{characterDao.insertCharacter(character) }
    fun delete(character: Character) = IO_EXECUTOR.execute{characterDao.deleteCharacter(character) }
    fun update(character: Character) = IO_EXECUTOR.execute{characterDao.updateCharacter(character) }

    fun findById(characterId: Long) = characterDao.findCharacterById(characterId)
    fun getAll() = characterDao.getAllCharacter()

    fun getCharacterWithEquipments(id : Long) = characterDao.getCharacterWithEquipments(id)
}

class EquipmentRepository(private val equipmentDAO: EquipmentDAO){
    fun insert(equipment: Equipment) = IO_EXECUTOR.execute { equipmentDAO.insertEquipment(equipment) }
    fun delete(equipment: Equipment) = IO_EXECUTOR.execute { equipmentDAO.deleteEquipment(equipment) }
    fun update(equipment: Equipment) = IO_EXECUTOR.execute { equipmentDAO.updateEquipment(equipment) }

    fun findEquipmentById(id : Long) = equipmentDAO.findEquipmentById(id)
    fun getAll() =  equipmentDAO.getAllEquipment()

}