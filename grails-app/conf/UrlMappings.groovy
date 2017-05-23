class UrlMappings {

	static mappings = {
        /*
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/api/v1/movie"(controller: "movie") {
            action = [GET: "listMovieHouseMovies"]
        }
        */
        "/api/v1/movie"(controller: "movie") {
            action = [GET: "listMovieHouseMovies"]
        }

        "/api/v1/schedule"(controller: "schedule") {
            action = [GET: "listMovieHousesMovieSchedules"]
        }

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
