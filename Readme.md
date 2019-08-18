Belong CustomerService API Solution using Spring boot application
=================================================================
IDE Used: Eclipse IDE

DB: H2 in memory

Server: Apache Tomcat

Framework/Libraries Used:
Spring Boot - Framework to build rest services
JPA - ORM Framework
Log4j - Logger Framework

Steps to run the project:
------------------------
a. Clone the project

b. Use Maven to install required jars from pom.xml

c. The database script file available in resources folder

d. Deploy the project to run

e. Basic authentication is used for all get methods. Hence authorization header should be set using tools like Postman with following credentials (Username : user Password : password)

Service APIs as below

1. Get all phone numbers:
-------------------------
URL: http://localhost:8181/api/v1/customerphones

Response:
[
    {
        "id": 1,
        "phone": "1111-1111-1111",
        "customer": {
            "id": 1,
            "name": "Bruce Wayne"
        },
        "active": false
    },
    {
        "id": 2,
        "phone": "1234-1234-1234",
        "customer": {
            "id": 1,
            "name": "Bruce Wayne"
        },
        "active": false
    },
    {
        "id": 3,
        "phone": "9999-9999-9999",
        "customer": {
            "id": 2,
            "name": "Peter Parker"
        },
        "active": false
    },
    {
        "id": 4,
        "phone": "9876-9876-9876",
        "customer": {
            "id": 2,
            "name": "Peter Parker"
        },
        "active": false
    },
    {
        "id": 5,
        "phone": "5555-5555-5555",
        "customer": {
            "id": 2,
            "name": "Peter Parker"
        },
        "active": false
    }
]

2. Get all phone numbers of a single customer:
---------------------------------------------
URL: http://localhost:8181/api/v1/customerphones/customer/1

Response:

[
    {
        "id": 1,
        "phone": "1111-1111-1111",
        "customer": {
            "id": 1,
            "name": "Bruce Wayne"
        },
        "active": false
    },
    {
        "id": 2,
        "phone": "1234-1234-1234",
        "customer": {
            "id": 1,
            "name": "Bruce Wayne"
        },
        "active": false
    }
]

3. Activate a phone number:
--------------------------
Note : In this API Primary Key id of CustomerPhone entity is used for activation since exposing phone number in get request is not advisable.

URL: http://localhost:8181/api/v1/customerphone/activate/1

Response:

{
    "id": 1,
    "phone": "1111-1111-1111",
    "customer": {
        "id": 1,
        "name": "Bruce Wayne"
    },
    "active": true
}
