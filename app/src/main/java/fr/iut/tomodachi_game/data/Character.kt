package fr.iut.tomodachi_game.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

const val NEW_CHARACTER_ID = 0L

@Entity(tableName = "characters")
data class Character(var nom: String,
                var rarity: Rarity,
                var obtainDate: Date,
                var imageUrl : String,
                @PrimaryKey(autoGenerate = true) val characterId: Long = NEW_CHARACTER_ID) {


    @Ignore
    var equipements = ArrayList<Equipement>()

    fun toEquip(equipement: Equipement){
        if(equipements.size <= 5)
            equipements.add(equipement)
    }

    fun unequip(equipement: Equipement){
        equipements.remove(equipement)
    }
}