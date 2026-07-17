# EduPlus

API REST Spring Boot de gestion scolaire.

## Fonctionnalités principales
- Authentification JWT
- Gestion des étudiants et enseignants
- Gestion des cours et inscriptions
- Upload de photos de profil et de documents PDF
- Documentation Swagger/OpenAPI

## Prérequis
- Java 17
- Maven
- MySQL

## Configuration
Configurer la base de données dans src/main/resources/application.yaml.

## Lancement
```bash
./mvnw spring-boot:run
```

## Documentation API
Une fois l'application lancée, ouvrir :
- http://localhost:8080/swagger-ui/index.html
