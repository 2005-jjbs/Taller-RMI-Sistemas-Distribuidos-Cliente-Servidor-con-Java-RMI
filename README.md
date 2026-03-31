# 📡 Taller RMI — Sistema Distribuido Cliente-Servidor con Java RMI

**Autor:** Juan José Ballesteros Suarez  
**Universidad:** Pontificia Universidad Javeriana  
**Fecha:** 03/2026

**Grupo:** Juan Diego Rojas - Danna Rojas

Taller académico de la asignatura **Introducción a los Sistemas Distribuidos**, en el que se implementa un sistema cliente-servidor distribuido utilizando **Java RMI (Remote Method Invocation)**. El objetivo principal es demostrar la comunicación entre dos máquinas virtuales a través de la invocación de métodos remotos.

---

## 🎯 Objetivo

Construir un servidor remoto que gestione información de estudiantes de un curso y exponga tres servicios consultables desde un cliente ubicado en una máquina diferente:

- **Notas**: dado un nombre o apellido, retorna el promedio entre Quiz 1 y Taller 1.
- **Grupo**: dado un apellido, retorna el grupo de trabajo del estudiante.
- **Evaluaciones**: retorna la lista de evaluaciones registradas hasta el momento.

---

## 🛠️ Tecnología utilizada

**Java RMI (Remote Method Invocation)** es el mecanismo de Java para ejecutar métodos de objetos que residen en una JVM remota, como si fueran llamadas locales. Los componentes clave son:

- **Interfaz remota** (`Remote`): define los métodos disponibles para el cliente.
- **Implementación** (`UnicastRemoteObject`): contiene la lógica de negocio en el servidor.
- **RMI Registry**: servicio de nombres donde el servidor publica el objeto y el cliente lo localiza.
- **Serialización**: los objetos que viajan por la red (como `Estudiante`) implementan `Serializable`.

La comunicación se establece así:

```
[Cliente - Máquina B]  →  RMI Registry (puerto 1099)  →  [Servidor - Máquina A]
```

---

## 🗂️ Estructura del proyecto

```
TallerRMI/
└── src/
    ├── client/
    │   └── ClienteRMI.java          # Interfaz de consola para el usuario
    ├── model/
    │   └── Estudiante.java          # Modelo de datos serializable
    ├── remote/
    │   └── ServicioEstudiantes.java # Interfaz RMI (contrato del servicio)
    └── server/
        ├── BaseDatosEstudiantes.java    # Base de datos en memoria
        ├── ServicioEstudiantesImpl.java # Implementación de los métodos remotos
        └── ServidorRMI.java             # Punto de entrada del servidor
```

---

## ▶️ Cómo ejecutar

### Requisitos
- Java JDK 8 o superior
- Dos máquinas (o VMs) en la misma red ( En este caso se usaron dos maquinas virtuales UBUNTU)
- Puerto **1099** y **5000** abiertos en el servidor

### 1. Compilar el proyecto

Desde la raíz del proyecto, compilar todas las clases:

```bash
cd TallerRMI/src
javac model/*.java remote/*.java server/*.java client/*.java
```

### 2. Levantar el servidor (Máquina A)

Antes de ejecutar, editar `ServidorRMI.java` y `ClienteRMI.java` para reemplazar la IP `10.43.100.115` con la IP real de la máquina servidor. Ádemas de abrir los puertos de comunicación entre el cliente y el servidor.

```bash
sudo ufw allow 1099
sudo ufw allow 5000
java server.ServidorRMI
```

Salida esperada:
```
Servidor RMI listo...
```

### 3. Ejecutar el cliente (Máquina B)

```bash
java client.ClienteRMI
```

El cliente mostrará un menú interactivo:

```
Bienvenido al cliente RMI de estudiantes
1. Consultar promedio
2. Consultar grupo
3. Ver evaluaciones
0. Salir
```

---

## 📋 Datos de prueba

El servidor carga en memoria 19 estudiantes distribuidos en 9 grupos de trabajo (GRT1–GRT9), con notas de Quiz 1 y Taller 1. El promedio se calcula como:

```
Promedio = (Quiz1 + Taller1) / 2
```

---

## 📁 Archivos entregables

| Archivo | Descripción |
|---|---|
| `src/` | Código fuente completo documentado |
| `README.md` | Este documento |

---

## 📚 Referencias

- [Documentación oficial Java RMI](https://docs.oracle.com/javase/8/docs/technotes/guides/rmi/)
- [gRPC para otros lenguajes](https://grpc.io/docs/languages/)
