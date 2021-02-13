package fr.iut.tomodachi_game.ui

import android.animation.ObjectAnimator
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Path
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.squareup.picasso.Picasso
import fr.iut.tomodachi_game.R
import fr.iut.tomodachi_game.data.Character
import fr.iut.tomodachi_game.data.Equipment
import fr.iut.tomodachi_game.ui.viewmodel.EquipmentVM
import fr.iut.tomodachi_game.ui.viewmodel.GenerateDataVM
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

const val CHANNEL_ID :String = "myChannelId"

class PlayActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    private val generateDataVM by viewModels<GenerateDataVM>()

    private lateinit var imChar: List<ImageView>
    private lateinit var imEqu: List<ImageView>

    private val paths = listOf<Path>(
        Path().apply {
            moveTo(500f, 0f)
            lineTo(450f, 650f)
        },
        Path().apply {
            moveTo(500f, 0f)
            lineTo(0f, 650f)
        },

        Path().apply {
            moveTo(500f, 0f)
            lineTo(850f, 650f)
        },
        Path().apply {
            moveTo(500f, 0f)
            lineTo(450f, 150f)
        },
        Path().apply {
            moveTo(500f, 0f)
            lineTo(450f, 1250f)
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        imChar = listOf<ImageView>(
            findViewById<ImageView>(R.id.gacha_im_1),
            findViewById<ImageView>(R.id.gacha_im_2),
            findViewById<ImageView>(R.id.gacha_im_3),
            findViewById<ImageView>(R.id.gacha_im_4),
            findViewById<ImageView>(R.id.gacha_im_5)
        )

        imEqu = listOf<ImageView>(
            findViewById<ImageView>(R.id.gacha_im_1_eq),
            findViewById<ImageView>(R.id.gacha_im_2_eq),
            findViewById<ImageView>(R.id.gacha_im_3_eq),
            findViewById<ImageView>(R.id.gacha_im_4_eq),
            findViewById<ImageView>(R.id.gacha_im_5_eq)
        )

    }

    private fun loadImage(equipments: List<Equipment>, characters: List<Character>) {
        for (i in 0..4) {
            Picasso.get().load(equipments[i].imageUrl).into(imEqu[i])
            Picasso.get().load(characters[i].imageUrl).into(imChar[i])
        }
    }

    private fun startAnimator() {
        for (i in 0..4) {
            ObjectAnimator.ofFloat(imChar[i], View.X, View.Y, paths[i]).apply {
                duration = 2000
                start()
            }
        }
    }


    fun onClickGacha(view: View) {
        view.visibility = View.GONE
        gachaNotify()
        launch {
            generateDataVM.toGenerateData().join()
            loadImage(generateDataVM.listNewEquipments, generateDataVM.listNewCharacters)
            startAnimator()
        }
    }


    private fun createNotificationChannel() {
        
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, "", NotificationManager.IMPORTANCE_DEFAULT)
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun gachaNotify() {
        //createNotificationChannel()
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.common)
            .setContentTitle(resources.getString(R.string.notif_title))
            .setContentText(resources.getString(R.string.notif_text))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)){
            notify(10,builder.build())
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}