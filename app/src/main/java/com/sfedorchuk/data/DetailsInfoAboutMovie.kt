package com.sfedorchuk.data

import android.os.Parcelable
import com.sfedorchuk.activity.MainActivity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailsInfoAboutMovie(val movieInfo: MainActivity.MovieInfo?, val actualColor: Int) : Parcelable