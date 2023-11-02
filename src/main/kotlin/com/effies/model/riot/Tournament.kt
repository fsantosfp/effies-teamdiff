package com.effies.model.riot

import kotlinx.serialization.Serializable

@Serializable
data class TournamentsResponse ( val data: Leagues )

@Serializable
data class Leagues ( val leagues: List<Tournaments> )

@Serializable
data class Tournaments( val tournaments: List<Tournament> )

@Serializable
data class Tournament(
    val id: String,
    val slug: String,
    val startDate: String,
    val endDate: String
)