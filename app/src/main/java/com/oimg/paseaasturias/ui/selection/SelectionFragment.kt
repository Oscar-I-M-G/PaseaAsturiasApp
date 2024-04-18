package com.oimg.paseaasturias.ui.selection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.oimg.paseaasturias.databinding.FragmentSelectionBinding
import com.oimg.paseaasturias.ui.map.MapViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectionFragment : Fragment() {
    //  -   -   -   -   View Model  -   -   -   -   -   -
    private val SelectionViewModel by viewModels<SelectionViewModel>()

    //  -   -   -   -   -   -   -   -   -   -   -   -   -

    //  -   -   -   -   -   Binding -   -   -   -   -   -
    private var _binding: FragmentSelectionBinding? = null
    private val binding get() = _binding!!
    //  -   -   -   -   -   -   -   -   -   -   -   -   -

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectionBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}