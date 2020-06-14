package com.example.protnegocios;

public class Cliente {
    private int cod;
    private int nombre;
    private int apellido;

    public Cliente(){}
    public Cliente(int cod, int nombre, int apellido) {
        this.cod = cod;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public int getApellido() {
        return apellido;
    }

    public void setApellido(int apellido) {
        this.apellido = apellido;
    }
}
