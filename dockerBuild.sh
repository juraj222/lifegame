# one docker image
docker build -t life-game:v1 .
docker run -d -p 8081:8081 life-game:v1