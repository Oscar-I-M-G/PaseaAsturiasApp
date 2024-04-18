package com.oimg.paseaasturias.domain.model

import com.oimg.paseaasturias.R

/**
 * Aqui categorizamos las actividades
 * */
sealed class PursuitInfo (val image:Int, val name:Int){
    object WaterActivities:PursuitInfo(R.drawable.ic_water,R.string.activity_name_water)
    object MountainActivities:PursuitInfo(R.drawable.ic_mountain,R.string.activity_name_mountain)
    object AdventureActivities:PursuitInfo(R.drawable.ic_adventure,R.string.activity_name_adventure)
    object SportActivities:PursuitInfo(R.drawable.ic_sport,R.string.activity_name_sports)
    object NatureActivities:PursuitInfo(R.drawable.ic_nature,R.string.activity_name_nature)
    object MiscActivities:PursuitInfo(R.drawable.ic_misc,R.string.activity_name_misc)
}