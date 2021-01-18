package fr.iut.tomodachi_game.data.persistance.converter

import androidx.room.TypeConverter
import fr.iut.tomodachi_game.data.Rarity

fun Int.toRarity() = enumValues<Rarity>()[this]

class RarityToIntConverter {

    @TypeConverter
    fun fromInt(ordinal: Int) = ordinal.toRarity()

    @TypeConverter
    fun toOrdinal(rarity: Rarity) = rarity.ordinal

}