package fr.iut.tomodachi_game.ui.fragment

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import fr.iut.tomodachi_game.R
import fr.iut.tomodachi_game.data.Character
import fr.iut.tomodachi_game.data.Equipment
import fr.iut.tomodachi_game.databinding.FragmentEquipmentDetailBinding
import fr.iut.tomodachi_game.ui.viewmodel.CharacterVM
import fr.iut.tomodachi_game.ui.viewmodel.EquipmentVM
import fr.iut.tomodachi_game.ui.viewmodel.viewModelFactory

class EquipmentDetailFragment : Fragment() {

    private var equipmentId : Long = 0

    private lateinit var equipmentVM: EquipmentVM


    companion object{
        fun newInstance(equipmentId : Long) = EquipmentDetailFragment().apply {
            this.equipmentId = equipmentId
        }
    }


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = FragmentEquipmentDetailBinding.inflate(inflater)
        equipmentVM = ViewModelProvider(this, viewModelFactory { EquipmentVM(equipmentId) }).get(EquipmentVM::class.java)
        binding.equipmentVM = equipmentVM
        binding.lifecycleOwner = viewLifecycleOwner


        return binding.root
    }

    var listener: CharacterDetailFragment.OnInterractionListener? = null

    override fun onDestroyView() {
        super.onDestroyView()
        equipmentVM.updateEquipment()
    }

}