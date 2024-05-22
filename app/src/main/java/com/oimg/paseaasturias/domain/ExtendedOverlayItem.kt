package com.oimg.paseaasturias.domain

import org.osmdroid.views.overlay.OverlayItem

class ExtendedOverlayItem(
    title: String?,
    snippet: String?,
    point: org.osmdroid.util.GeoPoint?,
    val telefono: String,
    val web: String,
    val email: String,
    val picture: String
): OverlayItem(title,snippet,point)