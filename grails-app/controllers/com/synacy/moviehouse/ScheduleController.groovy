package com.synacy.moviehouse

import com.synacy.moviehouse.exception.MissingParameterException
import org.springframework.http.HttpStatus

class ScheduleController {

    static responseFormats = ['json']

    ScheduleService scheduleService

    def fetchSchedules() {

        String date = params.date ? String.valueOf(params.date) : null

        if(!date) {
            throw new MissingParameterException("Missing date parameter in URI.")
        }

        List<Schedule> schedules = scheduleService.fetchAllSchedulesByDate(date)

        respond(schedules)
    }

    def handleMissingParameterException(MissingParameterException e) {
        response.status = HttpStatus.BAD_REQUEST.value()
        respond([error: e.getMessage()])
    }
}
