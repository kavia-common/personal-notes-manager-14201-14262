package com.example.notesappbackend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * OpenAPI metadata and tags configuration.
 */
@OpenAPIDefinition(
        info = @Info(
                title = "Notes App Backend API",
                version = "0.1.0",
                description = "REST API for managing personal notes (create/read/update/delete).",
                contact = @Contact(name = "Notes App", email = "support@example.com")
        ),
        tags = {
                @Tag(name = "Notes", description = "CRUD operations for personal notes"),
                @Tag(name = "Hello Controller", description = "Basic endpoints for notesappbackend")
        }
)
public class OpenApiConfig {
    // Empty class used only for annotations to configure OpenAPI.
}
