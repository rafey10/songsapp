# Songs Service

### Documentation
This is a Java Spring Boot Application which provides a REST API for adding and accessing data related to a User’s favorite songs of all time.

To run the application with docker, simply navigate to the root folder and do:

`docker build –t songs/mysongsapp`

`docker run –p 8080:8080 songs/mysongsapp`

To run the application with Gradle (you still need the docker daemon running for this), simply do:

`./gradlew build`

`./gradlew bootRun` 
