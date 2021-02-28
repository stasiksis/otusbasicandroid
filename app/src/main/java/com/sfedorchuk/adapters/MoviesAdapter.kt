package com.sfedorchuk.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sfedorchuk.R
import com.sfedorchuk.activity.MainActivity
import com.sfedorchuk.data.MoviesItem
import com.sfedorchuk.view_holders.MoviesVH

class MoviesAdapter(
    private val items: ArrayList<MoviesItem>,
    private val listener: MoviesClickListener?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MoviesVH(layoutInflater.inflate(R.layout.item_movies, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]


        if (holder is MoviesVH) {
            holder.titleTv.setTextColor(item.color)
            if (MainActivity.IS_FAVORITE_SCREEN) {
                if (item.isFavourite) {
                    holder.bind(item)
                    holder.imageFavourite.setBackgroundResource(R.drawable.ic_baseline_clear_24)
                }
            } else {
                holder.bind(item)
            }

            holder.imageFavourite.setOnClickListener {
                listener?.onFavoriteDeleteClick(item, position)
                if (MainActivity.IS_FAVORITE_SCREEN) {
                    items.removeAt(position)
                    notifyDataSetChanged()
                } else {
                    notifyItemChanged(position)
                }
            }

            holder.detailsButton.setOnClickListener {
                notifyDataSetChanged()
                listener?.onDetailsClick(item, position)
            }
        }
    }

    interface MoviesClickListener {
        fun onDetailsClick(moviesItem: MoviesItem, position: Int)
        fun onFavoriteDeleteClick(moviesItem: MoviesItem, position: Int)
    }
}