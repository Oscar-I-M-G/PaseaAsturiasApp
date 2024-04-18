package com.oimg.paseaasturias.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.oimg.paseaasturias.databinding.FragmentMapBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapFragment : Fragment() {
    //  -   -   -   -   View Model  -   -   -   -   -   -
    private val mapViewModel by viewModels<MapViewModel>()

    //  -   -   -   -   -   -   -   -   -   -   -   -   -
    //  -   -   -   -   -   Binding -   -   -   -   -   -
    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!

    //  -   -   -   -   -   -   -   -   -   -   -   -   -
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMapBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}