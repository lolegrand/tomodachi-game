package fr.iut.tomodachi_game.ui.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import com.squareup.picasso.Picasso
import fr.iut.tomodachi_game.R
import fr.iut.tomodachi_game.data.Rarity
import java.lang.Exception

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


}

object Converters {
    @JvmStatic
    @BindingConversion
    fun booleanToVisibility(isVisible: Boolean) = if (isVisible) View.VISIBLE else View.INVISIBLE


    @JvmStatic
    @BindingConversion
    fun rarityToDrawable(rarity: Rarity?): Int{

        return R.drawable.common
    }
}