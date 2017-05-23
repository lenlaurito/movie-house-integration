package com.synacy.moviehouse

import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import grails.transaction.Transactional
import groovy.json.JsonBuilder
import groovy.util.logging.Log4j
import org.springframework.http.HttpStatus
import org.springframework.web.client.ResourceAccessException
import grails.util.Holders

@Transactional
@Log4j
class ScheduleService {

    RestBuilder restBuilder

    String ichiUrl = Holders.grailsApplication.config.ichiApi.url
    String banjoeUrl = Holders.grailsApplication.config.banjoeApi.url

    def fetchAllSchedulesByDate(String date) {

        // List<Schedule> schedules = []
        List<Schedule> schedulesIchi = []
        List<Schedule> schedulesBanjoe = []

        try {
            RestResponse responseIchi = restBuilder.get(ichiUrl + "?date=" + date)
            if (responseIchi.statusCode == HttpStatus.OK) {
                List jsonArrayIchi = responseIchi.json
                schedulesIchi = convertSpringBootResponseToScheduleList(jsonArrayIchi)
            }else {
                log.error("Spring boot api responded with ${responseIchi.statusCode}: ${responseIchi.body}")
            }
        }catch (ResourceAccessException e) {
            log.error "Cannot connect to ${ichiUrl}"
        }

        try {
            RestResponse responseBanjoe = restBuilder.get(banjoeUrl + "?date=" + date)
            if (responseBanjoe.statusCode == HttpStatus.OK) {
                List jsonArrayBanjoe = responseBanjoe.json
                schedulesBanjoe = convertSpringBootResponseToScheduleList(jsonArrayBanjoe)
            }else {
                log.error("Spring boot api responded with ${responseBanjoe.statusCode}: ${responseBanjoe.body}")
            }
        }catch (ResourceAccessException e) {
            log.error "Cannot connect to ${banjoeUrl}"
        }

        Map mapIchi = [ichiSchedules: schedulesIchi]
        Map mapBanjoe = [banjoeSchedules: schedulesBanjoe]

        return [mapIchi, mapBanjoe]

        // return schedulesIchi

        // return schedulesBanjoe
    }

    private List<Schedule> convertSpringBootResponseToScheduleList(List<Map> jsonArray) {
        List<Schedule> schedules = []
		jsonArray.each { record ->
			Schedule schedule = new Schedule()
			schedule.movie = record.movie.name
			schedule.cinema = record.cinema.name
			schedule.startDateTime = record.startDateTime
			schedule.endDateTime = record.endDateTime
			schedules << schedule
		}

        return schedules
	}
}
