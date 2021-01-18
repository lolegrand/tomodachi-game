package fr.iut.tomodachi_game.ui.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import fr.iut.tomodachi_game.R
import fr.iut.tomodachi_game.data.Character
import fr.iut.tomodachi_game.data.Equipement

class CharacterListViewAdapter(private val dataSet: List<Character>): RecyclerView.Adapter<CharacterListViewAdapter.CharacterViewHolder>() {


    class CharacterViewHolder (val view: CardView) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_equipment, parent, false) as CardView
        return CharacterViewHolder(view);
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val nomTV = holder.view.findViewById<TextView>(R.id.cv_character_nom)
        val rarityV = holder.view.findViewById<View>(R.id.cv_character_rarity)

        nomTV.text = dataSet[position].nom
/*
        when(dataSet[position].rarity){
            Rarity.COMMON -> rarityV.background = Drawable.createFromResourceStream(R.color.black)
            Rarity.UNCOMMON  -> rarityV.background =
            Rarity.RARE  -> rarityV.background =
            Rarity.MYTHICS  -> rarityV.background =
            Rarity.LEGENDARY  -> rarityV.background =
        }

*/

    }

    override fun getItemCount() = dataSet.size





}