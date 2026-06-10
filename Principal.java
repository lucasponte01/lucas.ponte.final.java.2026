package finalprogra2;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Principal extends Application {

    // Gestión y lista observable para la tabla
    private Gestion<Persona> gestion = new Gestion<>();
    private ObservableList<Persona> listaObservable = FXCollections.observableArrayList();
    private TableView<Persona> tabla = new TableView<>();

    @Override
    public void start(Stage stage) {

        // ── TABLA ──────────────────────────────────────────────
        TableColumn<Persona, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Persona, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<Persona, Integer> colEdad = new TableColumn<>("Edad");
        colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));

        TableColumn<Persona, String> colGenero = new TableColumn<>("Género");
        colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));

        TableColumn<Persona, String> colTipo = new TableColumn<>("Tipo");
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        TableColumn<Persona, String> colExtra = new TableColumn<>("Extra");
        colExtra.setCellValueFactory(c -> {
            Persona p = c.getValue();
            if (p instanceof Empleado e)
                return new javafx.beans.property.SimpleStringProperty("Sueldo: $" + e.getSueldo() + " | Puesto: " + e.getPuesto());
            if (p instanceof Estudiante est)
                return new javafx.beans.property.SimpleStringProperty("Carrera: " + est.getCarrera() + " | Promedio: " + est.getPromedio());
            if (p instanceof Cliente cli)
                return new javafx.beans.property.SimpleStringProperty("Email: " + cli.getEmail() + " | Tipo: " + cli.getTipoCliente());
            return new javafx.beans.property.SimpleStringProperty("");
        });
        
        tabla.getColumns().addAll(colId, colNombre, colEdad, colGenero, colTipo, colExtra);
        tabla.setItems(listaObservable);
        tabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // ── FORMULARIO ─────────────────────────────────────────
        TextField txtId     = new TextField(); txtId.setPromptText("ID");
        TextField txtNombre = new TextField(); txtNombre.setPromptText("Nombre");
        TextField txtEdad   = new TextField(); txtEdad.setPromptText("Edad");

        ComboBox<Genero> cmbGenero = new ComboBox<>();
        cmbGenero.getItems().addAll(Genero.values());
        cmbGenero.setPromptText("Género");

        ComboBox<String> cmbTipo = new ComboBox<>();
        cmbTipo.getItems().addAll("Empleado", "Estudiante", "Cliente");
        cmbTipo.setPromptText("Tipo");

        // Campos específicos por tipo
        TextField txtExtra1 = new TextField(); txtExtra1.setPromptText("Sueldo / Carrera / Email");
        TextField txtExtra2 = new TextField(); txtExtra2.setPromptText("Puesto / Promedio / TipoCliente");

        GridPane form = new GridPane();
        form.setHgap(8); form.setVgap(8); form.setPadding(new Insets(8));
        form.add(new Label("ID:"),      0, 0); form.add(txtId,      1, 0);
        form.add(new Label("Nombre:"),  0, 1); form.add(txtNombre,  1, 1);
        form.add(new Label("Edad:"),    0, 2); form.add(txtEdad,    1, 2);
        form.add(new Label("Género:"),  0, 3); form.add(cmbGenero,  1, 3);
        form.add(new Label("Tipo:"),    0, 4); form.add(cmbTipo,    1, 4);
        form.add(new Label("Extra 1:"), 0, 5); form.add(txtExtra1,  1, 5);
        form.add(new Label("Extra 2:"), 0, 6); form.add(txtExtra2,  1, 6);

        // ── BOTONES CRUD ───────────────────────────────────────
        Button btnAgregar   = new Button("Agregar");
        Button btnActualizar = new Button("Actualizar");
        Button btnEliminar  = new Button("Eliminar");
        Button btnLimpiar   = new Button("Limpiar");

        Label lblMensaje = new Label();

        // Agregar
        btnAgregar.setOnAction(e -> {
            try {
                int id     = Integer.parseInt(txtId.getText());
                String nom = txtNombre.getText();
                int edad   = Integer.parseInt(txtEdad.getText());
                Genero gen = cmbGenero.getValue();
                String tipo = cmbTipo.getValue();

                if (nom.isEmpty() || gen == null || tipo == null) {
                    lblMensaje.setText("Completá todos los campos.");
                    return;
                }

                Persona p;
                switch (tipo) {
                    case "Empleado" ->
                        p = new Empleado(id, nom, edad, gen, (int) Double.parseDouble(txtExtra1.getText()),
                            Puesto.valueOf(txtExtra2.getText().trim().toUpperCase()));
                    case "Estudiante" ->
                        p = new Estudiante(id, nom, edad, gen,
                            txtExtra1.getText(),
                            Integer.parseInt(txtExtra2.getText().trim()));
                    default ->
                        p = new Cliente(id, nom, edad, gen,
                            txtExtra1.getText(),
                            TipoCliente.valueOf(txtExtra2.getText().trim().toUpperCase())) {};
                }

                gestion.crear(p);
                actualizarTabla();
                lblMensaje.setText("Persona agregada correctamente.");
            } catch (Datoinvalido ex) {
                lblMensaje.setText("Error: " + ex.getMessage());
            } catch (Exception ex) {
                lblMensaje.setText("Error: " + ex.getMessage());
            }
        });

        // Eliminar
        btnEliminar.setOnAction(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                gestion.eliminar(id);
                actualizarTabla();
                lblMensaje.setText("Persona eliminada.");
            } catch (Personanoencontrada ex) {
                lblMensaje.setText("Error: " + ex.getMessage());
            } catch (Exception ex) {
                lblMensaje.setText("ID inválido.");
            }
        });

        // Actualizar
        btnActualizar.setOnAction(e -> {
            try {
                int id     = Integer.parseInt(txtId.getText());
                String nom = txtNombre.getText();
                int edad   = Integer.parseInt(txtEdad.getText());
                Genero gen = cmbGenero.getValue();
                String tipo = cmbTipo.getValue();

                Persona p;
                switch (tipo) {
                case "Empleado" ->
                    p = new Empleado(id, nom, edad, gen, (int) Double.parseDouble(txtExtra1.getText()),
                        Puesto.valueOf(txtExtra2.getText().trim().toUpperCase()));
                case "Estudiante" ->
                    p = new Estudiante(id, nom, edad, gen,
                        txtExtra1.getText(),
                        Integer.parseInt(txtExtra2.getText().trim()));
                default ->
                    p = new Cliente(id, nom, edad, gen,
                        txtExtra1.getText(),
                        TipoCliente.valueOf(txtExtra2.getText().trim().toUpperCase()));
            }

                gestion.actualizar(p);
                actualizarTabla();
                lblMensaje.setText("Persona actualizada.");
            } catch (Personanoencontrada ex) {
                lblMensaje.setText("Error: " + ex.getMessage());
            } catch (Exception ex) {
                lblMensaje.setText("Error: " + ex.getMessage());
            }
        });

        // Limpiar formulario
        btnLimpiar.setOnAction(e -> {
            txtId.clear(); txtNombre.clear(); txtEdad.clear();
            txtExtra1.clear(); txtExtra2.clear();
            cmbGenero.setValue(null); cmbTipo.setValue(null);
            lblMensaje.setText("");
        });

        // Seleccionar fila en tabla → cargar en formulario
        tabla.getSelectionModel().selectedItemProperty().addListener((obs, old, sel) -> {
            if (sel != null) {
                txtId.setText(String.valueOf(sel.getId()));
                txtNombre.setText(sel.getNombre());
                txtEdad.setText(String.valueOf(sel.getEdad()));
                cmbGenero.setValue(sel.getGenero());
                cmbTipo.setValue(sel.getClass().getSimpleName());
            }
        });

        HBox botonescrud = new HBox(8, btnAgregar, btnActualizar, btnEliminar, btnLimpiar);
        botonescrud.setPadding(new Insets(8));

        // ── BOTONES EXTRAS ─────────────────────────────────────
        Button btnOrdenarNombre = new Button("Ordenar por Nombre");
        Button btnOrdenarEdad   = new Button("Ordenar por Edad");
        Button btnFiltrarMayores = new Button("Filtrar +30");
        Button btnAumento       = new Button("Aumento 10%");

        btnOrdenarNombre.setOnAction(e -> {
            gestion.ordenar(); // usa Comparable
            actualizarTabla();
            lblMensaje.setText("Ordenado por nombre.");
        });

        btnOrdenarEdad.setOnAction(e -> {
            gestion.ordenar(Comparar.porEdad()); // usa Comparator
            actualizarTabla();
            lblMensaje.setText("Ordenado por edad.");
        });

        btnFiltrarMayores.setOnAction(e -> {
            listaObservable.setAll(gestion.filtrar(p -> p.getEdad() > 30));
            lblMensaje.setText("Mostrando mayores de 30.");
        });

        btnAumento.setOnAction(e -> {
            gestion.modificar(p -> {
                if (p instanceof Empleado emp) {
                    emp.setSueldo((int) (emp.getSueldo() * 1.10));
                }
            });
            actualizarTabla();
            lblMensaje.setText("Aumento del 10% aplicado a empleados.");
        });

        HBox botonesExtra = new HBox(8, btnOrdenarNombre, btnOrdenarEdad,
                                        btnFiltrarMayores, btnAumento);
        botonesExtra.setPadding(new Insets(8));

        // ── BOTONES PERSISTENCIA ───────────────────────────────
        Button btnGuardarDat = new Button("Guardar .dat");
        Button btnCargarDat  = new Button("Cargar .dat");
        Button btnGuardarCSV = new Button("Guardar CSV");
        Button btncargarCSV = new Button("Cargar CSV");
        Button btnGuardarJSON = new Button("Guardar JSON");
        Button btncargarJSON = new Button("Cargar JSON");
        Button btnExportarTXT = new Button("Exportar TXT");

        btnGuardarDat.setOnAction(e -> {
            gestion.guardarDat("personas.dat");
            lblMensaje.setText("Guardado en personas.dat");
        });

        btnCargarDat.setOnAction(e -> {
            gestion.cargarDat("personas.dat");
            actualizarTabla();
            lblMensaje.setText("Cargado desde personas.dat");
        });

        btnGuardarCSV.setOnAction(e -> {
            gestion.guardarCSV("personas.csv");
            lblMensaje.setText("Guardado en personas.csv");
        });
        
        btncargarCSV.setOnAction(e -> {
            gestion.cargarCSV("personas.csv");
            actualizarTabla();
            lblMensaje.setText("cargando desde personas.csv");
        });

        btnGuardarJSON.setOnAction(e -> {
            gestion.guardarJSON("personas.json");
            lblMensaje.setText("Guardado en personas.json");
        });
        
        btncargarJSON.setOnAction(e -> {
            gestion.cargarJSON("personas.json");
            actualizarTabla();
            lblMensaje.setText("Guardado en personas.json");
        });

        btnExportarTXT.setOnAction(e -> {
            gestion.exportarTXT("personas.txt", p -> true);
            lblMensaje.setText("Exportado en personas.txt");
        });

        HBox botonesPers = new HBox(8, btnGuardarDat, btnCargarDat,
        btnGuardarCSV, btncargarCSV, btnGuardarJSON, btncargarJSON, btnExportarTXT);
        botonesPers.setPadding(new Insets(8));

        // ── LAYOUT PRINCIPAL ───────────────────────────────────
        VBox izquierda = new VBox(8, new Label("Formulario"), form, botonescrud);
        izquierda.setPadding(new Insets(8));

        VBox derecha = new VBox(8, new Label("Lista de Personas"), tabla,
                                   botonesExtra, botonesPers, lblMensaje);
        derecha.setPadding(new Insets(8));

        HBox root = new HBox(16, izquierda, derecha);
        root.setPadding(new Insets(12));
        colExtra.setPrefWidth(300);
        Scene scene = new Scene(root, 900, 550);
        stage.setTitle("Sistema de Gestión de Personas");
        stage.setScene(scene);
        stage.show();
    }

    private void actualizarTabla() {
        listaObservable.setAll(gestion.leer());
    }

    public static void main(String[] args) {
        launch(args);
    }
}