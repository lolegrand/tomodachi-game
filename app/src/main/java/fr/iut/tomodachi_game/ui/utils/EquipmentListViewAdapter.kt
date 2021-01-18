package fr.iut.tomodachi_game.ui.utils

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import fr.iut.tomodachi_game.R
import fr.iut.tomodachi_game.data.Equipement
import fr.iut.tomodachi_game.data.Rarity


class EquipmentListViewAdapter(private val dataSet: List<Equipement>): RecyclerView.Adapter<EquipmentListViewAdapter.EquipmentViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipmentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_equipment, parent, false) as CardView
        return EquipmentViewHolder(view);
    }

    override fun onBindViewHolder(holder: EquipmentViewHolder, position: Int) {
        val nomTV = holder.view.findViewById<TextView>(R.id.cv_equipment_nom)
        val rarityV = holder.view.findViewById<View>(R.id.cv_equipment_rarity)

        nomTV.text = dataSet[position].nom
/*
        when(dataSet[position].rarity){
            Rarity.COMMON -> rarityV.background = Drawable.createFromResourceStream(R.color.black,)
            Rarity.UNCOMMON  -> rarityV.background =
            Rarity.RARE  -> rarityV.background =
            Rarity.MYTHICS  -> rarityV.background =
            Rarity.LEGENDARY  -> rarityV.background =
        }

*/
    }

    override fun getItemCount() = dataSet.size





    class EquipmentViewHolder(val view: CardView) : RecyclerView.ViewHolder(view)
}