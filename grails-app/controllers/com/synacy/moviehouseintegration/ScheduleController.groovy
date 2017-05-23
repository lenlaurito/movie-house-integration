package com.synacy.moviehouseintegration

class ScheduleController {

    static responseFormats = ['json']

    ScheduleService scheduleService

    def listMovieHousesMovieSchedules() {
        HashMap<String, List<Schedule>>  list = scheduleService.fetchMovieHouseSchedules()

        respond(list)
    }
}
