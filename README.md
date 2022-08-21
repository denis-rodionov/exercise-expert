# Exercise Expert

Project for constructing and using exercises for learning purposes.

## Build docker image
Build Spring native image:
`mvn spring-boot:build-image`

Run image: `docker run --rm -p 8080:8080 docker.io/library/exercise-expert:0.0.1-SNAPSHOT`

To assign admin rights from mongosh:
`db.user.update({"_id" : ObjectId("XXXX")},{$set: { "role" : "ADMIN"}});`

[Spring Native Docs](https://docs.spring.io/spring-native/docs/current/reference/htmlsingle/)


