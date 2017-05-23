package com.synacy.moviehouse.schedule

import grails.converters.JSON

class ScheduleMarshaller {

	void register() {
		JSON.registerObjectMarshaller(Schedule) { Schedule schedule ->

			return [
					movie     		: schedule.movie,
					cinema 			: schedule.cinema,
					startDateTime	: schedule.startDateTime.format('yyyy-MM-dd HH:mm:ss'),
					endDateTime		: schedule.endDateTime.format('yyyy-MM-dd HH:mm:ss')
					
			]
		}
	}

}
