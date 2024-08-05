# AS0824
# Rental Tool API Documentation

## Overview
The `RentalController` manages rental transactions and tool management within the application. It allows for the checkout of tools, adding new tools to the inventory, and retrieving all available tools.

## Dependencies
- Spring Boot (Web, Data JPA)
- Jackson for JSON conversion

## API Endpoints

### POST /checkout
- **Description**: This endpoint is used for checking out a tool for rental. It requires a `RentalRequest` object and returns a `RentalAgreement`.
- **Request Body**: `RentalRequest` containing:
    - `toolCode`: String (Unique identifier for the tool)
    - `rentalDays`: Integer (Number of days the tool is rented)
    - `discountPercent`: Integer (Discount on the rental price, between 0 and 100)
    - `checkoutDate`: LocalDate (Date when the tool is checked out)
- **Responses**:
    - `200 OK`: Returns a `RentalAgreement` if the checkout is successful.
    - `400 Bad Request`: Returns an error if the rental days are zero or negative, or if the discount percentage is outside the 0-100 range.
- **Exceptions**:
    - `RentalDayCountException`: Triggered when `rentalDays` is less than or equal to zero.
    - `DiscountPercentageException`: Triggered when `discountPercent` is not between 0% and 100%.

### GET /tools
- **Description**: Retrieves a list of all tools currently available in the inventory.
- **Responses**:
    - `200 OK`: Returns a list of `Tool` objects.

### POST /add
- **Description**: Adds a new tool to the inventory.
- **Request Body**: `Tool` object containing details about the tool.
- **Responses**:
    - `200 OK`: Returns the added `Tool` object if the operation is successful.

## Setup and Installation
1. Ensure you have Java 17 installed.
2. Install Gradle, if not already installed.
3. Clone the repository:
4. Navigate to the root directory of the project:
5. Run the application using Gradle:
    ./gradlew bootRun
   Alternatively, you can build the project and run the JAR file:
   gradle build
   java -jar build/libs/<generated-jar-file>.jar

## Usage Examples
Here are some example CURL commands to interact with the API:

### Checkout a Tool
```bash
curl -X POST http://localhost:8080/checkout \
-H 'Content-Type: application/json' \
-d '{
"toolCode": "JAKR",
"rentalDays": 4,
"discountPercent": 20,
"checkoutDate": "2022-07-15"
}'
