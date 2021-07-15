#Command to run docker

1) #to run kafka
cd kafka-docker && docker-compose -f docker-compose-expose.yml up

this whole kafka-docker directory is copied from https://github.com/wurstmeister/kafka-docker.git
just for this project demo


2) # to run mongodb
docker -f docker-compose-mongo.yml up

3) #To run spring boot application
java -jar target/Intuit-1.0-SNAPSHOT.jar org.example.intuit.IntuitApplication


#Main points

1)Uses JWT authentication

2)User can signup or login using api

2)Once signedup user can use jwt-authentication to call other api

