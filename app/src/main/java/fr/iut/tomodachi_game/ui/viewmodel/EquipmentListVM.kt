package fr.iut.tomodachi_game.ui.viewmodel

import androidx.lifecycle.ViewModel
import fr.iut.tomodachi_game.data.persistance.AppDatabase
import fr.iut.tomodachi_game.data.persistance.AppRepository

class EquipmentListVM : ViewModel(){
    private val myRepo = AppRepository(AppDatabase.getInstance().appDAO())

    val equipments = myRepo.getAllEquipment()

}