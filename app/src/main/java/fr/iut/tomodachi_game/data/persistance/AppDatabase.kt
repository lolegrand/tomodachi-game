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
                            // TODO : remove in memory
                            instance = Room.inMemoryDatabaseBuilder(
                                    application.applicationContext,
                                    AppDatabase::class.java)
                                    .build()
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



    }

}