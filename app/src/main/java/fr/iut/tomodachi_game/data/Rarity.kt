package fr.iut.tomodachi_game.data

import kotlin.random.Random

/**
 * Enumération des rareters
 */
enum class Rarity {
    COMMON,
    UNCOMMON,
    RARE,
    MYTHICS,
    LEGENDARY;


    companion object{
        fun randomRarity(): Rarity=
            when(Random.nextInt(0, 100)){
                in 0..49 ->  COMMON
                in 50..74 ->  UNCOMMON
                in 75..89 ->  RARE
                in 90..96 ->  MYTHICS
                in 97..100 ->  LEGENDARY
                else ->  COMMON
            }
    }
}