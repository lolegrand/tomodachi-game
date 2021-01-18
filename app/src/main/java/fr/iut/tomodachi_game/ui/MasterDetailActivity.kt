package fr.iut.tomodachi_game.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import fr.iut.tomodachi_game.R
import fr.iut.tomodachi_game.ui.fragment.CharacterDetailFragment
import fr.iut.tomodachi_game.ui.fragment.CharacterMasterFragment
import fr.iut.tomodachi_game.ui.fragment.EquipmentDetailFragment
import fr.iut.tomodachi_game.ui.fragment.EquipmentMasterFragment

class MasterDetailActivity : AppCompatActivity() {

    fun getMaster() = findViewById<FrameLayout>(R.id.mda_fragment_master)
    fun getDetail() = findViewById<FrameLayout>(R.id.mda_fragment_detail)

    val myMaster = CharacterMasterFragment()
    val myDetail = CharacterDetailFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_master_detail)

        val type = intent.getStringExtra("TYPE")
        initView(type)



    }

    fun initView(type : String?){

        if(supportFragmentManager.findFragmentById(R.id.mda_fragment_detail) == null){
            supportFragmentManager.beginTransaction().add(R.id.mda_fragment_detail, myDetail).commit()
        }

        if(supportFragmentManager.findFragmentById(R.id.mda_fragment_master) == null){
            supportFragmentManager.beginTransaction().add(R.id.mda_fragment_master, myMaster).commit()
        }

    }





}