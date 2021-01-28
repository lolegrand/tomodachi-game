package fr.iut.tomodachi_game

import android.app.Application
import com.squareup.picasso.Picasso
import fr.iut.tomodachi_game.data.persistance.AppDatabase

class TomodachiGameApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        AppDatabase.initialize(this)


    }
}