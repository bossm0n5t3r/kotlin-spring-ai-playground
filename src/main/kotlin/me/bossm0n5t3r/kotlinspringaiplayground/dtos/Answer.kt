package me.bossm0n5t3r.kotlinspringaiplayground.dtos

data class Answer(
    val text: String,
)

fun String.toAnswer() = Answer(this)
