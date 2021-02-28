package com.sfedorchuk.data

import android.graphics.Color
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MoviesItem(val title: Int, val description: Int, val image: Int, var isFavourite: Boolean = false, var color: Int = Color.BLACK) :
    Parcelable