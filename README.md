# LiterAlura: Book Catalog Challenge

This project consists of developing a book catalog application where users can register books in a PostgreSQL database and retrieve information about them. The application is built using Java, Spring Boot, and Spring Data JPA.

## Project Overview

The console-based application provides five main functionalities:

### 1. Search for a book by title

- The user enters the book title.
  - <img width="588" height="152" alt="image" src="https://github.com/user-attachments/assets/4ae0190f-a433-473e-8a68-6d3af8c5c0ad" />

- The application queries the Gutendex API to retrieve book information.
- The book is registered in the database if it does not already exist.
- 'Example:' Searching for Pride and Prejudice by Jane Austen returns the title, author, language (EN), and number of downloads.
  - <img width="546" height="264" alt="image" src="https://github.com/user-attachments/assets/b0c106bd-8d6f-4cd5-aaca-0571c6cbeef2" />


### 2. List registered books

- Displays all books currently stored in the database.
  - <img width="570" height="543" alt="image" src="https://github.com/user-attachments/assets/097a6a7f-a8ff-49ca-bc58-c5b3a13a388c" />


### 3. List registered authors

- Displays all authors in the system.
  - <img width="584" height="397" alt="image" src="https://github.com/user-attachments/assets/4b3d09bc-57f1-49c6-a778-c73e775b6748" />

- Authors are listed only once, even if they have multiple books.
  - <img width="580" height="126" alt="image" src="https://github.com/user-attachments/assets/01e59e16-75c7-40c8-94bb-a2c8af022067" />

 
### 4. List authors alive in a specific year

- Users can query authors based on a year (e.g., 1600).
  - <img width="587" height="415" alt="image" src="https://github.com/user-attachments/assets/172048d7-a6c3-44e0-9edf-f75f88c62b35" />

- Only authors whose lifespan includes that year are shown.
  - <img width="592" height="87" alt="image" src="https://github.com/user-attachments/assets/3afd793a-c116-4db1-9b64-127b7772cca6" />
 

### 5. List books by language

- Users can search for books by language code (e.g., ES, EN, FR, PT).
  - <img width="586" height="360" alt="image" src="https://github.com/user-attachments/assets/a8ca42b0-ffae-4dcd-80ce-aa0549264885" />


## Additional Considerations

- The application prevents duplicate entries of the same book.

- If a book is not found in the API, the user receives an informative message.

- Optional tasks allow for enhancing the application with additional features or customizations.

## Setup and Requirements

- Create a Java Maven project using Spring Initializer.

- Use Java 17, Spring Boot 3.x, and dependencies: Spring Data JPA and PostgreSQL Driver.

- Familiarity with Java, OOP principles, and Spring Data JPA is required.

## API Integration

- The project uses the Gutendex API, a free digital library of over 70,000 books.

- Documentation is available online and can be translated for convenience.

- The API provides book metadata including title, author, language, and download counts.

## Learning Outcomes

By completing this project, participants will gain experience in:

- Java programming and OOP principles.

- Working with APIs and handling JSON data.

- Using Spring Boot and Spring Data JPA.

- Managing projects with agile tools like Trello.

- Practicing database operations and ensuring data integrity (avoiding duplicates).
