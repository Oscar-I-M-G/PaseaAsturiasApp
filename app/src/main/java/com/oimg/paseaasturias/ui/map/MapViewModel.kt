package com.oimg.paseaasturias.ui.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oimg.paseaasturias.domain.usecase.GetMapUseCase
import com.oimg.paseaasturias.domain.usecase.GetSelectionUseCase
import com.oimg.paseaasturias.ui.selection.SelectionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.osmdroid.util.GeoPoint
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(private val getMapUseCase: GetMapUseCase):ViewModel() {
    //  -   -   -   -   -   FLOW    -   -   -   -   -   -   -
    private val _state = MutableStateFlow<MapState>(MapState.Loading)
    val state: StateFlow<MapState> = _state
    //  -   -   -   -   -   -   -   -   -   -   -   -   -   -
    //  -   -   -   -   DATA  BOOLEAN -   -   -   -   -   -   -
    private var isDataFetched = false // para checar si los datos ya se consiguieron

    fun getMapList() {
        if(!isDataFetched){
        viewModelScope.launch {
            _state.value = MapState.Loading
            val result = withContext(Dispatchers.IO) { getMapUseCase() } // hilo secundario
            if (result != null) {
                _state.value = MapState.Success(result)
                isDataFetched = true
            } else {
                _state.value = MapState.Error("Error loading map data")
            }
        }
        }
    }
}