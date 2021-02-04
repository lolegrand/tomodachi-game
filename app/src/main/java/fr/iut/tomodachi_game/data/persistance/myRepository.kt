package fr.iut.tomodachi_game.data.persistance

import fr.iut.tomodachi_game.R
import fr.iut.tomodachi_game.data.Character
import fr.iut.tomodachi_game.data.Equipment
import fr.iut.tomodachi_game.data.Rarity
import fr.iut.tomodachi_game.data.api.DataGenerator
import java.util.*
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

    fun findEquipmentByIdSync(id: Long) = appDao.findEquipmentByIdSync(id)
    fun findEquipmentById(id : Long) = appDao.findEquipmentById(id)
    fun getAllEquipment() = appDao.getAllEquipment()

    fun toGenerateCharactersAndEquipment() = DataGenerator().toGenerateCharacters()

}

class AppStub() {

    private val myRepo = AppRepository(AppDatabase.getInstance().appDAO())

    fun toPopulateDB(){
        myRepo.insertCharacter(Character(
                "Yuzaki, Nasa",
                Rarity.MYTHICS,
                Date(222222),
                "https://cdn.myanimelist.net/images/characters/7/395696.jpg",
                true))
        myRepo.insertCharacter(Character(
                "Yuzaki, Tsukasa",
                Rarity.LEGENDARY,
                Date(22222222),
                "https://cdn.myanimelist.net/images/characters/7/378069.jpg",
                true))
        myRepo.insertCharacter(Character(
                "Aurora",
                Rarity.COMMON,
                Date(2222222),
                "https://cdn.myanimelist.net/images/characters/4/402929.jpg",
                false))

        myRepo.insertEquipment(Equipment("Bouclier en fer",Rarity.COMMON,Date(555555), R.drawable.item__25, null))
        myRepo.insertEquipment(Equipment("Epée en fer",Rarity.COMMON,Date(555555), R.drawable.item__00, null))


    }
}

