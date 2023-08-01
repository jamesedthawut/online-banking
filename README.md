# Online Banking REST API

The Online Banking REST API Backend project using Java Spring Boot. 
This API serves for managing user accounts, Checking balances 
and make a transaction.

Key Features:

1. User Management:
- Registration and authentication of users with encrypting password 
and token-based authentication such as JWT.
- Retrieval and update of user profile information.
- Sending user verification and OTP for logging in via user email.

2. Accounts and Balances:
- Retrieval of account information, including balance and transaction history.

3. Transactions:
- Initiation and processing of transactions, including deposits, withdrawals 
and transfers.
- Retrieval of transaction details and transaction history.
- Transaction categorization.

4. Security:
- Using token-based authentication such as JWT to access APIs.
- Using OTP authentication for logging in.
- Encryption of sensitive data such as passwords and user information.

5. Error Handling:
- Display error handling with appropriate HTTP status codes and error messages.

Tech Stack:

- Java
- HTML
- Spring Boot
- Postgresql
- Docker
- JWT
- Maven
- Lombok
- Mapstruct
- OpenAPI