 # Full‑Stack Product Store — Spring Boot + Angular + PostgreSQL

 Aplikacja demonstracyjna prezentująca:
 - logowanie i rejestrację z JWT,
 - role USER / ADMIN,
 - CRUD produktów,
 - Spring Events,
 - walidację DTO,
 - Angular 17 (standalone, zoneless),
 - PostgreSQL uruchamiany w Dockerze (docker-compose).

 ---

 ## 🚀 Uruchomienie projektu

 ### 1️⃣ Wymagania
 - Docker Desktop (lub inny Docker Engine)
 - Java 17+
 - Maven
 - Node.js 18+
 - Angular CLI

 ---

 ## 🐳 2️⃣ Uruchomienie bazy danych (Docker Compose)

 W katalogu `docker/` uruchom:

 ```bash
 docker compose up -d
 ```

 Uruchomi to:
 - PostgreSQL 16 → port **5432**
 - pgAdmin 4 → port **5050**

 Dane logowania do pgAdmin:
 - email: **admin@admin.com**
 - hasło: **admin**

 Baza danych:
 - nazwa: **demo**
 - user: **postgres**
 - password: **postgres**

 ---

 ## ⚙️ 3️⃣ Konfiguracja backendu

 Plik: `src/main/resources/application.properties`

 ```properties
 spring.datasource.url=jdbc:postgresql://localhost:5432/demo
 spring.datasource.username=postgres
 spring.datasource.password=postgres
 spring.jpa.hibernate.ddl-auto=update
 spring.jpa.show-sql=true
 spring.jpa.properties.hibernate.format_sql=true
 ```

 ---

 ## ▶️ 4️⃣ Uruchomienie backendu (Spring Boot)

 ```bash
 mvn spring-boot:run
 ```

 Backend działa pod:
 - **http://localhost:8080**

 Najważniejsze endpointy:
 - POST `/api/auth/register`
 - POST `/api/auth/login`
 - GET `/api/products`
 - POST `/api/products` (ADMIN)
 - PUT `/api/products/{id}` (ADMIN)
 - DELETE `/api/products/{id}` (ADMIN)

 ---

 ## ▶️ 5️⃣ Uruchomienie frontendu (Angular 17)

 ```bash
 npm install
 ng serve
 ```

 Frontend działa pod:
 - **http://localhost:4200**

 ---

 ## 🔐 6️⃣ Autoryzacja i JWT

 Po zalogowaniu backend zwraca token JWT.
 Angular zapisuje go w `localStorage` i automatycznie dodaje do nagłówków:

 ```
 Authorization: Bearer <token>
 ```

 Role:
 - USER → tylko podgląd produktów
 - ADMIN → pełny CRUD

 ---

 ## 📦 7️⃣ Walidacja danych (DTO)

 Backend używa Bean Validation:

 ```java
 @NotNull @Size(min = 3, max = 100)
 private String name;

 @NotNull @Min(0)
 private Double price;
 ```

 Błędy walidacji zwracane są jako JSON.

 ---

 ## 🔔 8️⃣ Spring Events

 Po utworzeniu produktu wywoływany jest event:

 ```java
 @EventListener
 public void handle(ProductCreatedEvent e) { ... }
 ```

 Eventy służą do rozluźnienia powiązań i dodawania logiki po‑zdarzeniowej.

 ---

 ## 🧱 9️⃣ Struktura projektu

 ### Backend
 - controller/ — REST API
 - dto/ — DTO + walidacja
 - model/ — encje JPA
 - repository/ — Spring Data
 - security/ — JWT + SecurityConfig
 - event/ — Spring Events
 - exception/ — GlobalExceptionHandler

 ### Frontend
 - auth/ — login, register
 - products/ — CRUD produktów
 - services/ — API services
 - guards/ — AuthGuard, AdminGuard
 - interceptors/ — JWT interceptor

 ---

 ## 🧪 1️⃣0️⃣ Testowanie

 ### Rejestracja
 - wypełnij formularz → zapis w bazie → komunikat sukcesu

 ### Logowanie
 - po zalogowaniu UI zmienia się zależnie od roli

 ### CRUD produktów
 - USER → tylko podgląd
 - ADMIN → dodawanie, edycja, usuwanie

 ### Eventy
 - po dodaniu produktu w konsoli backendu pojawia się log eventu

 ---

 ## ✔ Projekt gotowy do oceny

 Aplikacja działa w pełni, spełnia wymagania i prezentuje:
 - integrację Angular + Spring Boot,
 - JWT + role,
 - walidację danych,
 - event‑driven architecture,
 - Dockerową bazę danych,
 - poprawną strukturę backendu i frontendu.
