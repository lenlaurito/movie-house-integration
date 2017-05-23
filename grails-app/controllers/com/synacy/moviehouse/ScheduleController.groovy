package com.synacy.moviehouse

import com.synacy.moviehouse.utilities.DateUtils
import com.synacy.moviehouse.schedule.Schedule

import org.springframework.http.HttpStatus

class ScheduleController {
	
	static responseFormats = ['json']
	
	MyScheduleService myScheduleService
	KenScheduleService kenScheduleService

	def fetchAllSchedules() {
		Date date = params.date ? DateUtils.formatStringAsDate(params.date) : null
		if (!date) {
			throw new InvalidRequestException("Date is a required parameter.")
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
