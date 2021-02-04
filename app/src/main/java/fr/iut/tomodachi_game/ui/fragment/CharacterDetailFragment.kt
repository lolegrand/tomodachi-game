package fr.iut.tomodachi_game.ui.fragment


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import fr.iut.tomodachi_game.R
import fr.iut.tomodachi_game.data.Rarity
import fr.iut.tomodachi_game.databinding.FragmentCharacterDetailBinding
import fr.iut.tomodachi_game.ui.viewmodel.CharacterVM
import fr.iut.tomodachi_game.ui.viewmodel.EquipmentListVM
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


        val binding = FragmentCharacterDetailBinding.inflate(inflater)
        characterVM = ViewModelProvider(this, viewModelFactory { CharacterVM(characterId) }).get(CharacterVM::class.java)
        binding.charaterVM = characterVM
        binding.lifecycleOwner = viewLifecycleOwner

        binding.fragmentCharacterEqu1.setOnClickListener {
            lastSelectedEquipmentPosition = 0
            onClickEquipment(0)
        }

        binding.fragmentCharacterEqu2.setOnClickListener {
            lastSelectedEquipmentPosition = 1
            onClickEquipment(1)
        }

        binding.fragmentCharacterEqu3.setOnClickListener {
            lastSelectedEquipmentPosition = 2
            onClickEquipment(2)
        }

        binding.fragmentCharacterEqu4.setOnClickListener {
            lastSelectedEquipmentPosition = 3
            onClickEquipment(3)
        }

        return binding.root
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