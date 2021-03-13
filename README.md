# Backbase technical test

## How to run

System requirements:

- JDK 8 or greater
- Port system 9000 must be free

Clone or download the zip project and uncompress it and in the project's folder execute the next command:

##### For Windows CMD
```
mvnw tomcat7:run
```
##### For UNIX based
```
./mvnw tomcat7:run
```

It can take several minutes while downloading dependencies, compiling, building and starting.

After deployment is done you can access to the project's endpoints at:

```
host: http://localhost
port: 9000
base path: /backbasetest/v1
```

Also, you can view the endpoints definition on the embedded Swagger that you will find in the next link

```
http://localhost:9000/swagger-ui.html#/
```

## How to use 

First you need to login in

```
http://localhost:9000/backbasetest/v1/login
```

Send a POST json request. 
In this demo the credentials are
```
{
    "username" : "admin",
    "password" : "root"
}
```

The endpoint will return (if correct credentials) a header: 

"authorization" : "Bearer token"

To consume any endpoint you need to set this header:

"authorization" : "Bearer ```token got in login endpoint```"