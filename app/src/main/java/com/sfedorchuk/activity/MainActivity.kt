package com.sfedorchuk.activity

import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sfedorchuk.R
import com.sfedorchuk.adapters.MoviesAdapter
import com.sfedorchuk.data.DetailsInfoAboutMovie
import com.sfedorchuk.data.LikeData
import com.sfedorchuk.dialog.CustomDialog
import com.sfedorchuk.view.MoviesItem


class MainActivity : AppCompatActivity() {

    companion object {
        var IS_FAVORITE_SCREEN: Boolean = false
        const val KEY_ITEMS: String = "KEY_ITEMS"
    }

    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.recyclerView) }

    private var items =
        arrayListOf(
            MoviesItem(
                R.string.name_inception,
                R.string.description_inception,
                R.drawable.inception
            ),
            MoviesItem(
                R.string.name_mr_robot,
                R.string.description_mr_robot,
                R.drawable.mr_robot
            ),
            MoviesItem(
                R.string.name_x_men,
                R.string.description_x_men,
                R.drawable.x_men
            ),
            MoviesItem(
                R.string.name_iron_man,
                R.string.description_iron_man,
                R.drawable.iron_man
            ),
            MoviesItem(
                R.string.name_ai,
                R.string.description_ai,
                R.drawable.ai
            ),
            MoviesItem(
                R.string.name_ice,
                R.string.description_ice,
                R.drawable.ice
            ),
            MoviesItem(
                R.string.name_inception,
                R.string.description_inception,
                R.drawable.inception
            ),
            MoviesItem(
                R.string.name_mr_robot,
                R.string.description_mr_robot,
                R.drawable.mr_robot
            ),
            MoviesItem(
                R.string.name_x_men,
                R.string.description_x_men,
                R.drawable.x_men
            ),
            MoviesItem(
                R.string.name_iron_man,
                R.string.description_iron_man,
                R.drawable.iron_man
            ),
            MoviesItem(
                R.string.name_ai,
                R.string.description_ai,
                R.drawable.ai
            ),
            MoviesItem(
                R.string.name_ice,
                R.string.description_ice,
                R.drawable.ice
            )
        )


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

        if (savedInstanceState != null) {
            savedInstanceState.getParcelableArrayList<MoviesItem>(KEY_ITEMS)
                .let {
                    it?.forEachIndexed { index, moviesItem -> items[index] = moviesItem }
                    initRecycler()
                }
        } else {
            initRecycler()
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putParcelableArrayList(
            KEY_ITEMS,
            items
        )
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onBackPressed() {
        val dialog = CustomDialog(this)
        dialog.setCancelable(false)
        dialog.show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.all_list -> {
                IS_FAVORITE_SCREEN = false
                actualMenuItem = R.id.all_list
                initRecycler()
                return true
            }
            R.id.favourites -> {
                IS_FAVORITE_SCREEN = true
                actualMenuItem = R.id.favourites
                initRecycler()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initRecycler() {
        val orientation: Int = resources.configuration.orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            recyclerView.layoutManager = layoutManager
        } else {
            recyclerView.layoutManager = GridLayoutManager(this, 2)
        }

        val itemList: List<MoviesItem> =
            if (!IS_FAVORITE_SCREEN) items else items.filter { it.isFavourite }

        recyclerView.adapter = MoviesAdapter(
            itemList as ArrayList<MoviesItem>,
            object : MoviesAdapter.MoviesClickListener {
                override fun onDetailsClick(moviesItem: MoviesItem, position: Int) {
                    items.forEach {
                        it.color = Color.BLACK
                    }
                    if (IS_FAVORITE_SCREEN) {
                        items.filter { it.isFavourite }[position].color = Color.RED
                    } else {
                        items[position].color = Color.RED
                    }

                    Intent(this@MainActivity, DetailInfoAboutMovieActivity::class.java).apply {
                        putExtra(
                            DetailInfoAboutMovieActivity.EXTRA_MOVIE,
                            DetailsInfoAboutMovie(
                                findItemByName(items[position].title), Color.RED
                            )
                        )

                        startActivityForResult(this, 1)
                    }
                }

                override fun onFavoriteDeleteClick(moviesItem: MoviesItem, position: Int) {
                    Log.d("count_fav", "${items.filter { it.isFavourite }.size}")
                    if (IS_FAVORITE_SCREEN) {
                        items.filter { it.isFavourite }[position].isFavourite = false
                    } else {
                        items[position].isFavourite = !moviesItem.isFavourite
                    }
                }
            })

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

            }
        })

        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(itemDecoration)

        val itemDecorationHorizontal = DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL)
        recyclerView.addItemDecoration(itemDecorationHorizontal)
    }

    fun findItemByName(name: Int): MovieInfo? {
        MovieInfo.values().forEach {
            if (resources.getString(it.movieName) == resources.getString(name)) {
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

    enum class MovieInfo(val movieName: Int, val movieDescription: Int, val movieSrc: Int) {
        INCEPTION(R.string.name_inception, R.string.description_inception, R.drawable.inception),
        MR_ROBOT(R.string.name_mr_robot, R.string.description_mr_robot, R.drawable.mr_robot),
        IRON_MAN(R.string.name_iron_man, R.string.description_iron_man, R.drawable.iron_man),
        X_MEN(R.string.name_x_men, R.string.description_x_men, R.drawable.x_men),
        ICE(R.string.name_ice, R.string.description_ice, R.drawable.ice),
        AI(R.string.name_ai, R.string.description_ai, R.drawable.ai);
    }
}