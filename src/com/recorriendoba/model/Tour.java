package com.recorriendoba.model;

public abstract class Tour {
    private int id;
    private static int contador = 1;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private CategoriaTour categoria;
    private int vendidos = 0;
    

    public Tour(String nombre, String descripcion, double precio, int stock, CategoriaTour categoria) {
        this.id = contador++;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
    }
//Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String n) { this.nombre = n; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String d) { this.descripcion = d; }
    public double getPrecio() { return precio; }
    public void setPrecio(double p) { this.precio = p; }
    public int getStock() { return stock; }
    public void setStock(int s) { this.stock = s; }
    public int getVendidos() { return vendidos; }
    public void setVendidos(int v) { this.vendidos = v; }
    public CategoriaTour getCategoria() { return categoria; }

    @Override
    public String toString() {
        return String.format("[%02d] %-20s | %-12s | $%8.2f | Stock: %d | %s",
            getId(), getNombre(), categoria.getNombre(), getPrecio(), getStock(), getDescripcion());

    }

}

