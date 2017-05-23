### Schedule

#### View all Schedules
##### Request
GET: `/api/v1/schedule`

#### Params
| Filter |   Type   |Required   | Description |
|-----------|----------------|--------------|--------------|
| date  | Date      | Required  | Return only list of movie schedules within the given `date`. <br />Date format should be `yyyy-MM-dd` |

##### Response
HTTP Status: `200 OK`

Response Body:
```json
[
  {
    "movieHouseName": "JR Movie House",
    "schedules": [
      {
        "movie": {
          "name": "Movie"
        },
        "cinema": {
          "name": "IMAX Theater"
        },
        "startDateTime": "2017-01-01 16:00:00",
        "endDateTime": "2017-01-01 20:30:00"
      },
      {
        "movie": {
          "name": "Movie"
        },
        "cinema": {
          "name": "2D Theater 2"
        },
        "startDateTime": "2017-01-01 16:00:00",
        "endDateTime": "2017-01-01 20:30:00"
      }
    ]
  },
  {
    "movieHouseName": "Kenichi Movie House",
    "schedules": [
      {
        "movie": {
          "name": "John Wick"
        },
        "cinema": {
          "name": "Cinema 1"
        },
        "startDateTime": "2017-01-01 10:00:00",
        "endDateTime": "2017-01-03 00:00:00"
      },
      {
        "movie": {
          "name": "John Wick"
        },
        "cinema": {
          "name": "Cinema 1"
        },
        "startDateTime": "2017-01-01 10:00:00",
        "endDateTime": "2017-01-03 00:00:00"
      }
    ]
  }
]
```
