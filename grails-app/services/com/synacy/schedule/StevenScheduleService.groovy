package com.synacy.schedule

import com.synacy.ScheduleUtils
import com.synacy.moviehouse.MovieHouse
import grails.transaction.Transactional
import groovy.util.logging.Log4j

@Transactional
@Log4j
class StevenScheduleService {

    ScheduleUtils scheduleUtils

    String SPRING_BOOT_STEVENS_MOVIE_HOUSE_API_URL = "http://192.168.1.86:8070/api/v1/schedule"

    public MovieHouse fetchAllSchedules(String date) {

        MovieHouse movieHouse = new MovieHouse(movieHouseName: "Steven's Movie House")

        movieHouse.schedules = scheduleUtils.fetchAllSchedulesOfApi(SPRING_BOOT_STEVENS_MOVIE_HOUSE_API_URL, date)

        return movieHouse
    }
}
