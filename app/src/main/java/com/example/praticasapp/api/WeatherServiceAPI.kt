package com.example.praticasapp.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherServiceAPI {
    companion object {
        const val BASE_URL = "https://api.weatherapi.com/v1/"
    }
    // Procura a localização baseado no nome ou coordenadas
    @GET("search.json?lang=pt_br")
    fun search(@Query("key") key: String, @Query("q") query: String): Call<List<APILocation>?>

    @GET("current.json?lang=pt")
    fun weather(@Query("key") key: String, @Query("q") query: String): Call<APICurrentWeather?>
}
