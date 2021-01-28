package fr.iut.tomodachi_game.ui.viewmodel

import androidx.lifecycle.ViewModel
import fr.iut.tomodachi_game.data.persistance.AppDatabase
import fr.iut.tomodachi_game.data.persistance.EquipmentRepository

class EquipmentListVM : ViewModel(){
    private val myRepo = EquipmentRepository(AppDatabase.getInstance().equipmentDAO())

    val equipments = myRepo.getAll()

}