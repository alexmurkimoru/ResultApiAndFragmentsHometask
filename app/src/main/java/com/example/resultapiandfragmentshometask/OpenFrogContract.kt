package com.example.resultapiandfragmentshometask

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.result.contract.ActivityResultContract
import androidx.core.os.bundleOf

typealias FrogWithPosition = Pair<Int, Frog>

class OpenFrogContract : ActivityResultContract<FrogWithPosition, FrogWithPosition>() { //: ActivityResultContract<Frog, Intent?>() {

    private var frog: FrogWithPosition? = null

    override fun createIntent(context: Context, input: Pair<Int, Frog>): Intent {
        Log.d("kek", "ще сделаем интент")
        frog = input
        val intent = Intent(context, CareActivity::class.java)
        intent.putExtra(InfoActivity.EXTRA_NAME, input.second.name)
        intent.putExtra(InfoActivity.EXTRA_SKIN_ID, input.second.skinId)
        intent.putExtra(InfoActivity.EXTRA_HUNGER, input.second.hunger)
        intent.putExtra(InfoActivity.EXTRA_JOY, input.second.joy)
        intent.putExtra(InfoActivity.EXTRA_CLEAR, input.second.clear)
        Log.d("kek", "Интент готов")
        return intent
    }

//    override fun parseResult(resultCode: Int, result: Intent?): Intent? {
//        Log.d("kek", "The star id is ${result?.getIntExtra(InfoActivity.EXTRA_STARS, 666)}")
//        return result
//    }

    override fun parseResult(resultCode: Int, intent: Intent?): FrogWithPosition = intent?.let {
        (frog?.first ?: -1) to Frog(
            frog?.second?.name ?: "null",
            frog?.second?.skinId ?: R.drawable.frog5,
            it.getIntExtra(InfoActivity.EXTRA_JOY, -1),
            it.getIntExtra(InfoActivity.EXTRA_HUNGER, -1),
            it.getIntExtra(InfoActivity.EXTRA_CLEAR, -1),
            it.getIntExtra(InfoActivity.EXTRA_STARS, -1),
        )
    } ?: throw NullPointerException("lol kek")
}
