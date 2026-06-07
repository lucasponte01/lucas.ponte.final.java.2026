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

<img width="1365" height="766" alt="Captura de pantalla 2026-06-07 191140" src="https://github.com/user-attachments/assets/301349b9-8e4d-4c79-854c-ffd059338836" />

<img width="1365" height="767" alt="Captura de pantalla 2026-06-07 192259" src="https://github.com/user-attachments/assets/6b68420f-2c4f-476a-860a-86040eaedad4" />

2.Seleccionar un id y una fila de la tabla para editar.
<img width="1365" height="766" alt="Captura de pantalla 2026-06-07 191429" src="https://github.com/user-attachments/assets/19295401-62e1-48e5-8f38-08c85f28500e" />

<img width="1365" height="767" alt="Captura de pantalla 2026-06-07 191452" src="https://github.com/user-attachments/assets/0be0e17b-b5fe-44af-a9c0-ddcb10515659" />

3.Usar los botones de ordenar y filtrar.
<img width="1365" height="767" alt="Captura de pantalla 2026-06-07 191511" src="https://github.com/user-attachments/assets/0fe65acc-1f7c-49cb-b22d-d8fd255fa02d" />

<img width="1365" height="766" alt="Captura de pantalla 2026-06-07 191519" src="https://github.com/user-attachments/assets/dce4b879-5511-4ffb-8c0a-0f14d5b03792" />

4.Guardar y cargar datos con los botones de persistencia.
<img width="1365" height="767" alt="Captura de pantalla 2026-06-07 191615" src="https://github.com/user-attachments/assets/fb960054-bfc5-4443-9751-728b3696697c" />
<img width="1365" height="767" alt="Captura de pantalla 2026-06-07 191623" src="https://github.com/user-attachments/assets/f9e8728a-f896-43dc-ae24-3b135ec07fb6" />

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
