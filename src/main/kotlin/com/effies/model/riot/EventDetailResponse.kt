package com.effies.model.riot

import kotlinx.serialization.Serializable

@Serializable
data class EventDetailResponse( val data: EventData)

@Serializable
data class EventData( val event: Event)

@Serializable
data class Event(
    val match: Match
)

@Serializable
data class Match(
    val teams: List<EventTeam>
)

@Serializable
data class EventTeam(
    val id: String
)

