# 📘 GestionTareas – Microservicio de Gestión de Proyectos

Este microservicio permite gestionar **usuarios**, **proyectos**, y **tareas**, con operaciones CRUD y consultas por relaciones entre servicios.

---

## 🌐 Base URLs

- Usuarios: `http://localhost:8080/api/user`
- Proyectos: `http://localhost:8080/api/project`
- Tareas: `http://localhost:8080/api/task`

---

## 👤 Usuarios

### ➕ Crear usuario

```json
POST http://localhost:8080/api/user
```

**Body:**
``` json
{
  "name": "User",
  "email": "user@gmail.com"
}
```

---
### 🔍 Obtener usuario

Por ID:

```http
GET http://localhost:8080/api/user/by?id=1
```

Por correo:

```http
GET http://localhost:8080/api/user/by?email=user@gmail.com
```

---

### ✏️ Actualizar usuario

```http
PUT http://localhost:8090/api/user?id=1
```

**Body:**

```json
{
  "name": "BetterName",
  "email": "bestUser@gmail.com"
}
```

---

### ❌ Eliminar usuario

```http
DELETE http://localhost:8090/api/user?id=1
```

---

## 📁 Proyectos

### ➕ Crear proyecto

```http
POST http://localhost:8080/api/project
```

**Body:**

```json
{
  "name": "Proyecto 1",
  "description": "Descripcion de proyecto 1",
  "createdBy": 1
}
```

---

### ✏️ Actualizar proyecto

```http
PUT http://localhost:8080/api/project?id=1
```

**Body:**

```json
{
  "name": "Nuevo Nombre",
  "description": "Actualizando descripcion",
  "status": "PENDING"
}
```

---

### ❌ Eliminar proyecto

```http
DELETE http://localhost:8091/api/project?id=1
```

---

## 🔗 Usuario y Proyecto

### 🔍 Obtener proyecto por ID

```http
GET http://localhost:8080/api/project/by?id=1
```

---

### 🔍 Obtener proyectos de un usuario

```http
GET http://localhost:8080/api/project/by?user=1
```

---

## ✅ Tareas

### ➕ Crear tarea

```http
POST http://localhost:8080/api/task
```

**Body:**

```json
{
  "description": "Como se relacionan los usuarios con sn_nomina",
  "createdBy": 1,
  "projectId": 1,
  "taskId": 1,
  "status": "PENDING"
}
```

---

### ✏️ Actualizar tarea

```http
PUT http://localhost:8080/api/task?id=1
```

**Body:**

```json
{
  "description": "Nueva descripcion",
  "taskId": 2,
  "status": "IN_PROGRESS"
}
```

---

### 🔍 Obtener tarea por ID

```http
GET http://localhost:8080/api/task?id=1
```

---

### 🔍 Obtener subtareas

```http
GET http://localhost:8080/api/task/sub?id=1
```

---

### 🔍 Obtener tareas de un proyecto

```http
GET http://localhost:8080/api/task/project?id=1
```

---

## 👥 Usuarios, Tareas y Proyectos

> Puedes consultar proyectos con todas sus tareas o usuarios asociados desde:

```http
GET http://localhost:8080/api/project/by?id=1
```

---
