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
import fr.iut.tomodachi_game.ui.utils.CharacterListViewAdapter
import fr.iut.tomodachi_game.ui.viewmodel.CharacterListVM


class CharacterMasterFragment : Fragment(), CharacterListViewAdapter.Callbacks {

    private val characterListVM by viewModels<CharacterListVM>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_character_master, container, false)


        val viewAdapter = CharacterListViewAdapter(listOf<Character>())
        viewAdapter.listener = this
        characterListVM.characters.observe(viewLifecycleOwner, Observer {
            viewAdapter.updateList(it)
        })


        val viewManager: RecyclerView.LayoutManager = GridLayoutManager(context, 1)

        view.findViewById<RecyclerView>(R.id.fragment_character_recyclerview).apply {
            setHasFixedSize(false)
            layoutManager= viewManager
            adapter = viewAdapter
        }

        return view
    }

    private fun updateListView(it: List<Character>?) {

    }

    override fun onCharacterSelected(id: Long) {
        listener?.onCharacterSelected(id)
    }

    var listener: OnInterractionListener? = null

    interface OnInterractionListener{
        fun onCharacterSelected(id: Long)
    }




}