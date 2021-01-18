package fr.iut.tomodachi_game.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.iut.tomodachi_game.R
import fr.iut.tomodachi_game.data.Character
import fr.iut.tomodachi_game.data.persistance.AppDatabase
import fr.iut.tomodachi_game.ui.utils.CharacterListViewAdapter


class CharacterMasterFragment : Fragment() {

    var characters: List<Character> = AppDatabase.getInstance().myDAO().getAllCharacter()


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {


        val myAdapter: CharacterListViewAdapter? = CharacterListViewAdapter(characters)
        val viewManager: RecyclerView.LayoutManager = GridLayoutManager(context, 2)
        val view = inflater.inflate(R.layout.fragment_character_master, container, false)
        view.findViewById<RecyclerView>(R.id.fragment_character_recyclerview).apply {
            setHasFixedSize(false)
            layoutManager= viewManager
            adapter = myAdapter
        }
        return view
    }
}