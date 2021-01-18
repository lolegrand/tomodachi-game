package fr.iut.tomodachi_game.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.iut.tomodachi_game.R
import fr.iut.tomodachi_game.data.Equipement
import fr.iut.tomodachi_game.ui.utils.EquipmentListViewAdapter


class EquipmentMasterFragment : Fragment() {

    private var myEquipment: ArrayList<Equipement>? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View?{
        val myAdapter: EquipmentListViewAdapter? = myEquipment?.let { EquipmentListViewAdapter(it) }
        //Add listeners

        val viewManager: RecyclerView.LayoutManager = GridLayoutManager(context, 1)

        val view = inflater.inflate(R.layout.fragment_character_master, container, false)

        view.findViewById<RecyclerView>(R.id.fragment_equipment_recyclerview).apply {
            setHasFixedSize(false)
            layoutManager = viewManager
            adapter = myAdapter
        }

        return view;
    }


}