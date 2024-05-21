package com.oimg.paseaasturias.ui.selection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oimg.paseaasturias.domain.usecase.GetSelectionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SelectionViewModel @Inject constructor(private val getSelectionUseCase: GetSelectionUseCase):ViewModel(){
    //  -   -   -   -   -   -   FLOW    -   -   -   -   -   -   -   -   -   -   -
    private var _state = MutableStateFlow<SelectionState>(SelectionState.Loading)
    val state: StateFlow<SelectionState> = _state
    //  -   -   -   -   -   -   -   -   -   -   -   -   -   -   -   -   -   -   -
    fun getSelectionList(){
        viewModelScope.launch{
            _state.value = SelectionState.Loading
            val result = withContext(Dispatchers.IO){getSelectionUseCase()} // hilo Secundario
            // si nuestro retorno es diferente de nulo un success
            if(result !=null){
                _state.value = SelectionState.Success(result) // se pasa la lista
            }else{
                _state.value = SelectionState.Error("Esto no funcionoooo carajooo")
            }
        }
    }
}