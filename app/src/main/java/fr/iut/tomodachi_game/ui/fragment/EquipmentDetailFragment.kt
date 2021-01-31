package fr.iut.tomodachi_game.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.squareup.picasso.Picasso
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


        val equipmentVM by viewModels<EquipmentVM> { viewModelFactory { EquipmentVM(equipmentId) }}

        val view = inflater.inflate(R.layout.fragment_equipment_detail, container, false)

        val imageView = view.findViewById<ImageView>(R.id.fragment_equipment_iv)
        val nomTV = view.findViewById<TextView>(R.id.fragment_equipment_nom)
        val dateTV = view.findViewById<TextView>(R.id.fragment_equipment_date)


        equipmentVM.equipment.observe(viewLifecycleOwner) {
            if (it != null) {
                Picasso.get().load(it.imageUrl).into(imageView)
                nomTV.text = it.nom
                dateTV.text = it.obtainDate.toString()
            }
        }


        return view
    }
}