package com.sfedorchuk.data

import com.sfedorchuk.R

enum class MovieInfo(val movieIdResource: Int, val movieName: Int, val movieDescription: Int, val movieSrc: Int) {
    INCEPTION(R.id.text_view_inception, R.string.name_inception, R.string.description_inception, R.drawable.inception),
    MR_ROBOT(R.id.text_view_mr_robot, R.string.name_mr_robot, R.string.description_mr_robot, R.drawable.mr_robot),
    IRON_MAN(R.id.text_view_iron_man, R.string.name_iron_man, R.string.description_iron_man, R.drawable.iron_man),
    X_MEN(R.id.text_view_x_men, R.string.name_x_men, R.string.description_x_men, R.drawable.x_men)
}