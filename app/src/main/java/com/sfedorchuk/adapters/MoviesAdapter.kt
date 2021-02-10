package com.sfedorchuk.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sfedorchuk.R
import com.sfedorchuk.data.FavouriteData
import com.sfedorchuk.view.MoviesItem
import com.sfedorchuk.view_holders.MoviesVH

class MoviesAdapter(
    private val items: List<MoviesItem>,
    private val clickListener: MoviesClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_movies, parent, false)
        return MoviesVH(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]

        if (holder is MoviesVH) {
            holder.bind(item)


            holder.imageFavourite.setOnClickListener {
                if (item.isFavourite) {
                    holder.imageFavourite.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24)
                    items[position].isFavourite = false
                    FavouriteData.listData.remove(item)
                } else {
                    holder.imageFavourite.setBackgroundResource(R.drawable.ic_baseline_favorite_24)
                    items[position].isFavourite = true
                    FavouriteData.listData.add(item)
                }
            }

//            holder.detailsButton.setOnClickListener {
//                Intent(con, DetailInfoAboutMovieActivity::class.java).apply {
//                    putExtra(
//                        DetailInfoAboutMovieActivity.EXTRA_MOVIE,
//                        DetailsInfoAboutMovie(MovieInfo.findItemByName(item.title), Color.RED)
//                    )
//                    startActivityForResult(con as Activity, 1)
//                }
//            }
            holder.detailsButton.setOnClickListener {
                clickListener.onDetailsClick(item, position)
            }
        }
    }

    interface MoviesClickListener {
        fun onDetailsClick(moviesItem: MoviesItem, position: Int)
    }
}