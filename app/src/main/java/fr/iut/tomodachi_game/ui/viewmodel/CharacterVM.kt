package fr.iut.tomodachi_game.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import fr.iut.tomodachi_game.data.Character
import fr.iut.tomodachi_game.data.Equipment
import fr.iut.tomodachi_game.data.persistance.AppDatabase
import fr.iut.tomodachi_game.data.persistance.AppRepository

class CharacterVM(idCharacter: Long): ViewModel() {
    private val myRepo = AppRepository(AppDatabase.getInstance().appDAO())

    val characterWithEquipment = myRepo.getCharacterWithEquipments(idCharacter)

    private val _equipments = MutableLiveData<MutableList<Equipment>>()

    val equipments : LiveData<MutableList<Equipment>>
        get() = _equipments


    init {
        characterWithEquipment.observeForever {
            if(it != null){
                _equipments.value = it.equipments
            }
        }
    }

    fun toEquip(equipmentId: Long){
        val equipment = myRepo.findEquipmentById(equipmentId)

        equipment.observeForever {
            characterWithEquipment.value?.toEquip(it)
            equipments.value = equipments.value
        }

    }

    fun unequip(equipmentId: Long){
    }


}