package fr.iut.tomodachi_game.ui.fragment

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import fr.iut.tomodachi_game.R
import fr.iut.tomodachi_game.data.CharacterWithEquipment
import fr.iut.tomodachi_game.data.Rarity
import fr.iut.tomodachi_game.ui.viewmodel.CharacterVM
import fr.iut.tomodachi_game.ui.viewmodel.EquipmentVM
import fr.iut.tomodachi_game.ui.viewmodel.viewModelFactory

class CharacterDetailFragment : Fragment() {

    private var characterId : Long = 0

    private lateinit var characterVM: CharacterVM

    companion object{
        fun newInstance(characterId : Long) = CharacterDetailFragment().apply {
            this.characterId = characterId
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val characterVM by viewModels<CharacterVM> { viewModelFactory { CharacterVM(characterId) }}
        this.characterVM = characterVM

        val view = inflater.inflate(R.layout.fragment_character_detail, container, false)

        val imgChar = view.findViewById<ImageView>(R.id.fragment_character_iv)
        val nomTV = view.findViewById<TextView>(R.id.fragment_character_tvnom)

        val date = view.findViewById<TextView>(R.id.fragment_character_date)
        val main = view.findViewById<ImageView>(R.id.fc_ismain)
        val imgRarity = view.findViewById<ConstraintLayout>(R.id.fc_rarity)

        val eq1 = view.findViewById<ImageButton>(R.id.fragment_character_equ1)
        val eq2 = view.findViewById<ImageButton>(R.id.fragment_character_equ2)
        val eq3 = view.findViewById<ImageButton>(R.id.fragment_character_equ3)
        val eq4 = view.findViewById<ImageButton>(R.id.fragment_character_equ4)

        eq1.setOnClickListener{
            onClickEquipment(1)
        }

        eq2.setOnClickListener{
            onClickEquipment(2)
        }

        eq3.setOnClickListener{
            onClickEquipment(3)
        }

        eq4.setOnClickListener{
            onClickEquipment(4)
        }

        characterVM.characterWithEquipment.observe(viewLifecycleOwner){
            if(it != null){
                nomTV.text = it.character.nom
                date.text = it.character.obtainDate.toString()
                imgRarity.setBackgroundResource(loadRarity(it.character.rarity))
                Picasso.get().load(it.character.imageUrl).into(imgChar)
            }
        }


        characterVM.equipments.observe(viewLifecycleOwner){
            updateEquipment(it.size)
        }



        return view
    }

    private fun loadRarity(rarity: Rarity): Int {
        when(rarity){
            Rarity.COMMON -> return R.drawable.common
            Rarity.UNCOMMON -> return R.drawable.uncommon
            Rarity.RARE -> return R.drawable.rare
            Rarity.MYTHICS -> return R.drawable.mythic
            Rarity.LEGENDARY -> return R.drawable.legendary
        }
    }

    fun updateEquipment(size : Int){
        lateinit var ib : ImageButton

        if(size >= 1){
            Log.e("DAC", "EQ1 ok")
            ib = view?.findViewById<ImageButton>(R.id.fragment_character_equ1)!!
            characterVM.equipments.value?.get(0)?.let { Picasso.get().load(it.imageUrl).into(ib) }
        }
        if(size >= 2){
            Log.e("DAC", "EQ2 ok")
            ib = view?.findViewById<ImageButton>(R.id.fragment_character_equ2)!!
            characterVM.equipments.value?.get(1)?.let { Picasso.get().load(it.imageUrl).into(ib) }
        }
        if(size >= 3){
            Log.e("DAC", "EQ3 ok")
            ib = view?.findViewById<ImageButton>(R.id.fragment_character_equ3)!!
            characterVM.equipments.value?.get(2)?.let { Picasso.get().load(it.imageUrl).into(ib) }
        }
        if(size == 4){
            Log.e("DAC", "EQ4 ok")
            ib = view?.findViewById<ImageButton>(R.id.fragment_character_equ4)!!
            characterVM.equipments.value?.get(3)?.let { Picasso.get().load(it.imageUrl).into(ib) }
        }

    }


    private var lastSelectedEquipmentPosition = 0

    fun equipToCharacter(idEquip: Long){
        characterVM.toEquip(idEquip)
    }

    fun onClickEquipment(pos: Int){
        lastSelectedEquipmentPosition = pos
        listener?.onClickEquipment()
    }

    var listener: OnInterractionListener? = null

    interface OnInterractionListener{
        fun onClickEquipment()
    }



}