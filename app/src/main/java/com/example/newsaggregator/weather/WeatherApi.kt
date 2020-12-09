//package com.example.newsaggregator.weather
//
//import androidx.room.Query
//import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
//import kotlinx.coroutines.Deferred
//import okhttp3.Interceptor
//import okhttp3.OkHttpClient
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import retrofit2.http.GET
//import retrofit2.http.Query
//
//
//const val API_KEY = "284f892e5564405897e00824200912"
//
////http://api.weatherapi.com/v1/current.json?key=4e482c90c34c49a1a5e132107201311&q=London&Lang=en --- Query url
//
//interface WeatherAPIService {
//    @GET("current.json")
//    fun getCurrentWeather(
//        @retrofit2.http.Query("q") location: String,
//        @retrofit2.http.Query("lang") languageCode: String = "en"
//    ): Deferred<WeatherData>
//
//
//    //static method
//    companion object{
//        operator fun invoke(): WeatherAPIService {
//            val requestInterceptor = Interceptor { chain ->
//                val url = chain.request()
//                    .url()
//                    .newBuilder()
//                    .addQueryParameter("key", API_KEY)
//                    .build()
//                val request = chain.request().newBuilder().url(url).build()
//
//                return@Interceptor chain.proceed(request)
//            }
//            val okHttpClient = OkHttpClient.Builder()
//                .addInterceptor(requestInterceptor)
//                .build()
//            return Retrofit.Builder().client(okHttpClient).baseUrl("https://api.weatherapi.com/v1/")
//                .addCallAdapterFactory(CoroutineCallAdapterFactory())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//                .create(WeatherAPIService::class.java)
//        }
//    }
//}