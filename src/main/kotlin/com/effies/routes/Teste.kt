package com.effies.routes

import com.effies.http.out.api.League
import com.effies.http.out.api.Riot
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.time.LocalDate
import java.time.LocalDateTime

fun Route.getTournament() {
    get("/out/api/tournament"){
        val lolesportApi = Riot()
        val tournament = lolesportApi.getTournament(League.CBLOL.code)

        call.respond(tournament)
    }
}

fun Route.getSchedule() {
    get("/out/api/schedule"){
        val lolesportApi = Riot()
        val schedule = lolesportApi.getSchedule(League.CBLOL.code)

        call.respond(schedule)
    }
}

fun Route.getTeams() {
    get("/out/api/teams"){

        val league = call.request.queryParameters["league"] ?: League.CBLOL.code

        val lolesportApi = Riot()

        val tournament = lolesportApi.getTournament(league)
            .data.leagues.firstOrNull()?.tournaments?.firstOrNull { LocalDate.parse(it.endDate) >= LocalDate.now() }

        val schedule = lolesportApi.getSchedule(league)
        val teamsName = schedule.data.schedule.events
            .filter { onGoing(it.startTime, tournament?.startDate , tournament?.endDate) }
            .flatMap { it.match.teams.map { team -> team.name }.filter { name -> name != "TBD" }}.distinct()

        val teams = lolesportApi.getTeams().data.teams.filter { teamsName.contains(it.name) }

        call.respond(teams)
    }
}

fun Route.getEvenDetails(){
    get("/out/api/event/detail"){
        val lolesportApi = Riot()
        val eventDetail = lolesportApi.getEventDetail("108998961199502478")

        call.respond(eventDetail)
    }
}

fun Route.getLiveStats(){
    get("/out/api/stats"){
        val league = call.request.queryParameters["league"] ?: League.CBLOL.code

        val lolesportApi = Riot()
        val live = lolesportApi.getLive().data.schedule.events.firstOrNull()

        if(live?.league?.id != null && live.league.id == League.valueOf(league).id){
            // Consulta o GET WINDOW
        }
//        val tournament = lolesportApi.getTournament(league)
//            .data.leagues.firstOrNull()?.tournaments?.firstOrNull { LocalDate.parse(it.endDate) >= LocalDate.now() }
//
//        val schedule = lolesportApi.getSchedule(league).data.schedule.events
//            .filter { isLive(it.startTime) }

    }
}

fun onGoing(startTime: String, startDate: String?, endDate: String?): Boolean {

    if(startDate.isNullOrBlank() || endDate.isNullOrBlank()) return false

    val date = LocalDate.parse(startTime.split("T", limit = 10).first())
    val start = LocalDate.parse(startDate)
    val end = LocalDate.parse(endDate)

    return date in start..end

}

fun isLive(startTime: String) = LocalDateTime.parse(startTime) <= LocalDateTime.now()