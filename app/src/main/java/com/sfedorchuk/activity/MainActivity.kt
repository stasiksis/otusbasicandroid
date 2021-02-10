package com.sfedorchuk.activity

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sfedorchuk.R
import com.sfedorchuk.adapters.MoviesAdapter
import com.sfedorchuk.adapters.MoviesAdapterFavorite
import com.sfedorchuk.data.DetailsInfoAboutMovie
import com.sfedorchuk.data.FavouriteData
import com.sfedorchuk.data.LikeData
import com.sfedorchuk.view.MoviesItem


class MainActivity : AppCompatActivity() {

    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.recyclerView) }

    private val items by lazy {
        mutableListOf(
            MoviesItem(
                resources.getString(R.string.name_inception),
                resources.getString(R.string.description_inception),
                getDrawable(R.drawable.inception)
            ),
            MoviesItem(
                resources.getString(R.string.name_mr_robot),
                resources.getString(R.string.description_mr_robot),
                getDrawable(R.drawable.mr_robot)
            ),
            MoviesItem(
                resources.getString(R.string.name_x_men),
                resources.getString(R.string.description_x_men),
                getDrawable(R.drawable.x_men)
            ),
            MoviesItem(
                resources.getString(R.string.name_iron_man),
                resources.getString(R.string.description_iron_man),
                getDrawable(R.drawable.iron_man)
            ),
            MoviesItem(
                resources.getString(R.string.name_ai),
                resources.getString(R.string.description_ai),
                getDrawable(R.drawable.ai)
            ),
            MoviesItem(
                resources.getString(R.string.name_ice),
                resources.getString(R.string.description_ice),
                getDrawable(R.drawable.ice)
            ),
            MoviesItem(
                resources.getString(R.string.name_inception),
                resources.getString(R.string.description_inception),
                getDrawable(R.drawable.inception)
            ),
            MoviesItem(
                resources.getString(R.string.name_mr_robot),
                resources.getString(R.string.description_mr_robot),
                getDrawable(R.drawable.mr_robot)
            ),
            MoviesItem(
                resources.getString(R.string.name_x_men),
                resources.getString(R.string.description_x_men),
                getDrawable(R.drawable.x_men)
            ),
            MoviesItem(
                resources.getString(R.string.name_iron_man),
                resources.getString(R.string.description_iron_man),
                getDrawable(R.drawable.iron_man)
            ),
            MoviesItem(
                resources.getString(R.string.name_ai),
                resources.getString(R.string.description_ai),
                getDrawable(R.drawable.ai)
            ),
            MoviesItem(
                resources.getString(R.string.name_ice),
                resources.getString(R.string.description_ice),
                getDrawable(R.drawable.ice)
            )
        )
    }

    private var actualMenuItem: Int = R.id.all_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button_share).setOnClickListener() {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Посмотри фильм")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }

        initRecycler()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.all_list -> {
                actualMenuItem = R.id.all_list
                initRecycler()
                return true
            }
            R.id.favourites -> {
                actualMenuItem = R.id.favourites
                initRecyclerFavorite()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initRecycler() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager

        recyclerView.adapter = MoviesAdapter(items, object : MoviesAdapter.MoviesClickListener {
            override fun onDetailsClick(moviesItem: MoviesItem, position: Int) {
                Intent(this@MainActivity, DetailInfoAboutMovieActivity::class.java).apply {
                    putExtra(
                        DetailInfoAboutMovieActivity.EXTRA_MOVIE,
                        DetailsInfoAboutMovie(findItemByName(items[position].title), Color.RED
                        )
                    )

                    startActivity(this)
                }
            }
        })

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

            }
        })

        val itemDecoration = CustomItemDecoration(this, DividerItemDecoration.VERTICAL)

        recyclerView.addItemDecoration(itemDecoration)

    }

    private fun initRecyclerFavorite() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager

        recyclerView.adapter = MoviesAdapterFavorite(FavouriteData.listData)

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

            }
        })

        val itemDecoration = CustomItemDecoration(this, DividerItemDecoration.VERTICAL)

        recyclerView.addItemDecoration(itemDecoration)

    }

    fun findItemByName(name: String): MovieInfo? {
        MovieInfo.values().forEach {
            if (resources.getString(it.movieName) == name) {
                return it
            }
        }
        return null
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


    class CustomItemDecoration(context: Context, orientation: Int) :
        DividerItemDecoration(context, orientation) {
        override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            super.onDraw(c, parent, state)
        }

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
        }
    }

    enum class MovieInfo(val movieName: Int, val movieDescription: Int, val movieSrc: Int) {
        INCEPTION(R.string.name_inception, R.string.description_inception, R.drawable.inception),
        MR_ROBOT(R.string.name_mr_robot, R.string.description_mr_robot, R.drawable.mr_robot),
        IRON_MAN(R.string.name_iron_man, R.string.description_iron_man, R.drawable.iron_man),
        X_MEN(R.string.name_x_men, R.string.description_x_men, R.drawable.x_men),
        ICE(R.string.name_ice, R.string.description_ice, R.drawable.ice),
        AI(R.string.name_ai, R.string.description_ai, R.drawable.ai);
    }
}