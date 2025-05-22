package com.techlab.productos;

public class Producto {
    private static int sig_id = 1;
    private final int id;
    private String nombre;
    private double precio;
    private int stock;

    public Producto() {
        this.id = sig_id;
        sig_id++;
    };

    public Producto(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.id = sig_id;
        sig_id++;
    }


    public void mostrarProducto() {
        System.out.println("Id del producto: " + this.id);
        System.out.println("Nombre: " + this.nombre);
        System.out.println("Precio: " + this.precio);
        System.out.println("Stock: " + this.stock);
    }



    public int getId() {
        return this.id;
    }

    public  String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        if (!nombre.isEmpty() ) {
            this.nombre = nombre;
        }
    }

    public double getPrecio() {
        return  this.precio;
    }

    public void setPrecio(double precio) {
        if (precio > 0) {
            this.precio = precio;
        }
    }

    public  int getStock() {
        return this.stock;
    }

    public void setStock(int stock){
        if (stock >= 0) {
            this.stock = stock;
        }
    }
}
