package com.example.resultapiandfragmentshometask

import androidx.annotation.DrawableRes
import java.io.Serializable

data class Frog(
    val name: String = "testName",
    @DrawableRes val skinId: Int = 0,
    var hunger: Int = 3,
    var joy: Int = 3,
    var clear: Int = 3,
    @DrawableRes var starsId: Int = R.drawable.three_stars,
) : Serializable
