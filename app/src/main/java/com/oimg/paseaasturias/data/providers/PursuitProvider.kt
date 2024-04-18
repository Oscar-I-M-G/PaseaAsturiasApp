package com.oimg.paseaasturias.data.providers

import com.oimg.paseaasturias.domain.model.PursuitInfo.*
import com.oimg.paseaasturias.domain.model.PursuitInfo
import javax.inject.Inject

class PursuitProvider @Inject constructor() {
    fun getPursuits(): List<PursuitInfo>{
        return listOf(
            WaterActivities,
            MountainActivities,
            AdventureActivities,
            SportActivities,
            NatureActivities,
            MiscActivities
        )
    }
}