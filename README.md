# personal-notes-manager-14201-14262

Notes App Backend (Spring Boot)

Run:
- ./gradlew bootRun from notes_app_backend

Docs:
- Swagger UI: /swagger-ui.html
- API Docs: /api-docs

Sample API:
- List notes: GET /api/notes
- Create note: POST /api/notes
  {
    "title": "My Note",
    "content": "Hello world",
    "userId": "user-123"
  }
- Get note: GET /api/notes/{id}
- Update note: PUT /api/notes/{id}
  {
    "title": "Updated",
    "content": "Updated content"
  }
- Delete note: DELETE /api/notes/{id}

H2 Console:
- /h2-console (JDBC URL: jdbc:h2:mem:testdb, user: sa, password: empty)
