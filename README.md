# Todo App — CI/CD con GitHub Actions

[![CI - Todo App](https://github.com/mariagmgmgm/trabajo-ssdd-t11-cicd-github-actions/actions/workflows/ci.yml/badge.svg)](https://github.com/mariagmgmgm/trabajo-ssdd-t11-cicd-github-actions/actions/workflows/ci.yml)

Trabajo **T11 — DevOps, CI/CD en GitHub Actions**  
Asignatura: Sistemas Distribuidos UM

---

## Descripción

Implementación de un pipeline CI/CD completo usando **GitHub Actions** sobre una aplicación Java 17.  
El proyecto demuestra cómo automatizar la compilación, testing y generación del artefacto ejecutable en cada push al repositorio.

---

## Aplicación: Gestor de Tareas (Todo App)

Aplicación de consola en Java 17 que permite gestionar tareas con tres niveles de prioridad.

**Funcionalidades:**
- Añadir tareas con prioridad (`HIGH`, `MEDIUM`, `LOW`)
- Marcar tareas como completadas
- Eliminar tareas
- Listar tareas ordenadas por prioridad
- Filtrar tareas pendientes

---

## Pipeline CI/CD

El workflow se dispara automáticamente en cada `push` o `pull request` a `main` y ejecuta dos jobs encadenados.
Si algún test falla, el job de Release **no se ejecuta**.

![Pipeline CI/CD](docs/pipeline.png)
```
    push a main
        │
        ▼
┌──────────────────────┐
│  Compilar y testear  │  ← ubuntu-latest, Java 17
│  - mvn compile       │
│  - mvn test (8 tests)│
│  - Subir informe XML │
└─────────┬────────────┘
          │
   Si TEST OK también 
          │
          ▼
┌─────────────────────┐
│   Generar Release   │
│  - mvn package      │
│  - Publicar JAR     │
└─────────────────────┘
```

---

## Estructura del proyecto
```
.
├── .github/
│   └── workflows/
│       └── ci.yml          # Workflow de GitHub Actions
├── docs/
│   └── pipeline.png
├── src/
│   ├── main/java/todo/
│   │   ├── Main.java        # Punto de entrada
│   │   ├── Task.java        # Modelo de tarea
│   │   ├── Priority.java    # Enum de prioridades
│   │   └── TaskManager.java # Lógica principal
│   └── test/java/todo/
│       └── TaskManagerTest.java  # 8 tests JUnit 5
└── pom.xml
```

---

## Ejecutar en local

**Requisitos:** Java 17 y Maven instalados.

```bash
# Clonar el repositorio
git clone https://github.com/mariagmgmgm/trabajo-ssdd-t11-cicd-github-actions
cd trabajo-ssdd-t11-cicd-github-actions

# Ejecutar tests
mvn test

# Compilar y ejecutar
mvn package -DskipTests
java -jar target/t11-todo-1.0.jar
```

---

## Tecnologías utilizadas

| Tecnología | Versión | Uso |
|---|---|---|
| Java | 17 | Lenguaje principal |
| Maven | 3.x | Gestión de dependencias y build |
| JUnit Jupiter | 5.10.0 | Testing |
| GitHub Actions | — | CI/CD |
| Ubuntu | latest | Runner de GitHub |



