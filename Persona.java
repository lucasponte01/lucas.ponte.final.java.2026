package finalprogra2;

import java.io.Serializable;

/**
* Código generado por la app UXFtoJava by Charly Cimino
* @see https://github.com/CharlyCimino/uxf-to-java
*/
public abstract class Persona implements Comparable<Persona>, Serializable {
    private static final long serialVersionUID = 1L;

    private int  id;
    private String nombre;
    private int  edad;
    private Genero genero;

    public Persona(int id, String nombre , int edad, Genero genero) {
        this.id = id;
        this.nombre = nombre;        
        this.edad = edad;
        this.genero = genero;
    }

    public Persona(String nombre, int  edad, Genero genero) {
        this.nombre = nombre;        
        this.edad = edad;
        this.genero = genero;
    }

    public Persona() {
        
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    

    public void mostrarDatos() {
    }

    public int comparar(Persona otra) {
        return this.getNombre().compareTo(otra.getNombre());
    }
    
}
