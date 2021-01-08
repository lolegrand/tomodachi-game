package fr.iut.tomodachi_game.data

import java.util.*

class Equipement(var nom: String,
                 var rarity: Rarity,
                 var obtainDate: Date,
                 var destroyedDate: Date) {
    var isDestroyed: Boolean = false

    fun updateState(){
        if(destroyedDate < obtainDate)
            isDestroyed = true;
    }


}