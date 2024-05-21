package com.oimg.paseaasturias.ui.pursuits

import androidx.lifecycle.ViewModel
import com.oimg.paseaasturias.data.providers.PursuitProvider
import com.oimg.paseaasturias.domain.model.PursuitInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PursuitsViewModel @Inject constructor(pursuitProvider: PursuitProvider):ViewModel(){
    //  -   -   -   -   -   -   FLOW    -   -   -   -   -   -   -   -   -   -   -
    private var _pursuit = MutableStateFlow<List<PursuitInfo>>(emptyList())
    val pursuit:StateFlow<List<PursuitInfo>> = _pursuit
    //  -   -   -   -   -   -   -   -   -   -   -   -   -   -   -   -   -   -   -
    init {
        _pursuit.value = pursuitProvider.getPursuits()
    }
}