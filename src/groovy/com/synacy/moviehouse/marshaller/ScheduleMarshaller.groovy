package com.synacy.moviehouse.marshaller

import grails.converters.JSON
import com.synacy.moviehouse.Schedule

class ScheduleMarshaller {

     void register() {

          JSON.registerObjectMarshaller(Schedule) {

               return [
                         movie: it.movie,
                         cinema: it.cinema,
                         startDateTime: it.startDateTime,
                         endDateTime: it.endDateTime
               ]
          }
     }
}
