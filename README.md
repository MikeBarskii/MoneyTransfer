# MoneyTransfer

MoneyTransfer is REST API application for transfer money between accounts.

## Installing

Compile the project and run tests:
```
mvn clean package
```
Run the application on port 4567

```
java -jar target/MoneyTransfer.jar
```
## REST API

**Get accounts**

**API call**

```
GET localhost:4567/accounts/
```

**Example response**

```
[
    {
        "id": 1,
        "customer": {
            "id": 1,
            "name": "Mike",
            "surname": "Barskiy",
            "phoneNumber": "+79818403837",
            "email": "mikebarskiy@gmail.com"
        },
        "balance": 10000,
        "currency": "AUD"
    },
    {
        "id": 2,
        "customer": {
            "id": 2,
            "name": "Ekaterina",
            "surname": "Khodosova",
            "phoneNumber": "+79818257940",
            "email": "khodosova@mail.ru"
        },
        "balance": 25000,
        "currency": "USD"
    }
]
```

**Create a new transfer**
--
**API call**

Parameters:
- senderId - sender account identifier
- receiverId - receiver account identifier
- amount - amount of money to transfer
```
POST localhost:4567/transfers/create

{
	"senderId":4,
	"receiverId":1,
	"amount":1000
}
```

**Example response**

```
{
    "id": 2,
    "senderId": 4,
    "receiverId": 1,
    "amount": 1000
}
```

**Get all transfers**
--
**API call**

```
GET localhost:4567/transfers/
```

**Example response**

```
[
    {
        "id": 1,
        "senderId": 1,
        "receiverId": 2,
        "amount": 500
    },
    {
        "id": 2,
        "senderId": 4,
        "receiverId": 1,
        "amount": 1000
    }
]
```

**Receive a certain transfer by Id**
--
**API call**

```
GET localhost:4567/transfers/1
```

**Example response**

```
{
    "id": 1,
    "senderId": 1,
    "receiverId": 2,
    "amount": 500
}
```

**Transfer between the same account**
--
**API call**

```
POST localhost:4567/transfers/create

{
	"senderId":4,
	"receiverId":4,
	"amount":2000
}
```

**Example response**

```
{
    "status": "ERROR",
    "message": "Can't send money to the same account!"
}
```

**Attempt to get a non-existent page**
--
**API call**

```
GET localhost:4567/unused/1
```

**Example response**

```
{
    "status": "ERROR",
    "code": 404,
    "message": "Not Found"
}
```

## Built With

* [Spark Framework](http://sparkjava.com/) - Web application framework, runs on an embedded Jetty web server
* [Google Guice](https://github.com/google/guice) - Dependency injection
* [OrmLite](http://ormlite.com/) - Persisting Java objects to SQL
* [H2](http://www.h2database.com/) - Database
* [JUnit](https://junit.org) - Framework to write tests
* [Maven](https://maven.apache.org/) - Dependency Management
* [SLF4J](https://www.slf4j.org/) - Simple logging facade