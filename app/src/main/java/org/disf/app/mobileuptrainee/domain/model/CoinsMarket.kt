package org.disf.app.mobileuptrainee.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CoinsMarket(
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    val currentPrice: Double?,
    val priceChangePercentage24h: Double?,
    val costCurrency: VsCurrency,
): Parcelable
