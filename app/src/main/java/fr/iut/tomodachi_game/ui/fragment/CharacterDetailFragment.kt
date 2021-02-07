package fr.iut.tomodachi_game.ui.fragment


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.iut.tomodachi_game.R
import fr.iut.tomodachi_game.data.Rarity
import fr.iut.tomodachi_game.databinding.FragmentCharacterDetailBinding
import fr.iut.tomodachi_game.ui.utils.CharacterListViewAdapter
import fr.iut.tomodachi_game.ui.utils.EquipmentListViewAdapter
import fr.iut.tomodachi_game.ui.viewmodel.CharacterVM
import fr.iut.tomodachi_game.ui.viewmodel.EquipmentListVM
import fr.iut.tomodachi_game.ui.viewmodel.EquipmentVM
import fr.iut.tomodachi_game.ui.viewmodel.viewModelFactory
import kotlinx.coroutines.*
import okhttp3.Dispatcher
import java.time.Duration
import kotlin.coroutines.CoroutineContext

class CharacterDetailFragment : Fragment(), EquipmentListViewAdapter.Callbacks, CoroutineScope by MainScope() {

    private var characterId : Long = 0

    private lateinit var characterVM: CharacterVM
    private val equipmentListViewAdapter = EquipmentListViewAdapter(this)

    companion object{
        fun newInstance(characterId : Long) = CharacterDetailFragment().apply {
            this.characterId = characterId
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentCharacterDetailBinding.inflate(inflater)
        characterVM = ViewModelProvider(this, viewModelFactory { CharacterVM(characterId) }).get(CharacterVM::class.java)
        binding.charaterVM = characterVM
        binding.lifecycleOwner = viewLifecycleOwner
        binding.fcRecyclerview.adapter = equipmentListViewAdapter

        binding.addEquipment.setOnClickListener {
            listener?.onClickEquipment()
        }

        ItemTouchHelper(EquipmentListTouchHelper()).attachToRecyclerView(binding.fcRecyclerview)

        characterVM.characterWithEquipment.observe(viewLifecycleOwner){
            if(it != null) {
                equipmentListViewAdapter.submitList(it.equipments)
            }
        }

        return binding.root
    }


    fun equipToCharacter(idEquip: Long) {
        launch {
            characterVM.toEquip(idEquip).join()
            equipmentListViewAdapter.notifyDataSetChanged()
            Toast.makeText(context, "Equipment Added", Toast.LENGTH_SHORT ).show()
        }
    }

    var listener: OnInterractionListener? = null

    interface OnInterractionListener{
        fun onClickEquipment()
    }


    private inner class EquipmentListTouchHelper: ItemTouchHelper.Callback(){
        override fun isLongPressDragEnabled() = false

        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ) =
            makeMovementFlags(
                ItemTouchHelper.UP or ItemTouchHelper.DOWN,
                ItemTouchHelper.START or ItemTouchHelper.END
            )


        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ) = false

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            (viewHolder as EquipmentListViewAdapter.EquipmentViewHolder).equipment?.also {
                characterVM.unequip(it)
                Toast.makeText(context, "Equipment Deleted", Toast.LENGTH_SHORT ).show()
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }

    override fun onEquipmentSelected(id: Long) {

    }



}