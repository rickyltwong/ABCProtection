# ABCProtectionPlan

ABCProtectionPlan is a web application designed for ABC Company, an insurance provider offering protection plans for digital devices like TVs, cameras, cell phones, and more. This application enables customers to register products, file claims, and manage their accounts, while administrators can oversee and manage user accounts, claims, and product registrations.

### Prerequisites

- Java JDK 8 or later.
- Apache Tomcat 9 or later.
- MySQL Server 8 or later.

### Database Setup

1. Create the database using the SQL script provided in `src/main/resources/sql/init_db.sql`.
2. Configure your database connection settings in `src/main/java/com/abcprotection/util/DBConnectionUtil.java`.

### Build and Deploy

1. Open the project in Eclipse or any other IDE that supports J2EE projects.
2. Right-click on the project and select `Run As` -> `Run on Server`.
3. Make sure Apache Tomcat is set up correctly and point it to deploy this project.

## Dependencies

- Servlet API
- JSP API
- JDBC
- MySQL Connector Java

## Features

- User registration and login.
- Product registration for insurance.
- Claim submission and status tracking.
- Admin functionalities to manage users, claims, and product registrations.
