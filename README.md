Task Management RESTful API
This project is a Task Management API developed using Spring Boot, designed to manage and track tasks efficiently. 
This API includes several key features such as pagination, event logging, and JWT-based authentication to provide a secure and responsive experience for users.

GET /tasks-display: Fetch all tasks.
GET /tasks/{id}: Retrieve a specific task by its ID.
Pagination:

The API supports pagination for the task list. You can specify page and size query parameters to get a subset of tasks.
Example: GET /tasks?paginated=true&page={pagenumber}&size={NumberOfResponse}.

Event Logs: GET /event-logs: Displays changes made to tasks, useful for auditing and tracking task modifications.

JWT Authentication: The API is secured using JSON Web Token (JWT) for user authentication. Ensure to pass a valid token in the Authorization header when making requests that require authentication.


Run the application using Maven:
  mvn spring-boot:run
  The application will be available at "http://localhost:8080"

Endpoints Overview
  - GET /tasks-display: Get a list of all tasks.
  - GET /tasks/{id}: Get a specific task by its ID.
  - GET /tasks?paginated=true&page={pagenumber}&size={NumberOfResponse}: Get tasks with pagination support.
  - GET /event-logs: Get event logs of task changes.

