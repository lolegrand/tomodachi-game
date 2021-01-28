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
import fr.iut.tomodachi_game.ui.utils.EquipmentListViewAdapter
import fr.iut.tomodachi_game.ui.viewmodel.EquipmentListVM


class EquipmentMasterFragment : Fragment(), EquipmentListViewAdapter.Callbacks {

    private val equipmentListVM by viewModels<EquipmentListVM>()



    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View?{

        val view = inflater.inflate(R.layout.fragment_equipment_master, container, false)

        val viewAdapter = EquipmentListViewAdapter(listOf<Equipment>())
        equipmentListVM.equipments.observe(viewLifecycleOwner, Observer {
            viewAdapter.updateList(it)
        })
        viewAdapter.listener = this

        val viewManager: RecyclerView.LayoutManager = GridLayoutManager(context, 1)

        view.findViewById<RecyclerView>(R.id.fragment_equipment_recyclerview).apply {
            setHasFixedSize(false)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        return view;
    }


    override fun onEquipmentSelected(id: Long) {
        listener?.onEquipmentSelected(id)
    }

    var listener: EquipmentMasterFragment.OnInterractionListener? = null

    interface OnInterractionListener{
        fun onEquipmentSelected(id: Long)
    }




}