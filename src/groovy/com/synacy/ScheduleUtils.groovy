package com.synacy

import com.synacy.cinema.Cinema
import com.synacy.movie.Movie
import com.synacy.schedule.Schedule
import org.springframework.http.HttpStatus
import org.springframework.web.client.ResourceAccessException
import grails.plugins.rest.client.RestResponse
import grails.plugins.rest.client.RestBuilder

class ScheduleUtils {

    RestBuilder restBuilder

    public List<Schedule> fetchAllSchedulesOfApi(String APIUrl, String date) {
        List<Schedule> schedules = []
        try {
            RestResponse response = restBuilder.get(APIUrl + "?date=" + date)
            if(response.statusCode == HttpStatus.OK) {
                List jsonArray = response.json
                schedules = convertResponseToScheduleList(jsonArray)
            } else {
                log.error("${APIUrl} responded with ${response.statusCode}: ${response.body}")
            }
        } catch(ResourceAccessException e) {
            log.error "Cannot connect to ${APIUrl}"
        }

        return schedules
    }

    public List<Schedule> convertResponseToScheduleList(List<Map> jsonArray) {
        List<Schedule> schedules = []
        jsonArray.each { record ->
            Schedule schedule = new Schedule()

            Movie movie = new Movie()
            movie.name = record.movie.name
            schedule.movie = movie

            Cinema cinema = new Cinema()
            cinema.name = record.cinema.name
            schedule.cinema = cinema

            schedule.startDateTime = record.startDateTime
            schedule.endDateTime = record.endDateTime
            schedules << schedule
        }
        return schedules
    }
}