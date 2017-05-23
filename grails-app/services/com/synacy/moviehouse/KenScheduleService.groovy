package com.synacy.moviehouse

import com.synacy.moviehouse.schedule.Schedule

import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import grails.transaction.Transactional
import grails.util.Holders
import groovy.json.JsonBuilder
import groovy.util.logging.Log4j

import org.springframework.http.HttpStatus
import org.springframework.web.client.ResourceAccessException

@Transactional
@Log4j
class KenScheduleService {
	RestBuilder restBuilder
	
	static final KEN_MOVIE_HOUSE_SCHEDULE_API_URL = Holders.grailsApplication.config.KenichiMovieHouseApi.url

	List<Schedule> fetchSchedules(Date date) {
		List<Schedule> schedules = []
		try {
			RestResponse response = restBuilder.get(KEN_MOVIE_HOUSE_SCHEDULE_API_URL + "?date=" + date.format('yyyy-MM-dd'))
			if (response.statusCode == HttpStatus.OK) {
				List jsonArray = response.json
				schedules = convertSpringBootResponseToScheduleList(jsonArray)
			} else {
				log.error("Spring boot api responded with ${response.statusCode}: ${response.body}")
			}
		} catch (ResourceAccessException e) {
			log.error "Cannot connect to ${KEN_MOVIE_HOUSE_SCHEDULE_API_URL}"
		}
		return schedules
	}
	
	private List<Schedule> convertSpringBootResponseToScheduleList(List<Map> jsonArray) {
		List<Schedule> schedules = []
		jsonArray.each { record ->
			Schedule schedule = new Schedule()
			schedule.movie = [name: record.movie.name]
			schedule.cinema = [name: record.cinema.name]
			schedule.startDateTime = Date.parse('yyyy-MM-dd HH:mm', record.startDateTime)
			schedule.endDateTime = Date.parse('yyyy-MM-dd HH:mm', record.endDateTime)
			schedules << schedule
		}
		return schedules
	}
	
}
