package com.example.resultapiandfragmentshometask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.resultapiandfragmentshometask.databinding.FrogItemBinding

class FrogAdapter : RecyclerView.Adapter<FrogAdapter.FrogHolder>() {
    private val frogList = ArrayList<Frog>()

    class FrogHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = FrogItemBinding.bind(item)
        fun bind(frog: Frog) = with(binding) {
            myNameText.text = frog.name
            myAgeText.text = frog.age.toString()
            frogAppearance.setImageResource(frog.skinId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FrogHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.frog_item, parent, false)
        return FrogHolder(view)
    }

    override fun getItemCount(): Int {
        return frogList.size
    }

    override fun onBindViewHolder(holder: FrogHolder, position: Int) {
        holder.bind(frogList[position])
    }

    fun addFrog(frog: Frog) {
        frogList.add(frog)
        notifyItemInserted(frogList.size - 1)
    }
}