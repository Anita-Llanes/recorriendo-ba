# 🏙️ Recorriendo BA - Travel Management System
### Proyecto Integrador: Programación Orientada a Objetos (Java Core)

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Level](https://img.shields.io/badge/Nivel-T%C3%A9cnico_Secundario-blue?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Versi%C3%B3n_1.0_Estable-green?style=for-the-badge)

**Recorriendo BA** es un ecosistema de gestión de turismo desarrollado en Java, diseñado para cubrir el ciclo completo de una agencia de viajes en CABA: desde la administración avanzada del catálogo hasta la experiencia de reserva del usuario final. El sistema resuelve la problemática de la superposición de horarios y la gestión dinámica de catálogos mediante un enfoque orientado a objetos.

---

## 🧭 Visión del Proyecto

Este proyecto nació como pre-entrega para el curso de **Back-End Java de Talento Tech**, considerando la necesidad de digitalizar la experiencia de reserva de una agencia de viajes. Se puso especial énfasis en la **validación de datos** y la **integridad de las reglas de negocio**, asegurando que tanto el cliente como el administrador tengan una experiencia sin errores lógicos.

### 🧠 El Proceso de Diseño
1. **User-First Approach:** Definición de *user stories* para entender las necesidades del turista y del administrador.
2. **Modelado Lógico:** Diseño de un **Diagrama de Flujo** para mapear puntos críticos (validación de fechas y solapamiento).
3. **Implementación Técnica:** Uso de colecciones dinámicas y la API moderna `java.time`.

---

## 📖 Informe de Proceso (Presentación Académica)

### 1. Diseño Centrado en Roles (UX/DX)
El sistema diferencia claramente dos flujos de trabajo:
* **Módulo Cliente:** Optimizado para la usabilidad, permitiendo navegar tours y gestionar el carrito con validaciones en tiempo real.
* **Módulo Administrador:** Diseñado para la gestión del inventario (**ABM**), permitiendo el control de cupos, precios y actualización del catálogo.

### 2. Evolución de la Arquitectura (Data-Driven)
* **De Herencia a Composición:** Migración de clases rígidas a una estructura dinámica de `Tour` y `CategoriaTour`.
* **Escalabilidad:** Sistema **Data-Driven** que permite agregar productos sin modificar el núcleo del código (Responsabilidad Única).

### 3. Desafíos Técnicos y Resolución de Bugs
* **Gestión de Buffer (Clase `Validator`):** Capa de validación personalizada para solucionar el problema del buffer del `Scanner`.
* **Algoritmo de Conflicto Horario:** Restricción logística de **3 horas** entre actividades para garantizar traslados realistas en la ciudad.

---

## 🛠️ Características Técnicas

### 1. Motor de Validaciones (Clase `Validator`)
* **Buffer Cleaning:** Manejo avanzado para evitar saltos de línea indeseados.
* **Parsing Seguro:** Conversión de datos con manejo de `NumberFormatException`.

### 2. Lógica de Negocio: Algoritmo de Conflicto Horario
* **Regla de las 3 horas:** Mediante la API `Duration`, el sistema rechaza automáticamente reservas que se solapen por un margen menor a 180 minutos.

---

## 🏗️ Diagrama de Clases (Simplificado)

* `Tour`: Entidad base (ID, nombre, descripción, precio, stock).
* `CategoriaTour`: Define el tipo de experiencia (Gastronomía, Historia, etc.).
* `ItemReserva`: Vincula un `Tour` con una `FechaHora` específica.
* `CarritoService`: Gestiona los ítems y aplica las validaciones de negocio.

---

## 📂 Estructura del Proyecto (Paquetes y Módulos)

El código se organiza en paquetes lógicos para separar responsabilidades y facilitar la escalabilidad:

```text
recorriendo-ba/
├── src/
│   ├── com/recorriendoba/
│   │   ├── model/                 # Capa de Entidades (POO)
│   │   │   ├── Tour.java
│   │   │   ├── CategoriaTour.java
│   │   │   ├── ItemReserva.java
│   │   │   └── Pedido.java
│   │   ├── service/               # Lógica de Negocio
│   │   │   ├── CarritoService.java
│   │   │   └── AdminService.java
│   │   ├── utils/                 # Herramientas Transversales
│   │   │   ├── Validator.java
│   │   │   └── DateHelper.java
│   │   ├── excepciones/           # Manejo de Errores Personalizados
│   │   │   └── StockInsuficienteException.java
│   │   └── Main.java              # Orquestador del Menú
└── docs/                          # Documentación complementaria
    └── diagrama_flujo.png         # Lógica visual del sistema
```
---

## 🔬 Descripción de Componentes Críticos
**Encapsulamiento:** Atributos privados con Getters/Setters en la capa model.

**Colaboración de Clases:** Estructura de agregación técnica entre Pedido, ItemReserva y Tour.

**Gestión de Excepciones:** Paquete centralizado para atrapar fallos específicos (como falta de cupos) sin que la aplicación se interrumpa.

---

## 🛠️ Cumplimiento de Requerimientos Técnicos
**POO:** Encapsulamiento y colaboración de clases.

**Colecciones:** ArrayList para manejo dinámico de productos y pedidos.

**Manejo de Errores:** Uso intensivo de try-catch para robustez del sistema.

**Lógica de Negocio:** Validación de stock y cálculo automático de costos totales.

---

## 🚀 Instalación y Ejecución
**Clonar el repositorio:**
Bash
git clone [https://github.com/Anita-Llanes/recorriendo-ba.git](https://github.com/Anita-Llanes/recorriendo-ba.git)
**Compilar el proyecto:**
Bash
javac -d bin src/com/recorriendoba/**/*.java
**Ejecutar:**
Bash
java -cp bin com.recorriendoba.Main

---

## 🗺️ Roadmap de Desarrollo (Próximas Etapas)

### Fase 2: Migración a Ecosistema Web 🌐
- [ ] **Backend con Spring Boot:** Refactorizar hacia un framework empresarial.
- [ ] **API REST:** Transformar el sistema para responder a peticiones HTTP.
- [ ] **Persistencia:** Integrar **Spring Data JPA** con bases de datos (PostgreSQL/MySQL).

### Fase 3: Interfaz de Usuario Moderna (Front-end) 🎨
- [ ] **Desarrollo Web:** Interfaz en **React** o HTML/CSS/JS.
- [ ] **Seguridad:** Implementación de roles con **Spring Security**.

---

## 👤 Autora
**Anita Llanes**  
Estudiante de Back-End Java - **Talento Tech**.  
*Proyecto integrador desarrollado para la pre-entrega del proyecto final del curso.*
