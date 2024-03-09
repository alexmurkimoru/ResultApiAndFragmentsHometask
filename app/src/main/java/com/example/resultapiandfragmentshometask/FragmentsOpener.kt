package com.example.resultapiandfragmentshometask

interface FragmentsOpener {
    fun onRestartMainFragmentAfterCreation(frog: Frog)
    fun onStartCareFragment(frog: Frog, position: Int)

    fun onRestartMainFragmentAfterCare(frog: Frog, position: Int)

}