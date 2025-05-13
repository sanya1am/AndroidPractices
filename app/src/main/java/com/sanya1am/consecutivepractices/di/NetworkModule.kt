package com.sanya1am.consecutivepractices.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.sanya1am.impl.data.api.MovieApi
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.sanya1am.consecutivepractices.BuildConfig

private const val BASE_URL = "https://api.kinopoisk.dev/v1.4/"

val networkModule = module {
    factory { provideRetrofit(get()) }
    single { provideNetworkApi(get()) }
}

fun provideRetrofit(context: Context): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient.Builder().apply {
            addInterceptor {
                Interceptor { chain ->
                    val request: Request = chain.request().newBuilder()
                        .addHeader("X-API-KEY", BuildConfig.KINOPOISK_API_KEY)
                        .build()
                    val url: HttpUrl = request.url.newBuilder()
                        .build()
                    chain.proceed(request.newBuilder().url(url).build())
                }.intercept(it)
            }
            addInterceptor(ChuckerInterceptor(context))
        }.build())
        .build()
}

fun provideNetworkApi(retrofit: Retrofit): MovieApi =
    retrofit.create(MovieApi::class.java)