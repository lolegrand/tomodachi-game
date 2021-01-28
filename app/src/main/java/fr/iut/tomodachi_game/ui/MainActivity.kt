package fr.iut.tomodachi_game.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import fr.iut.tomodachi_game.R

const val TYPE = "tomodachigame.TYPE"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
    }

    fun buttonCharacter(view: View) {
        val intent = Intent(this, MasterDetailActivity::class.java).apply {
            putExtra( TYPE,"character" )
        }
        startActivity(intent)
    }

    fun buttonEquipment(view: View) {
        val intent = Intent(this, MasterDetailActivity::class.java).apply {
            putExtra(TYPE,"equipment")
        }
        startActivity(intent)
    }

    fun buttonPlay(view: View) {}

    fun buttonHelp(view: View) {}
}