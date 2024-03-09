package com.example.resultapiandfragmentshometask

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.IllegalArgumentException

//P.s. Factory is lower ⬇︎⬇︎⬇︎🐸
class CareViewModel(private val frogArg: Frog) : ViewModel() {

    private val _frog = MutableLiveData<Frog>(frogArg)
    val frog: LiveData<Frog> = _frog

    private val _hunger = MutableLiveData<Int>(frogArg.hunger)
    val hunger: LiveData<Int> = _hunger

    private val _joy = MutableLiveData<Int>(frogArg.joy)
    val joy: LiveData<Int> = _joy

    private val _clear = MutableLiveData<Int>(frogArg.clear)
    val clear: LiveData<Int> = _clear

    private val _levelOfHappiness = MutableLiveData<Int>(frogArg.starsId)
    val levelOfHappiness: LiveData<Int> = _levelOfHappiness

    fun play() = increaseValue(_joy)

    fun feed() = increaseValue(_hunger)

    fun clean() = increaseValue(_clear)


    private fun increaseValue(data: MutableLiveData<Int>) {
        if (data.value != null && data.value!! < 5) {
            data.value = data.value!! + 1
            Log.d("MyLog", "hey hey its ${data.value}")
            updateLevelOfHappiness()
        }
    }


    private fun indicateStarPic(level: Int): Int {
        Thread.sleep(5000)
        return when (level) {
            in 0..1 -> R.drawable.zero_star
            in 2..4 -> R.drawable.one_star
            in 5..7 -> R.drawable.two_stars
            in 8..10 -> R.drawable.three_stars
            in 11..13 -> R.drawable.four_stars
            in 14..15 -> R.drawable.five_stars
            else -> throw IllegalArgumentException("unexpected happines score!")
        }
    }

    @SuppressLint("CheckResult")
    private fun updateLevelOfHappiness() {
        Observable.just(_clear, _joy, _hunger)
            .subscribeOn(Schedulers.computation())
            .map {
                it.value?.toInt() ?: 0
            }
            .buffer(3)
            .map { it.sum() }
            .map {
                indicateStarPic(it)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                _levelOfHappiness.postValue(it)
            }
    }

}

class CareViewModelFactory(private val frogArg: Frog) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CareViewModel::class.java)) {
            return CareViewModel(frogArg) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel")
        }

    }
}

