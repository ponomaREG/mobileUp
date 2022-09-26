package org.disf.app.mobileuptrainee.domain.repository

import org.disf.app.mobileuptrainee.domain.model.CoinDescription
import org.disf.app.mobileuptrainee.domain.model.CoinsMarket
import org.disf.app.mobileuptrainee.domain.model.VsCurrency

interface CoinRepository {

    suspend fun getMarketCoins(vsCurrency: VsCurrency): List<CoinsMarket>

    suspend fun getCoinDescription(id: String): CoinDescription
}