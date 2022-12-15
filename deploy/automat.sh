########
###
## Tis script is used in the dev enviroment to facilatate the workeflow and 
## The process of developpment by automating the buid of the application's api
## And deploying the whole infrastructe
## The script takes 1 arguments with the values [Build|Destroy]
## NB: the script is still underdeveloppment. Reading the script is recommanded before use.
## Build: Build the application
## Destroy: Remove and Stop the application's infrastructure
###

## Author: Mohamed Aziz Hassene
## Day: 12-15-2022
########


#! /bin/bash

API_PATH=~/Desktop/mcda/api
DEPLOY_FILE_PATH=~/Desktop/mcda/deploy/docker-compose.yml


if [ $# -gt 0 ] 
then 

    if [ $1 == "Build" ]
    then
        echo $options
        
        echo "Building and deploying the application"

        if ! options=$(getopt -o rc -l rebuild,check -- "$@")
        then
        echo "Wrong argumetns"
        exit 1
        fi

        set -- $options
        case "${options}" in

            # cleaning and rebuilding the jar target
            -r | --rebuild)  mvn clean -f  ${API_PATH}; mvn install -f ${API_PATH};;
        esac    
        
        # building and deploying 
        docker-compose -f ${DEPLOY_FILE_PATH} up -d --build

        case "${options}" in
            -c | --check ) docker ps -aq;;
        esac
        echo "Build and deployment Terminated..."

    elif [ $1 == "Destroy" ]
    then
        # destroy app
        echo "$options"
         
        docker-compose down

        case "${options}" in 
            -c | --check) docker ps -aq;;
        esac

        echo "Infrastructure is down. Terminating..."

    else 
    echo "Wrong Argument. Please use Build or Destroy as Argument!!"

    fi

else 
echo "Usage: $0 [Build|Destroy]"
exit 2
fi 

