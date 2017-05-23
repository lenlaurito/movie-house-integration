package com.synacy.schedule

import com.synacy.exception.ParameterRequiredException
import com.synacy.moviehouse.MovieHouse
import org.springframework.http.HttpStatus

class ScheduleController {

    static responseFormats = ['json']

    MyScheduleService myScheduleService
    StevenScheduleService stevenScheduleService

    def fetchAllMovieHouse() {
        String date = params.date ?: null

        if(date == null || date.isEmpty()) {
            throw new ParameterRequiredException("Required String parameter 'date' is not present")
        }

        MovieHouse myMovieHouse = myScheduleService.fetchAllSchedules(date)
        MovieHouse stevensMovieHouse = stevenScheduleService.fetchAllSchedules(date)

        List<MovieHouse> movieHouseList = [myMovieHouse, stevensMovieHouse]

        respond(movieHouseList)
    }

    def handleParameterRequiredException(ParameterRequiredException e) {
        response.status = HttpStatus.BAD_REQUEST.value()
        respond([error: e.getMessage()])
    }

}
