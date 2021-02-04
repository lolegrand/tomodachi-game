package fr.iut.tomodachi_game.ui.viewmodel

import androidx.lifecycle.ViewModel
import fr.iut.tomodachi_game.data.Equipment
import fr.iut.tomodachi_game.data.persistance.AppDatabase
import fr.iut.tomodachi_game.data.persistance.AppRepository
import kotlinx.coroutines.*

class CharacterVM(idCharacter: Long): ViewModel() {
    private val myRepo = AppRepository(AppDatabase.getInstance().appDAO())

    val characterWithEquipment = myRepo.getCharacterWithEquipments(idCharacter)


    fun deleteCharacter(){
        characterWithEquipment.value?.character?.let { myRepo.deleteCharacter(it) }
    }

    fun toEquip(idEquipment: Long){

        lateinit var equipment: Equipment
        val job: Job = GlobalScope.launch {
            val equipmentSync = async {getEquipment(idEquipment)}
            equipment = equipmentSync.await()
        }
        runBlocking { job.join() }
        characterWithEquipment.value?.toEquip(equipment)

        myRepo.updateEquipment(equipment)
    }

    suspend fun getEquipment(idEquipment: Long): Equipment{
        return myRepo.findEquipmentByIdSync(idEquipment)
    }

    fun unequip(equipmentId: Long){

    }


}