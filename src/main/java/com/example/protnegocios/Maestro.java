package com.example.protnegocios;

public class Maestro {

    private int cod;
    private String nombre;
    private Cliente cli;
    private Zona zona;


    public Maestro(){}

    public Maestro(int cod, String nombre, Cliente cli, Zona zona) {
        this.cod = cod;
        this.nombre = nombre;
        this.cli = cli;
        this.zona = zona;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Cliente getCli() {
        return cli;
    }

    public void setCli(Cliente cli) {
        this.cli = cli;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }
}
