package fr.iut.tomodachi_game.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.iut.tomodachi_game.data.persistance.AppDatabase
import fr.iut.tomodachi_game.data.persistance.AppRepository
import kotlinx.coroutines.launch

class EquipmentVM(private val idEquipment: Long) : ViewModel() {

    private val myRepo = AppRepository(AppDatabase.getInstance().appDAO())

    val equipment = myRepo.findEquipmentById(idEquipment)

    fun deleteEquipment(){
        equipment.value?.let { myRepo.deleteEquipment(it) }
    }

    fun updateEquipment() = equipment.value?.let {
        if(it.nom.isBlank())
            false
        else{
            viewModelScope.launch {
                myRepo.updateEquipment(it)
            }
            true
        }
    }


}

