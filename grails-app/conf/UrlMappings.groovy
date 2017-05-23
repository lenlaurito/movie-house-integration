class UrlMappings {

	static mappings = {
        //Schedule
        "/api/v1/schedule"(controller: "schedule") {
            action = [GET: "fetchAllSchedules"]
        }

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
