package fr.iut.tomodachi_game.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Embedded
import androidx.room.Relation

data class CharacterWithEquipment (@Embedded val character: Character,
                                   @Relation(parentColumn = "characterId",
                                             entityColumn = "characterOwnerId")
                                             var equipments: MutableList<Equipment>){



    fun toEquip(equipment: Equipment){
        if(equipment.characterOwnerId == character.characterId)
            return

        if(equipments.size >= 4)
            return

        equipment.characterOwnerId = character.characterId
        equipments.add(equipment)
    }


    fun unequipt(equipment: Equipment){
        if(!equipments.contains(equipment)){
            return
        }
        equipments.remove(equipment)
        equipment.characterOwnerId = 0
    }



}