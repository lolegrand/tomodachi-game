package fr.iut.tomodachi_game.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.iut.tomodachi_game.data.Character
import fr.iut.tomodachi_game.data.Equipment
import fr.iut.tomodachi_game.data.persistance.AppDatabase
import fr.iut.tomodachi_game.data.persistance.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class GenerateDataVM() : ViewModel() {
    private val myRepo = AppRepository(AppDatabase.getInstance().appDAO())

    lateinit var listNewEquipments: List<Equipment>
    lateinit var listNewCharacters: List<Character>




    fun toGenerateData() = viewModelScope.launch(Dispatchers.IO) {
        val equipmentRequest = async { myRepo.toGenerateEquipments() }
        val characterRequest = async { myRepo.toGenerateCharacters() }
        listNewCharacters = characterRequest.await()
        listNewEquipments = equipmentRequest.await()

        launch {  listNewCharacters.forEach{myRepo.insertCharacter(it)}}

        launch {  listNewEquipments.forEach{myRepo.insertEquipment(it)}}
    }


}