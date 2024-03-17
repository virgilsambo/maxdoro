package com.maxdoro.employer.model

import com.google.gson.annotations.SerializedName

data class Employer(
    @SerializedName("Name") val _name: String?,
    @SerializedName("EmployerID") val _employerId: Int?,
    @SerializedName("DiscountPercentage") val _discountPercentage: Int?,
    @SerializedName("Place") val _place: String?,
) {
    val name: String
        get() = _name.orEmpty()

    val employerId: Int
        get() = _employerId ?: 0

    val discountPercentage: Int
        get() = _discountPercentage ?: 0

    val place: String
        get() = _place.orEmpty()

}