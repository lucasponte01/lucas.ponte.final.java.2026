package finalprogra2;

import java.util.Comparator;

public interface Comparar {
    
    public static Comparator<Persona> porNombre() {
        return (p1, p2) -> p1.getNombre().compareTo(p2.getNombre());
    }
    
    public static Comparator<Persona> porEdad() {
        return (p1, p2) -> Integer.compare(p1.getEdad(), p2.getEdad());
    }
}