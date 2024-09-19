package com.todopc;

public class Tablet extends Equipo {
    private boolean esCapacitiva;

    public Tablet(String fabricante, String modelo, String microprocesador, int memoria, boolean esCapacitiva) {
        super(fabricante, modelo, microprocesador, memoria);
        this.esCapacitiva = esCapacitiva;
    }

    @Override
    public String mostrarInformacion() {
        return super.mostrarInformacion() + "\nPantalla Capacitiva: " + (esCapacitiva ? "Sí" : "No");
    }
}
