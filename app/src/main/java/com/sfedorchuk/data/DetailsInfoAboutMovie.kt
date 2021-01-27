package com.sfedorchuk.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class DetailsInfoAboutMovie(val name: MovieInfo?, val actualColor: Int) : Parcelable