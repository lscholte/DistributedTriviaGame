#!/usr/bin/env bash

#Build the Mongo DB image. It is based off of an existing image and simply copies over the TriviaQuiz.json file into the image
docker build -t trivia-game-mongo -f MongoDockerfile .

#Kill and remove any existing containers so that we start fresh
docker stop mongo1
docker stop mongo2
docker stop mongo3

docker rm mongo1
docker rm mongo2
docker rm mongo3

docker network rm trivia-game-network
docker network create trivia-game-network

docker run -d -p 30001:27017 --network trivia-game-network --name mongo1 trivia-game-mongo mongod --replSet trivia
docker run -d -p 30002:27017 --network trivia-game-network --name mongo2 trivia-game-mongo mongod --replSet trivia
docker run -d -p 30003:27017 --network trivia-game-network --name mongo3 trivia-game-mongo mongod --replSet trivia

sleep 5

# Gets the 3 mongo DB container IPs. I don't think these IPs are accessible from host -- only accessible from within the trivia-game-network that the 3 containers use (meaning only the containers can communicate with each other)
mongo1Ip=$(docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' mongo1)
mongo2Ip=$(docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' mongo2)
mongo3Ip=$(docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' mongo3)

mongo1host="$mongo1Ip:27017"
mongo2host="$mongo2Ip:27017"
mongo3host="$mongo3Ip:27017"

config="{
            	'_id' : 'trivia',
            	'members' : [
            		{
            			'_id' : 0,
            			'host' : '$mongo1host'
            		},
            		{
            			'_id' : 1,
            			'host' : '$mongo2host'
            		},
            		{
            			'_id' : 2,
            			'host' : '$mongo3host'
            		}
            	],
            	'settings' : {
                    'heartbeatTimeoutSecs' : 1,
                    'electionTimeoutMillis' : 1000
                }
            }"

echo $config

# Some replica set initialization. Perform this on any one of the mongo DB containers
docker exec -it mongo1 mongo --eval "rs.initiate($config)"

sleep 5

# # Add the questions to the primary. The other mongo DBs should automatically see this
docker exec -it mongo1 mongoimport --jsonArray --db trivia --collection questions --file TriviaQuiz.json

args="$mongo1host $mongo2host $mongo3host"
echo $args

# Run the game server, connecting it to the Mongo databases
java -jar ./Server.jar $args


