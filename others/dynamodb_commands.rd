aws dynamodb create-table --cli-input-json file://person-table.json --endpoint-url http://localhost:8000
//aws dynamodb get-item --table-name Person --key '{"PersonId":{"S": "1"}}'  --endpoint-url http://localhost:8000
//aws dynamodb put-item --table-name Person --item file://put-item.json --endpoint-url http://localhost:8000
//aws dynamodb delete-table --table-name Person --endpoint-url http://localhost:8000
aws dynamodb create-table --cli-input-json file://user-table.json --profile tamil
aws dynamodb create-table --cli-input-json file://verification_token.json --profile tamil
aws dynamodb create-table --cli-input-json file://person-table.json --profile tamil
aws dynamodb create-table --cli-input-json file://job-table.json --profile tamil
aws dynamodb create-table --cli-input-json file://applied-job-table.json --profile tamil
aws dynamodb delete-table --table-name job  --profile tamil
aws dynamodb create-table --cli-input-json file://recipe.json

aws dynamodb query --table-name recipe --index-name GSI-2 --key-condition-expression "category = :cat" --expression-attribute-values file://input.json --endpoint-url http://localhost:8000 --profile local

aws dynamodb delete-table --table-name recipe --endpoint-url http://localhost:8000 --profile local

aws dynamodb list-tables --endpoint-url http://localhost:8000 --profile app_user
aws dynamodb create-table --cli-input-json file://recipe.json --endpoint-url http://localhost:8000 --profile local

aws dynamodb query --table-name recipe --index-name GSI-2 --key-condition-expression "category = :cat" --expression-attribute-values file://input.json --endpoint-url http://localhost:8000 --profile local

