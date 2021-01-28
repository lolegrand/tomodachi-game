package fr.iut.tomodachi_game.ui.viewmodel

import androidx.lifecycle.ViewModel
import fr.iut.tomodachi_game.data.persistance.AppDatabase
import fr.iut.tomodachi_game.data.persistance.EquipmentRepository

class EquipmentVM(idEquipment: Long) : ViewModel() {
    private val myRepo = EquipmentRepository(AppDatabase.getInstance().equipmentDAO())

    val equipment = myRepo.findEquipmentById(idEquipment);

}