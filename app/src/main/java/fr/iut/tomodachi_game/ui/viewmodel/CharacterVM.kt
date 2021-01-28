package fr.iut.tomodachi_game.ui.viewmodel

import androidx.lifecycle.ViewModel
import fr.iut.tomodachi_game.data.persistance.AppDatabase
import fr.iut.tomodachi_game.data.persistance.CharacterRepository

class CharacterVM(idCharacter: Long): ViewModel() {
    private val  myRepo = CharacterRepository(AppDatabase.getInstance().characterDAO())

    val character =  myRepo.getCharacterWithEquipments(idCharacter)

}