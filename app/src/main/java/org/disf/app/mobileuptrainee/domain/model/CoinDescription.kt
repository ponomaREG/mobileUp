package org.disf.app.mobileuptrainee.domain.model

data class CoinDescription(
    val id: String,
    val name: String,
    val description: String,
    val categories: ArrayList<String>,
    val image: String,
)
