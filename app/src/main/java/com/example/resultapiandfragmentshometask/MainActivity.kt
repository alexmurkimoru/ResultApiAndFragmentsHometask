package com.example.resultapiandfragmentshometask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.resultapiandfragmentshometask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), FragmentsOpener{

    private lateinit var binding: ActivityMainBinding
    private val myAdapter = FrogAdapter(this as FragmentsOpener)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.frahment_holder, MainFragment.newInstance(myAdapter))
                .commitNow()
        }

    }

    override fun onRestartMainFragmentAfterCreation(frog: Frog) {
        myAdapter.addFrog(frog)
        Log.d("MyLog","fun onRestartMainFragment")
        supportFragmentManager.beginTransaction()
            .replace(R.id.frahment_holder, MainFragment.newInstance(myAdapter))
            .commitNow()
    }

    override fun onStartCareFragment(frog: Frog, position: Int) {
        Log.d("MyLog","fun onStartCareFragment")
        supportFragmentManager.beginTransaction()
            .replace(R.id.frahment_holder, CareFragment.newInstance(frog, position))
            .commitNow()

    }

    override fun onRestartMainFragmentAfterCare(frog: Frog, position: Int) {
        Log.d("MyLog","fun onRestartMainFragmentAfterCare")
        myAdapter.updateFrogData(position, frog)
        supportFragmentManager.beginTransaction()
            .replace(R.id.frahment_holder, MainFragment.newInstance(myAdapter))
            .commitNow()
    }

}