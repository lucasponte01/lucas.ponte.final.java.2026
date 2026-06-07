package finalprogra2;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.Collections;
import java.util.Comparator;
/**
* Código generado por la app UXFtoJava by Charly Cimino
* @see https://github.com/CharlyCimino/uxf-to-java
*/
public class Gestion<P extends Persona> implements Administrar<P> {

    private  ArrayList<P> administrar = new ArrayList<>();

    @Override
    public void crear(P p) throws Datoinvalido {
        if(p == null){
            throw new Datoinvalido("no se puede agregar un objeto nulo");
        }
        administrar.add(p); 
    }
    
    @Override
    public List<P> leer() {
        return administrar;
    }

    @Override
    public void actualizar(P p) throws Personanoencontrada { 
        boolean persona_encontrada = false;
        for(int i = 0; i < administrar.size(); i++){
            if(administrar.get(i).getId() == p.getId()){
                administrar.set(i, p);
                persona_encontrada = true;
                break;
            }
        }
        if(!persona_encontrada){
                throw new Personanoencontrada("No se encontró persona con id:" + p.getId());
            }
    }

    @Override
    public void eliminar(int id) throws Personanoencontrada {
        boolean persona_encontrada = false;
        for(int i = 0; i<administrar.size(); i++){
            if(administrar.get(i).getId() == id){
                administrar.remove(i);
                persona_encontrada = true;
                break;
            }
        }
        if(!persona_encontrada){
            throw new Personanoencontrada("No se encontró una persona con el id:" + id);
            }
    }

    public IteradorPersona<P> iterator() {
        return new IteradorPersona<>(administrar);
    }
    
    public void mostrarLista(List<? extends Persona> lista) {
        for (Persona p : lista) {
            p.mostrarDatos();
        }
    }

    public void copiarEn(List<? super Persona> destino) {
        for (P p : administrar) {
            destino.add(p);
        }

    }
    
    public void modificar(Consumer<P> accion) {
        for (P p : administrar) {
        accion.accept(p);
        }
    }
    
    public <R> List<R> transformar(Function<P, R> transformacion) {
        List<R> resultado = new ArrayList<>();
        for (P p : administrar) {
        resultado.add(transformacion.apply(p));
        }
        return resultado;
    }
    
    public List<P> filtrar(Predicate<P> criterio) {
        List<P> resultado = new ArrayList<>();
        for (P p : administrar) {
            if (criterio.test(p)) {
            resultado.add(p);
            }
        }
        return resultado;
    }   
    
    

        
        public void ordenar() {
            Collections.sort(administrar);
        }

        
        public void ordenar(Comparator<P> comparador) {
            administrar.sort(comparador);
        }
    
    public void guardarDat(String archivo) {
    try (
        FileOutputStream fos = new FileOutputStream(archivo);
        ObjectOutputStream oos = new ObjectOutputStream(fos)
    ) {
        oos.writeObject(administrar);
        System.out.println("Lista guardada en: " + archivo);
    } catch (IOException e) {
        System.out.println("Error al guardar .dat: " + e.getMessage());
        }
    }
 
    @SuppressWarnings("unchecked")
    public void cargarDat(String archivo) {
        try (
            FileInputStream fis = new FileInputStream(archivo);
            ObjectInputStream ois = new ObjectInputStream(fis)
        ) {
            administrar = (ArrayList<P>) ois.readObject();
            System.out.println("Lista cargada desde: " + archivo);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar .dat: " + e.getMessage());
        }
    }
 
 

    public void guardarCSV(String archivo) {
        try (
            FileWriter fw = new FileWriter(archivo);
            BufferedWriter bw = new BufferedWriter(fw)
        ) {
           
            bw.write("id,nombre,edad,genero,tipo");
            bw.newLine();

            for (P p : administrar) {
                bw.write(
                    p.getId() + "," +
                    p.getNombre() + "," +
                    p.getEdad() + "," +
                    p.getGenero() + "," +
                    p.getClass().getSimpleName()
                );
                bw.newLine();
            }
            System.out.println("Lista guardada en CSV: " + archivo);
        } catch (IOException e) {
            System.out.println("Error al guardar CSV: " + e.getMessage());
        }
    }
 
    public void cargarCSV(String archivo) {
        try (
            BufferedReader br = new BufferedReader(new FileReader(archivo))
        ) {
            br.readLine(); 
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                
                System.out.println("Leído: " + datos[1] + " (" + datos[4] + ")");
            }
        } catch (IOException e) {
            System.out.println("Error al cargar CSV: " + e.getMessage());
        }
    }
 
 

    public void guardarJSON(String archivo) {
        try (
            FileWriter fw = new FileWriter(archivo);
            BufferedWriter bw = new BufferedWriter(fw)
        ) {
            bw.write("[");
            bw.newLine();

            for (int i = 0; i < administrar.size(); i++) {
                P p = administrar.get(i);
                bw.write("  {");
                bw.newLine();
                bw.write("    \"id\": " + p.getId() + ",");
                bw.newLine();
                bw.write("    \"nombre\": \"" + p.getNombre() + "\",");
                bw.newLine();
                bw.write("    \"edad\": " + p.getEdad() + ",");
                bw.newLine();
                bw.write("    \"genero\": \"" + p.getGenero() + "\",");
                bw.newLine();
                bw.write("    \"tipo\": \"" + p.getClass().getSimpleName() + "\"");
                bw.newLine();
                
                bw.write(i < administrar.size() - 1 ? "  }," : "  }");
                bw.newLine();
            }

            bw.write("]");
            System.out.println("Lista guardada en JSON: " + archivo);
        } catch (IOException e) {
            System.out.println("Error al guardar JSON: " + e.getMessage());
        }
    }
 
 

    public void exportarTXT(String archivo, Predicate<P> criterio) {
        try (
            FileWriter fw = new FileWriter(archivo);
            BufferedWriter bw = new BufferedWriter(fw)
        ) {
            
            bw.write("==============================");
            bw.newLine();
            bw.write("  LISTADO DE PERSONAS");
            bw.newLine();
            bw.write("  Fecha: " + new java.util.Date());
            bw.newLine();
            bw.write("==============================");
            bw.newLine();
            bw.newLine();

            int contador = 0;
            for (P p : administrar) {
                if (criterio.test(p)) {
                    bw.write("Nombre : " + p.getNombre());
                    bw.newLine();
                    bw.write("Edad   : " + p.getEdad());
                    bw.newLine();
                    bw.write("Género : " + p.getGenero());
                    bw.newLine();
                    bw.write("Tipo   : " + p.getClass().getSimpleName());
                    bw.newLine();
                    bw.write("------------------------------");
                    bw.newLine();
                    contador++;
                }
            }

            bw.newLine();
            bw.write("Total registros: " + contador);
            bw.newLine();
            System.out.println("Exportado a TXT: " + archivo);
        } catch (IOException e) {
            System.out.println("Error al exportar TXT: " + e.getMessage());
        }
    }
    
}
