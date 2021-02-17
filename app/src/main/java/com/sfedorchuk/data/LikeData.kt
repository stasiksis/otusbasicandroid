package com.sfedorchuk.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LikeData(val checkboxValue: Boolean, val comment: String) : Parcelable