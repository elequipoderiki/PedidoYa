package com.techlab.productos;

public class Bebida extends Producto {

    private double volumenLitros;

    public Bebida(String nombre, double precio, int stock, double volumen){
        super(nombre, precio, stock);
        this.volumenLitros = volumen;
    }

    public void setVolumenLitros(double volumen) {
        this.volumenLitros = volumen;
    }

    public double getVolumenLitros() {
        return this.volumenLitros;
    }

    @Override
    public void mostrarProducto() {
        super.mostrarProducto();
        System.out.println("Volumen en litros de la bebida: " + this.volumenLitros);
    }
}
