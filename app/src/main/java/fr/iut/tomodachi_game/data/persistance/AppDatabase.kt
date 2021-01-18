package fr.iut.tomodachi_game.data.persistance

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import fr.iut.tomodachi_game.TomodachiGameApplication
import fr.iut.tomodachi_game.data.Character
import fr.iut.tomodachi_game.data.Equipement
import fr.iut.tomodachi_game.data.Rarity
import fr.iut.tomodachi_game.data.persistance.converter.DateToLongConverter
import fr.iut.tomodachi_game.data.persistance.converter.RarityToIntConverter
import java.util.*


@Database(entities = [Equipement::class, Character::class], version = 1)
@TypeConverters(DateToLongConverter::class, RarityToIntConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun myDAO(): MyDAO

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
            getInstance().myDAO().apply {
                insertCharacter(Character("Naruto", Rarity.LEGENDARY, Date(1610902077 ),"https://cdn.myanimelist.net/images/characters/2/284121.jpg"))
                insertCharacter(Character("Kakashi", Rarity.LEGENDARY, Date(1610902077 ),"https://cdn.myanimelist.net/images/characters/7/284129.jpg"))
                insertCharacter(Character("Sasuke", Rarity.LEGENDARY, Date(1610902077 ),"https://cdn.myanimelist.net/images/characters/9/131317.jpg"))
                insertCharacter(Character("Sakura", Rarity.LEGENDARY, Date(1610902077 ),"https://cdn.myanimelist.net/images/characters/9/69275.jpg"))

                insertEquipment(Equipement("arme",Rarity.MYTHICS,Date(1610902077), Date(1610902077), 0))
            }
        }


    }

}