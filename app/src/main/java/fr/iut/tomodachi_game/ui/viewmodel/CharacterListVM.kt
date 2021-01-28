package fr.iut.tomodachi_game.ui.viewmodel

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import fr.iut.tomodachi_game.data.Character
import fr.iut.tomodachi_game.data.persistance.AppDatabase
import fr.iut.tomodachi_game.data.persistance.CharacterRepository

class CharacterListVM: ViewModel() {
    private val myRepo = CharacterRepository(AppDatabase.getInstance().characterDAO())

    val characters = myRepo.getAll()


}