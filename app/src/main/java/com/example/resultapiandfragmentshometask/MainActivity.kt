package com.example.resultapiandfragmentshometask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.resultapiandfragmentshometask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val myAdapter = FrogAdapter()
    private val contract = registerForActivityResult(CreateFrogContract()) {
        // it - объект Frog  который собирается в parseReult()
        // описываем callback, Коллбек сработает при получении результата.
        myAdapter.addFrog(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.myList.adapter = myAdapter
        binding.addButton.setOnClickListener {
            contract.launch(Unit)
        }
    }


}