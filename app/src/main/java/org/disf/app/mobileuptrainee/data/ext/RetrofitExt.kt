package org.disf.app.mobileuptrainee.data.ext

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.disf.app.mobileuptrainee.BuildConfig
import retrofit2.Retrofit

@ExperimentalSerializationApi
fun Retrofit.Builder.addJsonConverter(): Retrofit.Builder = apply {
    val json = Json { ignoreUnknownKeys = true }
    val contentType = "application/json".toMediaType()
    addConverterFactory(json.asConverterFactory(contentType))
}

fun Retrofit.Builder.addClient() = apply {
    if (BuildConfig.DEBUG) {
        client(
            OkHttpClient.Builder()
                .addNetworkInterceptor(getHttpLoggingInterceptor())
                .build()
        )
    }
}

private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }
}