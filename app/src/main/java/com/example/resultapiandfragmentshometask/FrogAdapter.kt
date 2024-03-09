package com.example.resultapiandfragmentshometask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.resultapiandfragmentshometask.databinding.FrogItemBinding
import java.io.Serializable

class FrogAdapter(val listener: FragmentsOpener) : RecyclerView.Adapter<FrogAdapter.FrogHolder>(),
    Serializable {

    private val frogList = ArrayList<Frog>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FrogHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.frog_item, parent, false)
        return FrogHolder(view)
    }

    override fun getItemCount(): Int {
        return frogList.size
    }

    override fun onBindViewHolder(holder: FrogHolder, position: Int) {
        holder.bind(frogList[position], listener, position)
    }

    fun addFrog(frog: Frog) {
        frogList.add(frog)
        notifyItemInserted(frogList.size - 1)
    }

    fun updateFrogData(
        position: Int,
        newFrog: Frog
    ) {
        frogList[position] = newFrog
        notifyItemChanged(position)
    }

    inner class FrogHolder(item: View) : RecyclerView.ViewHolder(item) {

        private val binding = FrogItemBinding.bind(item)

        fun bind(frog: Frog, listener: FragmentsOpener, position: Int) = with(binding) {
            myNameText.text = frog.name
            frogAppearance.setImageResource(frog.skinId)
            starPic.setImageResource(frog.starsId)

            careButton.setOnClickListener {
                listener.onStartCareFragment(frog, position)
            }
        }

        private fun reloadStarPic(resource: Int) {
            binding.starPic.setImageResource(resource)
        }

    }


}