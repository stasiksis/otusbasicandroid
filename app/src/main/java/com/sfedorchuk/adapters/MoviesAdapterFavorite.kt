package com.sfedorchuk.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sfedorchuk.R
import com.sfedorchuk.data.FavouriteData
import com.sfedorchuk.view.MoviesItem
import com.sfedorchuk.view_holders.MoviesFavoriteVH

class MoviesAdapterFavorite(
    private val items: List<MoviesItem>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_movies_favorite, parent, false)
        return MoviesFavoriteVH(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]

        if (holder is MoviesFavoriteVH) {
            holder.bind(item)

            holder.imageDeleteView.setOnClickListener {
                items[position].isFavourite = false
                FavouriteData.listData.remove(item)
                notifyItemRemoved(position)
            }
        }
    }
}