package fr.iut.tomodachi_game.data

import androidx.room.Embedded
import androidx.room.Relation

data class CharacterWithEquipment (@Embedded val character: Character,
                                   @Relation(parentColumn = "characterId",
                                             entityColumn = "equipmentId")
                                             val equipement: List<Equipment>)