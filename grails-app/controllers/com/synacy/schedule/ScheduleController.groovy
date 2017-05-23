package com.synacy.schedule

import com.synacy.exception.InvalidRequestException
import groovy.util.logging.Log4j
import org.springframework.http.HttpStatus

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat

@Log4j
class ScheduleController {

    static responseFormats = ['json']

    ScheduleService scheduleService

    def fetchAllSchedule() {
        String dateInString = params.date ? params.date : null

        if(!dateInString) throw new InvalidRequestException("Date parameter is required and is not nullable")
        try{
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd")
            Date date = formatter.parse(dateInString)
            respond(scheduleService.fetchSchedule(date))
        }
        catch (ParseException e){
            respond(error: e.getMessage())
        }
    }

    def handleInvalidRequestException(InvalidRequestException e) {
        response.status = HttpStatus.BAD_REQUEST.value()
        respond([error: e.getMessage()])
    }
}
