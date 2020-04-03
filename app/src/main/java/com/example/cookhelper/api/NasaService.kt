package com.example.cookhelper.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


class NasaService {
    private var api: NasaApi
    fun getApi(): NasaApi {
        return api
    }

    private fun createOkHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val original = chain.request()
                val originalHttpUrl = original.url
                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("api_key", KEY)
                    .build()
                val requestBuilder = original.newBuilder()
                    .url(url)
                val request = requestBuilder.build()
                return chain.proceed(request)
            }
        })
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(logging)
        return httpClient.build()
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/recipes/findByIngredients")
            .addConverterFactory(GsonConverterFactory.create())
            .client(createOkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    companion object {
        var KEY = "ce37e6dff6544d8b93584e1fa3ae8309"
    }

    init {
        val retrofit = createRetrofit()
        api = retrofit.create(NasaApi::class.java)
    }
}