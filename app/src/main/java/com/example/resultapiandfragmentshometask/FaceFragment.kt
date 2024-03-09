package com.example.resultapiandfragmentshometask

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.resultapiandfragmentshometask.databinding.FragmentFaceBinding


class FaceFragment : Fragment() {
    private var _binding: FragmentFaceBinding? = null
    private val binding get() = _binding!!
    private val myAdapter = SkinAdapter()
    private var fragmentsOpenerActivity: FragmentsOpener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentsOpenerActivity = activity as FragmentsOpener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFaceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.skinPickList.adapter = myAdapter
        myAdapter.setListContent(skinList)

        binding.vzhuh2Button.setOnClickListener {
            fragmentsOpenerActivity?.onRestartMainFragmentAfterCreation(Frog(name = arguments?.getString(ARG_NAME) ?: "null", skinId = myAdapter.chosenPic))
        }
    }

    companion object {
        private const val ARG_NAME = "arg_name"

        @JvmStatic
        fun newInstance(frogName: String): FaceFragment {
            val fragment = FaceFragment()
            fragment.arguments = bundleOf(ARG_NAME to frogName)
            return fragment
        }


    }
}

val skinList = listOf<Skin>(
    Skin("1", R.drawable.frog1),
    Skin("2", R.drawable.frog2),
    Skin("3", R.drawable.frog3),
    Skin("4", R.drawable.frog4),
    Skin("5", R.drawable.frog5),
    Skin("6", R.drawable.frog6),
    Skin("7", R.drawable.frog7),
    Skin("8", R.drawable.frog8),
    Skin("9", R.drawable.frog9),
    Skin("10", R.drawable.frog10),
    Skin("11", R.drawable.frog11),
    Skin("12", R.drawable.frog12),
    Skin("13", R.drawable.frog13),
)