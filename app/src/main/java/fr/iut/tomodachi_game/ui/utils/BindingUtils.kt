package fr.iut.tomodachi_game.ui.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import com.squareup.picasso.Picasso
import fr.iut.tomodachi_game.R
import fr.iut.tomodachi_game.data.CharacterWithEquipment
import fr.iut.tomodachi_game.data.Rarity
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

object Adapters {
    @JvmStatic
    @BindingAdapter("app:imageUrl")
    fun loadImage(view: ImageView, newValue: Int?) {

        if (newValue != 0)
            newValue?.let {
                Picasso.get()
                    .load(it)
                    .into(view)
            }
    }

    @JvmStatic
    @BindingAdapter("app:imageUrl")
    fun loadImage(view: ImageView, newValue: String?) {
        if (newValue != null || newValue != "")
            Picasso.get()
                .load(newValue)
                .into(view)
    }

    @JvmStatic
    @BindingAdapter("app:imageUrl")
    fun loadImage(view: ImageButton, newValue: Int?) {
        if (newValue != null )
            Picasso.get()
                .load(newValue)
                .into(view)
    }

    @JvmStatic
    @BindingAdapter("app:listEq")
    fun loadEquipment(view: ImageButton, newValue: CharacterWithEquipment?){
        if(newValue == null) return

        Log.e("DAC", "${newValue.character.name} :")
        newValue.equipments.forEach{Log.e("DAC", "-${it.name}")}


    }

}

object Converters {
    @JvmStatic
    @BindingConversion
    fun booleanToVisibility(isVisible: Boolean) = if (isVisible) View.VISIBLE else View.INVISIBLE


    @JvmStatic
    fun rarityToDrawable(context: Context, rarity: Rarity?): Drawable{
        return when(rarity){
            Rarity.COMMON -> ResourcesCompat.getDrawable(context.resources,R.drawable.common, context.theme)!!
            Rarity.UNCOMMON -> ResourcesCompat.getDrawable(context.resources,R.drawable.uncommon,context.theme)!!
            Rarity.RARE -> ResourcesCompat.getDrawable(context.resources,R.drawable.rare,context.theme)!!
            Rarity.MYTHICS -> ResourcesCompat.getDrawable(context.resources,R.drawable.mythic,context.theme)!!
            Rarity.LEGENDARY -> ResourcesCompat.getDrawable(context.resources,R.drawable.legendary,context.theme)!!
            else -> ResourcesCompat.getDrawable(context.resources,R.drawable.common,context.theme)!!
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @JvmStatic
    @BindingConversion
    fun dateToString(value: Date?): String {
        if(value == null)
            return ""

        val date = Instant.ofEpochMilli(value.time).atZone(ZoneId.systemDefault()).toLocalDate()

        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
    }
}