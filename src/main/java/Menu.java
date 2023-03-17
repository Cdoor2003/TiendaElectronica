import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public void mostrarMenu(){
        System.out.println("1) Mostrar productos.");
        System.out.println("2) Buscar producto por su nombre.");
        System.out.println("3) Buscar producto por su categoría.");
        System.out.println("4) Modificar producto.");
        System.out.println("5) Eliminar un producto.");
        System.out.println("6) Realizar una compra.");
        System.out.println("7) Agregar producto.");
        System.out.println("Seleccione una de las opciones anteriores.");
    }
    public void salirMenu(){
        System.out.println("\n Ha salido del menu");
    }
    public void opcionesMenu(){
        Scanner teclado = new Scanner(System.in);
        Tienda tienda = new Tienda("Tienda Electronica");
        int opcion = 0;
        do {
            try {
                mostrarMenu();
                opcion = teclado.nextInt();
                if(opcion < 1 || opcion > 7){
                    teclado.nextLine();
                    System.err.println("Opción ingresada no valida, por favor intente de nuevo");
                }
                switch (opcion){
                    case 1: {
                        tienda.mostrarProductos();
                        break;
                    }
                    case 2:{
                        System.out.println("Ingrese el nombre del producto que desea buscar: ");
                        String nombre = teclado.nextLine();
                        tienda.buscarProductosNombre(nombre);
                    }
                    case 3:{
                        System.out.println("Ingrese el categoría del producto que desea buscar: ");
                        String categoria = teclado.nextLine();
                        tienda.buscarProductosCategoria(categoria);

                    }
                    case 4:{
                        tienda.modificarProducto();
                    }
                    case 5:{
                        System.out.println("Ingrese el nombre del producto que sea eliminar.");
                        String nombre = teclado.nextLine();
                        tienda.eliminarProducto(nombre);
                    }
                    case 6:{
                        System.out.println("Ingrese el nombre del producto que desee comprar: ");
                        String nombre = teclado.nextLine();
                        System.out.println("\n Ingrese la cantidad que desea de este producto: ");
                        int cantidad = teclado.nextInt();
                        double boleta = tienda.realizarCompra(nombre,cantidad);
                        System.out.println("El valor de su compra es: "+boleta);
                    }
                    case 7:{
                        System.out.println("Ingrese el nombre del producto: ");
                        String nombre = teclado.nextLine();
                        System.out.println("Ingrese el precio del producto: ");
                        int precio = teclado.nextInt();
                        System.out.println("Escriba la descripción del producto: ");
                        String descripción = teclado.nextLine();
                        System.out.println("Ingrese el stock disponible: ");
                        int stock = teclado.nextInt();
                        System.out.println("Ingrese la categoría del producto: ");
                        String categoria = teclado.nextLine();
                        Producto producto = new Producto(nombre,stock,precio,descripción,categoria);
                        tienda.agregarProducto(producto);
                    }
                    }
            } catch (InputMismatchException e) {
                teclado.nextLine();
                System.err.println("Opción ingresada no valida, por favor intente de nuevo");
            }
        }while (opcion != 7) ;
        salirMenu();
    }
}
