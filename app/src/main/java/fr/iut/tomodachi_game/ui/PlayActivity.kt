package fr.iut.tomodachi_game.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import fr.iut.tomodachi_game.R
import fr.iut.tomodachi_game.data.persistance.AppDatabase
import fr.iut.tomodachi_game.data.persistance.AppRepository

class PlayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)
    }

    fun OnClickGacha(view: View) {
        val myRepo = AppRepository(AppDatabase.getInstance().appDAO())
        myRepo.toGenerateCharactersAndEquipment()
    }




}