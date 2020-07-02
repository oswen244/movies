package com.example.android.movies.data

import com.example.android.movies.models.Genre
import com.example.android.movies.models.GenreResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object ApiClient {
    const val URL = "https://api.themoviedb.org/3/"
    const val APIKEY = "?api_key=04dcf41b423aeb4d22523e0b8001bde2"
    const val IMAGES = "https://image.tmdb.org/t/p/w500"

    private var servicesApiInterface:ServicesApiInterface?=null

    fun build(): ServicesApiInterface?{
        var builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())

        var httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor())

        var retrofit: Retrofit = builder.client(httpClient.build()).build()
        servicesApiInterface = retrofit.create(
            ServicesApiInterface::class.java)

        return servicesApiInterface as ServicesApiInterface
    }

    private fun interceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level=HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    interface ServicesApiInterface{
        @GET("genre/movie/list${APIKEY}")
        suspend fun genres(): Response<GenreResponse>
    }

}