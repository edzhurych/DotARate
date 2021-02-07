package com.ez.domain.model


data class SearchUser(
    val account_id: Int,
    val avatarfull: String,
    val last_match_time: String?,
    val personaname: String
)