Belong CustomerService API Solution using Spring boot application

IDE Used: Eclipse IDE

DB: H2 in memory

Server: Apache Tomcat

Framework/Libraries Used:
Spring Boot - Framework to build rest services
JPA - ORM Framework
Log4j - Logger Framework

Steps to run the project:
========================
Clone the project
Use Maven to install required jars from pom.xml
The database script file available in resources folder
Deploy the project to run
Basic authentication is used for all get methods. (Username : user Password : password)

Service URLs as below

Get all phone numbers:
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

Get all phone numbers of a single customer:

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