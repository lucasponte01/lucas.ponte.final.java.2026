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

<img width="895" height="574" alt="Captura de pantalla 2026-06-01 215241" src="https://github.com/user-attachments/assets/df9f6f04-3f38-4fe9-8878-564e7091f71c" />
2.Seleccionar un id y una fila de la tabla para editar.

3.Usar los botones de ordenar y filtrar.

<img width="896" height="577" alt="Captura de pantalla 2026-06-01 215558" src="https://github.com/user-attachments/assets/71978f1e-745a-484f-9b43-c1274f4f94f2" />
4.Guardar y cargar datos con los botones de persistencia.

<img width="892" height="577" alt="Captura de pantalla 2026-06-01 215752" src="https://github.com/user-attachments/assets/96402c39-64f8-4f70-acff-1cf967fe37bb" />


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
