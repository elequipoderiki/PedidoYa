package com.techlab.productos;

public class Comida extends Producto {

    private String fechaVencimiento;

    public Comida(String nombre, double precio, int stock, String fechaVencimiento) {
        super(nombre, precio, stock);
        this.fechaVencimiento = fechaVencimiento;
    }

    public void setFechaVencimiento(String fecha) {
        this.fechaVencimiento = fecha;
    }

    public String getFechaVencimiento() {
        return this.fechaVencimiento;
    }

    public void mostrarProducto() {
        super.mostrarProducto();
        System.out.println("La fecha de vencimiento es: " + this.fechaVencimiento);
    }
}
