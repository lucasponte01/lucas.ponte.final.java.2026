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
   
        // Orden por nombre
        public void ordenar() {
            Collections.sort(administrar);
        }

        // Orden por edad
        public void ordenar(Comparator<P> comparador) {
            administrar.sort(comparador);
        }
    
    //persistencia    
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
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            
            bw.write("id,nombre,edad,genero,tipo,datoExtra1,datoExtra2");
            bw.newLine();

            for (P p : administrar) {
                StringBuilder linea = new StringBuilder();
                linea.append(p.getId()).append(",")
                     .append(p.getNombre()).append(",")
                     .append(p.getEdad()).append(",")
                     .append(p.getGenero()).append(",")
                     .append(p.getClass().getSimpleName());

               
                if (p instanceof Empleado emp) {
                    linea.append(",").append(emp.getSueldo())
                         .append(",").append(emp.getPuesto()); // Aquí se guarda el puesto real
                } else if (p instanceof Estudiante est) {
                    linea.append(",").append(est.getCarrera())
                         .append(",").append(est.getPromedio());
                } else if (p instanceof Cliente cli) {
                    linea.append(",").append(cli.getEmail())
                         .append(",").append(cli.getTipoCliente()); // Aquí se guarda el tipo real
                }

                bw.write(linea.toString());
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
                int id = Integer.parseInt(datos[0]);
                String nombre = datos[1];
                int edad = Integer.parseInt(datos[2]);
        
                Genero genero = Genero.valueOf(datos[3].toUpperCase());
                String tipo = datos[4];

                Persona p = null;
                switch (tipo) {
                    case "Empleado" -> {
                        int sueldo = Integer.parseInt(datos[5].trim());
                        Puesto puesto = Puesto.valueOf(datos[6].toUpperCase().trim());
                        p = new Empleado(id, nombre, edad, genero, sueldo, puesto);
                    }
                    case "Estudiante" -> {
                        String carrera = datos[5].trim();
                        int promedio = Integer.parseInt(datos[6].trim());
                        p = new Estudiante(id, nombre, edad, genero, carrera, promedio);
                    }
                    case "Cliente" -> {
                        String email = datos[5].trim();
                        TipoCliente tipoCliente = TipoCliente.valueOf(datos[6].toUpperCase().trim());
                        p = new Cliente(id, nombre, edad, genero, email, tipoCliente);
                    }
                }
                if (p != null) {
                    administrar.add((P) p);
                }
            }
            System.out.println("CSV cargado correctamente.");
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Error al cargar CSV (posible dato inválido): " + e.getMessage());
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

                
                 if (p instanceof Empleado emp) {
                     bw.write(","); bw.newLine();
                     bw.write("    \"sueldo\": " + emp.getSueldo() + ","); bw.newLine();
                     bw.write("    \"puesto\": \"" + emp.getPuesto() + "\"");
                 } else if (p instanceof Estudiante est) {
                     bw.write(","); bw.newLine();
                     bw.write("    \"carrera\": \"" + est.getCarrera() + "\","); bw.newLine();
                     bw.write("    \"promedio\": " + est.getPromedio());
                 } else if (p instanceof Cliente cli) {
                     bw.write(","); bw.newLine();
                     bw.write("    \"email\": \"" + cli.getEmail() + "\","); bw.newLine();
                     bw.write("    \"tipoCliente\": \"" + cli.getTipoCliente() + "\"");
                 }
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
    public void cargarJSON(String archivo) {
        try (
            BufferedReader br = new BufferedReader(new FileReader(archivo))
        ) {
            String linea;
            
            
            String nombre = ""; 
            int id = 0, edad = 0;
            Genero genero = Genero.MASCULINO; 
            String tipo = "";
            
            
            int sueldo = 0;
            Puesto puesto = Puesto.ADMINISTRATIVO;
            String carrera = "";
            int promedio = 0;
            String email = "";
            TipoCliente tipoCliente = TipoCliente.REGULAR;

            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                
                if (linea.contains("\"id\""))
                    id = Integer.parseInt(linea.replaceAll("[^0-9]", ""));
                else if (linea.contains("\"nombre\""))
                    nombre = linea.split(":")[1].replaceAll("[\"\\s,]", "");
                else if (linea.contains("\"edad\""))
                    edad = Integer.parseInt(linea.replaceAll("[^0-9]", ""));
                else if (linea.contains("\"genero\"")) {
                    String valorGenero = linea.split(":")[1].replaceAll("[\"\\s,]", "").toUpperCase();
                    genero = Genero.valueOf(valorGenero);
                }
                else if (linea.contains("\"tipoCliente\""))
                    tipoCliente = TipoCliente.valueOf(linea.split(":")[1].replaceAll("[\"\\s,]", "").toUpperCase());
                else if (linea.contains("\"tipo\""))
                    tipo = linea.split(":")[1].replaceAll("[\"\\s,]", "");
                
                // --- CAPTURA DE ATRIBUTOS ESPECÍFICOS ---
                else if (linea.contains("\"sueldo\""))
                    sueldo = Integer.parseInt(linea.replaceAll("[^0-9]", ""));
                else if (linea.contains("\"puesto\""))
                    puesto = Puesto.valueOf(linea.split(":")[1].replaceAll("[\"\\s,]", "").toUpperCase());
                else if (linea.contains("\"carrera\""))
                    carrera = linea.split(":")[1].replaceAll("[\"\\s,]", "");
                else if (linea.contains("\"promedio\""))
                    promedio = Integer.parseInt(linea.replaceAll("[^0-9]", ""));
                else if (linea.contains("\"email\""))
                    email = linea.split(":")[1].replaceAll("[\"\\s,]", "");
                else if (linea.contains("\"tipoCliente\""))
                    tipoCliente = TipoCliente.valueOf(linea.split(":")[1].replaceAll("[\"\\s,]", "").toUpperCase());
                
             
                else if (linea.equals("}") || linea.equals("},")) {
                    if (!tipo.isEmpty()) {
                        Persona p = null;
                        switch (tipo) {
                            case "Empleado" -> p = new Empleado(id, nombre, edad, genero, sueldo, puesto);
                            case "Estudiante" -> p = new Estudiante(id, nombre, edad, genero, carrera, promedio);
                            case "Cliente" -> p = new Cliente(id, nombre, edad, genero, email, tipoCliente);
                        }
                        if (p != null) administrar.add((P) p);
                        
                     
                        tipo = ""; nombre = ""; id = 0; edad = 0;
                        genero = Genero.MASCULINO;
                        sueldo = 0; puesto = Puesto.ADMINISTRATIVO;
                        carrera = ""; promedio = 0;
                        email = ""; tipoCliente = TipoCliente.REGULAR;
                    }
                }
            }
            System.out.println("JSON cargado correctamente con todos sus atributos específicos.");
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Error al cargar JSON: " + e.getMessage());
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