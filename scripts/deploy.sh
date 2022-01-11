
mvn clean

mvn package -e

scp -P 2222 target/plyuk-0.0.1-SNAPSHOT.jar \
s310282@se.ifmo.ru:/home/s310282/
ssh -L 8080:localhost:8080 -p 2222 s310282@se.ifmo.ru