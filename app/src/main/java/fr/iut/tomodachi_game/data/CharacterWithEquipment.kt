package fr.iut.tomodachi_game.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Embedded
import androidx.room.Relation

data class CharacterWithEquipment (@Embedded val character: Character,
                                   @Relation(parentColumn = "characterId",
                                             entityColumn = "equipmentId")
                                             val equipments: MutableList<Equipment>){

    init {
        for (eq in equipments){
            if(eq.characterId == null)
                equipments.remove(eq)
        }
    }

    fun toEquip(equipment: Equipment){

        Log.e("DAC", "on "+equipment.characterId+" to " + equipment.equipmentId)
        if(equipment.characterId == character.characterId){
            Log.e("DAC", "Eq on")
            return
        }
        if(equipments.size >= 4){
            Log.e("DAC", "Eq on2")
            return
        }

        Log.e("DAC", "Eq added")
        equipment.characterId = character.characterId

        equipments.add(equipment)
    }


    fun unequipt(equipment: Equipment){
        if(!equipments.contains(equipment)){
            return
        }
        equipments.remove(equipment)
        equipment.characterId = 0
    }



}