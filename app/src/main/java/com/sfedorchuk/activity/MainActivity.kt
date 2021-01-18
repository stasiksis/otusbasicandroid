package com.sfedorchuk.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sfedorchuk.R
import com.sfedorchuk.data.DetailsInfoAboutMovie
import com.sfedorchuk.data.LikeData
import com.sfedorchuk.data.MovieInfo

class MainActivity : AppCompatActivity() {
    private val textViewInception by lazy {
        findViewById<TextView>(R.id.text_view_inception)
    }

    private val textViewMrRobot by lazy {
        findViewById<TextView>(R.id.text_view_mr_robot)
    }

    private val textViewIronMan by lazy {
        findViewById<TextView>(R.id.text_view_iron_man)
    }

    private val textViewXMen by lazy {
        findViewById<TextView>(R.id.text_view_x_men)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.details_inception).setOnClickListener() {
            resetColor()
            textViewInception.setTextColor(Color.RED)
            Intent(this, DetailInfoAboutMovieActivity::class.java).apply {
                putExtra(
                    DetailInfoAboutMovieActivity.EXTRA_MOVIE,
                    DetailsInfoAboutMovie(MovieInfo.INCEPTION, Color.RED)
                )
                startActivityForResult(this, 1)
            }
        }

        findViewById<Button>(R.id.details_mr_robot).setOnClickListener() {
            resetColor()
            textViewMrRobot.setTextColor(Color.RED)

            Intent(this, DetailInfoAboutMovieActivity::class.java).apply {
                putExtra(
                    DetailInfoAboutMovieActivity.EXTRA_MOVIE,
                    DetailsInfoAboutMovie(MovieInfo.MR_ROBOT, Color.RED)
                )
                startActivityForResult(this, 2)
            }
        }

        findViewById<Button>(R.id.details_iron_man).setOnClickListener() {
            resetColor()
            textViewIronMan.setTextColor(Color.RED)

            Intent(this, DetailInfoAboutMovieActivity::class.java).apply {
                putExtra(
                    DetailInfoAboutMovieActivity.EXTRA_MOVIE,
                    DetailsInfoAboutMovie(MovieInfo.IRON_MAN, Color.RED)
                )
                startActivityForResult(this, 3)
            }
        }

        findViewById<Button>(R.id.details_x_men).setOnClickListener() {
            resetColor()
            textViewXMen.setTextColor(Color.RED)

            Intent(this, DetailInfoAboutMovieActivity::class.java).apply {
                putExtra(
                    DetailInfoAboutMovieActivity.EXTRA_MOVIE,
                    DetailsInfoAboutMovie(MovieInfo.X_MEN, Color.RED)
                )
                startActivityForResult(this, 4)
            }
        }

        savedInstanceState?.getParcelable<DetailsInfoAboutMovie>(DetailInfoAboutMovieActivity.EXTRA_MOVIE)
            ?.let {
                if (it.name != null) findViewById<TextView>(it.name.movieIdResource).setTextColor(it.actualColor)
            }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val actualMovie: MovieInfo? = when (Color.RED) {
            textViewInception.currentTextColor -> MovieInfo.INCEPTION
            textViewMrRobot.currentTextColor -> MovieInfo.MR_ROBOT
            textViewIronMan.currentTextColor -> MovieInfo.IRON_MAN
            textViewXMen.currentTextColor -> MovieInfo.X_MEN
            else -> null
        }

        outState.putParcelable(
            DetailInfoAboutMovieActivity.EXTRA_MOVIE,
            DetailsInfoAboutMovie(actualMovie, Color.RED)
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        data?.let {
            it.getParcelableExtra<LikeData>(DetailInfoAboutMovieActivity.EXTRA_DATA)
                ?.let { likeData ->
                    Log.d(
                        "debug",
                        "Нравится ли фильм? ${likeData.checkboxValue}\n Комментарий: ${likeData.comment}"
                    )

                    val toast = Toast.makeText(
                        applicationContext,
                        "Нравится ли фильм? ${likeData.checkboxValue}\n Комментарий: ${likeData.comment}",
                        Toast.LENGTH_LONG
                    )
                    toast.show()

                }
        }
    }

    private fun resetColor() {
        textViewInception.setTextColor(Color.BLACK)
        textViewMrRobot.setTextColor(Color.BLACK)
        textViewIronMan.setTextColor(Color.BLACK)
        textViewXMen.setTextColor(Color.BLACK)
    }
}