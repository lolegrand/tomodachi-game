package fr.iut.tomodachi_game.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

const val NEW_EQUIPMENT_ID = 0L


/**
 * Classe d'équipement
 */
@Entity(tableName = "equipments")
data class Equipment(var name: String,
                     val rarity: Rarity,
                     val obtainDate: Date,
                     val imageUrl : Int,
                     var characterOwnerId: Long?,
                     @PrimaryKey(autoGenerate = true) val equipmentId: Long = NEW_EQUIPMENT_ID){


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Equipment

        if (equipmentId != other.equipmentId) return false

        return true
    }

    override fun hashCode(): Int {
        return equipmentId.hashCode()
    }
}