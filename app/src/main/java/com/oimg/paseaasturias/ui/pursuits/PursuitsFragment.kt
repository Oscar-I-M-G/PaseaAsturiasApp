package com.oimg.paseaasturias.ui.pursuits

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.oimg.paseaasturias.databinding.FragmentPursuitsBinding
import com.oimg.paseaasturias.ui.pursuits.adapter.PursuitsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PursuitsFragment : Fragment() {
    //  -   -   -   -   View Model  -   -   -   -   -   -
    private val pursuitsViewModel by viewModels<PursuitsViewModel>()

    //  -   -   -   -   -   -   -   -   -   -   -   -   -
    //  -   -   -   -   Adapter -   -   -   -   -   -   -
    private lateinit var adapter: PursuitsAdapter

    //  -   -   -   -   -   -   -   -   -   -   -   -   -
    //  -   -   -   -   -   Binding -   -   -   -   -   -
    private var _binding: FragmentPursuitsBinding? = null
    private val binding get() = _binding!!

    //  -   -   -   -   -   -   -   -   -   -   -   -   -
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeUI()
    }

    //  -   -   -   -   Inicializamos el UI -   -   -   -
    private fun initializeUI() {
        initlist()
        initUIState()
    }

    //  -   -   -   -   -   -   -   -   -   -   -   -   -
    //  -   -   -   -   Inicializamos Lista -   -   -   -
    private fun initlist() {
        adapter = PursuitsAdapter()
        binding.rvPursuits.layoutManager =
            GridLayoutManager(context,  2)
        binding.rvPursuits.adapter = adapter
    }

    //  -   -   -   -   -   -   -   -   -   -   -   -   -
    //  -   -   -   -   Inicialicion de Estado  -   -   -
    private fun initUIState() {
        // esto es una corrutina que se engancha al ciclo de vida del fragmento
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                pursuitsViewModel.pursuit.collect {
                    // Cambios en HOROSCOPE
                    adapter.updateList(it)
                    Log.i("Ivan", it.toString())
                }
            }
        }
    }
    //  -   -   -   -   -   -   -   -   -   -   -   -   -

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPursuitsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


}