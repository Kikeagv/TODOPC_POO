package com.todopc;

public class Laptop extends Equipo {
    private int tamanoPantalla;

    public Laptop(String fabricante, String modelo, String microprocesador, int memoria, int tamanoPantalla) {
        super(fabricante, modelo, microprocesador, memoria);
        this.tamanoPantalla = tamanoPantalla;
    }

    @Override
    public String mostrarInformacion() {
        return super.mostrarInformacion() + "\nTamaño Pantalla: " + tamanoPantalla + " pulgadas";
    }
}
