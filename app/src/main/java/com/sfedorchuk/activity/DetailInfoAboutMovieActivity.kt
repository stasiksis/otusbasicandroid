package com.sfedorchuk.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.sfedorchuk.R
import com.sfedorchuk.data.DetailsInfoAboutMovie
import com.sfedorchuk.data.LikeData

class DetailInfoAboutMovieActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_MOVIE = "EXTRA_MOVIE"
        const val EXTRA_DATA = "EXTRA_DATA"
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
    }

    override fun onBackPressed() {
        val intent: Intent = Intent().apply {
            putExtra(
                EXTRA_DATA,
                LikeData(
                    findViewById<CheckBox>(R.id.checkbox_like).isChecked,
                    findViewById<EditText>(R.id.edit_text).text.toString()
                )
            )
        }
        setResult(Activity.RESULT_OK, intent)

        finish()

    }
}