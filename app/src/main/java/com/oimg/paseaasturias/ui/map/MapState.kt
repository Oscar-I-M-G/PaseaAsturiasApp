package com.oimg.paseaasturias.ui.map

import com.oimg.paseaasturias.domain.model.MapModel

sealed class MapState {
    data object Loading:MapState()
    data class Error(val error:String):MapState()
    data class Success(val data: List<MapModel>?):MapState()
}