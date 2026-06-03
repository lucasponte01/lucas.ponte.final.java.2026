package finalprogra2;

import java.util.Iterator;
import java.util.List;
/**
* Código generado por la app UXFtoJava by Charly Cimino
* @see https://github.com/CharlyCimino/uxf-to-java
*/
public class IteradorPersona<P extends Persona> implements Iterator<P> {

    private List<P> lista;
    private int index;
    
    public IteradorPersona(List<P> lista){
        this.lista = lista;
        this.index = 0;
    }
    
    @Override
    public boolean hasNext() {
        return index < lista.size();
    }
    
    @Override
    public P next() {
        return lista.get(index++);
    }

    

}