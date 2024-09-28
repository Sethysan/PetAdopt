# Pet Adoption Shelter

## Overview

Pet Adoption Shelter is a full-stack web application that allows users to manage pet adoption activities. Users can add new pets to the shelter, view available pets, and adopt them. The application consists of a Vue.js frontend (`shelter-client-vue`) and a Java Spring Boot backend (`shelter-server`).

## Features

- **Add Pets**: Users can add pets to the shelter through a form.
- **View Pets**: Users can browse a list of available pets for adoption.
- **Adopt Pets**: Users can adopt pets and the adoption status is updated in the system.
- **Manage People**: Users can add information about pet adopters.

## Project Structure

### Frontend (`shelter-client-vue`)

This folder contains the Vue.js client for the shelter application.

- **Vue components**: `AddPet.vue`, `Home.vue`
- **Router**: `index.js` for client-side routing.
- **Services**: `ShelterService.js` communicates with the backend API.
- **Main entry**: `main.js` and `App.vue`
- **Styling**: Custom CSS in `style.css`
- **Assets**: Images and static files in `assets/`

### Backend (`shelter-server`)

This folder contains the Spring Boot server for the shelter application.

- **Controller**: `PetController.java` manages API endpoints for pet-related operations.
- **DAO**: Data Access Objects for interacting with the database (`PetDAO.java`, `PetJdbcDAO.java`)
- **Models**: `Pet.java`, `Parent.java` represent data models for pets and adopters.
- **DTO**: `AdoptionDTO.java` handles data transfer for adoptions.
- **Main Application**: `ShelterApplication.java` is the main entry point for the Spring Boot application.

## Prerequisites

- **Node.js** (for the frontend)
- **Java 17+** (for the backend)
- **Maven** (for building the backend)
- **PostgreSQL** or any other relational database for managing data.

## Setup Instructions

### Backend Setup

1. Navigate to the `shelter-server` directory:

   ```bash
   cd shelter-server

   ```

2. Configure the database connection in : `src/main/resources/application.properties`:
   
   `spring.application.name=shelter`

   `spring.datasource.url=jdbc:postgresql://localhost:5432/pets`

   `spring.datasource.username=your_username`

   `spring.datasource.password=your_password`


3. Run the `build.sql` file to set up the database:   

```bash
   psql -h localhost -U your_username -d pets -f Database/build.sql


   ```
   This script will create all the necessary tables and initial data for the application to function properly.
   

4. Build and run the server:

   ```bash
   ./mvnw spring-boot:run

   ```

### Frontend Setup

1. Navigate to the shelter-client-vue directory:
   ```bash
   cd shelter-client-vue

   ```
2. Install dependencies:
   ```bash
   npm install

   ```

3. Run the frontend development server:
   ```bash
   npm run dev

   ```

##### The frontend should be available at http://localhost:5173/ 
##### The backend should be available at http://localhost:8080/

### API Endpoints
   The backend exposes several RESTful API endpoints for managing pets:

-  ***GET***  `"/pets"` : Retrieve a list of all pets.
-  ***POST*** `"/add-pet"` : Add a new pet to the shelter.
-  ***GET***  `"/pet/{id}"` : Retrieve a details of specific pet.
-  ***GET***  `"/parents"` : Retrieve a list of all parents.
-  ***POST*** `"/parents"` : Add a new parent to the shelter.
-  ***GET***  `"/parent/{id}"` : Retrieve a details of specific parent.
-  ***PUT*** `"/adopt"` : Adopt a pet.


### Technologies Used
-   **Frontend** : Vue.js, Vue Router
-   **Backend** : Java Spring Boot, PostgreSQL, Maven
-   **Build Tools** : Vite for the frontend, Maven for the backend


## Contributing

   1. **Fork the repository.**
   2. **Create a new feature branch:**
   ```bash
   git checkout -b feature-name

   ```
   3. **Commit your changes:**
   ```bash
   git commit -m 'Add some feature'

   ```
   4. **Push the branch:**
   ```bash
   git push origin feature-name

   ```
   5. **Open a pull request**
   
   #### License
   This project is licensed under the MIT License

