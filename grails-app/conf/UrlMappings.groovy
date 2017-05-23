class UrlMappings {

	static mappings = {
        // Schedule
        "/api/v1/schedule"(controller: "schedule") {
            action = [GET: "fetchAllMovieHouse"]
        }

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
