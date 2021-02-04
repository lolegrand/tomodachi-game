package fr.iut.tomodachi_game.ui.viewmodel

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import com.squareup.picasso.Picasso
import fr.iut.tomodachi_game.data.persistance.AppDatabase
import fr.iut.tomodachi_game.data.persistance.AppRepository
import java.lang.IllegalArgumentException

class EquipmentVM(private val idEquipment: Long) : ViewModel() {

    private val myRepo = AppRepository(AppDatabase.getInstance().appDAO())

    val equipment = myRepo.findEquipmentById(idEquipment)

    fun deleteEquipment(){
        equipment.value?.let { myRepo.deleteEquipment(it) }
    }

}

