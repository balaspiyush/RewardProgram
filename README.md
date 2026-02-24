# Reward Program Calculation API
> This project created in Spring Boot to calculate rewards of customers.

### Below are the endpoints for this application with its usage
1. http://localhost:8080/v1/api/
    * This endpoint returns all the customers from DB and calculate the rewards
    ```
    [{
    "customerId": "CUST003",
    "customerName": "CustomerThree",
    "rewardsPerMonth": {
        "2025-May": 0.0,
        "2025-Jun": 25.0
    },
    "totalRewards": 25.0
   }, {
   "customerId": "CUST002",
   "customerName": "CustomerTwo",
   "rewardsPerMonth": {
   "2025-Jul": 90.0,
   "2025-Apr": 0.0
   },
   "totalRewards": 90.0
   }, {
   "customerId": "CUST001",
   "customerName": "CustomerOne",
   "rewardsPerMonth": {
   "2025-Jan": 130.0,
   "2025-Mar": 450.0,
   "2025-Feb": 0.0
   },
   "totalRewards": 580.0
   }]

2. http://localhost:8080/v1/api/details
    * This endpoint returns all the transactions from DB(No Reward calculation)
    ```
    [{
    "id": "1",
    "customerId": "CUST001",
    "customerName": "CustomerOne",
    "transactionDate": "2025-01-01",
    "amount": 140.0
   }, {
   "id": "2",
   "customerId": "CUST001",
   "customerName": "CustomerOne",
   "transactionDate": "2025-02-01",
   "amount": 50.0
   }, {
   "id": "3",
   "customerId": "CUST001",
   "customerName": "CustomerOne",
   "transactionDate": "2025-03-01",
   "amount": 300.0
   }, {
   "id": "4",
   "customerId": "CUST002",
   "customerName": "CustomerTwo",
   "transactionDate": "2025-04-10",
   "amount": 48.5
   }, {
   "id": "5",
   "customerId": "CUST002",
   "customerName": "CustomerTwo",
   "transactionDate": "2025-07-01",
   "amount": 120.0
   }, {
   "id": "6",
   "customerId": "CUST003",
   "customerName": "CustomerThree",
   "transactionDate": "2025-05-01",
   "amount": 10.0
   }, {
   "id": "7",
   "customerId": "CUST003",
   "customerName": "CustomerThree",
   "transactionDate": "2025-06-01",
   "amount": 75.0
   }]
3. http://localhost:8080/v1/api/{customerId}
    * This endpoint returns specific customer and its rewards
    ```
    http://localhost:8080/v1/api/CUST001

    [{
    "customerId": "CUST001",
    "customerName": "CustomerOne",
    "rewardsPerMonth": {
        "2025-Jan": 130.0,
        "2025-Mar": 450.0,
        "2025-Feb": 0.0
    },
    "totalRewards": 580.0
   }]

## For now added test cases for controller but can add more around service class.
## I have used azul-17 JDK to create this application