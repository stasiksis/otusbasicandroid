package com.sfedorchuk.view_holders

import android.app.Application
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sfedorchuk.R
import com.sfedorchuk.data.FavouriteData
import com.sfedorchuk.view.MoviesItem
import kotlinx.android.synthetic.main.item_movies.view.*

class MoviesFavoriteVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val titleTv: TextView = itemView.findViewById(R.id.titleTv)
    val imageView: ImageView = itemView.findViewById(R.id.image)
    val imageDeleteView: ImageView = itemView.findViewById(R.id.delete)
    val detailsButton: Button = itemView.findViewById(R.id.details)

    fun bind(item: MoviesItem) {
        titleTv.text = item.title
        imageView.setImageDrawable(item.image)
    }
}