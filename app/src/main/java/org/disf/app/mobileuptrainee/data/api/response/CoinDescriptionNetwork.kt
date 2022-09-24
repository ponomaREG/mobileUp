package org.disf.app.mobileuptrainee.data.api.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinDescriptionNetwork(
    @SerialName("id") val id: String? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("categories") val categories: ArrayList<String> = arrayListOf(),
    @SerialName("description") val description: Description? = null,
    @SerialName("image") val image: Image,
)

@Serializable
data class Description(
    @SerialName("en") val description: String
)

@Serializable
data class Image(
    @SerialName("large") val large: String,
)