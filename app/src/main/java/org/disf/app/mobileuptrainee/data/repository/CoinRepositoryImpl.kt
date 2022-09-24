package org.disf.app.mobileuptrainee.data.repository

import org.disf.app.mobileuptrainee.data.api.CoinAPI
import org.disf.app.mobileuptrainee.data.api.response.CoinMarketNetwork
import org.disf.app.mobileuptrainee.data.ext.toDomain
import org.disf.app.mobileuptrainee.domain.model.CoinDescription
import org.disf.app.mobileuptrainee.domain.model.CoinsMarket
import org.disf.app.mobileuptrainee.domain.model.VsCurrency
import org.disf.app.mobileuptrainee.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val coinAPI: CoinAPI,
) : CoinRepository {

    override suspend fun getMarketCoins(vsCurrency: VsCurrency): List<CoinsMarket> {
        return coinAPI.getMarketCoins(
            vsCurrency = vsCurrency.label,
            per_page = 30,
            page = 1,
            order = "market_cap_desc"
        ).map {
            it.toDomain(vsCurrency)
        }
    }

    override suspend fun getCoinDescription(id: String): CoinDescription {
        return coinAPI.getCoinDescription(
            id = id
        ).toDomain()
    }
}