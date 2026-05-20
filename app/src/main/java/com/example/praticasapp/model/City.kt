package com.example.praticasapp.model

import com.google.android.gms.maps.model.LatLng

data class City(
    val name: String,
    val weather: String? = null,
    //val location: String? = null
    val location: LatLng? = null
)