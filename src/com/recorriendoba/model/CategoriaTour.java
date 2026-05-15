package com.recorriendoba.model;

public class CategoriaTour {
    private int id;
    private String nombre;

    public CategoriaTour(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {return id;}
    
    public String getNombre() {return nombre;}

    public void setId(int id) { this.id = id; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    @Override
    public String toString() {
        return String.format("ID: %d | Categoría: %s", id, nombre);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CategoriaTour other = (CategoriaTour) obj;
        return id == other.id;
    }
}



