package com.example.resultapiandfragmentshometask

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.resultapiandfragmentshometask.databinding.FrogItemBinding

class FrogAdapter(
    private val launchContact: (FrogWithPosition) -> Unit,
) : RecyclerView.Adapter<FrogAdapter.FrogHolder>() {

    private val frogList = ArrayList<Frog>()

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

    fun replaceFrog(position: Int, frog: Frog) {
        frogList[position] = frog
        notifyItemChanged(position)
    }

    inner class FrogHolder(item: View) : RecyclerView.ViewHolder(item) {

        private val binding = FrogItemBinding.bind(item)

        fun bind(frog: Frog) = with(binding) {
            myNameText.text = frog.name
            frogAppearance.setImageResource(frog.skinId)
            binding.starPic.setImageResource(frog.starsId)

            careButton.setOnClickListener {
                launchContact(frogList.indexOf(frog) to frog)
//                activity.launchContractForAdapter(frog)
//                reloadStarPic(activity.returnStarPic())
//                updateFrogData(
//                    frog,
//                    activity.returnIntent()?.getIntExtra(InfoActivity.EXTRA_JOY, 0) ?: 0,
//                    activity.returnIntent()?.getIntExtra(InfoActivity.EXTRA_HUNGER, 0) ?: 0,
//                    activity.returnIntent()?.getIntExtra(InfoActivity.EXTRA_CLEAR, 0) ?: 0
//                )
            }
        }

//        private fun reloadStarPic(resource: Int) {
//            binding.starPic.setImageResource(resource)
//        }

//        private fun updateFrogData(
//            itemFrog: Frog,
//            newJoy: Int,
//            newHunger: Int,
//            newClear: Int
//        ) {
//            itemFrog.joy = newJoy
//            itemFrog.hunger = newHunger
//            itemFrog.clear = newClear
//        }
    }


}