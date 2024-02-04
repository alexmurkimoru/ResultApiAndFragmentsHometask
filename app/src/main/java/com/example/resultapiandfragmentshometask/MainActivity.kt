package com.example.resultapiandfragmentshometask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.resultapiandfragmentshometask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {//, StarPicReturnable {

    private lateinit var binding: ActivityMainBinding

    private val myAdapter = FrogAdapter(::launchContract)
//    private var starPicFromActivity = 0
//    private var intentFromCareActivity: Intent? = null
    private val contract = registerForActivityResult(CreateFrogContract()) {
        // it - объект Frog  который собирается в parseReult()
        // описываем callback, Коллбек сработает при получении результата.
        myAdapter.addFrog(it)
    }
    private val starContract = registerForActivityResult(OpenFrogContract()) {
        myAdapter.replaceFrog(it.first, it.second)
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

    private fun launchContract(inputFrog: FrogWithPosition) {
        starContract.launch(inputFrog)
    }

//    override fun returnStarPic(): Int {
//        return starPicFromActivity
//    }
//
//    override fun launchContractForAdapter(frog: Frog) {
//        starContract.launch(frog)
//    }
//
//    override fun returnIntent(): Intent? = intentFromCareActivity
}