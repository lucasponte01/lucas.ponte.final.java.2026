package finalprogra2;

import java.util.List;

/**
* Código generado por la app UXFtoJava by Charly Cimino
* @see https://github.com/CharlyCimino/uxf-to-java
*/
public interface Administrar<P> {
    void crear(P p) throws Datoinvalido;
    List<P> leer();
    void actualizar(P p) throws Personanoencontrada;
    void eliminar(int id) throws Personanoencontrada;
}