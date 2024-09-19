package com.todopc;

public class Equipo {
    protected String fabricante;
    protected String modelo;
    protected String microprocesador;
    protected int memoria;

    public Equipo(String fabricante, String modelo, String microprocesador, int memoria) {
        this.fabricante = fabricante;
        this.modelo = modelo;
        this.microprocesador = microprocesador;
        this.memoria = memoria;
    }

    public String getFabricante() {
        return fabricante;
    }

    public String getModelo() {
        return modelo;
    }

    public String getMicroprocesador() {
        return microprocesador;
    }

    public int getMemoria() {
        return memoria;
    }

    public String mostrarInformacion() {
        return "Fabricante: " + fabricante + "\nModelo: " + modelo + "\nMicroprocesador: " + microprocesador + "\nMemoria: " + memoria + "GB";
    }
}
