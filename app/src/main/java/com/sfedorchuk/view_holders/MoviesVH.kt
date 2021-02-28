package com.sfedorchuk.view_holders

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sfedorchuk.R
import com.sfedorchuk.data.MoviesItem

class MoviesVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleTv: TextView = itemView.findViewById(R.id.titleTv)
    val imageView: ImageView = itemView.findViewById(R.id.image)
    val imageFavourite: ImageView = itemView.findViewById(R.id.favourite)
    val detailsButton: Button = itemView.findViewById(R.id.details)

    fun bind(item: MoviesItem) {
        titleTv.text = itemView.context.resources.getString(item.title)
        imageView.setImageResource(item.image)

        if (item.isFavourite) {
            imageFavourite.setBackgroundResource(R.drawable.ic_baseline_favorite_24)
        } else {
            imageFavourite.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }
}