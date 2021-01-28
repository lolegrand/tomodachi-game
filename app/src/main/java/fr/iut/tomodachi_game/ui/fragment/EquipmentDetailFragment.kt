package fr.iut.tomodachi_game.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import fr.iut.tomodachi_game.R
import fr.iut.tomodachi_game.data.Character
import fr.iut.tomodachi_game.data.Equipment
import fr.iut.tomodachi_game.ui.viewmodel.CharacterVM
import fr.iut.tomodachi_game.ui.viewmodel.EquipmentVM
import fr.iut.tomodachi_game.ui.viewmodel.viewModelFactory

class EquipmentDetailFragment : Fragment() {

    private var equipmentId : Long = 0

    companion object{
        fun newInstance(equipmentId : Long) = EquipmentDetailFragment().apply {
            this.equipmentId = equipmentId
        }
    }


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_equipment_detail, container, false)


        return view
    }
}