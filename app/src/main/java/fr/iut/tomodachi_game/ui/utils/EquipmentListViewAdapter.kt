package fr.iut.tomodachi_game.ui.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import fr.iut.tomodachi_game.data.Equipment
import fr.iut.tomodachi_game.databinding.CardEquipmentBinding


class EquipmentListViewAdapter(private var listener: Callbacks):
    ListAdapter<Equipment, EquipmentListViewAdapter.EquipmentViewHolder>(EquipmentDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipmentViewHolder =
        EquipmentViewHolder(CardEquipmentBinding.inflate(LayoutInflater.from(parent.context)), listener)

    override fun onBindViewHolder(holder: EquipmentViewHolder, position: Int) =
        holder.binding(getItem(position))

    class EquipmentViewHolder(private val binding: CardEquipmentBinding, listener: Callbacks) :
        RecyclerView.ViewHolder(binding.root){

            val equipment: Equipment? get() = binding.equipment

            init {
                itemView.setOnClickListener{equipment?.let { listener.onEquipmentSelected(it.equipmentId)}}
            }

        fun binding(equipment: Equipment){
                binding.equipment = equipment
                binding.executePendingBindings()
            }
    }

    interface Callbacks{
        fun onEquipmentSelected(id : Long)
    }

    private object EquipmentDiffCallback : DiffUtil.ItemCallback<Equipment>() {
        override fun areItemsTheSame(oldItem: Equipment, newItem: Equipment) = oldItem.equipmentId == newItem.equipmentId

        override fun areContentsTheSame(oldItem: Equipment, newItem: Equipment) = oldItem == newItem
    }


}