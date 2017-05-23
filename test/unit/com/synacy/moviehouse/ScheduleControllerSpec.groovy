package com.synacy.moviehouse

import grails.test.mixin.TestFor
import spock.lang.Specification
import org.springframework.http.HttpStatus

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(ScheduleController)
class ScheduleControllerSpec extends Specification {

    // ScheduleService scheduleService = Mock()
    //
    // def setup() {
    //     controller.scheduleService = scheduleService
    // }
    //
    // def cleanup() {
    // }
    //
    // void "fetchSchedules should respond with all schedules"() {
    //     given:
    //         Schedule schedule1 = new Schedule(movie: "movie1", cinema: "cinema1", startDateTime: "2017-01-01 10:00", endDateTime: "2017-01-02 00:00")
    //         Schedule schedule2 = new Schedule(movie: "movie2", cinema: "cinema2", startDateTime: "2017-02-01 10:00", endDateTime: "2017-02-02 00:00")
    //         scheduleService.fetchAllSchedules(null) >> [schedule1, schedule2]
    //
    //     when:
    //         controller.fetchSchedules()
    //
    //     then:
    //         response.status == HttpStatus.OK.value()
    //         // response.json.size == 2
    //         response.json.find {
    //             it.movie == "movie1" && it.cinema == "cinema1" && it.startDateTime == "2017-01-01 10:00" && it.endDateTime == "2017-01-02 00:00"
    //         }
    //         response.json.find {
    //             it.movie == "movie2" && it.cinema == "cinema2" && it.startDateTime == "2017-02-01 10:00" && it.endDateTime == "2017-02-02 00:00"
    //         }
    // }
    //
    // void "fetchSchedules should respond with all schedules by date"() {
    //     given:
    //         Schedule schedule1 = new Schedule(movie: "movie1", cinema: "cinema1", startDateTime: "2017-01-01 10:00", endDateTime: "2017-01-02 00:00")
    //         Schedule schedule2 = new Schedule(movie: "movie2", cinema: "cinema2", startDateTime: "2017-02-01 10:00", endDateTime: "2017-02-02 00:00")
    //         Schedule schedule3 = new Schedule(movie: "movie3", cinema: "cinema3", startDateTime: "2017-02-01 10:00", endDateTime: "2017-02-04 00:00")
    //         Schedule schedule4 = new Schedule(movie: "movie4", cinema: "cinema4", startDateTime: "2017-02-01 10:00", endDateTime: "2017-02-06 00:00")
    //
    //         String date = "2017-02-01"
    //         scheduleService.fetchAllSchedules(date) >> [schedule2, schedule3, schedule4]
    //
    //     when:
    //         controller.fetchSchedules()
    //
    //     then:
    //         response.status == HttpStatus.OK.value()
    //         response.json.size == 3
    //         response.json.find {
    //             it.movie == "movie2" && it.cinema == "cinema2" && it.startDateTime == "2017-02-01 10:00" && it.endDateTime == "2017-02-02 00:00"
    //         }
    //         response.json.find {
    //             it.movie == "movie3" && it.cinema == "cinema3" && it.startDateTime == "2017-02-01 10:00" && it.endDateTime == "2017-02-04 00:00"
    //         }
    //         response.json.find {
    //             it.movie == "movie4" && it.cinema == "cinema4" && it.startDateTime == "2017-02-01 10:00" && it.endDateTime == "2017-02-06 00:00"
    //         }
    // }
}
