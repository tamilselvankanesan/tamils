aws dynamodb create-table --cli-input-json file://person-table.json --endpoint-url http://localhost:8000
//aws dynamodb get-item --table-name Person --key '{"PersonId":{"S": "1"}}'  --endpoint-url http://localhost:8000
//aws dynamodb put-item --table-name Person --item file://put-item.json --endpoint-url http://localhost:8000
//aws dynamodb delete-table --table-name Person --endpoint-url http://localhost:8000
aws dynamodb create-table --cli-input-json file://user-table.json --profile tamil
aws dynamodb create-table --cli-input-json file://verification_token.json --profile tamil
aws dynamodb create-table --cli-input-json file://person-table.json --profile tamil


