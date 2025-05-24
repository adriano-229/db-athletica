package com.adriano.view;

import com.adriano.controller.AlumnoController;
import com.adriano.controller.EncargadoController;
import com.adriano.controller.PersonaController;
import com.adriano.controller.ProfesorController;
import com.adriano.model.Alumno;
import com.adriano.model.Encargado;
import com.adriano.model.Persona;
import com.adriano.model.Profesor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class PersonaView extends JFrame {

    // Common fields for all types
    private final JTextField documentoField = new JTextField(10);
    private final JTextField nombreField = new JTextField(20);
    private final JTextField nacimientoField = new JTextField(10);
    private final JComboBox<String> sexoBox = new JComboBox<>(new String[]{"M", "F", "X"});
    private final JComboBox<String> tipoBox = new JComboBox<>(new String[]{"Alumno", "Profesor", "Encargado"});

    // Specific fields for each type
    private final JPanel alumnoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private final JTextField certificadoField = new JTextField(10);

    private final JPanel profesorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private final JTextField especialidadField = new JTextField(15);

    private final JPanel encargadoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private final JTextField telefonoField = new JTextField(10);

    // Buttons
    private final JButton addButton = new JButton("Add");
    private final JButton listButton = new JButton("List All");
    private final JButton deleteButton = new JButton("Delete Selected");
    private final JButton expiredButton = new JButton("Show Expired Certificates");

    private final JTable table = new JTable();

    // Controllers
    private final PersonaController personaController = new PersonaController();
    private final AlumnoController alumnoController = new AlumnoController();
    private final ProfesorController profesorController = new ProfesorController();
    private final EncargadoController encargadoController = new EncargadoController();

    public PersonaView() {
        super("Personas Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        // Main form panel
        JPanel formPanel = new JPanel(new GridLayout(0, 1));

        // Type selector panel
        JPanel typePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        typePanel.add(new JLabel("Tipo:"));
        typePanel.add(tipoBox);
        formPanel.add(typePanel);

        // Common fields panel
        JPanel commonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        commonPanel.add(new JLabel("Documento:"));
        commonPanel.add(documentoField);
        commonPanel.add(new JLabel("Nombre:"));
        commonPanel.add(nombreField);
        commonPanel.add(new JLabel("Nacimiento (YYYY-MM-DD):"));
        commonPanel.add(nacimientoField);
        commonPanel.add(new JLabel("Sexo:"));
        commonPanel.add(sexoBox);
        formPanel.add(commonPanel);

        // Setup specific panels
        alumnoPanel.add(new JLabel("Vence Certificado (YYYY-MM-DD):"));
        alumnoPanel.add(certificadoField);
        alumnoPanel.setVisible(false);
        formPanel.add(alumnoPanel);

        profesorPanel.add(new JLabel("Especialidad:"));
        profesorPanel.add(especialidadField);
        profesorPanel.setVisible(false);
        formPanel.add(profesorPanel);

        encargadoPanel.add(new JLabel("Teléfono:"));
        encargadoPanel.add(telefonoField);
        encargadoPanel.setVisible(false);
        formPanel.add(encargadoPanel);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(listButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(expiredButton);
        expiredButton.setVisible(false);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(formPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Add listeners
        tipoBox.addActionListener(e -> updateFormFields());
        addButton.addActionListener(e -> saveEntity());
        listButton.addActionListener(e -> listEntities());
        deleteButton.addActionListener(e -> deleteSelected());
        expiredButton.addActionListener(e -> showExpiredCertificates());

        updateFormFields(); // Initialize form fields
        setVisible(true);
    }

    private void updateFormFields() {
        String selectedType = (String) tipoBox.getSelectedItem();

        // Hide all specific panels first
        alumnoPanel.setVisible(false);
        profesorPanel.setVisible(false);
        encargadoPanel.setVisible(false);
        expiredButton.setVisible(false);

        // Show the relevant panel based on selection
        switch (selectedType) {
            case "Alumno":
                alumnoPanel.setVisible(true);
                expiredButton.setVisible(true);
                break;
            case "Profesor":
                profesorPanel.setVisible(true);
                break;
            case "Encargado":
                encargadoPanel.setVisible(true);
                break;
        }

        // Repaint the form
        revalidate();
        repaint();
    }

    private void saveEntity() {
        try {
            int doc = Integer.parseInt(documentoField.getText());
            String nombre = nombreField.getText();
            LocalDate fechaNacimiento = LocalDate.parse(nacimientoField.getText());
            String sexoStr = sexoBox.getSelectedItem().toString();
            Persona.Sexo sexo = Persona.Sexo.valueOf(sexoStr);

            String selectedType = (String) tipoBox.getSelectedItem();

            switch (selectedType) {
                case "Alumno":
                    LocalDate fechaCertificado = LocalDate.parse(certificadoField.getText());
                    Date certificadoDate = Date.from(fechaCertificado.atStartOfDay(ZoneId.systemDefault()).toInstant());

                    Alumno alumno = new Alumno();
                    alumno.setDocumento(doc);
                    alumno.setNombreCompleto(nombre);
                    alumno.setFechaNacimiento(fechaNacimiento);
                    alumno.setSexo(sexo);
                    alumno.setFechaVenceCertificado(certificadoDate);
                    alumnoController.save(alumno);
                    break;

                case "Profesor":
                    String especialidad = especialidadField.getText();

                    Profesor profesor = new Profesor();
                    profesor.setDocumento(doc);
                    profesor.setNombreCompleto(nombre);
                    profesor.setFechaNacimiento(fechaNacimiento);
                    profesor.setSexo(sexo);
                    profesor.setEspecialidad(especialidad);
                    profesorController.save(profesor);
                    break;

                case "Encargado":
                    String telefono = telefonoField.getText();

                    Encargado encargado = new Encargado();
                    encargado.setDocumento(doc);
                    encargado.setNombreCompleto(nombre);
                    encargado.setFechaNacimiento(fechaNacimiento);
                    encargado.setSexo(sexo);
                    encargado.setTelefono(telefono);
                    encargadoController.save(encargado);
                    break;
            }

            JOptionPane.showMessageDialog(this, selectedType + " guardado correctamente.");
            clearForm();
            listEntities();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        documentoField.setText("");
        nombreField.setText("");
        nacimientoField.setText("");
        certificadoField.setText("");
        especialidadField.setText("");
        telefonoField.setText("");
    }

    private void listEntities() {
        String selectedType = (String) tipoBox.getSelectedItem();

        switch (selectedType) {
            case "Persona":
                displayPersonas(personaController.getAll());
                break;
            case "Alumno":
                displayAlumnos(alumnoController.getAll());
                break;
            case "Profesor":
                List<Profesor> profesores = profesorController.getAll();
                displayProfesores(profesores);
                break;
            case "Encargado":
                List<Encargado> encargados = encargadoController.getAll();
                displayEncargados(encargados);
                break;
        }
    }

    private void displayPersonas(List<Persona> personas) {
        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"Documento", "Nombre", "Nacimiento", "Sexo"}, 0);

        for (Persona p : personas) {
            model.addRow(new Object[]{
                    p.getDocumento(),
                    p.getNombreCompleto(),
                    p.getFechaNacimiento(),
                    p.getSexo()
            });
        }
        table.setModel(model);
    }

    private void displayAlumnos(List<Alumno> alumnos) {
        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"Documento", "Nombre", "Nacimiento", "Sexo", "Vence Certificado"}, 0);

        for (Alumno a : alumnos) {
            model.addRow(new Object[]{
                    a.getDocumento(),
                    a.getNombreCompleto(),
                    a.getFechaNacimiento(),
                    a.getSexo(),
                    a.getFechaVenceCertificado()
            });
        }
        table.setModel(model);
    }

    private void displayProfesores(List<Profesor> profesores) {
        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"Documento", "Nombre", "Nacimiento", "Sexo", "Especialidad"}, 0);

        for (Profesor p : profesores) {
            model.addRow(new Object[]{
                    p.getDocumento(),
                    p.getNombreCompleto(),
                    p.getFechaNacimiento(),
                    p.getSexo(),
                    p.getEspecialidad()
            });
        }
        table.setModel(model);
    }

    private void displayEncargados(List<Encargado> encargados) {
        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"Documento", "Nombre", "Nacimiento", "Sexo", "Teléfono"}, 0);

        for (Encargado e : encargados) {
            model.addRow(new Object[]{
                    e.getDocumento(),
                    e.getNombreCompleto(),
                    e.getFechaNacimiento(),
                    e.getSexo(),
                    e.getTelefono()
            });
        }
        table.setModel(model);
    }

    private void deleteSelected() {
        int row = table.getSelectedRow();
        if (row != -1) {
            int doc = (int) table.getValueAt(row, 0);
            String selectedType = (String) tipoBox.getSelectedItem();

            try {
                switch (selectedType) {
                    case "Persona":
                        personaController.delete(personaController.get(doc));
                        break;
                    case "Alumno":
                        alumnoController.delete(alumnoController.get(doc));
                        break;
                    case "Profesor":
                        profesorController.delete(profesorController.get(doc));
                        break;
                    case "Encargado":
                        encargadoController.delete(encargadoController.get(doc));
                        break;
                }
                JOptionPane.showMessageDialog(this, selectedType + " eliminado correctamente.");
                listEntities();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una fila para eliminar.");
        }
    }

    private void showExpiredCertificates() {
        try {
            Date currentDate = new Date();
            List<Alumno> expiredAlumnos = alumnoController.findExpiredCertificates(currentDate);

            DefaultTableModel model = new DefaultTableModel(
                    new Object[]{"Documento", "Nombre", "Nacimiento", "Sexo", "Vence Certificado"}, 0);

            for (Alumno a : expiredAlumnos) {
                model.addRow(new Object[]{
                        a.getDocumento(),
                        a.getNombreCompleto(),
                        a.getFechaNacimiento(),
                        a.getSexo(),
                        a.getFechaVenceCertificado()
                });
            }
            table.setModel(model);

            if (expiredAlumnos.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay certificados vencidos.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error buscando certificados vencidos: " + ex.getMessage());
        }
    }
}