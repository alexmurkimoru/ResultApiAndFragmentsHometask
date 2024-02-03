package com.example.resultapiandfragmentshometask

import android.content.Intent

interface StarPicReturnable {
    fun returnStarPic(): Int
    fun launchContractForAdapter(frog: Frog)

    fun returnIntent(): Intent?

}