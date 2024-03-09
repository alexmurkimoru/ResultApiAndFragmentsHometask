package com.example.resultapiandfragmentshometask.com.example.resultapiandfragmentshometask

import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

class Parameter(
    private val textView: TextView,
    private val data: LiveData<Int>,
    private val button: Button,
    private val viewLifecycleOwner : LifecycleOwner,
    private val targetFun : () -> Unit,
) {
    fun launchParameterFunctions(){
        observeLiveData()
        setOnButtonClickListener()
    }
    private fun observeLiveData(){
        data.observe(viewLifecycleOwner) { newValue ->
            textView.text = newValue.toString()
        }
    }

    private fun setOnButtonClickListener(){
        button.setOnClickListener{
            targetFun()
        }
    }
}