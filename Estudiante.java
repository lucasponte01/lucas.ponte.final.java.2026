package finalprogra2;

/**
* Código generado por la app UXFtoJava by Charly Cimino
* @see https://github.com/CharlyCimino/uxf-to-java
*/
public class Estudiante extends Persona implements CambiarCarrera {

    private String carrera;
    private int promedio;

    public Estudiante(int id, String nombre, int edad, Genero genero, String carrera, int promedio) {
        super(id, nombre, edad, genero);
        this.carrera = carrera;
        this.promedio = promedio;
                
    }

    public Estudiante(String nombre, int edad, Genero genero, String carrera, int promedio) {
        super(nombre,edad,genero);
        this.carrera = carrera;
        this.promedio = promedio;
    }

    public Estudiante() {
        super();
    }

    public String getCarrera() {
        return carrera;
    }

    public int getPromedio() {
        return promedio;
    }
    
    public String getTipo() {
    return "Estudiante";
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public void setPromedio(int promedio) {
        this.promedio = promedio;
    }
    

    @Override
    public void mostrarDatos() {
        System.out.println(" Estudiante "
            + "\nNombre: " + getNombre()
            + "\nEdad: " + getEdad()
            + "\nCarrera: " + carrera
            + "\npromedio: " + promedio);
    }

    @Override
    public void cambiarCarrera(String nuevacarrera) {
       this.carrera = nuevacarrera;
    }
    
    @Override
    public int compareTo(Persona otra) {
        return this.getNombre().compareTo(otra.getNombre());
    }
    

}