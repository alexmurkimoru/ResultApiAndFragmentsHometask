package com.example.resultapiandfragmentshometask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.resultapiandfragmentshometask.databinding.ActivityCareBinding

class CareActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCareBinding
    private var starPicId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("kek", "мы в онкриэйте")
        super.onCreate(savedInstanceState)
        binding = ActivityCareBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.nameText.text = intent.getStringExtra(InfoActivity.EXTRA_NAME)
        binding.frogPic.setImageResource(
            intent.getIntExtra(
                InfoActivity.EXTRA_SKIN_ID,
                R.drawable.frog10
            )
        )
        binding.foodScore.text = intent.getIntExtra(InfoActivity.EXTRA_HUNGER, 3).toString()
        binding.playScore.text = intent.getIntExtra(InfoActivity.EXTRA_JOY, 3).toString()
        binding.clenScore.text = intent.getIntExtra(InfoActivity.EXTRA_CLEAR, 3).toString()
        happinessUpdate()
        val buttons = mapOf<Button, TextView>(
            binding.playButton to binding.playScore,
            binding.feedButton to binding.foodScore,
            binding.cleanButton to binding.clenScore
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

    private fun happinessUpdate() = with(binding) {
        var happinessLevel = getScore(foodScore) + getScore(playScore) + getScore(clenScore)
        if (happinessLevel in 0..1) {
            starPicId = R.drawable.zero_star
            starPic.setImageResource(starPicId)
            return
        }
        if (happinessLevel in 2..4) {
            starPicId = R.drawable.one_star
            starPic.setImageResource(starPicId)
            return
        }
        if (happinessLevel in 5..7) {
            starPicId = R.drawable.two_stars
            starPic.setImageResource(starPicId)
            return
        }
        if (happinessLevel in 8..10) {
            starPicId = R.drawable.three_stars
            starPic.setImageResource(starPicId)
            return
        }
        if (happinessLevel in 11..13) {
            starPicId = R.drawable.four_stars
            starPic.setImageResource(starPicId)
            return
        }
        if (happinessLevel in 14..15) {
            starPicId = R.drawable.five_stars
            starPic.setImageResource(starPicId)
            return
        }

    }

    private fun increaseScore(view: TextView) {
        if (getScore(view) == 5) {
            return
        }
        view.text = (getScore(view) + 1).toString()
        happinessUpdate()
    }

    private fun getScore(view: TextView) = view.text?.toString()?.toInt() ?: 0
}