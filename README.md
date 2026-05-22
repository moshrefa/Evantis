# Evantis Backend

Spring Boot 3 REST API for the Evantis photography and event decoration platform.

---

## Prerequisites

- Java 21
- Maven 3.8+
- PostgreSQL 14+

---

## Local Setup

### 1. Create the database

```sql
CREATE DATABASE evantis_db;
```

### 2. Create your `.env` file

```bash
cp .env.example .env
```

Open `.env` and fill in your values:

```
DB_URL=jdbc:postgresql://localhost:5432/evantis_db
DB_USERNAME=your_postgres_username
DB_PASSWORD=your_postgres_password
FRONTEND_URL=http://localhost:3000
ANTHROPIC_API_KEY=           # leave blank for now
```

### 3. Run the app

```bash
./mvnw spring-boot:run
```

Or with the dev profile (verbose SQL logging):

```bash
SPRING_PROFILES_ACTIVE=dev ./mvnw spring-boot:run
```

### 4. Verify it's running

```
GET http://localhost:8080/api/health
→ 200 OK: "Evantis API running"
```

---

## Environment Variables

| Variable | Required | Description |
|---|---|---|
| `DB_URL` | Yes | PostgreSQL JDBC URL |
| `DB_USERNAME` | Yes | Database username |
| `DB_PASSWORD` | Yes | Database password |
| `FRONTEND_URL` | No | Deployed frontend URL (for CORS) |
| `ANTHROPIC_API_KEY` | No | Future AI chat feature |

---

## Folder Structure

```
src/main/java/com/evantis/
├── EvantisApplication.java     # App entry point, loads .env
├── config/
│   ├── CorsConfig.java         # Allowed frontend origins
│   └── SecurityConfig.java     # Auth rules (open for now)
├── controller/
│   └── HealthController.java   # GET /api/health
├── model/
│   └── Inquiry.java            # Client inquiry entity (DB table)
├── repository/
│   └── InquiryRepository.java  # Database access for inquiries
└── service/
    └── InquiryService.java     # Business logic for inquiries

src/main/resources/
├── application.properties      # Main config (reads from env vars)
└── application-dev.properties  # Local dev overrides

frontend/
└── index.html                  # Placeholder frontend
```

---

## Roadmap

- [ ] `InquiryController` — POST /api/inquiries to receive form submissions
- [ ] Email notification on new inquiry (SendGrid or AWS SES)
- [ ] Admin dashboard API (list/manage inquiries)
- [ ] User authentication (JWT)
- [ ] Vendor/marketplace accounts
- [ ] AI chat assistant (Anthropic API)
