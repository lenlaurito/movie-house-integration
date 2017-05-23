import com.synacy.moviehouse.marshaller.ScheduleMarshaller

class BootStrap {

    def init = { servletContext ->

        initializeMarshallers()
    }
    def destroy = {
    }

    private void initializeMarshallers() {

        new ScheduleMarshaller().register()
    }
}
