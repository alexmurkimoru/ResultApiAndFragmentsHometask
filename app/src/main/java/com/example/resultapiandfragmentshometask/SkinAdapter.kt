package com.example.resultapiandfragmentshometask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.resultapiandfragmentshometask.databinding.SkinItemBinding

class SkinAdapter : RecyclerView.Adapter<SkinAdapter.SkinHolder>() {
    var chosenPic: Int = R.drawable.frog1
        private set

    private val skinList = mutableListOf<Skin>()
    private var positionOfTruth = 0
    private var previousPositionOfTruth = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkinHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.skin_item, parent, false)
        return SkinHolder(view)
    }

    override fun getItemCount(): Int {
        return skinList.size
    }

    override fun onBindViewHolder(holder: SkinHolder, position: Int) {
        holder.bind(position)
    }

    fun setListContent(images: List<Skin>) {
        skinList.clear()
        skinList.addAll(images)
        notifyDataSetChanged()
    }

    inner class SkinHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = SkinItemBinding.bind(item)
        fun bind(position: Int) {
            binding.radioButton.text = skinList[position].name
            binding.skinPic.setImageResource(skinList[position].imageId)
            binding.radioButton.setOnCheckedChangeListener(null)
            binding.radioButton.isChecked = position == positionOfTruth
            binding.radioButton.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    previousPositionOfTruth = positionOfTruth
                    positionOfTruth = position
                    chosenPic = skinList[position].imageId // для передачи дальше
                    notifyItemChanged(positionOfTruth)
                    notifyItemChanged(previousPositionOfTruth)
                }
            }
        }
    }
}