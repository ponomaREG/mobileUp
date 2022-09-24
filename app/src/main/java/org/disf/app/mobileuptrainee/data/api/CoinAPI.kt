package org.disf.app.mobileuptrainee.data.api

import org.disf.app.mobileuptrainee.data.api.response.CoinDescriptionNetwork
import org.disf.app.mobileuptrainee.data.api.response.CoinMarketNetwork
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinAPI {

    @GET("coins/markets")
    suspend fun getMarketCoins(
        @Query("vs_currency") vsCurrency: String,
        @Query("per_page") per_page: Int,
        @Query("page") page: Int,
        @Query("order") order: String,
    ): List<CoinMarketNetwork>

    @GET("coins/{id}")
    suspend fun getCoinDescription(@Path("id") id: String): CoinDescriptionNetwork
}