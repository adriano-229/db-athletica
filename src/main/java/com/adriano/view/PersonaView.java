package com.adriano.view;

import com.adriano.controller.AlumnoController;
import com.adriano.controller.EncargadoController;
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

    // Common fields
    private final JTextField documentoField = new JTextField(10);
    private final JTextField nombreField = new JTextField(20);
    private final JTextField nacimientoField = new JTextField(10);
    private final JTextField telefonoField = new JTextField(10);
    private final JComboBox<String> sexoBox = new JComboBox<>(new String[]{"M", "F", "X"});
    private final JComboBox<String> tipoBox = new JComboBox<>(new String[]{"Alumno", "Profesor", "Encargado"});

    // Specific fields
    private final JTextField certificadoField = new JTextField(10);
    private final JTextField fechaIncorporacion = new JTextField(10);
    private final JTextField porcentajeField = new JTextField(5);

    private final JPanel alumnoPanel = createLabeledField("Vence Certificado (YYYY-MM-DD):", certificadoField);
    private final JPanel profesorPanel = createLabeledField("Incorporación (YYYY-MM-DD):", fechaIncorporacion);
    private final JPanel encargadoPanel = createLabeledField("Porcentaje Accionario:", porcentajeField);
    private final JButton expiredButton = new JButton("Mostrar Certificados Vencidos");
    private final JTable table = new JTable();
    private final AlumnoController alumnoController = new AlumnoController();
    private final ProfesorController profesorController = new ProfesorController();
    private final EncargadoController encargadoController = new EncargadoController();

    public PersonaView() {
        super("Gestión de Personas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLayout(new BorderLayout());

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(0, 1));
        formPanel.add(createTypePanel());
        formPanel.add(createCommonFieldsPanel());
        formPanel.add(alumnoPanel);
        formPanel.add(profesorPanel);
        formPanel.add(encargadoPanel);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Agregar");
        JButton listButton = new JButton("Listar todos");
        JButton deleteButton = new JButton("Borrar seleccionado");
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

        // Listeners
        tipoBox.addActionListener(e -> updateFormFields());
        addButton.addActionListener(e -> saveEntity());
        listButton.addActionListener(e -> listEntities());
        deleteButton.addActionListener(e -> deleteSelected());
        expiredButton.addActionListener(e -> showExpiredCertificates());

        updateFormFields();
        setVisible(true);
    }

    private JPanel createTypePanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel("Tipo:"));
        panel.add(tipoBox);
        return panel;
    }

    private JPanel createCommonFieldsPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel("Documento:"));
        panel.add(documentoField);
        panel.add(new JLabel("Nombre:"));
        panel.add(nombreField);
        panel.add(new JLabel("Nacimiento (YYYY-MM-DD):"));
        panel.add(nacimientoField);
        panel.add(new JLabel("Sexo:"));
        panel.add(sexoBox);
        panel.add(new JLabel("Teléfono:"));
        panel.add(telefonoField);
        return panel;
    }

    private JPanel createLabeledField(String label, JTextField field) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel(label));
        panel.add(field);
        panel.setVisible(false);
        return panel;
    }

    private void updateFormFields() {
        String selected = (String) tipoBox.getSelectedItem();
        alumnoPanel.setVisible(false);
        profesorPanel.setVisible(false);
        encargadoPanel.setVisible(false);
        expiredButton.setVisible(false);

        if ("Alumno".equals(selected)) {
            alumnoPanel.setVisible(true);
            expiredButton.setVisible(true);
        } else if ("Profesor".equals(selected)) {
            profesorPanel.setVisible(true);
        } else if ("Encargado".equals(selected)) {
            encargadoPanel.setVisible(true);
        }

        revalidate();
        repaint();
    }

    private void setCommonPersonaFields(Persona persona, int doc, String nombre, LocalDate fechaNacimiento, Persona.Sexo sexo, String telefono) {
        persona.setDocumento(doc);
        persona.setNombreCompleto(nombre);
        persona.setFechaNacimiento(fechaNacimiento);
        persona.setSexo(sexo);
        persona.setTelefono(telefono);
    }

    private void saveEntity() {
        try {
            int doc = Integer.parseInt(documentoField.getText());
            String nombre = nombreField.getText();
            LocalDate fechaNacimiento = LocalDate.parse(nacimientoField.getText());
            Persona.Sexo sexo = Persona.Sexo.valueOf(sexoBox.getSelectedItem().toString());
            TipoPersona tipo = TipoPersona.fromString((String) tipoBox.getSelectedItem());
            String telefono = telefonoField.getText();

            switch (tipo) {
                case ALUMNO:
                    Alumno alumno = new Alumno();
                    setCommonPersonaFields(alumno, doc, nombre, fechaNacimiento, sexo, telefono);
                    alumno.setFechaVenceCertificado(Date.from(LocalDate.parse(certificadoField.getText()).atStartOfDay(ZoneId.systemDefault()).toInstant()));
                    alumnoController.save(alumno);
                    break;
                case PROFESOR:
                    Profesor profesor = new Profesor();
                    setCommonPersonaFields(profesor, doc, nombre, fechaNacimiento, sexo, telefono);
                    profesor.setFechaIncorporacion(Date.from(LocalDate.parse(fechaIncorporacion.getText()).atStartOfDay(ZoneId.systemDefault()).toInstant()));
                    profesorController.save(profesor);
                    break;
                case ENCARGADO:
                    Encargado encargado = new Encargado();
                    setCommonPersonaFields(encargado, doc, nombre, fechaNacimiento, sexo, telefono);
                    encargado.setPorcentajeAccionario(Double.parseDouble(porcentajeField.getText()));
                    encargadoController.save(encargado);
                    break;
            }

            JOptionPane.showMessageDialog(this, tipo + " guardado correctamente.");
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
        telefonoField.setText("");
        sexoBox.setSelectedIndex(0);
        certificadoField.setText("");
        fechaIncorporacion.setText("");
        porcentajeField.setText("");
    }

    private void listEntities() {
        TipoPersona tipo = TipoPersona.fromString((String) tipoBox.getSelectedItem());
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Documento");
        model.addColumn("Nombre");
        model.addColumn("Nacimiento");
        model.addColumn("Sexo");
        model.addColumn("Teléfono");

        try {
            List<? extends Persona> personas;
            switch (tipo) {
                case ALUMNO:
                    personas = alumnoController.getAll();
                    model.addColumn("Vence Certificado");
                    for (Alumno alumno : (List<Alumno>) personas) {
                        model.addRow(new Object[]{
                                alumno.getDocumento(),
                                alumno.getNombreCompleto(),
                                alumno.getFechaNacimiento(),
                                alumno.getSexo(),
                                alumno.getTelefono(),
                                alumno.getFechaVenceCertificado()
                        });
                    }
                    break;
                case PROFESOR:
                    personas = profesorController.getAll();
                    model.addColumn("Fecha Incorporación");
                    for (Profesor profesor : (List<Profesor>) personas) {
                        model.addRow(new Object[]{
                                profesor.getDocumento(),
                                profesor.getNombreCompleto(),
                                profesor.getFechaNacimiento(),
                                profesor.getSexo(),
                                profesor.getTelefono(),
                                profesor.getFechaIncorporacion()
                        });
                    }
                    break;
                case ENCARGADO:
                    personas = encargadoController.getAll();
                    model.addColumn("Porcentaje Accionario");
                    for (Encargado encargado : (List<Encargado>) personas) {
                        model.addRow(new Object[]{
                                encargado.getDocumento(),
                                encargado.getNombreCompleto(),
                                encargado.getFechaNacimiento(),
                                encargado.getSexo(),
                                encargado.getTelefono(),
                                encargado.getPorcentajeAccionario()
                        });
                    }
                    break;
            }
            table.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al listar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void deleteSelected() {
        int row = table.getSelectedRow();
        if (row != -1) {
            int doc = (int) table.getValueAt(row, 0);
            TipoPersona tipo = TipoPersona.fromString((String) tipoBox.getSelectedItem());
            try {
                switch (tipo) {
                    case ALUMNO:
                        alumnoController.delete(alumnoController.get(doc));
                        break;
                    case PROFESOR:
                        profesorController.delete(profesorController.get(doc));
                        break;
                    case ENCARGADO:
                        encargadoController.delete(encargadoController.get(doc));
                        break;
                }
                JOptionPane.showMessageDialog(this, tipo + " eliminado correctamente.");
                listEntities();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al eliminar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una fila para eliminar.");
        }
    }


    private void showExpiredCertificates() {
        // displays the exact same alumnos table but only those with expired certificates
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Documento");
        model.addColumn("Nombre");
        model.addColumn("Nacimiento");
        model.addColumn("Sexo");
        model.addColumn("Teléfono");
        model.addColumn("Vence Certificado");

        try {
            List<Alumno> alumnos = alumnoController.findExpiredCertificates(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            for (Alumno alumno : alumnos) {
                model.addRow(new Object[]{
                        alumno.getDocumento(),
                        alumno.getNombreCompleto(),
                        alumno.getFechaNacimiento(),
                        alumno.getSexo(),
                        alumno.getTelefono(),
                        alumno.getFechaVenceCertificado()
                });
            }
            table.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al listar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private enum TipoPersona {
        ALUMNO("Alumno"), PROFESOR("Profesor"), ENCARGADO("Encargado");

        private final String label;

        TipoPersona(String label) {
            this.label = label;
        }

        public static TipoPersona fromString(String s) {
            for (TipoPersona tipo : values()) {
                if (tipo.label.equals(s)) {
                    return tipo;
                }
            }
            throw new IllegalArgumentException("Unknown type: " + s);
        }

        @Override
        public String toString() {
            return label;
        }
    }
}
