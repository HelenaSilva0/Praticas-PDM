package com.example.praticasapp.ui

import android.content.pm.PackageManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.praticasapp.MainViewModel
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory.defaultMarker
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberUpdatedMarkerState

@Composable
fun MapPage(modifier: Modifier = Modifier,
            viewModel: MainViewModel
) {
    val recife = remember { MarkerState(LatLng(-8.05, -34.9)) }
    val caruaru = remember { MarkerState( LatLng(-8.27, -35.98)) }
    val joaopessoa = remember { MarkerState(LatLng(-7.12, -34.84)) }

    val camPosState = rememberCameraPositionState ()

    val context = LocalContext.current

    val hasLocationPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(context,
                android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED
        )
    }

    GoogleMap (modifier = modifier.fillMaxSize(), cameraPositionState = camPosState, onMapClick = {
        viewModel.addCity(it) },
        properties = MapProperties(isMyLocationEnabled = hasLocationPermission),
        uiSettings = MapUiSettings(myLocationButtonEnabled = true)
    ) {
        viewModel.cities.forEach { city ->
            val location = city.location
            if (location != null) {
                Marker( state = rememberUpdatedMarkerState(position = location),
                    title = city.name,
                    snippet = "${location.latitude}, ${location.longitude}")
            }
        }
        Marker(
            state = recife,
            title = "Recife",
            snippet = "Marcador em Recife",
            icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)
        )

        Marker(
            state = caruaru,
            title = "caruaru",
            snippet = "Marcador em caruaru",
            icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)
        )

        Marker(
            state = joaopessoa,
            title = "joaopessoa",
            snippet = "Marcador em joaopessoa",
            icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)
        )

    }
    /*Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Companion.Gray)
            .wrapContentSize(Alignment.Companion.Center)
    ) {
        Text(
            text = "Mapa",
            fontWeight = FontWeight.Companion.Bold,
            color = Color.Companion.White,
            modifier = modifier.align(Alignment.Companion.CenterHorizontally),
            textAlign = TextAlign.Companion.Center,
            fontSize = 20.sp
        )
    }*/
}