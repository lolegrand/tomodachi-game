package fr.iut.tomodachi_game.ui.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import fr.iut.tomodachi_game.data.Character
import fr.iut.tomodachi_game.data.Equipment
import fr.iut.tomodachi_game.databinding.CardCharacterBinding
import fr.iut.tomodachi_game.databinding.FragmentCharacterMasterBinding

class CharacterListViewAdapter(private var listener: Callbacks):
    ListAdapter<Character, CharacterListViewAdapter.CharacterViewHolder>(CharacterDiffCallBack) {

    private object CharacterDiffCallBack : DiffUtil.ItemCallback<Character>(){
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean = oldItem.characterId == newItem.characterId
        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean = oldItem == newItem
    }


    class CharacterViewHolder (private val binding: CardCharacterBinding, listener: Callbacks):
        RecyclerView.ViewHolder(binding.root){

            val character: Character? get() = binding.character

            init {
                itemView.setOnClickListener{ character?.let { listener.onCharacterSelected(it.characterId) } }
            }

            fun binding(character: Character){
                binding.character = character
                binding.executePendingBindings()
            }

    }


    interface Callbacks{
        fun onCharacterSelected(id : Long)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder =
        CharacterViewHolder(CardCharacterBinding.inflate(LayoutInflater.from(parent.context)), listener)

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) =
        holder.binding(getItem(position))


}