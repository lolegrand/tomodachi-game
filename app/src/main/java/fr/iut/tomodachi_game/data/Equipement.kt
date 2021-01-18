package fr.iut.tomodachi_game.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

const val NEW_EQUIPMENT_ID = 0L

@Entity(tableName = "equipements")
data class Equipement(var nom: String,
                 var rarity: Rarity,
                 var obtainDate: Date,
                 var destroyedDate: Date,
                 var equipedCharacterId: Long,
                 @PrimaryKey(autoGenerate = true) val equipementId: Long = NEW_EQUIPMENT_ID
                 ) {

    @Ignore var isDestroyed: Boolean = false

    fun updateState(){
        if(destroyedDate < obtainDate)
            isDestroyed = true;
    }


}