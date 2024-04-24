package com.oimg.paseaasturias.domain

import com.oimg.paseaasturias.domain.model.SelectionModel

// voy usar el GeneralResponse
interface Repository {
    suspend fun getPreviewData(): List<SelectionModel>?
}