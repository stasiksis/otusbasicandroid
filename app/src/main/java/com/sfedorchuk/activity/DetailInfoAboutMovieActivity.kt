package com.sfedorchuk.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.sfedorchuk.R
import com.sfedorchuk.data.DetailsInfoAboutMovie

class DetailInfoAboutMovieActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_MOVIE = "EXTRA_MOVIE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_details_info_movie)

        intent.getParcelableExtra<DetailsInfoAboutMovie>(EXTRA_MOVIE)?.let {
            if (it.name != null) {
                findViewById<ImageView>(R.id.image_view_details)?.setImageResource(it.name.movieSrc)
                findViewById<TextView>(R.id.text_view_name)?.setText(it.name.movieName)
                findViewById<TextView>(R.id.text_view_name)?.setTextColor(it.actualColor)
                findViewById<TextView>(R.id.text_view_description)?.setText(it.name.movieDescription)
            }
        }

        findViewById<Button>(R.id.button_share).setOnClickListener() {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Посмотри фильм")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }
}