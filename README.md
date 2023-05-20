# Healthy Sleep

Short description of the project.

## Getting Started

Instructions for how to build and run the project locally.

## Prerequisites

Before running the code, make sure you have the following installed:

Java Development Kit (JDK)
MySQL Server
MySQL Connector/J library (mysql-connector-java-8.0.26.jar)

## Setup
Step 1: git clone https://github.com/viggez/healthySleep.git

Step 2: Import the SQL File

- Open your MySQL client (e.g., MySQL Workbench).
- Create a new database called sleep_environment_db.
- Open the sleep_environment_data.sql file provided in this repository.
- Execute the queries in the file to create the necessary tables and insert sample data.

Step 3: Configure the Database Connection

- Ensure you have the MySQL Connector/J library (JAR file) added to the project's dependencies. You can download it from here.
- In the app/DataBaseHandler.java file, adjust the following variables:
- url: jdbc:mysql://localhost:3306/sleep_environment_db
- username: root
- password: healthySleep!

Step 4: Run the Application

- Run the Java application to connect to the database.
- It allows users to input their caffeine intake and screen time, and stores the data in the database.
- The actionPerformed method in the SleepEnvironmentAnalysis class handles the event when the user interacts with the GUI.
- When the user clicks the coffee buttons or the compare button, the data is saved to the database using the DataBaseHandler class.

## Running the Tests

Instructions for how to run the automated tests for the project.

## Deployment

Instructions for how to deploy the project to a production environment.

## Built With

List of any software or libraries used to build the project.

## Contributing

Guidelines for how to contribute to the project, including how to submit pull requests and how to contact the project maintainers.

## Versioning

Explanation of the versioning strategy used for the project, including how to interpret version numbers and how to find release notes.

## License

Explanation of the license used for the project, including a link to the full license text.

## Acknowledgments

List of any individuals or organizations that contributed to the project, or that the project depends on.

## Contact

Instructions for how to contact the project maintainers with questions or issues.
