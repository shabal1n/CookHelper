package com.example.cookhelper.api


import com.example.cookhelper.api.model.PhotoDTO
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path


interface NasaApi {
    @get:GET("natural/all")
    val datesWithPhoto: Single<List<Any?>?>?

    @GET("natural/date/{date}")
    fun getPhotosForDate(@Path("date") date: String?): Single<List<PhotoDTO?>?>?
}