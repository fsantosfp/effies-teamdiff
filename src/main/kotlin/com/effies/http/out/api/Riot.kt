package com.effies.http.out.api

import com.effies.http.out.api.Riot.HeaderConstants.API_KEY_HEADER
import com.effies.http.out.api.Riot.HeaderConstants.API_KEY_VALUE
import com.effies.http.out.api.Riot.HeaderConstants.LANGUAGE
import com.effies.model.riot.*
import com.fasterxml.jackson.databind.DeserializationFeature
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.jackson.*

class Riot {

    private val client = HttpClient(CIO) {
        install(ContentNegotiation){ jackson {
            configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        } }

        defaultRequest {
            contentType(ContentType.Application.Json)
            url {
                protocol = URLProtocol.HTTPS
                host = "esports-api.lolesports.com"
                path("/persisted/gw/")
                parameters.append("hl", LANGUAGE)
            }
            header(API_KEY_HEADER, API_KEY_VALUE)
        }
    }

    suspend fun getTournament(league:String): TournamentsResponse {
        return  client.get{
            url("getTournamentsForLeague")
            url.parameters.append("leagueId", League.valueOf(league.uppercase()).id)
        }.body()
    }

    suspend fun getSchedule(league: String, pageToken: String? = null): ScheduleResponse {
        return client.get{
            url("getSchedule")
            url.parameters.append("leagueId", League.valueOf(league.uppercase()).id)
        }.body()
    }

    suspend fun getEventDetail(eventId:String): EventDetailResponse{
        return client.get {
            url("getEventDetails")
            url.parameters.append("id", eventId)
        }.body()
    }

    suspend fun getTeams(): TeamResponse {
        return client.get("getTeams").body()
    }

    suspend fun getLive(): LiveResponse {
        return client.get("getLive").body()
    }

    object HeaderConstants {
        const val API_KEY_HEADER = "x-api-key"
        const val API_KEY_VALUE = "0TvQnueqKa5mxJntVWt0w4LpLfEkrV1Ta8rQBb9Z"
        const val LANGUAGE = "pt-BR"
    }
}

enum class League (val code:String, val id:String){
    CBLOL("cblol","98767991332355509"),
    WORLDS("worlds","98767975604431411")
}