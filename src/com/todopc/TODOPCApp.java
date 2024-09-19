package com.todopc;

import javax.swing.*;
import java.util.ArrayList;

public class TODOPCApp {
    private static ArrayList<Equipo> equipos = new ArrayList<>();

    public static void main(String[] args) {
        String[] opcionesMenu = {"Registrar equipo", "Ver equipos", "Salir"};
        int opcion;

        do {
            opcion = JOptionPane.showOptionDialog(null, "Seleccione una opción", "TODOPC",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcionesMenu, opcionesMenu[0]);

            try {
                switch (opcion) {
                    case 0 -> registrarEquipo();  // Opción para registrar un equipo
                    case 1 -> verEquipos();  // Opción para ver los equipos registrados
                    case 2 -> JOptionPane.showMessageDialog(null, "Gracias por usar TODOPC.");
                    default -> throw new IllegalStateException("Opción inesperada: " + opcion);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (opcion != 2);
    }

    private static void registrarEquipo() {
        boolean datosValidos = false;  // Bandera para verificar si los datos ingresados son válidos

        // Crear los campos del formulario
        String[] tiposEquipo = {"Desktop", "Laptop", "Tablet"};
        JComboBox<String> tipoComboBox = new JComboBox<>(tiposEquipo);
        JTextField fabricanteField = new JTextField(10);
        JTextField modeloField = new JTextField(10);
        JTextField microprocesadorField = new JTextField(10);
        JTextField memoriaField = new JTextField(10);
        JTextField atributoEspecificoField1 = new JTextField(10);  // Para tarjeta gráfica o tamaño pantalla
        JTextField atributoEspecificoField2 = new JTextField(10);  // Para segundo atributo específico

        // Etiquetas para campos adicionales
        JLabel etiquetaEspecifica1 = new JLabel("Tarjeta Gráfica:");
        JLabel etiquetaEspecifica2 = new JLabel("Tamaño Torre (pulgadas):");

        // Crear el panel del formulario
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Tipo de equipo:"));
        panel.add(tipoComboBox);
        panel.add(new JLabel("Fabricante:"));
        panel.add(fabricanteField);
        panel.add(new JLabel("Modelo:"));
        panel.add(modeloField);
        panel.add(new JLabel("Microprocesador:"));
        panel.add(microprocesadorField);
        panel.add(new JLabel("Memoria (GB):"));
        panel.add(memoriaField);
        panel.add(etiquetaEspecifica1);
        panel.add(atributoEspecificoField1);
        panel.add(etiquetaEspecifica2);
        panel.add(atributoEspecificoField2);

        tipoComboBox.addActionListener(e -> {
            String tipoSeleccionado = (String) tipoComboBox.getSelectedItem();
            switch (tipoSeleccionado) {
                case "Desktop" -> {
                    etiquetaEspecifica1.setText("Tarjeta Gráfica:");
                    etiquetaEspecifica2.setText("Tamaño Torre (pulgadas):");
                    atributoEspecificoField2.setVisible(true);
                }
                case "Laptop" -> {
                    etiquetaEspecifica1.setText("Tamaño Pantalla (pulgadas):");
                    etiquetaEspecifica2.setText("");
                    atributoEspecificoField2.setVisible(false);
                }
                case "Tablet" -> {
                    etiquetaEspecifica1.setText("Tamaño diagonal pantalla:");
                    etiquetaEspecifica2.setText("¿Pantalla Capacitiva? (Sí/No):");
                    atributoEspecificoField2.setVisible(true);
                }
            }
        });

        // Mantener el formulario abierto hasta que los datos sean válidos
        while (!datosValidos) {
            int resultado = JOptionPane.showConfirmDialog(null, panel, "Registrar nuevo equipo",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (resultado == JOptionPane.CANCEL_OPTION) return;  // Regresar al menú si se presiona Cancelar

            try {
                String tipoSeleccionado = (String) tipoComboBox.getSelectedItem();
                String fabricante = validarTexto(fabricanteField.getText(), "Fabricante");
                String modelo = validarTexto(modeloField.getText(), "Modelo");
                String microprocesador = validarTexto(microprocesadorField.getText(), "Microprocesador");
                int memoria = Integer.parseInt(validarTexto(memoriaField.getText(), "Memoria"));

                switch (tipoSeleccionado) {
                    case "Desktop" -> {
                        String tarjetaGrafica = validarTexto(atributoEspecificoField1.getText(), "Tarjeta Gráfica");
                        int tamanoTorre = Integer.parseInt(validarTexto(atributoEspecificoField2.getText(), "Tamaño Torre"));
                        equipos.add(new Desktop(fabricante, modelo, microprocesador, memoria, tarjetaGrafica, tamanoTorre));
                    }
                    case "Laptop" -> {
                        int tamanoPantalla = Integer.parseInt(validarTexto(atributoEspecificoField1.getText(), "Tamaño Pantalla"));
                        equipos.add(new Laptop(fabricante, modelo, microprocesador, memoria, tamanoPantalla));
                    }
                    case "Tablet" -> {
                        int tamanoPantalla = Integer.parseInt(validarTexto(atributoEspecificoField1.getText(), "Tamaño Pantalla"));
                        boolean capacitiva = validarCapacitiva(atributoEspecificoField2.getText());
                        equipos.add(new Tablet(fabricante, modelo, microprocesador, memoria, capacitiva));
                    }
                }

                datosValidos = true;  // Si todos los datos son válidos, salir del ciclo
                JOptionPane.showMessageDialog(null, "Equipo registrado exitosamente.");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error en la entrada numérica. Asegúrese de ingresar números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void verEquipos() {
        if (equipos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay equipos registrados.");
            return;
        }

        String[] tiposEquipo = {"Desktop", "Laptop", "Tablet", "Todos"};
        int tipo = JOptionPane.showOptionDialog(null, "Seleccione el tipo de equipo que desea ver", "Ver equipos",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, tiposEquipo, tiposEquipo[0]);

        if (tipo == JOptionPane.CLOSED_OPTION) return;

        StringBuilder sb = new StringBuilder();
        for (Equipo equipo : equipos) {
            if (tipo == 0 && equipo instanceof Desktop) {
                sb.append(equipo.mostrarInformacion()).append("\n\n");
            } else if (tipo == 1 && equipo instanceof Laptop) {
                sb.append(equipo.mostrarInformacion()).append("\n\n");
            } else if (tipo == 2 && equipo instanceof Tablet) {
                sb.append(equipo.mostrarInformacion()).append("\n\n");
            } else if (tipo == 3) {
                sb.append(equipo.mostrarInformacion()).append("\n\n");
            }
        }

        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(null, sb.toString());
        } else {
            JOptionPane.showMessageDialog(null, "No hay equipos del tipo seleccionado.");
        }
    }

    // Validar que el campo no esté vacío
    private static String validarTexto(String texto, String campo) throws Exception {
        if (texto == null || texto.trim().isEmpty()) {
            throw new Exception(campo + " no puede estar vacío.");
        }
        return texto.trim();
    }

    // Validar entrada de pantalla capacitiva
    private static boolean validarCapacitiva(String texto) throws Exception {
        if ("Sí".equalsIgnoreCase(texto.trim())) {
            return true;
        } else if ("No".equalsIgnoreCase(texto.trim())) {
            return false;
        } else {
            throw new Exception("Debe ingresar 'Sí' o 'No' para el campo 'Pantalla Capacitiva'.");
        }
    }
}
