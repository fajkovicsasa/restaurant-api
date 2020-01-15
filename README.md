# restaurant-api
[![Maintenance](https://img.shields.io/badge/Maintained%3F-no-red.svg)](https://bitbucket.org/lbesson/ansi-colors)
[![GitHub license](https://img.shields.io/github/license/Naereen/StrapDown.js.svg)](https://github.com/Naereen/StrapDown.js/blob/master/LICENSE)
[![Build Status](https://travis-ci.org/fajkovicsasa/restaurant-api.svg?branch=master)](https://travis-ci.org/fajkovicsasa/restaurant-api)

API for managing restaurant reservations and orders. Each table has it's specific location and a capacity for how many people
can sit on that table. <b>Double-reservations are allowed</b> meaning if the table has a capacity of 10 people and the first
reservation os only for 5 people, a seconds reservation of 3 people will be allowed.

There can be multiple food categories and each category can have it's own rules from which time, and until which time is 
being served to the customer.

By default, reservations can be made on the current date and up to 2 days in the future but this can easily be changed
depending on the needs of the restaurant.

# Security
All endpoints are secured using basic auth. In production, switch to JWT or some better solution.
When the application starts it will create one test user with username "user1" and password "pass1".

To register new users make a POST request to "/api/v1/users" while providing username and password in the body
of the request. Example:
```
[POST] http://localhost:8080/api/v1/users/
{
    "username": "user2",
    "password": "pass2"
}
```

<b>All the passwords are stored using BCrypt.</b>

# Test data
Dummy data is provided in the data.sql located under "main/java/resources/data.sql". After you start the API for the first time
make sure to comment-out all the lines starting with "INSERT" in that file.

### REST API endpoints ###
All of the available endpoints for the APi can be seen inside the "controller" package.

### Pre-requisite ###
To run the API, Java 11 or higher is mandatory, and Maven 3.6.0 or higher.

### Build the API and run it
Simply use Maven to create a runnable jar.-> "mvn package".
Run it using the terminal with the command "java -jar PACKAGE_NAME"

# ENDPOINTS

Here are examples how to interact with the API.

Change the base URL according to the actual URL and adjust the port if needed 
 * (application.properties -> server.port =YOUR_PORT)
http://localhost:8080/api/v1/settings

### Application Settings
Used to work with global application settings.

- Get All settings
```
[GET] http://localhost:8080/api/v1/settings
```
- Get specific setting
```
[GET] http://localhost:8080/api/v1/settings/{SETTING_ID}
```
- Add new setting
```
[POST] http://localhost:8080/api/v1/settings
{
	"name": "SETTING_NAME",
	"value": "SETTING_VALUE" 
}
```
- Update existing setting
```
[PUT] http://localhost:8080/api/v1/settings
{
	"id": "SETTING_ID",
	"name": "SETTING_NAME",
	"value": "SETTING_VALUE" 
}
```
- Delete setting 
```
[DELETE] http://localhost:8080/api/v1/settings{SETTING_ID}
```

### Reservations
Used for interacting with reservations

- Get all reservations using pagination
```
[GET] http://localhost:8080/api/v1/reservations?page={PAGE_INDEX}}&size={PAGE_SIZE}
```
- Get specific reservation details
```
[GET] http://localhost:8080/api/v1/reservations/{RESERVATION_ID}
```
- Create reservation (foodCategories property can be omitted)
```
[POST] http://localhost:8080/api/v1/reservations
{
	"peopleCount": HOW_MANY_PEOPLE,
	"dateTime": DATE_AND_TIME,
	"tableId": TABLE_ID,
	"foodCategories": [ID_NUMBERS_OF_CATEGORIES_CUSTOMER_HAS_REQUESTED] 
}
```
- Update reservation (foodCategories property can be omitted)
```
[PUT] http://localhost:8080/api/v1/reservations
{
	"id":RESERVATION_ID
    "peopleCount": HOW_MANY_PEOPLE,
	"dateTime": DATE_AND_TIME,
	"tableId": TABLE_ID,
	"foodCategories": [ID_NUMBERS_OF_CATEGORIES_CUSTOMER_HAS_REQUESTED] 
}
```
- Delete reservation
```
[DELETE] http://localhost:8080/api/v1/reservations/{RESERVATION_ID}
```

### Table locations
Used to interact with table locations in the restaurant

- Get all table locations
```
[GET] http://localhost:8080/api/v1/table-locations
```
- Get all active table locations
```
[GET] http://localhost:8080/api/v1/table-locations/active
```
- Get specific table location
```
[GET] http://localhost:8080/api/v1/table-locations/{TABLE_LOCATION_ID}
```
- Create table location (parentId can be omitted)
```
[POST] http://localhost:8080/api/v1/table-locations
{
	"name": LOCATION_NAME,
	"parentId": ID_OF_PARENT_LOCATION,
	"isActive": TRUE_OR_FALSE
}
```
- Update table location (parentId can be omitted)
```
[PUT] http://localhost:8080/api/v1/reservations
{
    "id", TABLE_LOCATION_ID,
	"name": LOCATION_NAME,
	"parentId": ID_OF_PARENT_LOCATION,
	"isActive": TRUE_OR_FALSE
}
```
- Deactivate location
```
[PATCH] http://localhost:8080/api/v1/table-locations/{TABLE_LOCATION_ID}/deactivate
```
- Activate location
```
[PATCH] http://localhost:8080/api/v1/table-locations/{TABLE_LOCATION_ID}/activate
```

### Tables
Used for interacting with tables inside the restaurant

- Get all tables
```
[GET] http://localhost:8080/api/v1/tables
```
- Get all active tables
```
[GET] http://localhost:8080/api/v1/tables/active
```
- Get specific table
```
[GET] http://localhost:8080/api/v1/tables/{TABLE_LOCATION_ID}
```
- Create table
```
[POST] http://localhost:8080/api/v1/table-locations
{
	"capacity": HOW_MANY_PEOPLE_CAN_FIT,
	"locationId": LOCATION_ID,
	"isActive": TRUE_OR_FALSE
}
```
- Update table
```
[PUT] http://localhost:8080/api/v1/reservations
{
    "id":TABLE_ID,
	"capacity": HOW_MANY_PEOPLE_CAN_FIT,
	"locationId": LOCATION_ID,
	"isActive": TRUE_OR_FALSE
}
```
- Deactivate table
```
[PATCH] http://localhost:8080/api/v1/tables/{TABLE_ID}/deactivate
```
- Activate table
```
[PATCH] http://localhost:8080/api/v1/tables/{TABLE_ID}/activate
```

### Users
Used to interact with users (all passwords are encrypted using BCrypt)

<b>NOTICE:</b> The default username is "user1" and password is "pass1". These will be recreated with every start 
of application if they get deleted/changed.

- Get specific user details
```
[GET] http://localhost:8080/api/v1/users/{USER_ID}
``` 
- Register a new user
```
[POST] http://localhost:8080/api/v1/users
{
    "username":USERNAME,
	"password": PASSWORD
}
```
