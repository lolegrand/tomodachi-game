package fr.iut.tomodachi_game.data

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

const val NEW_CHARACTER_ID = 0L

@Entity(tableName = "characters")
data class Character(val nom: String,
                val rarity: Rarity,
                val obtainDate: Date,
                val imageUrl : String,
                val isMain : Boolean,
                @PrimaryKey(autoGenerate = true) val characterId: Long = NEW_CHARACTER_ID) {


    @Ignore var equipements = ArrayList<Equipment>()

    fun toEquip(equipement: Equipment){
        if(equipements.size <= 4)
            equipements.add(equipement)
    }

    fun unequip(equipement: Equipment){
        equipements.remove(equipement)
    }
}