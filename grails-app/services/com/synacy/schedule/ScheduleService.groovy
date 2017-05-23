package com.synacy.schedule

import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import grails.transaction.Transactional
import groovy.util.logging.Log4j
import org.springframework.http.HttpStatus
import org.springframework.web.client.ResourceAccessException

import java.text.DateFormat
import java.text.SimpleDateFormat

@Transactional
@Log4j
class ScheduleService {

    RestBuilder restBuilder

    String SPRING_BOOT_STEVEN_SCHEDULE_API_URL = "http://localhost:8070/api/v1/schedule"
    String SPRING_BOOT_MIKE_SCHEDULE_API_URL = "http://192.168.1.203:8080/api/v1/schedule"

    public List<Map> fetchSchedule(Date date) {
//        Map<String, Object> scheduleDetails1 = [:]
//        Map<String, Object> scheduleDetails2 = [:]
//        List<Schedule> schedules1 = []
//        List<Schedule> schedules2 = []
//        RestResponse response
//
//        try {
//            response = restBuilder.get(SPRING_BOOT_STEVEN_SCHEDULE_API_URL)
//            if (response.statusCode == HttpStatus.OK) {
//                List jsonArray = response.json
//                schedules1 << convertSpringBootResponseToScheduleListWithSameDate(jsonArray, date)
//                scheduleDetails1 << [movieHouseName: "Steven's Movie House", schedules: schedules1]
//            }
//            else{log.error("Spring boot api responded with ${response.statusCode}: ${response.body}")}
//        }
//        catch (ResourceAccessException e){log.error "Cannot connect to ${SPRING_BOOT_STEVEN_SCHEDULE_API_URL}"}
//
//        try {
//            response = restBuilder.get(SPRING_BOOT_MIKE_SCHEDULE_API_URL)
//            if (response.statusCode == HttpStatus.OK) {
//                List jsonArray = response.json
//                schedules2 << convertSpringBootResponseToScheduleListWithSameDate(jsonArray, date)
//                scheduleDetails2 << [movieHouseName: "Mikes's Movie House", schedules: schedules2]
//            }
//            else{log.error("Spring boot api responded with ${response.statusCode}: ${response.body}")}
//        }
//        catch (ResourceAccessException e){log.error "Cannot connect to ${SPRING_BOOT_MIKE_SCHEDULE_API_URL}"}
        List<Map> fetchAllResult = []
        connectAndRetrieveSchedulesFromMovieHouses(SPRING_BOOT_STEVEN_SCHEDULE_API_URL, "Steven's Movie House", date)
        connectAndRetrieveSchedulesFromMovieHouses(SPRING_BOOT_MIKE_SCHEDULE_API_URL, "Mikes's Movie House", date)

        return fetchAllResult
    }

    private Map<String, Object> connectAndRetrieveSchedulesFromMovieHouses(String url, String movieHouseName, Date date){
        RestResponse response
        Map<String, Object> scheduleDetails = [:]
        List<Schedule> scheduleList = []
        try {
            response = restBuilder.get(url)
            if (response.statusCode == HttpStatus.OK) {
                List jsonArray = response.json
                scheduleList << convertSpringBootResponseToScheduleListWithSameDate(jsonArray, date)
                scheduleDetails << [movieHouseName: movieHouseName, schedules: scheduleList]
            }
            else{log.error("Spring boot api responded with ${response.statusCode}: ${response.body}")}
        }
        catch (ResourceAccessException e){log.error "Cannot connect to ${url}"}
    }

    private List<Schedule> convertSpringBootResponseToScheduleListWithSameDate(List<Map> jsonArray, Date date) {
        List<Schedule> schedules = []
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd")
        jsonArray.each { record ->
           if(formatter.parse(record.startDateTime.toString()) == date){
               Schedule schedule = new Schedule()
               schedule.id = record.id
               schedule.movie = [name: record.movie.name]
               schedule.cinema = [name: record.cinema.name]
               schedule.startDateTime = record.startDateTime
               schedule.endDateTime = record.endDateTime
               schedules << schedule
           }
        }
        return schedules
    }
}
