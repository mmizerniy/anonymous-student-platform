# anonymous-student-platform
Anonymous University Platform

A backend system for anonymous academic interaction within a university environment. The platform enables students to share study materials, discuss subjects, and provide anonymous feedback on courses and instructors.

Overview

Anonymous University Platform is a RESTful backend service designed to support academic collaboration and feedback exchange while preserving user anonymity where required.

The system models a university ecosystem where students can:

Share and access study materials
Discuss academic subjects
Leave anonymous reviews for instructors
Comment on shared content

The platform is built with scalability, modularity, and maintainability in mind.

Key Features
User & Access Management
User registration and management
Role-based access control (Student, Moderator, Admin)
Secure authentication (JWT-based)
Academic Structure
Subject and course management
Association of materials with subjects
Study Materials
Upload and retrieval of study resources
Metadata-based organization (title, description, subject)
Community Interaction
Comments on materials
Anonymous teacher reviews
Moderation capabilities
System Capabilities
Pagination and filtering support
Global exception handling
DTO-based API design
Input validation
Architecture

The system follows a layered architecture:

Controller Layer — REST API endpoints
Service Layer — business logic
Repository Layer — data access (Spring Data JPA)
Domain Layer — JPA entities
DTO Layer — API contracts
Exception Layer — centralized error handling
Tech Stack
Java 21
Spring Boot
Spring Web
Spring Data JPA
Spring Security (JWT)
PostgreSQL
Hibernate
Maven
Lombok
Bean Validation (Jakarta Validation)
Domain Model
Core Entities
User — system users with role-based permissions
Subject — academic courses and disciplines
Material — uploaded study resources linked to subjects
Comment — discussions under materials
Review — anonymous feedback about instructors
Security Model

The system uses JWT-based authentication with role-based authorization.

Roles:
STUDENT — can access and contribute content
MODERATOR — can manage and moderate user-generated content
ADMIN — full system access

Anonymous reviews are stored with internal user linkage while masking identity at API level.

API Design

The API follows REST principles with JSON-based communication.

Example endpoints:
Users
POST /api/users
GET /api/users/{id}
Subjects
POST /api/subjects
GET /api/subjects
Materials
POST /api/materials
GET /api/materials
GET /api/materials/{id}
DELETE /api/materials/{id}
Comments
POST /api/materials/{id}/comments
GET /api/materials/{id}/comments
Reviews
POST /api/reviews
GET /api/reviews
Data Storage

The system uses PostgreSQL as the primary relational database.

Schema management:

Development: Hibernate auto schema generation
Production: Migration-based approach (Flyway-ready design)
Design Principles
Separation of concerns
DTO-first API design
Secure-by-design architecture
Stateless authentication
Clean service boundaries
Scalable domain modeling
Non-Functional Requirements
Stateless REST API
Horizontal scalability ready
Secure authentication and authorization
Input validation at API boundary
Centralized error handling
Consistent API responses
Future Extensions

The architecture is designed to support:

Event-driven processing (Kafka)
Distributed caching (Redis)
Full-text search engine integration
Object storage (S3-compatible)
Real-time notifications (WebSocket)
Containerized deployment (Docker)
CI/CD pipelines
Observability (metrics & logging stack)
Configuration

The application is environment-driven.

Sensitive values are externalized via environment variables:

Database credentials
JWT secrets
External service keys

The system supports multiple environments (development, production).

Author

Backend system designed as a scalable university collaboration platform with a focus on real-world backend engineering practices.

License

This project is intended for educational and portfolio purposes.
:::
