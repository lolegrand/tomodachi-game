package fr.iut.tomodachi_game.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

const val NEW_EQUIPMENT_ID = 0L

@Entity(tableName = "equipements")
data class Equipment(val nom: String,
                 val rarity: Rarity,
                 val obtainDate: Date,
                 val imageUrl : String,
                 @PrimaryKey(autoGenerate = true) val equipmentId: Long = NEW_EQUIPMENT_ID) {


}