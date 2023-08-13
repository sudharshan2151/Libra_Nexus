# Library Management System📚📚
![Grey Black Minimalist Modern Floral Book Club Logo (1)](https://github.com/sudharshan2151/Libra_Nexus/assets/123924081/6724415d-e420-439c-9e87-849a20f7f237)

The Library Management System is a software solution designed to efficiently manage the operations of a library. It aims to provide librarians and students with a platform to facilitate book management, rental transactions, feedback collection, and more.

## Table of Contents:
- [Description](#description)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Database Schema](#database-schema)
- [Setup Instructions](#setup-instructions)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Description:

The Library Management System is a console-based Java application developed using the DAO pattern and Hibernate for database interaction. It allows librarians to perform administrative tasks related to books, track student rentals, view feedback and ratings provided by students. Students can explore available books, rent books, provide feedback, and rate rented books.

## Features:

- Librarian
  - Register and login to librarian accounts.
  - Add new books and update book information.
  - Remove books from the system.
  - View the status of student rentals and feedback.
  - Log out from the librarian account.

- Student
  - Register and login to student accounts.
  - View available books and apply filters/sorting options.
  - Rent books and provide feedback and ratings.
  - Return rented books within 7 days.
  - Log out from the student account.

## Technologies Used:

- Java
- Hibernate
- MySQL
- DAO Pattern
- Jakarta Persistence API (JPA)

## Database Schema:

The database schema for the Library Management System consists of tables for librarians, students, books, rentals, feedbacks, etc. The ER-Diagram and SQL scripts for creating the database tables can be found in the `database` folder.

![diagram-export-7_24_2023, 7_37_27 PM](https://github.com/sudharshan2151/ablaze-twist-8354/assets/123924081/3542b644-be33-4567-be14-2d3b51dcf975)

## Setup Instructions:

1. Install Java (JDK) on your system.
2. Install MySQL and create a database named "project" (or update the database configuration in `persistence.xml`).
3. Clone this repository to your local machine.
4. Import the project into your favorite IDE (e.g., Eclipse, IntelliJ).
5. Build the project to download dependencies.
6. Run the `Main` class to start the console application.

## Usage:

1. Log in as a librarian or student based on the provided functionalities.
2. Follow the console prompts to perform various tasks (e.g., add books, rent books, provide feedback, etc.).

## Contributing:

Contributions are welcome! If you'd like to contribute to the project, please follow the standard GitHub fork and pull request workflow.

## License:

This project is licensed under the [MIT License](LICENSE).

---
Feel free to modify this README file to suit your project's specific details and requirements. Make sure to include clear and concise instructions on how to set up and use the project. Happy coding!
