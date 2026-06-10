# lucas.ponte.final.java.2026
*Titulo*:CRUD-Personas

*Presentacion*:hola soy lucas ponte , estudio programacion en la utn de avellaneda y este es mi proyecto para el final de programacion 2 .

*Resumen*:Sistema de gestión de personas con interfaz gráfica en JavaFX. que permite administrar empleados, estudiantes y clientes. Permite agregar, listar, actualizar y eliminar personas, ordenarlas por nombre o edad, filtrarlas  y aplicar modificaciones."

*Qué permite hacer*

Agregar, listar, actualizar y eliminar personas (Empleados, Estudiantes y Clientes)

Ordenar la lista por nombre o por edad

Filtrar personas mayores de 30 años

Aplicar aumentos de sueldo a todos los empleados

Guardar y cargar datos en formato .dat, .csv, .json y .txt

*Como se usa*:

1.Completar el formulario de la izquierda y apretar Agregar.
<img width="1359" height="767" alt="Captura de pantalla 2026-06-10 152918" src="https://github.com/user-attachments/assets/780fe2e4-2290-4745-97fb-f77bb32a261e" />

<img width="1359" height="767" alt="Captura de pantalla 2026-06-10 153007" src="https://github.com/user-attachments/assets/f2e7802e-08ff-4b3e-8638-a0432287e03e" />


2.Seleccionar un id y una fila de la tabla para editar.
<img width="1359" height="763" alt="Captura de pantalla 2026-06-10 153115" src="https://github.com/user-attachments/assets/1df4db69-435e-45ab-8bb9-5c5582176674" />

<img width="1359" height="767" alt="Captura de pantalla 2026-06-10 153206" src="https://github.com/user-attachments/assets/477b569e-ed7b-4b2c-96c8-a7417d97b9b9" />

3.Usar los botones de ordenar y filtrar.
<img width="1359" height="767" alt="Captura de pantalla 2026-06-10 153304" src="https://github.com/user-attachments/assets/1eab4c64-eaaa-42a1-a56b-23167c2d4a93" />

<img width="1359" height="767" alt="Captura de pantalla 2026-06-10 153319" src="https://github.com/user-attachments/assets/e06cce5a-0a81-4d41-be69-db9c8db51ccd" />

4.Guardar y cargar datos con los botones de persistencia.
<img width="1359" height="767" alt="Captura de pantalla 2026-06-10 153424" src="https://github.com/user-attachments/assets/030b4762-801e-4d3c-9d7f-d7b087a66565" />

<img width="1359" height="767" alt="Captura de pantalla 2026-06-10 153432" src="https://github.com/user-attachments/assets/9a19c5e6-78bc-490b-99a5-bde3605f8857" />

*Foto de el diagrama UML*
<img width="1560" height="1130" alt="progra2_casi final" src="https://github.com/user-attachments/assets/7258c4d7-50e8-49e2-b0bc-9bd9de611179" />

*archivos de ejemplo*

[personas.csv](https://github.com/user-attachments/files/28489633/personas.csv)

personas.json
json[
  {
    "id": 1,
    "nombre": "lucas",
    "edad": 21,
    "genero": "Masculino",
    "tipo": "Empleado"
  },
  {
    "id": 2,
    "nombre": "mariel",
    "edad": 40,
    "genero": "Femenino",
    "tipo": "Estudiante"
  },
  {
    "id": 3,
    "nombre": "lopez",
    "edad": 50,
    "genero": "Masculino",
    "tipo": "Cliente"
  }
]

personas.txt
==============================
  LISTADO DE PERSONAS
  Fecha: Tue Jun 02 00:52:56 ART 2026
==============================

Nombre : lucas
Edad   : 21
Género : Masculino
Tipo   : Empleado
------------------------------
Nombre : mariel
Edad   : 40
Género : Femenino
Tipo   : Estudiante
------------------------------
Nombre : lopez
Edad   : 50
Género : Masculino
Tipo   : Cliente
------------------------------

Total registros: 3

personas.dat

El archivo personas.dat contiene la lista de personas serializada en formato binario.
Se genera automáticamente al presionar el botón Guardar .dat en la aplicación y se recupera con Cargar .dat.
