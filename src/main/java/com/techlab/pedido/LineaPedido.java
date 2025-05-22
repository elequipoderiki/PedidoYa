package com.techlab.pedido;

public class LineaPedido {
    private int idProducto;
    private int cantidadUnidades;
    private String nombre;

    public LineaPedido() {}

    public LineaPedido(int cantidadUnidades, int idProducto, String nombre) {
        this.idProducto = idProducto;
        this.cantidadUnidades = cantidadUnidades;
        this.nombre = nombre;
    }


    public void setCantidadUnidades(int cantidad) {
        if(cantidad > 0) {
            this.cantidadUnidades = cantidad;
        }
    }

    public int getIdProductoPedido() {
        return this.idProducto;
    }

    public int getCantidadUnidadesProducto() {
        return  this.cantidadUnidades;
    }

    public void mostrarDatosProductoPedido() {
        System.out.println("Id del producto: " + this.idProducto);
        System.out.println("Cantidad pedida: " + this.cantidadUnidades);
        System.out.println("Nombre del producto: " + this.nombre);
    }
}
