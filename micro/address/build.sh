mvn clean package
$(aws ecr get-login --no-include-email --region us-east-1)
docker build -t myaddress .
docker tag myaddress:latest 326286503284.dkr.ecr.us-east-1.amazonaws.com/myaddress:latest
docker push 326286503284.dkr.ecr.us-east-1.amazonaws.com/myaddress:latest

