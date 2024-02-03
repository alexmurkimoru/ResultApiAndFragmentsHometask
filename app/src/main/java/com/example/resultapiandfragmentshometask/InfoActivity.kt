package com.example.resultapiandfragmentshometask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.resultapiandfragmentshometask.databinding.ActivityInfoBinding

class InfoActivity : AppCompatActivity() {
    lateinit var binding: ActivityInfoBinding
    private val myAdapter = SkinAdapter()

    private val skinList = listOf<Skin>(
        Skin("1", R.drawable.frog1),
        Skin("2", R.drawable.frog2),
        Skin("3", R.drawable.frog3),
        Skin("4", R.drawable.frog4),
        Skin("5", R.drawable.frog5),
        Skin("6", R.drawable.frog6),
        Skin("7", R.drawable.frog7),
        Skin("8", R.drawable.frog8),
        Skin("9", R.drawable.frog9),
        Skin("10", R.drawable.frog10),
        Skin("11", R.drawable.frog11),
        Skin("12", R.drawable.frog12),
        Skin("13", R.drawable.frog13),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        binding.button.setOnClickListener {
            val intent = Intent()
            intent.putExtra(EXTRA_NAME, binding.editTextText.text.toString())
            intent.putExtra(EXTRA_SKIN_ID, myAdapter.chosenPic)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    private fun init() {
        binding.apply {
            skinPickList.adapter = myAdapter
            myAdapter.setListContent(skinList)
        }
    }

    companion object {
        const val EXTRA_NAME = "name"
        const val EXTRA_SKIN_ID = "skinId"
        const val EXTRA_HUNGER = "hunger"
        const val EXTRA_JOY = "joy"
        const val EXTRA_CLEAR= "clear"
        const val EXTRA_FROG = "frog"
        const val EXTRA_STARS = "stars"
        const val EXTRA_ID = "id"
    }
}