package fr.iut.tomodachi_game.ui.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import fr.iut.tomodachi_game.R
import fr.iut.tomodachi_game.data.Character
import fr.iut.tomodachi_game.data.Equipment
import fr.iut.tomodachi_game.ui.viewmodel.EquipmentListVM


class EquipmentListViewAdapter(private var dataSet: List<Equipment>): RecyclerView.Adapter<EquipmentListViewAdapter.EquipmentViewHolder>() {

    class EquipmentViewHolder(val view: CardView) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipmentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_equipment, parent, false) as CardView
        return EquipmentViewHolder(view);
    }

    override fun onBindViewHolder(holder: EquipmentViewHolder, position: Int) {

        val equipmentLayout = holder.view.findViewById<CardView>(R.id.card_equipment)
        val nomTV = holder.view.findViewById<TextView>(R.id.cv_equipment_nom)
        val rarityV = holder.view.findViewById<View>(R.id.cv_equipment_rarity)

        equipmentLayout.setOnClickListener {
            listener!!.onEquipmentSelected(dataSet[position].equipmentId) //TODO
        }

        nomTV.text = dataSet[position].nom //TODO

    }

    override fun getItemCount() = dataSet.size


    fun updateList(equipments: List<Equipment>){
        this.dataSet = equipments
        notifyDataSetChanged()
    }

    interface Callbacks{
        fun onEquipmentSelected(id : Long)
    }

    var listener: Callbacks? = null



}