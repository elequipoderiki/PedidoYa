package com.techlab.inicio;

import com.techlab.pedido.LineaPedido;
import com.techlab.pedido.Pedido;
import com.techlab.productos.Bebida;
import com.techlab.productos.Comida;
import com.techlab.productos.Producto;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        ArrayList<Producto> productos = new ArrayList<>();
        ArrayList<Pedido> pedidos = new ArrayList<>();

        Producto jabon = new Producto("jabon", 20, 4);
        Producto arrozIntegral = new Comida("arroz integral", 40, 4, "2/4/2028");
        Producto azucar = new Comida("Azucar", 90, 9, "3/2/2030");
        Producto arrozLargo = new Comida("Arroz largo", 50, 2, "5/9/2039");
        Producto lavandina = new Bebida("Lavandina" , 20, 5, 1);
        productos.add(jabon);
        productos.add(arrozIntegral);
        productos.add(arrozLargo);
        productos.add(azucar);
        productos.add(lavandina);

        System.out.println("Bienvenido a PedidoYa: ");
        boolean salir = false;
        while(!salir) {
            System.out.println("===============================================");
            System.out.println("Opciones: ");
            System.out.println("1 - Agregar Producto");
            System.out.println("2 - Listar Productos");
            System.out.println("3 - Buscar/Actualizar producto");
            System.out.println("4 - Eliminar Producto");
            System.out.println("5 - Crear pedido");
            System.out.println("6 - Listar pedidos");
            System.out.println("7 - Salir");
            System.out.println("Ingrese número de opción: ");
            try {
                Scanner sc = new Scanner(System.in);
                int valorIngresado = sc.nextInt();
                switch (valorIngresado) {
                    case 1:
                        agregarProducto(productos);
                        break;
                    case 2:
                        listarProductos(productos);
                        break;
                    case 3:
                        buscarYActualizarProducto(productos);
                        break;
                    case 4:
                        eliminarProducto(productos);
                        break;
                    case 5:
                        crearPedido(pedidos, productos);
                        //System.out.println("Pedido creado");
                        break;
                    case 6:
                        //System.out.println("pedidos listados");
                        listarPedidos(pedidos);
                        break;
                    case 7:
                        System.out.println("Saliendo del sistema");
                        salir = true;
                        break;
                    default:
                        System.out.println("Valor incorrecto. Elija opción de la lista.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Chequee que el ingreso por teclado sea el requerido (numerico o texto)");
            } catch(Exception e) {
                //e.printStackTrace();
                System.out.println(e.getMessage());
            }


        }
    }

    public static void listarPedidos(ArrayList<Pedido> pedidos){
        int i = 0;
        System.out.println("===============================================");
        System.out.println("Listando pedidos");
        for(Pedido pedido : pedidos) {
            System.out.println("===============================================");
            System.out.println("############## Pedido " + i + " ################") ;
            //pedido.listarProductos();
            for(LineaPedido datosProducto: pedido.obtenerItemsPedido()) {
                System.out.println("===============================================");
                datosProducto.mostrarDatosProductoPedido();
            }
            System.out.println("===============================================");
            System.out.println("El costo del pedido es: " + pedido.getPrecioTotal());
            i++;
        }
    }

    public static void crearPedido(ArrayList<Pedido> pedidos, ArrayList<Producto> productos) {
        Scanner sc = new Scanner(System.in);
        Pedido pedido = new Pedido();
        System.out.println("Agregue productos al pedido");

        while(true) {
            System.out.println("Ingrese id de producto: ");
            int idProducto = sc.nextInt();
            System.out.println("Ingrese cantidad en unidades: ");
            int cantidad = sc.nextInt();

            if (cantidad <= 0) {
                System.out.println("Ingrese cantidad positiva");
                continue;
            }
            boolean productoEncontrado = false;

            for (Producto producto : productos) {
                if (producto.getId() == idProducto) {
                    productoEncontrado = true;
                    if(producto.getStock() >= cantidad)
                    {
                        LineaPedido item = new LineaPedido(cantidad, idProducto, producto.getNombre());
                        double costo = cantidad * producto.getPrecio();
                        pedido.agregarAlPedido(item, costo);
                        int stockRestante = producto.getStock() - cantidad;
                        producto.setStock(stockRestante);
                        System.out.printf("El producto %s por cantidad %s fue agregado al pedido \n", producto.getNombre(), cantidad );
                    } else {
                        System.out.println("No hay stock suficiente. Disminuya la cantidad de unidades pedidas.");
                    }
                    break;
                }
            }
            if(!productoEncontrado)
                System.out.println("Producto no encontrado");
            System.out.println("Agregar más productos?");
            System.out.println("1 - Si");
            System.out.println("2 - No");
            int opcion = sc.nextInt();
            if (opcion != 1)
                break;
        }

        if (!pedido.obtenerItemsPedido().isEmpty()) {
            pedidos.add(pedido);
            System.out.println("Pedido creado con costo de: $" + pedido.getPrecioTotal());
        }

    }

    public static void eliminarProducto(ArrayList<Producto> productos ) {
        System.out.println("Ingrese ID del producto a eliminar ");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        boolean productoEncontrado = false;
        Producto productoARemover = null;
        for(Producto producto : productos) {
            if (producto.getId() == id) {
                productoARemover = producto;
                productoEncontrado = true;
                break;
            }
        }

        if (productoEncontrado) {
            System.out.println("El producto a eliminar es: ");
            productoARemover.mostrarProducto();
            System.out.println("Confirme la eliminación");
            System.out.println("1 - Eliminar");
            System.out.println("2 - Cancelar");
            Scanner sc2 = new Scanner(System.in);
            int opcion = sc2.nextInt();
            if (opcion == 1 ) {
                productos.remove(productoARemover);
                System.out.println("Producto eliminado");

            } else if (opcion == 2) {
                System.out.println("Operación cancelada");
            } else {
                System.out.println("Opcion invalida. Volviendo al menu.");
            }
        } else {
            System.out.println("Producto no encontrado");
        }
    }

    public static void buscarYActualizarProducto(ArrayList<Producto> productos) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Seleccione opción de búsqueda ");
        System.out.println("1 - Buscar por nombre de producto");
        System.out.println("2 - Buscar por id de producto");

        int opcion =  sc.nextInt();

        ArrayList<Producto> productosBuscados = new ArrayList<>();

        if (opcion == 1) {
            System.out.println("Ingrese nombre del producto a buscar ");
            Scanner sc2 = new Scanner(System.in);

            String nombre = sc2.nextLine();

            for(Producto producto : productos) {
                if(producto.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                    productosBuscados.add(producto);
                }
            }
        } else if (opcion == 2) {
            System.out.println("Ingrese id del producto a buscar ");
            Scanner sc2 = new Scanner(System.in);

            int id = sc2.nextInt();

            for (Producto producto : productos) {
                if (producto.getId() == id) {
                    productosBuscados.add(producto);
                    break;
                }
            }
        } else {
            System.out.println("Opcion incorrecta. Volviendo al menu.");
        }

        if ( !productosBuscados.isEmpty()) {
            System.out.println("Desea actualizar el producto?");
            System.out.println("1 - si");
            System.out.println("2 - no");
            Scanner sc3 = new Scanner(System.in);
            int opcion3 = sc3.nextInt();
            if (opcion3 == 1) {
                for(Producto producto : productosBuscados) {
                    System.out.printf("Ingrese nuevo nombre del producto %s: \n", producto.getNombre());
                    Scanner sc4 = new Scanner(System.in);
                    String nombre = sc4.nextLine();
                    producto.setNombre(nombre);
                    System.out.println("Ingrese nuevo precio: ");
                    double precio = sc4.nextDouble();
                    producto.setPrecio(precio);
                    System.out.println("Ingrese nuevo stock: ");
                    int stock = sc4.nextInt();
                    producto.setStock(stock);
                    System.out.println("Producto actualizado");
                }

            } else if (opcion3 == 2) {
                for (Producto producto : productosBuscados) {
                    System.out.println("===============================================");
                    System.out.println("producto encontrado es: ");
                    System.out.println("===============================================");
                    producto.mostrarProducto();
                }

            } else {
                System.out.println("Opcion incorrecta. Volviendo al menu");
            }
        } else {
            System.out.println("Producto no encontrado");
        }

    }

    public static void agregarProducto(ArrayList<Producto> productos) {
        System.out.println("Ingrese nombre del producto: ");
        Scanner sc = new Scanner(System.in);
        String nombre = sc.nextLine();
        System.out.println("Ingrese precio: ");
        double precio = sc.nextDouble();
        System.out.println("Ingrese stock: ");
        int stock = sc.nextInt();

        Producto producto = new Producto(nombre, precio, stock);

        System.out.println("El producto ingresado es ");
        producto.mostrarProducto();
        productos.add(producto);

        System.out.println("Producto Agregado");

    }

    public static void listarProductos(ArrayList<Producto> productos) {
        if (productos.isEmpty()) {
            System.out.println("No tiene productos que mostrar");
        } else {
            System.out.println("Listando productos: ");
            productos.forEach(producto -> {
                System.out.println("===============================================");
                producto.mostrarProducto();
            });
        }

    }
}