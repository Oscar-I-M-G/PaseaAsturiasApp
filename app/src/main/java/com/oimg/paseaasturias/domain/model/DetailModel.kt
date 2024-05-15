package com.oimg.paseaasturias.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class DetailModel(
    val images: String,
    val slideTitulo: String
): Parcelable