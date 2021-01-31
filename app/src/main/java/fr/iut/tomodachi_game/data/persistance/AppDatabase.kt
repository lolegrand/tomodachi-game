package fr.iut.tomodachi_game.data.persistance

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import fr.iut.tomodachi_game.R
import fr.iut.tomodachi_game.TomodachiGameApplication
import fr.iut.tomodachi_game.data.*
import fr.iut.tomodachi_game.data.persistance.converter.DateToLongConverter
import fr.iut.tomodachi_game.data.persistance.converter.RarityToIntConverter
import java.util.*


@Database(entities = [Equipment::class, Character::class], version = 1)
@TypeConverters(DateToLongConverter::class, RarityToIntConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun appDAO(): AppDAO


    companion object{
        private lateinit var application: Application
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(): AppDatabase{
            if(::application.isInitialized){
                if(instance == null)
                    synchronized(this){
                        if(instance == null)
                            instance = Room.inMemoryDatabaseBuilder(
                                    application.applicationContext,
                                    AppDatabase::class.java)
                                    .allowMainThreadQueries()
                                    .build()
                        toPopulateDB()
                    }
                return instance!!
            }else
                throw RuntimeException("the database isn't yet initialized")
        }


        @Synchronized
        fun initialize(app: TomodachiGameApplication){
            if(::application.isInitialized)
                throw RuntimeException("the database must not be initialized twice")

            application = app
        }


        private fun toPopulateDB() {
            getInstance().appDAO().apply {
                insertCharacter(Character(
                        "Yuzaki, Nasa",
                        Rarity.MYTHICS,
                        Date(222222),
                        "https://cdn.myanimelist.net/images/characters/7/395696.jpg",
                        true))

                insertCharacter(Character(
                        "Yuzaki, Tsukasa",
                        Rarity.LEGENDARY,
                        Date(22222222),
                        "https://cdn.myanimelist.net/images/characters/7/378069.jpg",
                        true))

                insertCharacter(Character(
                        "Aurora",
                        Rarity.COMMON,
                        Date(2222222),
                        "https://cdn.myanimelist.net/images/characters/4/402929.jpg",
                        false))
            }

            getInstance().appDAO().apply {
                insertEquipment(Equipment("Fer Bouclier",Rarity.COMMON,Date(555555), R.drawable.item__25, null))
                insertEquipment(Equipment("Bois Bonclier",Rarity.COMMON,Date(555555), R.drawable.item__00, null))
            }
        }


    }

}