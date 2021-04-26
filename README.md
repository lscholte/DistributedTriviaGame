# Compiling the code
TODO: Document how to compile the protobufs (only necessary if changing the .proto files)

# Running Client
Client must specify a server IP and port as a command line argument. E.g.
> java -jar Client.jar localhost:60000

# Running Coordinator
No arguments need to be specified. Coordinator is hardcoded to run at port 5000

# Running Server
Server must specify its port number as a command line arguments. Nothing is needed for coordinator as its assumed to be on localhost:5000. E.g
> java -jar Server.jar 60000

# MongoDB Installation / Setup
1. Select the correct platform and follow installation instructions below\
To install MongoDB: https://docs.mongodb.com/manual/administration/install-community/ \
To install Mongo Compass (GUI): https://www.mongodb.com/try/download/compass

2. Once install, test connection mongodb through compass.
Default settings is localhost:27017

3. Create a database called "trivia"
4. Inside "trivia" create a collection "questions"
5. Upload TrivaQuiz.json through add data feature on Compass.
There should be 100 questions

