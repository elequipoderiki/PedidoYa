package com.techlab.pedido;

import java.util.ArrayList;

public class Pedido {
    private ArrayList<LineaPedido> productosPedidos;
    private double precioTotal;

    public Pedido() {
        this.productosPedidos = new ArrayList<LineaPedido>();
    }

    public void agregarAlPedido(LineaPedido producto, double costo) {
        this.productosPedidos.add(producto);
        this.precioTotal = this.precioTotal + costo;
    }

    public ArrayList<LineaPedido> obtenerItemsPedido () {
        ArrayList<LineaPedido> copiaPedido = new ArrayList<>(this.productosPedidos);
        return copiaPedido;
    }

    public void setPrecioTotal(double costo) {
        this.precioTotal = costo;
    }

    public double getPrecioTotal() {
        return this.precioTotal;
    }

    /*
    public void listarProductos() {
        if(this.productosPedidos.isEmpty()) {
            System.out.println("No hay productos en el pedido actual");
        } else {
            System.out.println("Los items del pedido son: ");
            this.productosPedidos.forEach(item -> {
                System.out.println("===============================================");
                item.mostrarDatosProductoPedido();
            });
        }
    }

     */
}
