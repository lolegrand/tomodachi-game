package fr.iut.tomodachi_game.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.iut.tomodachi_game.R
import fr.iut.tomodachi_game.data.Character
import fr.iut.tomodachi_game.databinding.FragmentCharacterMasterBinding
import fr.iut.tomodachi_game.ui.utils.CharacterListViewAdapter
import fr.iut.tomodachi_game.ui.viewmodel.CharacterListVM


class CharacterMasterFragment : Fragment(), CharacterListViewAdapter.Callbacks {

    private val characterListVM by viewModels<CharacterListVM>()
    private val characterListViewAdapter = CharacterListViewAdapter(this)

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        val binding = FragmentCharacterMasterBinding.inflate(inflater)
        binding.characterListVM = characterListVM
        binding.lifecycleOwner = viewLifecycleOwner
        binding.fragmentCharacterRecyclerView.adapter = characterListViewAdapter

        characterListVM.characters.observe(viewLifecycleOwner) {
            characterListViewAdapter.submitList(it)
        }

        return binding.root
    }


    override fun onCharacterSelected(id: Long) {
        listener?.onCharacterSelected(id)
    }

    var listener: OnInterractionListener? = null

    interface OnInterractionListener{
        fun onCharacterSelected(id: Long)
    }




}