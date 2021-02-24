package com.sfedorchuk.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.sfedorchuk.R
import com.sfedorchuk.adapters.MoviesAdapter
import com.sfedorchuk.data.DetailsInfoAboutMovie
import com.sfedorchuk.dialog.CustomDialog
import com.sfedorchuk.fragments.MovieDetailsFragment
import com.sfedorchuk.fragments.MoviesListFragment
import com.sfedorchuk.view.MoviesItem


class MainActivity : AppCompatActivity(), MoviesAdapter.MoviesClickListener {

    companion object {
        var IS_FAVORITE_SCREEN: Boolean = false
        const val EXTRA_DATA = "EXTRA_DATA"
        const val KEY_ITEMS: String = "KEY_ITEMS"
    }

    private var detailsFragment: MovieDetailsFragment? = null

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

        savedInstanceState?.getParcelableArrayList<MoviesItem>(KEY_ITEMS)?.let {
            it.forEachIndexed { index, moviesItem -> items[index] = moviesItem }
        }

        if (IS_FAVORITE_SCREEN) {
            openMoviesFavouriteList()
        } else {
            openMoviesList()
        }

        val navigation =
            findViewById<View>(R.id.bottom_navigation) as BottomNavigationView
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun onResume() {
        super.onResume()
    }

    private fun openMoviesList() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, MoviesListFragment(items), MoviesListFragment.TAG)
            .commit()
    }

    private fun openMoviesFavouriteList() {
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fragmentContainer,
                MoviesListFragment(items.filter { it.isFavourite } as ArrayList<MoviesItem>),
                MoviesListFragment.TAG)
            .commit()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putParcelableArrayList(
            KEY_ITEMS,
            items
        )
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.all_list -> {
                    IS_FAVORITE_SCREEN = false
                    openMoviesList()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.favourites -> {
                    IS_FAVORITE_SCREEN = true
                    openMoviesFavouriteList()
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
            findViewById<BottomNavigationView>(R.id.bottom_navigation).visibility = View.VISIBLE
            findViewById<Button>(R.id.button_share).visibility = View.VISIBLE
        } else {
            val dialog = CustomDialog(this)
            dialog.setCancelable(false)
            dialog.show()
        }
    }

    private fun findItemByName(name: Int): MovieInfo? {
        MovieInfo.values().forEach {
            if (resources.getString(it.movieName) == resources.getString(name)) {
                return it
            }
        }
        return null
    }

    enum class MovieInfo(val movieName: Int, val movieDescription: Int, val movieSrc: Int) {
        INCEPTION(R.string.name_inception, R.string.description_inception, R.drawable.inception),
        MR_ROBOT(R.string.name_mr_robot, R.string.description_mr_robot, R.drawable.mr_robot),
        IRON_MAN(R.string.name_iron_man, R.string.description_iron_man, R.drawable.iron_man),
        X_MEN(R.string.name_x_men, R.string.description_x_men, R.drawable.x_men),
        ICE(R.string.name_ice, R.string.description_ice, R.drawable.ice),
        AI(R.string.name_ai, R.string.description_ai, R.drawable.ai);
    }

    override fun onDetailsClick(moviesItem: MoviesItem, position: Int) {
        items.forEach {
            it.color = Color.BLACK
        }
        if (IS_FAVORITE_SCREEN) {
            items.filter { it.isFavourite }[position].color = Color.RED
        } else {
            items[position].color = Color.RED
        }

        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fragmentContainer, MovieDetailsFragment.newInstance(
                    DetailsInfoAboutMovie(
                        findItemByName(items[position].title), Color.RED
                    )
                ), MovieDetailsFragment.TAG
            )
            .addToBackStack(null)
            .commit()
        findViewById<BottomNavigationView>(R.id.bottom_navigation).visibility = View.GONE
        findViewById<Button>(R.id.button_share).visibility = View.GONE
    }

    override fun onFavoriteDeleteClick(moviesItem: MoviesItem, position: Int) {

        if (IS_FAVORITE_SCREEN) {
            items.filter { it.isFavourite }[position].isFavourite = false
        } else {
            items[position].isFavourite = !moviesItem.isFavourite
        }

        val title = resources.getString(moviesItem.title)
        if (!moviesItem.isFavourite) {
            Snackbar.make(
                this.findViewById<View>(android.R.id.content).rootView,
                "Удалили из избранного $title",
                Snackbar.LENGTH_LONG
            )
                .show()
        } else {
            Snackbar.make(
                this.findViewById<View>(android.R.id.content).rootView,
                "Добавили в избранное $title",
                Snackbar.LENGTH_LONG
            )
                .show()
        }
    }
}