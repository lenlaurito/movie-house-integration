class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        //Schedule
        "/api/v1/schedule"(controller: "schedule") {
            action = [GET: "fetchAllSchedule"]
        }

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
