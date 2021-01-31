package fr.iut.tomodachi_game.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

const val NEW_EQUIPMENT_ID = 0L

@Entity(tableName = "equipments")
data class Equipment(val nom: String,
                     val rarity: Rarity,
                     val obtainDate: Date,
                     val imageUrl : Int,
                     var characterId: Long?,
                     @PrimaryKey(autoGenerate = true) val equipmentId: Long = NEW_EQUIPMENT_ID){


    override fun equals(other: Any?): Boolean {
        if(other == null ||
                other !is Equipment ||
                equipmentId != other.equipmentId) return false
        return true
    }

    override fun hashCode(): Int {
        return equipmentId.hashCode()
    }
}