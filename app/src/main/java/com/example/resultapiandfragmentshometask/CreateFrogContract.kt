package com.example.resultapiandfragmentshometask

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

class CreateFrogContract : ActivityResultContract<Unit, Frog>() {
    //входные и выходные данные, т.к. по итогу нам нужно получить объект
//    класса Frog, чтобы потом заебашить его в адаптер, то выходной тип как раз Frog,
//    а на вход нам ничего не надо

    override fun createIntent(context: Context, input: Unit): Intent =
        //принимает входные данные и создает интент, который будет в дальнейшем запущен вызовом launch()
        Intent(context, InfoActivity::class.java)

    override fun parseResult(resultCode: Int, result: Intent?): Frog {
        //отвечает за возврат результата, обработку resultCode и парсинг данных
        return Frog(
            result?.getStringExtra(InfoActivity.EXTRA_NAME) ?: "xyi",
            skinId = result?.getIntExtra(InfoActivity.EXTRA_SKIN_ID, 0) ?: 0
        )
    }
}
