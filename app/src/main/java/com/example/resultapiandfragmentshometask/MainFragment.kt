package com.example.resultapiandfragmentshometask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.resultapiandfragmentshometask.databinding.FragmentMainBinding


class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.myList.adapter = arguments?.getSerializable(ARGS_ADAPTER) as FrogAdapter
        binding.addButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.frahment_holder, InfoFragment.newInstance())
                .commitNow()
        }
    }

    companion object {
        private const val ARGS_ADAPTER = "args_adapter"

        @JvmStatic
        fun newInstance(adapter: FrogAdapter) : MainFragment {
            val fragment = MainFragment()
            fragment.arguments = bundleOf(ARGS_ADAPTER to adapter)
            return fragment
        }
    }
}