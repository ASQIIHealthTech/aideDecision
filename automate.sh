####################################################
# Author: Mohamed Aziz Hassene
# Email: azizhassen69@gmail.com
# Description: Automate the build and destruction of
# Of docker images and building, destroying the docker
# Compose infrastructre.
#####################################################

#! /bin/bash

PROJECT_PATH=$HOME/Desktop/PFE/aideDecision
DOCKER_COMPOSE=$PROJECT_PATH/deploy/docker-compose.yml

if [ $# -ne 1 ] 
then
    echo "Usage: $0 [ build | clean ]"
    echo "Please run help to see description"
    exit 1
fi

if [ $1 = "help" ]
then
    echo "Usage: $0 [ build | destroy ]"
    echo "  build          automate the update and run of new version of images"
    echo "  destroy        stop the application and the images"
    echo "  check          check if the infrastructure is up"
    exit 0
fi

if [ $1 == "build" ] 
then
    # Clean old images from the repo
    docker image rm mcda-front mcda-api-deploy mcda-db 

    # Update jar file and rebuild the image
    mvn clean package -f $PROJECT_PATH/api/pom.xml
    docker build -t mcda-api-deploy:latest $PROJECT_PATH/api/

    # Build the other image (front and database)
    docker build --no-cache -t mcda-front:latest $PROJECT_PATH/front/
    docker build -t mcda-db:latest $PROJECT_PATH/deploy/db/

    # Bring up the docker-compose
    docker-compose -f $DOCKER_COMPOSE up -d
    exit 0
fi

if [ $1 == "clean" ] 
then
    # Bring down the docker-compose
    docker-compose -f $DOCKER_COMPOSE down 2> /dev/null 
    exit 0
fi

if [ $1 == "check" ]
then

    # Check runing containers
    CONTAINER_NUMBER=$(docker ps | wc -l)  
    echo "The number of running containers is $(expr ${CONTAINER_NUMBER} - 1)"
    exit 0 
    
fi

exit 0 


