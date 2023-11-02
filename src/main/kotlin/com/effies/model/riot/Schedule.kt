package com.effies.model.riot

import kotlinx.serialization.Serializable

@Serializable
data class ScheduleResponse( val data: ScheduleData)

@Serializable
data class ScheduleData ( val schedule: Schedule)

@Serializable
data class  Schedule ( val pages: Pagination?, val events: List<Events> )

@Serializable
data class Pagination ( val older: String?, val newer: String? )

@Serializable
data class Events (
    val startTime: String,
    val state: String,
    val type: String,
    val blockName: String,
    val league: EventLeague,
    val match: EventMatch,
    val id: String?
)

@Serializable
data class EventLeague( val name: String, val slug: String, val id: String? )

@Serializable
data class EventMatch(
    val id: String,
    val flags: List<String>,
    val teams: List<MatchTeam>,
    val strategy: MatchStrategy
)

@Serializable
data class MatchStrategy ( val type: String, val count: Int)

@Serializable
data class MatchTeam (
    val name: String,
    val code: String,
    val image: String?,
    val result: TeamResult?,
    val record: TeamRecord?
)

@Serializable
data class TeamResult ( val outcome: String?, val gameWins: Int? )

@Serializable
data class TeamRecord ( val wins: Int?, val losses: Int?)