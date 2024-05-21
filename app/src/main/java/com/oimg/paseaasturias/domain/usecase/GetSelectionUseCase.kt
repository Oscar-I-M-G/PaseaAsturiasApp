package com.oimg.paseaasturias.domain.usecase

import com.oimg.paseaasturias.domain.Repository
import javax.inject.Inject

class GetSelectionUseCase @Inject constructor(private val repository: Repository) {
    //Inyectamos el Repositorio , operator nos permite sobre escribit operaciones de esta clase
    suspend operator fun invoke() = repository.getPreviewData()

}