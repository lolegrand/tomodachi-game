package fr.iut.tomodachi_game.data

import android.util.Log
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

const val NEW_CHARACTER_ID = 0L


/**
 * Classe de personnage
 */
@Entity(tableName = "characters")
data class Character(val name: String,
                     val rarity: Rarity,
                     val obtainDate: Date,
                     val imageUrl : String,
                     val isMain : Boolean,
                     @PrimaryKey(autoGenerate = true) val characterId: Long = NEW_CHARACTER_ID){


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Character

        if (characterId != other.characterId) return false

        return true
    }

    override fun hashCode(): Int {
        return characterId.hashCode()
    }
}