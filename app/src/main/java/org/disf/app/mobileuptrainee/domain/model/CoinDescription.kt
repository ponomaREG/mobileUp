package org.disf.app.mobileuptrainee.domain.model

data class CoinDescription(
    var id: String,
    var name: String,
    var description: String,
    var categories: ArrayList<String>,
)
