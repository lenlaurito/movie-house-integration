package com.synacy.moviehouseintegration

class ScheduleController {

    static responseFormats = ['json']

    ScheduleService scheduleService

    def listMovieHousesMovieSchedules() {
        def list = scheduleService.fetchAMovieHouseSchedules()

        respond(list)
    }
}
