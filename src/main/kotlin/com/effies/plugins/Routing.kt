package com.effies.plugins

import com.effies.routes.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        getTournament()
        getSchedule()
        getTeams()
        getEvenDetails()
        getLiveStats()
    }
}
