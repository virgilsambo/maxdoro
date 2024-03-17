package com.maxdoro.employer.common

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.maxdoro.employer.model.Employer

inline fun <reified T> Gson.fromJson(json: String) =
    fromJson<T>(json, object : TypeToken<T>() {}.type)

fun Int.withPercentageSign(): String {
    return "$this%"
}

fun List<Employer>.sortByPlace(): List<Employer> {
    return this.sortedBy { it.place }
}

fun List<Employer>.sortByDiscountPercentage(): List<Employer> {
    return this.sortedBy { it.discountPercentage }
}

fun List<Employer>.sortByName(): List<Employer> {
    return this.sortedBy { it.name }
}