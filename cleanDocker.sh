# one docker image
if [ $(docker ps -a -q --filter ancestor=life-game:v1 --format="{{.ID}}") ]
then
   docker rm $(docker stop $(docker ps -a -q --filter ancestor=life-game:v1 --format="{{.ID}}"))
else
   echo "docker already clean"
fi