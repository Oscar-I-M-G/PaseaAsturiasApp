package com.oimg.paseaasturias.domain.usecase

import com.oimg.paseaasturias.domain.Repository
import javax.inject.Inject

class GetMapUseCase @Inject constructor(private val repository: Repository){
    suspend operator fun invoke() = repository.getMapData()
}