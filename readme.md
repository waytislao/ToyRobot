# Toy Robot Project

## Prerequisites
Before running this project, ensure you have the following installed:

- **Java Development Kit (JDK)**: Version 17 or later.
- **Apache Maven**: Version 3.8 or later.
- **Git** (optional): To clone the repository.

## Setting Up the Project

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/waytislao/ToyRobot.git
   cd ToyRobot
   ```

2. **Verify Java and Maven Installation**:
    - Check Java version:
      ```bash
      java -version
      ```
    - Check Maven version:
      ```bash
      mvn -version
      ```

3. **Build the Project**:
   Run the following command to build the project and resolve dependencies:
   ```bash
   mvn clean install
   ```

   The `clean` command ensures that any previous build artifacts are removed, and the `install` command compiles the project, runs tests, and packages the application.

## Running the Application

1. **Navigate to the Target Directory**:
   After a successful build, the compiled JAR file will be located in the `target` directory.
   ```bash
   cd target
   ```

2. **Run the Application**:
   Use the `java` command to run the application:
   ```bash
   java -jar ./ToyRobot-1.0-SNAPSHOT.jar
   ```

## Running Tests
To execute the test suite, run the following Maven command:
```bash
mvn test
```
This will compile and execute all test cases in the `src/test/java` directory.

## Project Structure
The project follows the standard Maven directory layout:
```
project-root
|— src
   |— main
   |    |— java           # Application source code
   |    |— resources      # Application resources
   |— test
        |— java           # Test source code
        |— resources      # Test resources
|— pom.xml                # Maven Project Object Model (POM) file
```
