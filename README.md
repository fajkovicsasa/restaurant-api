# restaurant-api

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