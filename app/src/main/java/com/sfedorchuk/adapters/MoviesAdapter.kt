package com.sfedorchuk.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sfedorchuk.R
import com.sfedorchuk.activity.MainActivity
import com.sfedorchuk.view.MoviesItem
import com.sfedorchuk.view_holders.MoviesVH

class MoviesAdapter(
    private val items: ArrayList<MoviesItem>,
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
            if (MainActivity.IS_FAVORITE_SCREEN) {
                if (item.isFavourite) {
                    holder.bind(item)
                    holder.imageFavourite.setBackgroundResource(R.drawable.ic_baseline_clear_24)
                }
            } else {
                holder.bind(item)
            }

            holder.imageFavourite.setOnClickListener {
                clickListener.onFavoriteDeleteClick(item, position)
                if (MainActivity.IS_FAVORITE_SCREEN) {
                    items.removeAt(position)
                    notifyDataSetChanged()
                } else {
                    notifyItemChanged(position)
                }
            }

            holder.detailsButton.setOnClickListener {
                clickListener.onDetailsClick(item, position)
            }
        }
    }

    interface MoviesClickListener {
        fun onDetailsClick(moviesItem: MoviesItem, position: Int)
        fun onFavoriteDeleteClick(moviesItem: MoviesItem, position: Int)
    }
}