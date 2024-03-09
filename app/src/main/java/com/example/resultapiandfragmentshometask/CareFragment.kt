package com.example.resultapiandfragmentshometask

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import com.example.resultapiandfragmentshometask.com.example.resultapiandfragmentshometask.Parameter
import com.example.resultapiandfragmentshometask.databinding.FragmentCareBinding


class CareFragment : Fragment() {
    private var _binding: FragmentCareBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CareViewModel
    private lateinit var viewModelFactory: CareViewModelFactory
    private lateinit var frog: Frog
    private var position = -1
    private var fragmentsOpenerActivity: FragmentsOpener? = null

    init {
        Log.d("MyLog", "init")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("MyLog", "OnAttach")
        fragmentsOpenerActivity = activity as FragmentsOpener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("MyLog", "OnCreateView")
        _binding = FragmentCareBinding.inflate(inflater, container, false)
        frog = arguments?.getSerializable(ARG_FROG) as Frog
        position = arguments?.getInt(ARG_POSITION, 0)!!
        viewModelFactory = CareViewModelFactory(frog!!)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CareViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        nameText.text = frog.name
        frogPic.setImageResource(frog.skinId)

        val parameters: List<Parameter> = listOf(
            Parameter(
                playScore,
                viewModel.joy,
                playButton,
                viewLifecycleOwner,
                viewModel::play
            ),
            Parameter(
                foodScore,
                viewModel.hunger,
                feedButton,
                viewLifecycleOwner,
                viewModel::feed
            ),
            Parameter(
                clenScore,
                viewModel.clear,
                cleanButton,
                viewLifecycleOwner,
                viewModel::clean
            )
        )
        for (i in parameters) {
            i.launchParameterFunctions()
        }
        viewModel.levelOfHappiness.observe(viewLifecycleOwner) { newPic ->
            starPic.setImageResource(newPic)
        }
        backButton.setOnClickListener {
            val newFrog = Frog(
                frog.name,
                frog.skinId,
                viewModel.hunger.value ?: 0,
                viewModel.joy.value ?: 0,
                viewModel.clear.value ?: 0,
                viewModel.levelOfHappiness.value!!
            )
            fragmentsOpenerActivity?.onRestartMainFragmentAfterCare(newFrog, position ?: 0)
        }


    }


    companion object {
        private const val ARG_FROG = "arg_frog"
        private const val ARG_POSITION = "arg_position"

        @JvmStatic
        fun newInstance(frog: Frog, position: Int): CareFragment {
            Log.d("MyLog", "NewInstance\tfrog = ${frog.name}\tposition = $position\")")
            val fragment = CareFragment()
            Log.d("MyLog", "NewInstance obj is done)")
            fragment.arguments = bundleOf(ARG_FROG to frog, ARG_POSITION to position)
            Log.d("MyLog", "NewInstance arguments are filled")
            return fragment
        }
    }

}