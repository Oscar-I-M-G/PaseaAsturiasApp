package com.oimg.paseaasturias.ui.selection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.oimg.paseaasturias.databinding.FragmentSelectionBinding
import com.oimg.paseaasturias.ui.selection.adapter.SelectionAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SelectionFragment : Fragment() {
    //  -   -   -   -   View Model  -   -   -   -   -   -
    private val selectionViewModel by viewModels<SelectionViewModel>()

    //  -   -   -   -   -   -   -   -   -   -   -   -   -
    //  -   -   -   -   -   Adapter -   -   -   -   -   -
    private lateinit var adapter: SelectionAdapter
    //  -   -   -   -   -   -   -   -   -   -   -   -   -
    //  -   -   -   -   -   Binding -   -   -   -   -   -
    private var _binding: FragmentSelectionBinding? = null
    private val binding get() = _binding!!
    //  -   -   -   -   -   -   -   -   -   -   -   -   -

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectionViewModel.getSelectionList()         // obtendremos lo del viewModel
        initializeUI()
    }
    //  -   -   -   -   -   Inicializamos la UI -   -   -
    private fun initializeUI() {
        initUIState()
    }
    //  -   -   -   -   -   -   -   -   -   -   -   -   -
    //  -   -   -   -   Inicializacion de Estado  -   -   -
    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                selectionViewModel.state.collect {
                    when(it){
                        is SelectionState.Error -> errorState()
                        SelectionState.Loading -> loadingState()
                        is SelectionState.Success -> succesState(it)
                    }
                }
            }
        }
    }
    //  -   -   -   -   -   -   -   -   -   -   -   -   -

    private fun loadingState(){
        binding.pbSelection.isVisible = true
    }

    private fun succesState(selectionState: SelectionState.Success) {
        binding.pbSelection.isVisible = false
        adapter = SelectionAdapter()
        binding.rvSelection.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvSelection.adapter = adapter
        selectionState.data?.let { adapter.updateList(it) }
    }
    override fun onResume() {
        super.onResume()
    }

    private fun errorState(){
        binding.pbSelection.isVisible = false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectionBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}