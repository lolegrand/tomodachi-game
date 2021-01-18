package fr.iut.tomodachi_game.data

import androidx.room.Embedded
import androidx.room.Relation
import fr.iut.tomodachi_game.data.Character
import fr.iut.tomodachi_game.data.Equipement

data class CharacterWithEquipments (
    @Embedded val character: Character,

    @Relation(
            parentColumn = "characterId",
            entityColumn =  "equipedCharacterId"
    )

    val equipments: List<Equipement>
)