package com.sfedorchuk.data

import android.os.Parcelable
import com.sfedorchuk.activity.MainActivity
import kotlinx.android.parcel.Parcelize

@Parcelize
class DetailsInfoAboutMovie(val name: MainActivity.MovieInfo?, val actualColor: Int) : Parcelable