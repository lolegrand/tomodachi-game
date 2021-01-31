package fr.iut.tomodachi_game.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import fr.iut.tomodachi_game.R
import fr.iut.tomodachi_game.data.Character
import fr.iut.tomodachi_game.data.Equipment
import fr.iut.tomodachi_game.data.Rarity
import fr.iut.tomodachi_game.data.persistance.AppDatabase
import fr.iut.tomodachi_game.ui.fragment.CharacterDetailFragment
import fr.iut.tomodachi_game.ui.fragment.CharacterMasterFragment
import fr.iut.tomodachi_game.ui.fragment.EquipmentDetailFragment
import fr.iut.tomodachi_game.ui.fragment.EquipmentMasterFragment
import java.util.*

class MasterDetailActivity : AppCompatActivity(), CharacterMasterFragment.OnInterractionListener, EquipmentMasterFragment.OnInterractionListener, CharacterDetailFragment.OnInterractionListener {


    private val myDatabase = AppDatabase.getInstance()

    private lateinit var myDetail : Fragment
    private lateinit var myMaster : Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_master_detail)

        val type = intent.getStringExtra(TYPE)

        if(type == "character"){
            myMaster = CharacterMasterFragment()
            (myMaster as CharacterMasterFragment).listener = this
            myDetail = CharacterDetailFragment()
        }else{
            myMaster = EquipmentMasterFragment()
            (myMaster as EquipmentMasterFragment).listener = this
            myDetail= EquipmentDetailFragment()
        }

        initView()
    }

    fun initView(){
        if(supportFragmentManager.findFragmentById(R.id.mda_fragment_master) == null){
            supportFragmentManager.beginTransaction().add(R.id.mda_fragment_master, myMaster).commit()
            supportFragmentManager.beginTransaction().add(R.id.mda_fragment_detail, myDetail).commit()
        }
    }

    override fun onCharacterSelected(id: Long) {
        myDetail = CharacterDetailFragment.newInstance(id)
        (myDetail as CharacterDetailFragment).listener = this
        supportFragmentManager.beginTransaction().replace(R.id.mda_fragment_detail, myDetail).commit()
    }

    override fun onEquipmentSelected(id: Long) {
        if(myDetail is EquipmentDetailFragment){
            myDetail = EquipmentDetailFragment.newInstance(id)
            supportFragmentManager.beginTransaction().replace(R.id.mda_fragment_detail, myDetail).commit()
        }else{
            (myDetail as CharacterDetailFragment).equipToCharacter(id)
            myMaster = CharacterMasterFragment()
            (myMaster as CharacterMasterFragment).listener = this
            supportFragmentManager.beginTransaction().replace(R.id.mda_fragment_master, myMaster).commit()
        }
    }


    override fun onClickEquipment() {
        myMaster = EquipmentMasterFragment()
        (myMaster as EquipmentMasterFragment).listener = this
        supportFragmentManager.beginTransaction().replace(R.id.mda_fragment_master, myMaster).commit()
    }


}