package fr.iut.tomodachi_game.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.iut.tomodachi_game.R

import fr.iut.tomodachi_game.data.Equipment
import fr.iut.tomodachi_game.databinding.FragmentEquipmentDetailBinding
import fr.iut.tomodachi_game.databinding.FragmentEquipmentMasterBinding
import fr.iut.tomodachi_game.ui.utils.EquipmentListViewAdapter
import fr.iut.tomodachi_game.ui.viewmodel.EquipmentListVM


class EquipmentMasterFragment : Fragment(), EquipmentListViewAdapter.Callbacks {

    private val equipmentListVM by viewModels<EquipmentListVM>()
    private val equipmentListViewAdapter = EquipmentListViewAdapter(this)


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        val binding = FragmentEquipmentMasterBinding.inflate(inflater)
        binding.equipmentListVM = equipmentListVM
        binding.lifecycleOwner = viewLifecycleOwner

        binding.fragmentEquipmentRecyclerview.adapter = equipmentListViewAdapter

        equipmentListVM.equipments.observe(viewLifecycleOwner) {
            equipmentListViewAdapter.submitList(it)
        }

        return binding.root;
    }


    override fun onEquipmentSelected(id: Long) {
        listener?.onEquipmentSelected(id)
    }

    var listener: OnInterractionListener? = null

    interface OnInterractionListener{
        fun onEquipmentSelected(id: Long)
    }




}