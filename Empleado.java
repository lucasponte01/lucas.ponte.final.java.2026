package finalprogra2;

/**
* Código generado por la app UXFtoJava by Charly Cimino
* @see https://github.com/CharlyCimino/uxf-to-java
*/
public class Empleado extends Persona implements RecibirAumento {

    private int sueldo;
    private Puesto puesto;

    public Empleado(int id, String nombre, int edad, Genero genero, int sueldo, Enum puesto) {
        super(id, nombre, edad, genero);
        this.sueldo = sueldo;
        this.puesto = (Puesto) puesto;
    }
    

    public Empleado(String nombre, int edad, Genero genero, int sueldo, Enum puesto) {
        super(nombre, edad, genero);
        this.sueldo = sueldo;
        this.puesto = (Puesto) puesto;
    }

    public Empleado() {
        super();
    }

    @Override
    public void mostrarDatos() {
        System.out.println(" Empleado "
            + "\nNombre: " + getNombre()
            + "\nEdad: " + getEdad()
            + "\nSueldo: " + sueldo
            + "\nPuesto: " + puesto);
    }

    public int getSueldo() {
        return sueldo;
    }

    public Puesto getPuesto() {
        return puesto;
    }
    
    public String getTipo() {
    return "Empleado";
    }

    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }
    
    @Override
    public void recibirAumento(double porcentaje) {
        this.sueldo += this.sueldo * porcentaje / 100;
    }

    @Override
    public int compareTo(Persona otra) {
        return this.getNombre().compareTo(otra.getNombre());
    }

}