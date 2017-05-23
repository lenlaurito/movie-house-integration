package com.synacy.moviehouse

class ScheduleController {

    static responseFormats = ['json']

    ScheduleService scheduleService

    def fetchSchedules() {

        String date = params.date ? String.valueOf(params.date) : null

        List<Schedule> schedules = scheduleService.fetchAllSchedules(date)
    }
}
