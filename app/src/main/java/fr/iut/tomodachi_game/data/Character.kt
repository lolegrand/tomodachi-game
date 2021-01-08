package fr.iut.tomodachi_game.data

import java.util.*

class Character(var nom: String, var rarity: Rarity,  var obtainDate: Date) {
    var equipements = ArrayList<Equipement>()




    fun toEquip(equipement: Equipement){
        if(equipements.size <= 5)
            equipements.add(equipement)
    }

    fun unequip(equipement: Equipement){
        equipements.remove(equipement)
    }


}