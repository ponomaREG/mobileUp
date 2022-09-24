package org.disf.app.mobileuptrainee.domain.repository

import org.disf.app.mobileuptrainee.domain.model.CoinDescription
import org.disf.app.mobileuptrainee.domain.model.CoinsMarket

interface CoinRepository {

    suspend fun getMarketCoins(vsCurrency: String): List<CoinsMarket>

    suspend fun getCoinDescription(id: String): CoinDescription
}