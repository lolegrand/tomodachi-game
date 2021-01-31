package fr.iut.tomodachi_game.data

import android.util.Log
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
                @PrimaryKey(autoGenerate = true) val characterId: Long = NEW_CHARACTER_ID)