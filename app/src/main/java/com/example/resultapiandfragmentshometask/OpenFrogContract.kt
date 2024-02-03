package com.example.resultapiandfragmentshometask

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.result.contract.ActivityResultContract
import androidx.core.os.bundleOf

class OpenFrogContract : ActivityResultContract<Frog, Intent?>() {
    override fun createIntent(context: Context, input: Frog): Intent {
        Log.d("kek", "ще сделаем интент")
        val intent: Intent = Intent(context, CareActivity::class.java)
        intent.putExtra(InfoActivity.EXTRA_NAME, input.name)
        intent.putExtra(InfoActivity.EXTRA_SKIN_ID, input.skinId)
        intent.putExtra(InfoActivity.EXTRA_HUNGER, input.hunger)
        intent.putExtra(InfoActivity.EXTRA_JOY, input.joy)
        intent.putExtra(InfoActivity.EXTRA_CLEAR, input.clear)
        Log.d("kek", "Интент готов")
        return intent
    }

    override fun parseResult(resultCode: Int, result: Intent?): Intent? {
        Log.d("kek", "The star id is ${result?.getIntExtra(InfoActivity.EXTRA_STARS, 666)}")
        return result
    }


}