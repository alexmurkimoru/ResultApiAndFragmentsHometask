package com.example.resultapiandfragmentshometask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.annotation.DrawableRes
import com.example.resultapiandfragmentshometask.databinding.ActivityCareBinding
import java.lang.IllegalArgumentException

class CareActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCareBinding
    private var starPicId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("kek", "мы в онкриэйте")
        super.onCreate(savedInstanceState)
        binding = ActivityCareBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val frog = intent.getSerializableExtra(InfoActivity.EXTRA_FROG) as Frog
        with (binding) {
            nameText.text = intent.getStringExtra(InfoActivity.EXTRA_NAME)
            frogPic.setImageResource(frog.skinId)
            foodScore.text = frog.hunger.toString()
            playScore.text = intent.getIntExtra(InfoActivity.EXTRA_JOY, 3).toString()
            clenScore.text = intent.getIntExtra(InfoActivity.EXTRA_CLEAR, 3).toString()
            happinessUpdate()
            val buttons = mapOf(
                playButton to playScore,
                feedButton to foodScore,
                cleanButton to clenScore
            )
            for ((key, value) in buttons) {
                key.setOnClickListener {
                    increaseScore(value)
                }
            }

            binding.backButton.setOnClickListener {
                val intent = Intent()
                intent.putExtra(InfoActivity.EXTRA_STARS, starPicId)
                intent.putExtra(InfoActivity.EXTRA_JOY, getScore(binding.playScore))
                intent.putExtra(InfoActivity.EXTRA_HUNGER, getScore(binding.foodScore))
                intent.putExtra(InfoActivity.EXTRA_CLEAR, getScore(binding.clenScore))
                Log.d("kek", "The star id is ${intent.getIntExtra(InfoActivity.EXTRA_STARS, 666)}")
                setResult(RESULT_OK, intent)
                finish()
            }
        }

    }

    private fun happinessUpdate() = when (getScore(binding.foodScore) + getScore(binding.playScore) + getScore(binding.clenScore)) {
        in 0..1 -> setNewPic(R.drawable.zero_star)
        in 2..4 -> setNewPic(R.drawable.one_star)
        in 5..7 -> setNewPic(R.drawable.two_stars)
        in 8..10 -> setNewPic(R.drawable.three_stars)
        in 11..13 -> setNewPic(R.drawable.four_stars)
        in 14..15 -> setNewPic(R.drawable.five_stars)
        else -> throw IllegalArgumentException("unexpected happines score!")
    }

    private fun increaseScore(view: TextView) {
        if (getScore(view) == 5) {
            return
        }
        view.text = (getScore(view) + 1).toString()
        happinessUpdate()
    }

    private fun setNewPic(@DrawableRes newStarPicId: Int) {
        starPicId = newStarPicId
        binding.starPic.setImageResource(starPicId)
    }

    private fun getScore(view: TextView) = view.text?.toString()?.toInt() ?: 0
}