package com.effies.model.riot

import kotlinx.serialization.Serializable

@Serializable
data class TeamResponse( val data: TeamData )

@Serializable
data class TeamData(val teams: List<Team>)

@Serializable
data class Team(
    val id: String,
    val slug: String,
    val name: String,
    val code: String,
    val image: String?,
    val alternativeImage: String?,
    val backgroundImage: String?,
    val status: String?,
    val homeLeague: HomeLeague?,
    val players: List<Player>?
)

@Serializable
data class HomeLeague(
    val name: String?,
    val region: String?
)

@Serializable
data class Player(
    val id: String,
    val summonerName: String,
    val firstName: String,
    val lastName: String,
    val image: String?,
    val role: String
)
