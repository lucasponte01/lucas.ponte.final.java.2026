package finalprogra2;

 

/**
* Código generado por la app UXFtoJava by Charly Cimino
* @see https://github.com/CharlyCimino/uxf-to-java
*/
public class Cliente extends Persona {

    private String email;
    private TipoCliente tipoCliente;

    public Cliente(int id, String nombre, int edad, Genero genero, String email, TipoCliente tipoCliente) {
        super(id, nombre, edad, genero);
        this.email = email;
        this.tipoCliente = tipoCliente;
    }

    public Cliente(String nombre, int edad, Genero genero, String email, TipoCliente tipoCliente) {
        super(nombre, edad, genero);
        this.email = email;
        this.tipoCliente = tipoCliente;
    }

    public Cliente() {
        super();
    }

    @Override
    public void mostrarDatos() {
        System.out.println(" Cliente "
            + "\nNombre: " + getNombre()
            + "\nEdad: " + getEdad()
            + "\nEmail: " + email
            + "\nTipo: " + tipoCliente);
    }

        public String getEmail() {
            return email; 
        }
        
        public String getTipo() {
        return "Cliente";
        }
        
        public void setEmail(String email) { 
            this.email = email; 
        }
        public TipoCliente getTipoCliente() { 
            return tipoCliente; 
        }
        public void setTipoCliente(TipoCliente tipoCliente) { 
            this.tipoCliente = tipoCliente; 
        }
    @Override
    public int compareTo(Persona otra) {
        return this.getNombre().compareTo(otra.getNombre());
    }
    
    
}

