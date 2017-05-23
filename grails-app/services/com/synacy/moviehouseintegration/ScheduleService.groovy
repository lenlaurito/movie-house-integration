package com.synacy.moviehouseintegration

import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import grails.transaction.Transactional
import groovy.util.logging.Log4j
import org.springframework.http.HttpStatus
import org.springframework.web.client.ResourceAccessException
import java.text.SimpleDateFormat

@Transactional
@Log4j
class ScheduleService {

    RestBuilder restBuilder
    SimpleDateFormat simpleDateFormat

    static String JR_MOVIE_HOUSE_SCHEDULE_API_URL = 'http://192.168.1.55:8085/api/v1/schedule'
    static String MY_MOVIE_HOUSE_SCHEDULE_API_URL = 'http://192.168.1.203:8080/api/v1/schedule'

    def fetchAMovieHouseSchedules() {
        List<Schedule> jrMovieHouseSchedules = []

        try {

            RestResponse response = restBuilder.get(JR_MOVIE_HOUSE_SCHEDULE_API_URL)

            if (response.statusCode == HttpStatus.OK) {
                List jsonArray = response.json

                jrMovieHouseSchedules = convertMovieApiScheduleResponseToScheduleList(jsonArray)
            } else {
                log.error("Jr's Movie House api responded with ${response.statusCode}: ${response.body}")
            }

        } catch(ResourceAccessException e) {
            log.error "Cannot connect to ${JR_MOVIE_HOUSE_SCHEDULE_API_URL}"
        }

        List<Schedule> myMovieHouseSchedules = []

        try {

            RestResponse response = restBuilder.get(MY_MOVIE_HOUSE_SCHEDULE_API_URL)

            if (response.statusCode == HttpStatus.OK) {
                List jsonArray = response.json

                myMovieHouseSchedules = convertMovieApiScheduleResponseToScheduleList(jsonArray)
            } else {
                log.error("My Movie House api responded with ${response.statusCode}: ${response.body}")
            }

        } catch(ResourceAccessException e) {
            log.error "Cannot connect to ${MY_MOVIE_HOUSE_SCHEDULE_API_URL}"
        }

        return [jrMovieHouseSchedules: jrMovieHouseSchedules, myMovieHouseSchedules: myMovieHouseSchedules]
    }

    private List<Schedule> convertMovieApiScheduleResponseToScheduleList(List<Map> jsonArray) {
        List<Schedule> scheduleList = []

        jsonArray.each { record ->
            Schedule schedule = new Schedule()

            schedule.startDateTime = simpleDateFormat.parse(record.startDateTime)
            schedule.endDateTime = simpleDateFormat.parse(record.endDateTime)
            schedule.movie = new Movie(name: record.movie.name, genre: record.movie.genre,
                    duration: record.movie.duration, description: record.movie.description)
            schedule.cinema = new Cinema(name: record.movie.name, type: record.cinema.type)

            scheduleList << schedule
        }

        return scheduleList
    }
}
