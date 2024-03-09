package com.example.resultapiandfragmentshometask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class InfoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button = view.findViewById<Button>(R.id.vghuh_button)
        val frogName = view.findViewById<EditText>(R.id.frog_name_edit_text).text.toString()
        button.setOnClickListener {
            val frogName = view.findViewById<EditText>(R.id.frog_name_edit_text).text.toString()
            if (frogName != "") {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.frahment_holder, FaceFragment.newInstance(frogName))
                    .commitNow()
            } else {
                val myToast = Toast.makeText(activity, R.string.no_name_toast_text, Toast.LENGTH_SHORT)
                myToast.show()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = InfoFragment()

    }
}