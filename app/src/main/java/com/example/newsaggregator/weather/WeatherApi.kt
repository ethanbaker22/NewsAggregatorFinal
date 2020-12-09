package com.example.newsaggregator.weather

/**
 * Could not get this to work! Included as comments to show to attempt
 */

//import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
//import kotlinx.coroutines.Deferred
//import okhttp3.Interceptor
//import okhttp3.OkHttpClient
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import retrofit2.http.GET
//
//const val API_KEY = "284f892e5564405897e00824200912"
//
//interface WeatherApi {
//    @GET("current.json")
//    fun getCurrentWeather(
//        @retrofit2.http.Query("q") location: String,
//        @retrofit2.http.Query("lang") languageCode: String = "en"
//    ): Deferred<WeatherData>
//
//    companion object {
//        operator fun invoke(): WeatherApi {
//            val requestInterceptor = Interceptor { chain ->
//                val url = chain.request()
//                    .url()
//                    .newBuilder()
//                    .addQueryParameter("key", API_KEY)
//                    .build()
//                val request = chain.request().newBuilder().url(url).build()
//                return@Interceptor chain.proceed(request)
//            }
//            val okHttpClient = OkHttpClient.Builder()
//                .addInterceptor(requestInterceptor)
//                .build()
//            return Retrofit.Builder().client(okHttpClient).baseUrl("https://api.weatherapi.com/v1/")
//                .addCallAdapterFactory(CoroutineCallAdapterFactory())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//                .create(WeatherApi::class.java)
//        }
//    }
//}