package com.synacy.moviehouse

import com.synacy.moviehouse.schedule.Schedule
import com.synacy.moviehouse.utilities.DateUtils

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
class MyScheduleService {
	RestBuilder restBuilder
	
	static final JR_MOVIE_HOUSE_SCHEDULE_API_URL = Holders.grailsApplication.config.JRMovieHouseApi.url

	List<Schedule> fetchSchedules(Date date) {
		List<Schedule> schedules = []
		try {
			RestResponse response = restBuilder.get(JR_MOVIE_HOUSE_SCHEDULE_API_URL + "?date=" + DateUtils.formatDateAsString(date, "yyyy-MM-dd"))
			if (response.statusCode == HttpStatus.OK) {
				List jsonArray = response.json
				schedules = convertSpringBootResponseToScheduleMap(jsonArray)
			} else {
				log.error("Spring boot api responded with ${response.statusCode}: ${response.body}")
			}
		} catch (ResourceAccessException e) {
			log.error "Cannot connect to ${JR_MOVIE_HOUSE_SCHEDULE_API_URL}"
		}
		return schedules
	}
	
	private List<Schedule> convertSpringBootResponseToScheduleMap(List<Map> jsonArray) {
		List<Schedule> schedules = []
		jsonArray.each { record ->
			Schedule schedule = new Schedule()
			schedule.movie = [name: record.movie.name]
			schedule.cinema = [name: record.cinema.name]
			schedule.startDateTime = DateUtils.formatStringAsDateTime((String) record.startDateTime)
			schedule.endDateTime = DateUtils.formatStringAsDateTime((String) record.endDateTime)
			schedules << schedule
		}
		return schedules
	}
	
}
