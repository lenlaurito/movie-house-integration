import com.synacy.marshaller.ScheduleMarshaller

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
