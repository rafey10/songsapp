# Songs Service

### Documentation
This is a Java Spring Boot Application which provides a REST API for adding and accessing data related to a User’s favorite songs of all time.

To run the application with docker, simply navigate to the root folder and do:

`docker build –t songs/mysongsapp`

`docker run –p 8080:8080 songs/mysongsapp`

To run the application with Gradle (you still need the docker daemon running for this), simply do:

`./gradlew build`

`./gradlew bootRun` 

The application may sometimes fail to start up, as it is connected to the PROD Database, which has a limitiation for maximum connections which is often breached. This is due to it being a basic AWS Free Tier single instance DB with demo level availability, with Fargate constantly spinning up instances which send connection requests to the DB. Keep trying for a couple of minutes, and eventually it will connect.  
