package fr.iut.tomodachi_game.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Embedded
import androidx.room.Relation


/**
 * Classe pour représenter le one to many dans la base de donné room
 */
data class CharacterWithEquipment (@Embedded val character: Character,
                                   @Relation(parentColumn = "characterId",
                                             entityColumn = "characterOwnerId")
                                             var equipments: MutableList<Equipment>){


    /**
     * Fonction pour ajouter un équipement au personnage
     */
    fun toEquip(equipment: Equipment){
        if(equipment.characterOwnerId == character.characterId)
            return

        equipment.characterOwnerId = character.characterId
        equipments.add(equipment)
    }

    /**
     * Fonction pour déséquiper un personnage
     */
    fun unequipt(equipment: Equipment){
        if(!equipments.contains(equipment)){
            return
        }
        equipments.remove(equipment)
        equipment.characterOwnerId = 0
    }



}