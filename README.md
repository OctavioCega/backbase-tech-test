# Backbase technical test

## How to run

System requirements:

- JDK 8 or greater
- Port system 9000 must be free

Clone or download the zip project and uncompress it and in the project's folder execute the next command:

```
mvn tomcat7:run
```

It can take several minutes while downloading dependencies, compiling, building and starting.

After deployment is done you can access to the project's endpoints at:

```
host: http://localhost
port: 9000
base path: /bank/v1
```

Also, you can view the endpoints definition on the embedded Swagger that you will find in the next link

```
http://localhost:9000/bank/v1/....
```