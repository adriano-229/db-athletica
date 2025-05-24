package com.adriano.view;

import com.adriano.controller.PersonaController;
import com.adriano.model.Persona;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;

public class PersonaView extends JFrame {

    private final JTextField documentoField = new JTextField(10);
    private final JTextField nombreField = new JTextField(20);
    private final JTextField nacimientoField = new JTextField(10);
    private final JComboBox<String> sexoBox = new JComboBox<>(new String[]{"M", "F", "X"});
    private final JButton addButton = new JButton("Add Persona");
    private final JButton listButton = new JButton("List All");
    private final JButton deleteButton = new JButton("Delete Selected");
    private final JTable table = new JTable();

    private final PersonaController controller = new PersonaController();

    public PersonaView() {
        super("Personas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        JPanel form = new JPanel();
        form.add(new JLabel("Documento:"));
        form.add(documentoField);
        form.add(new JLabel("Nombre:"));
        form.add(nombreField);
        form.add(new JLabel("Nacimiento (YYYY-MM-DD):"));
        form.add(nacimientoField);
        form.add(new JLabel("Sexo:"));
        form.add(sexoBox);
        form.add(addButton);
        form.add(listButton);
        form.add(deleteButton);
        add(form, BorderLayout.NORTH);

        add(new JScrollPane(table), BorderLayout.CENTER);

        addButton.addActionListener(e -> addPersona());
        listButton.addActionListener(e -> listPersonas());
        deleteButton.addActionListener(e -> deleteSelected());

        setVisible(true);
    }

    private void addPersona() {
        try {
            int doc = Integer.parseInt(documentoField.getText());
            String nombre = nombreField.getText();
            LocalDate fecha = LocalDate.parse(nacimientoField.getText());
            char sexo = sexoBox.getSelectedItem().toString().charAt(0);

            Persona persona = new Persona(doc, nombre, fecha, sexo);
            controller.addPersona(persona);

            JOptionPane.showMessageDialog(this, "Persona guardada.");
            listPersonas();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void listPersonas() {
        java.util.List<Persona> personas = controller.getAllPersonas();
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Documento", "Nombre", "Nacimiento", "Sexo"}, 0);
        for (Persona p : personas) {
            model.addRow(new Object[]{p.getDocumento(), p.getNombreCompleto(), p.getFechaNacimiento(), p.getSexo()});
        }
        table.setModel(model);
    }

    private void deleteSelected() {
        int row = table.getSelectedRow();
        if (row != -1) {
            int doc = (int) table.getValueAt(row, 0);
            controller.deletePersona(controller.getPersona(doc));
            listPersonas();
        }
    }
}
