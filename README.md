# Microservice Item - API REST

Este es un microservicio desarrollado con **Java 21** y **Spring Boot** para la gestión de productos o artículos (Items). Proporciona operaciones CRUD (Create, Read, Update, Delete) completas a través de una API REST e interactúa con una base de datos MySQL.

---

## 🛠️ Tecnologías y Herramientas

*   **Lenguaje:** Java 21
*   **Framework:** Spring Boot (con soporte para Spring Web y Spring Data JPA)
*   **Base de Datos:** MySQL
*   **Gestor de Dependencias:** Maven
*   **Plantillas (Opcional):** Thymeleaf

---

## ⚙️ Configuración del Entorno

Antes de ejecutar la aplicación, asegúrate de tener una base de datos MySQL corriendo y configurada según las propiedades en [application.properties](file:///c:/Users/emili/OneDrive/Documentos/Curso%20microservicios/MicroserviceItem1/microserviceitem/src/main/resources/application.properties):

*   **URL de conexión:** `jdbc:mysql://localhost:3308/microserviceitem`
*   **Usuario:** `root`
*   **Contraseña:** `root`
*   **Puerto de la aplicación:** `9093`

> [!NOTE]
> La propiedad `spring.jpa.hibernate.ddl-auto=update` está activada, por lo que las tablas se crearán automáticamente al iniciar la aplicación si la base de datos `microserviceitem` ya existe.

---

## 🚀 Cómo Ejecutar la Aplicación

1. Clona o ubícate en el directorio del proyecto `microserviceitem`.
2. Asegúrate de tener iniciada tu base de datos en el puerto `3308`.
3. Ejecuta el siguiente comando en la terminal desde la raíz del proyecto Maven (`microserviceitem/`):

   **En Windows (PowerShell/CMD):**
   ```bash
   ./mvnw.cmd spring-boot:run
   ```

   **En Linux/macOS:**
   ```bash
   ./mvnw spring-boot:run
   ```

La aplicación estará disponible en: `http://localhost:9093`

---

## 📌 Endpoints de la API REST

La raíz de los endpoints de la API es `/item`. A continuación se detallan las operaciones disponibles:

### 1. Obtener todos los items
*   **Método:** `GET`
*   **Ruta:** `/item`
*   **Respuesta Exitosa:** `200 OK` con la lista de items en formato JSON.

### 2. Obtener un item por su ID
*   **Método:** `GET`
*   **Ruta:** `/item/{id}`
*   **Respuesta Exitosa:** `200 OK` con el objeto del item.
*   **Respuestas de Error:** `404 NOT FOUND` si no existe.

### 3. Buscar un item por su Nombre
*   **Método:** `GET`
*   **Ruta:** `/item/name/{name}`
*   **Respuesta Exitosa:** `200 OK` con el item correspondiente.
*   **Respuestas de Error:** `404 NOT FOUND` si no existe.

### 4. Crear un nuevo item
*   **Método:** `POST`
*   **Ruta:** `/item`
*   **Cuerpo de la Petición (JSON):**
    ```json
    {
      "name": "Laptop",
      "description": "Laptop de alto rendimiento",
      "price": 1200.50,
      "quantity": 15
    }
    ```
*   **Respuesta Exitosa:** `201 CREATED` con el mensaje `"Insertado"`.
*   **Respuestas de Error:** `400 BAD REQUEST` si el formato es inválido.

### 5. Actualizar un item existente
*   **Método:** `PUT`
*   **Ruta:** `/item`
*   **Cuerpo de la Petición (JSON):**
    ```json
    {
      "id": 1,
      "name": "Laptop Pro",
      "description": "Laptop de alto rendimiento actualizada",
      "price": 1350.00,
      "quantity": 10
    }
    ```
*   **Respuesta Exitosa:** `200 OK` con el mensaje `"actualizado"`.
*   **Respuestas de Error:** `400 BAD REQUEST` o `404 NOT FOUND` si el item no existe.

### 6. Eliminar un item
*   **Método:** `DELETE`
*   **Ruta:** `/item/{id}`
*   **Respuesta Exitosa:** `200 OK` con el mensaje `"Eliminado"`.
*   **Respuestas de Error:** `400 BAD REQUEST` o `404 NOT FOUND` si no se puede eliminar.

---

## 📂 Estructura del Código Fuente

*   [`MicroserviceController.java`](file:///c:/Users/emili/OneDrive/Documentos/Curso%20microservicios/MicroserviceItem1/microserviceitem/src/main/java/com/sedena/app/controllers/MicroserviceController.java): Controlador REST con la exposición de los endpoints CRUD.
*   [`item.java`](file:///c:/Users/emili/OneDrive/Documentos/Curso%20microservicios/MicroserviceItem1/microserviceitem/src/main/java/com/sedena/app/entities/item.java): Entidad JPA que mapea a la tabla `items` de la base de datos MySQL.
*   `IService.java`: Interfaz que define los métodos de la capa de servicio.
