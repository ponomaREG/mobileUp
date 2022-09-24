package org.disf.app.mobileuptrainee.domain.model

data class CoinsMarket(
    var id: String,
    var symbol: String,
    var name: String,
    var image: String,
    var currentPrice: Double?,
    var priceChangePercentage24h: Double?,
)
