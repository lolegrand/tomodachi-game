package fr.iut.tomodachi_game.ui.fragment

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import fr.iut.tomodachi_game.R
import fr.iut.tomodachi_game.data.Rarity
import fr.iut.tomodachi_game.ui.viewmodel.CharacterVM
import fr.iut.tomodachi_game.ui.viewmodel.viewModelFactory

class CharacterDetailFragment : Fragment() {

    private var characterId : Long = 0

    companion object{
        fun newInstance(characterId : Long) = CharacterDetailFragment().apply {
            this.characterId = characterId
        }
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val characterVM by viewModels<CharacterVM> { viewModelFactory { CharacterVM(characterId) }}
        val view = inflater.inflate(R.layout.fragment_character_detail, container, false)


        val imgChar = view.findViewById<ImageView>(R.id.fragment_character_iv)
        val nomTV = view.findViewById<TextView>(R.id.fragment_character_tvnom)

        val date = view.findViewById<TextView>(R.id.fragment_character_date)
        val main = view.findViewById<ImageView>(R.id.fc_ismain)
        val imgRarity = view.findViewById<ConstraintLayout>(R.id.fc_rarity)



        characterVM.character.observe(viewLifecycleOwner, Observer {
            nomTV.text = it.character.nom
            date.text = it.character.obtainDate.toString()
            imgRarity.setBackgroundResource(loadRarity(it.character.rarity))
            Picasso.get().load(it.character.imageUrl).into(imgChar)
        })



        return view

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


}