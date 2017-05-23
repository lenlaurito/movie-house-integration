// Place your Spring DSL code here
import grails.plugins.rest.client.RestBuilder
import java.text.SimpleDateFormat

beans = {
    restBuilder(RestBuilder)
    simpleDateFormat(SimpleDateFormat, "yyyy-MM-dd HH:mm:ss")
}
