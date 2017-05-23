import grails.plugins.rest.client.RestBuilder
import com.synacy.ScheduleUtils

// Place your Spring DSL code here
beans = {
    restBuilder(RestBuilder)
    scheduleUtils(ScheduleUtils) {
        restBuilder = ref("restBuilder")
    }
}
