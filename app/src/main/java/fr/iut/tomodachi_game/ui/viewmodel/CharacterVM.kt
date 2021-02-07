package fr.iut.tomodachi_game.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.iut.tomodachi_game.data.Equipment
import fr.iut.tomodachi_game.data.persistance.AppDatabase
import fr.iut.tomodachi_game.data.persistance.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class CharacterVM(idCharacter: Long): ViewModel() {
    private val myRepo = AppRepository(AppDatabase.getInstance().appDAO())

    val characterWithEquipment = myRepo.getCharacterWithEquipments(idCharacter)

    fun deleteCharacter(){
        characterWithEquipment.value?.character?.let { myRepo.deleteCharacter(it) }
        characterWithEquipment.value?.equipments?.forEach{
            it.characterOwnerId = null
            myRepo.updateEquipment(it)
        }
    }

    fun toEquip(idEquipment: Long) = viewModelScope.launch(Dispatchers.IO) {
        val equipmentSync = async { getEquipment(idEquipment) }
        val equipment = equipmentSync.await()
        characterWithEquipment.value?.toEquip(equipment)
        myRepo.updateEquipment(equipment)
    }

    suspend fun getEquipment(idEquipment: Long): Equipment{
        return myRepo.findEquipmentByIdSync(idEquipment)
    }

    fun unequip(equipment: Equipment){
        characterWithEquipment.value?.unequipt(equipment)
        myRepo.updateEquipment(equipment)
    }


}