package com.oimg.paseaasturias.ui.selection

import com.oimg.paseaasturias.domain.model.SelectionModel

// Trabajamos con estaddos
sealed class SelectionState {
    data object Loading:SelectionState()
    data class Error(val error:String):SelectionState()
    data class Success(val data: List<SelectionModel>?):SelectionState()
}