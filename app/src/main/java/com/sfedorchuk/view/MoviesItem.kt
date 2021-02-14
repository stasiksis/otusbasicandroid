package com.sfedorchuk.view

import android.graphics.Color
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class MoviesItem(val title: Int, val description: Int, val image: Int, var isFavourite: Boolean = false, var color: Int = Color.BLACK) :
    Parcelable