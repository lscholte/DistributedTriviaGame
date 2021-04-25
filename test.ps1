#Build the Mongo DB image. It is based off of an existing image and simply copies over the TriviaQuiz.json file into the image
docker build -t trivia-game-mongo -f MongoDockerfile .

#Kill and remove any existing containers so that we start fresh
docker stop mongo1
docker stop mongo2
docker stop mongo3
#docker stop server1

docker rm mongo1
docker rm mongo2
docker rm mongo3
#docker rm server1

docker network rm trivia-game-network
docker network create trivia-game-network

#--add-host mongo1:127.0.0.1 --add-host mongo2:127.0.0.1 --add-host mongo3:127.0.0.1
#--add-host mongo1:127.0.0.1 --add-host mongo2:127.0.0.1 --add-host mongo3:127.0.0.1
#--add-host mongo1:127.0.0.1 --add-host mongo2:127.0.0.1 --add-host mongo3:127.0.0.1

Start-Process -FilePath docker -ArgumentList ("run -p 30001:27017 --network trivia-game-network --name mongo1 trivia-game-mongo mongod --replSet trivia")
Start-Process -FilePath docker -ArgumentList ("run -p 30002:27017 --network trivia-game-network --name mongo2 trivia-game-mongo mongod --replSet trivia")
Start-Process -FilePath docker -ArgumentList ("run -p 30003:27017 --network trivia-game-network --name mongo3 trivia-game-mongo mongod --replSet trivia")

Start-Sleep -Seconds 3

# Gets the 3 mongo DB container IPs. I don't think these IPs are accessible from host -- only accessible from within the trivia-game-network that the 3 containers use (meaning only the containers can communicate with each other)
$mongo1Ip = docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' mongo1
$mongo2Ip = docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' mongo2
$mongo3Ip = docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' mongo3

# $mongo1Ip = "172.25.0.2"
# $mongo2Ip = "172.25.0.3"
# $mongo3Ip = "172.25.0.4"

$mongo1host = "{0}:27017" -f $mongo1Ip
$mongo2host = "{0}:27017" -f $mongo2Ip
$mongo3host = "{0}:27017" -f $mongo3Ip

$config = "{
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
#docker exec -it mongo1 mongo --port 30001 --eval "rs.initiate()"
#docker exec -it mongo1 mongo --port 30001 --eval ("rs.add('{0}:30002')" -f $mongo2Ip)
#docker exec -it mongo1 mongo --port 30001 --eval ("rs.add('{0}:30003')" -f $mongo3Ip)

docker exec -it mongo1 mongo --eval ("rs.initiate({0})" -f $config)

# # Add the questions to the primary. The other mongo DBs should automatically see this
docker exec -it mongo1 mongoimport --jsonArray --db trivia --collection questions --file TriviaQuiz.json


$args = ("{0}" -f $mongo1host) + "," + ("{0}" -f $mongo2host) + "," + ("{0}" -f $mongo3host)
echo $args
#Start-Process -FilePath docker -ArgumentList ("run -p 40001:7766 --network trivia-game-network --name server1 trivia-game-java java -jar /Server.jar $args")

#Start-Process -FilePath java -ArgumentList ("-jar ./Server.jar $args")


