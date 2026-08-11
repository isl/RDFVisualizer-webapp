# Docker build and deploy support

```
# move to the project folder containing pom.xml file
cd C:\Codes\Echoes\RDFVisualizer

# build the docker image using the expected version tag
docker build -t rdfv:2.1 -f docker/Dockerfile .

# if not already created, create a .env file based on .env_template.txt 
cp docker/.env_template.txt docker/.env

# edit and adjust .env values
nano docker/.env

# run docker container based on .env values and docker compose 
docker compose -f docker/docker-compose.yml up -d

# visit the web application using url http://{hostip}:{.envport}/RDFV/
```
