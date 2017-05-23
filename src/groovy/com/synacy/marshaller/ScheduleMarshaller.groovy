package com.synacy.marshaller

import com.synacy.schedule.Schedule
import grails.converters.JSON

/**
 * Created by steven on 5/22/17.
 */
public class ScheduleMarshaller {

    void register() {
        JSON.registerObjectMarshaller(Schedule) { Schedule schedule ->

            return [
                    id               : schedule.id,
                    movie            : schedule.movie,
                    cinema           : schedule.cinema,
                    startDateTime    : schedule.startDateTime,
                    endDateTime      : schedule.endDateTime
            ]
        }
    }
}
