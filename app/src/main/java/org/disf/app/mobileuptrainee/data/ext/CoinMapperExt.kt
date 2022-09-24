package org.disf.app.mobileuptrainee.data.ext

import org.disf.app.mobileuptrainee.data.api.response.CoinDescriptionNetwork
import org.disf.app.mobileuptrainee.data.api.response.CoinMarketNetwork
import org.disf.app.mobileuptrainee.domain.model.CoinDescription
import org.disf.app.mobileuptrainee.domain.model.CoinsMarket
import org.disf.app.mobileuptrainee.domain.model.VsCurrency

fun CoinDescriptionNetwork.toDomain() = CoinDescription(
    id = requireNotNull(id),
    name = name.orEmpty(),
    description = description?.description.orEmpty(),
    categories = categories,
    image = image.large
)

fun CoinMarketNetwork.toDomain(vsCurrency: VsCurrency) = CoinsMarket(
    id = id.orEmpty(),
    name = name.orEmpty(),
    symbol = symbol.orEmpty(),
    image = image.orEmpty(),
    currentPrice = currentPrice,
    priceChangePercentage24h = priceChangePercentage24h,
    costCurrency = vsCurrency,
)