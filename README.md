# 🏦 Proyecto Devenco - Sistema de Gestión Presupuestaria
![image](https://github.com/user-attachments/assets/c83d696d-176f-44a9-a932-11d1805e53ff)
![image](https://github.com/user-attachments/assets/827caeab-91e3-488b-b49f-d140aa52e694)

## 📌 Descripción del Proyecto
El proyecto Devenco es una solución integral desarrollada para la gestión de presupuestos externos, implementando un sistema de base de datos robusto en Oracle, interfaces de usuario en Java bajo el patrón MVC (Modelo-Vista-Controlador), y un dashboard analítico creado en Power BI.)

## 📑 Tabla de Contenidos
1. [Introducción](#-introducción)
2. [Tecnologías Clave](#-tecnologías-clave)
3. [Estructura del Proyecto](#-estructura-del-proyecto)


## 🔍 Introducción
Sistema diseñado para instituciones que requieren:  
✔️ Control detallado de partidas presupuestarias  
✔️ Integración con sistemas legacy Oracle  
✔️ Generación de reportes financieros clave  
✔️ Auditoría completa de movimientos  

## 🛠 Tecnologías Clave

| Capa            | Componentes                                                                 |
|-----------------|------------------------------------------------------------------------------|
| **Persistencia**| Oracle 19c (Tablas relacionales, PL/SQL, Secuencias)                        |
| **Backend**     | Java 8+ (JDBC, Patrón DAO)                                                   |
| **Frontend**    | Java Swing (MVC Clean Architecture)                                          |
| **Reporting**   | Power BI (Visualizaciones)                                          |
## Estructura del proyecto
```bash

ProyectoDevenco_Java_Oracle_PowerBI/
├── docs/                          # Documentación del proyecto
│   ├── proyecto de base de datos.pdf  # Esquema y detalles de la BD
├── src/                           # Código fuente Java (MVC)
│   ├── modelo/                    # Lógica de negocio y conexión a Oracle
│   ├── vista/                     # Interfaces de usuario
│   ├── controlador/               # Manejo de eventos y lógica de aplicación
├── sql/                           # Scripts SQL para Oracle
│   ├── tablas/                    # Creación de tablas
│   ├── secuencias/                # Secuencias de la base de datos
│   ├── procedimientos/            # Procedimientos almacenados
├── powerbi/                       # Archivos de Power BI
│   ├── dashboard/                 # Reportes y visualizaciones
├── README.md                      # Este archivo
```
## 📍 Conclusión

El proyecto Devenco ofrece una solución completa para la gestión presupuestaria de instituciones, combinando tecnologías probadas y escalables. Al integrar Oracle para la persistencia de datos, Java para el desarrollo backend y frontend con una arquitectura MVC, y Power BI para análisis y reportes, Devenco proporciona una plataforma robusta y flexible que permite:

- Un control detallado y eficiente de las partidas presupuestarias.

- Una integración fluida con sistemas legacy.

- Generación y visualización de reportes financieros clave para facilitar la toma de decisiones.

- Una auditoría completa y trazabilidad de los movimientos presupuestarios.

Esta arquitectura modular permite la fácil expansión y mantenimiento del sistema, lo que asegura que Devenco pueda adaptarse a las necesidades cambiantes de las instituciones a medida que evolucionan.
