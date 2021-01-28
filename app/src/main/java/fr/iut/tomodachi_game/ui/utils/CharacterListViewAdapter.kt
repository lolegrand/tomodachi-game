package fr.iut.tomodachi_game.ui.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.iut.tomodachi_game.R
import fr.iut.tomodachi_game.data.Character
import fr.iut.tomodachi_game.data.Rarity

class CharacterListViewAdapter(private var dataSet: List<Character>): RecyclerView.Adapter<CharacterListViewAdapter.CharacterViewHolder>() {


    class CharacterViewHolder (val view: CardView) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_character, parent, false) as CardView
        return CharacterViewHolder(view);
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val characterLayout = holder.view.findViewById<CardView>(R.id.card_character)
        val nomTV = holder.view.findViewById<TextView>(R.id.cv_character_nom)
        val imageRarity = holder.view.findViewById<ImageView>(R.id.cv_character_rarity)

        characterLayout.setOnClickListener{
            listener!!.onCharacterSelected(dataSet[position].characterId)
        }

        nomTV.text = dataSet[position].nom

        imageRarity.setBackgroundResource(loadRarity(dataSet[position].rarity))

    }

    private fun loadRarity(rarity: Rarity): Int {
        when(rarity){
            Rarity.COMMON -> return R.drawable.common
            Rarity.UNCOMMON -> return R.drawable.uncommon
            Rarity.RARE -> return R.drawable.rare
            Rarity.MYTHICS -> return R.drawable.mythic
            Rarity.LEGENDARY -> return R.drawable.legendary
        }
    }


    override fun getItemCount() = dataSet.size

    fun updateList(characters: List<Character>){
        this.dataSet = characters
        notifyDataSetChanged()
    }


    interface Callbacks{
        fun onCharacterSelected(id : Long)
    }

    var listener: Callbacks? = null

}