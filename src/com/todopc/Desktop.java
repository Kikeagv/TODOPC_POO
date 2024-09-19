package com.todopc;

public class Desktop extends Equipo {
    private String tarjetaGrafica;
    private int tamanoTorre;

    public Desktop(String fabricante, String modelo, String microprocesador, int memoria, String tarjetaGrafica, int tamanoTorre) {
        super(fabricante, modelo, microprocesador, memoria);
        this.tarjetaGrafica = tarjetaGrafica;
        this.tamanoTorre = tamanoTorre;
    }

    @Override
    public String mostrarInformacion() {
        return super.mostrarInformacion() + "\nTarjeta Gráfica: " + tarjetaGrafica + "\nTamaño Torre: " + tamanoTorre + " pulgadas";
    }
}
