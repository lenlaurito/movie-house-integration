package com.synacy.moviehouse

import com.synacy.moviehouse.schedule.Schedule

import org.springframework.http.HttpStatus
import java.text.ParseException

class ScheduleController {
	
	static responseFormats = ['json']
	
	MyScheduleService myScheduleService
	KenScheduleService kenScheduleService

	def fetchAllSchedules() {
		String dateStr = params.date ?: null
		if (!dateStr) {
			throw new InvalidRequestException("Date is a required parameter.")
		}
		Date date;
		try {
			date = Date.parse('yyyy-MM-dd', dateStr)
		} catch (ParseException e) {
			throw new InvalidRequestException("Unable to parse date. Use this format 'yyyy-MM-dd' instead.")
		}
		List<Schedule> schedulesJR = myScheduleService.fetchSchedules(date)
		List<Schedule> schedulesKen = kenScheduleService.fetchSchedules(date)
		Map schedulesMap1 = [movieHouseName: "JR Movie House", schedules: schedulesJR]
		Map schedulesMap2 = [movieHouseName: "Kenichi Movie House", schedules: schedulesKen]
		respond([schedulesMap1, schedulesMap2])
	}
	
	def handleInvalidRequests(InvalidRequestException e) {
		response.status = HttpStatus.UNPROCESSABLE_ENTITY.value()
		respond([error: e.getMessage()])
	}
	
}
