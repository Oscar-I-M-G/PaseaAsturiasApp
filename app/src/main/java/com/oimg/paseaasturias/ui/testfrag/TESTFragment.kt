package com.oimg.paseaasturias.ui.testfrag

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.oimg.paseaasturias.R
import com.oimg.paseaasturias.databinding.FragmentSelectionBinding
import com.oimg.paseaasturias.databinding.FragmentTESTBinding


class TESTFragment : Fragment() {


    //  -   -   -   -   -   Binding -   -   -   -   -   -
    private var _binding: FragmentTESTBinding? = null
    private val binding get() = _binding!!
    //  -   -   -   -   -   -   -   -   -   -   -   -   -


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTESTBinding.inflate(layoutInflater,container,false)
        return binding.root
    }


}