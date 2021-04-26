# Overview
This is a simple distributed trivia game.

Major components include
* A cluster of 1 or more MongoDB containers which hold multiple-choice trivia questions
* A cluster of 1 or more game servers
* A coordinator to ensure consistency among game state servers
* 1 or more players which can be connected to any game server


# Setup Requirements
1) It seems that you must have a Linux OS such as Ubuntu. We are using Linux Docker containers for our project, and it seems that a Windows host cannot communicate correctly with Linux containers, preventing our project from working if you run it on Windows.
2) You must have Docker installed

# Setup
We have provided a bash script that handles the MongoDB and server setup automatically for you. Simply run the following:
> sudo bash setup.sh

This will start a cluster of 3 Docker containers each hosting a replica MongoDB. The game server will automatically connect to this cluster. You can kill any one of the 3 MongoDB containers and expect the server to still work correctly. The commands to kill the 3 containers are
> docker stop mongo1
> docker stop mongo2
> docker stop mongo3



# Playing a Game
1) As a player, launch Client.jar
2) Enter a name to identify yourself
3) Either create a new lobby or join an existing lobby
3a) If creating a new lobby, a lobby ID will automatically be generated. Share this ID with your friends
3b) If joining an existing lobby, paste in the lobby ID that you want to join and click join
4) When all players have joined, click the start button. Any player may start the game.
5) There will be 10 rounds of questions. You have 10 seconds to answer each question. Incorrect answers award 0 points. Correct answers award between 0 and 100 points depending on how soon the question was answered.

For demo/testing purposes, note that the correct answer will always be option A.
