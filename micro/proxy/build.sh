mvn clean package
$(aws ecr get-login --no-include-email --region us-east-1)
docker build -t myproxy .
docker tag myproxy:latest 326286503284.dkr.ecr.us-east-1.amazonaws.com/myproxy:latest
docker push 326286503284.dkr.ecr.us-east-1.amazonaws.com/myproxy:latest
