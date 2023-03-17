import java.util.*;

public class Tienda {
    private String nombreTienda;
    public ArrayList<Producto> listaProductos = new ArrayList<>();

    public Tienda(String nombreTienda) {
        this.nombreTienda = nombreTienda;
    }

    public void mostrarProductos(){
        ArrayList<Producto> listaDeProductos = new ArrayList<>();
        listaDeProductos = GestorDatos.leerArchivoProductos();
        for(Producto producto : listaDeProductos){
            System.out.println("Producto : "+producto.getNombre()+"\nDescripción: "+producto.getDescripcion()+"\nCantidad : "+producto.getStock()+"\nPrecio : "+producto.getPrecio()+"$ "+"\nCategoría: "+producto.getCategoria());
        }
    }

    public void buscarProductosNombre(String nombre){
        Producto producto = null;
        ArrayList<Producto> listaDeProductos = new ArrayList<>();
        listaDeProductos = GestorDatos.leerArchivoProductos();
        for(Producto producto1 : listaDeProductos){
            if(producto1.getNombre().equals(nombre)){
                producto = producto1;
            }
        }
        if(producto != null){
            System.out.println("Producto : "+producto.getNombre()+"\nDescripción: "+producto.getDescripcion()+"\nCantidad : "+producto.getStock()+"\nPrecio : "+producto.getPrecio()+"$ "+"\nCategoría: "+producto.getCategoria());
        }
        else{
            System.out.println("El producto no ha sido encontrado.");
        }
    }

    public void buscarProductosCategoria(String categoria){
        Producto producto = null;
        ArrayList<Producto> listaDeProductos = new ArrayList<>();
        listaDeProductos = GestorDatos.leerArchivoProductos();
        for(Producto producto1 : listaDeProductos){
            if(producto1.getCategoria().equals(categoria)){
                producto = producto1;
            }
        }
        if(producto != null){
            System.out.println("Producto : "+producto.getNombre()+"\nDescripción: "+producto.getDescripcion()+"\nCantidad : "+producto.getStock()+"\nPrecio : "+producto.getPrecio()+"$ "+"\nCategoría: "+producto.getCategoria());
        }
        else{
            System.out.println("El producto no ha sido encontrado.");
        }
    }

    public void agregarProducto(Producto producto){
        ArrayList<Producto> listaDeProductos = new ArrayList<>();
        listaDeProductos = GestorDatos.leerArchivoProductos();
        String nombre = producto.getNombre();
        if(buscarProducto(nombre,listaDeProductos) == null){
            listaDeProductos.add(producto);
            GestorDatos.registrarDatos(listaDeProductos,"Productos.txt");
            System.out.println("\n Se ha agregado un producto al inventario");
        }
        else {
            System.out.println("\n El producto ya existe en el inventario");
        }
    }

    public void eliminarProducto(String nombre){
        ArrayList<Producto> listaDeProductos = new ArrayList<>();
        listaDeProductos = GestorDatos.leerArchivoProductos();
        Producto producto1 = buscarProducto(nombre,listaDeProductos);
        for(Producto producto : listaDeProductos){
            if(producto.getNombre().equals(nombre)){
                listaDeProductos.remove(producto1);
                GestorDatos.registrarDatos(listaDeProductos,"Productos.txt");
                System.out.println("\n El producto ha sido eliminado correctamente.");
            }
        }
    }

    public double realizarCompra(String nombre, int cantidad){
        ArrayList<Producto> listaDeProductos = new ArrayList<>();
        listaDeProductos = GestorDatos.leerArchivoProductos();
        Producto producto = buscarProducto(nombre,listaDeProductos);
        double vendido = 0;
        if(producto == null){
            vendido = -1;
            System.out.println("El producto no existe.");
        } else if (producto.vendido(cantidad)) {
            vendido = cantidad * producto.getPrecio();
            if(producto.getStock() == 0){
                listaDeProductos.remove(nombre);
            }
        }
        return vendido;
    }

    public void modificarProducto(){
        ArrayList<Producto> listaDeProductos = new ArrayList<>();
        listaDeProductos = GestorDatos.leerArchivoProductos();
        Scanner teclado = new Scanner(System.in);
        System.out.println("Ingrese el nombre del producto que desea modificar: ");
        String nombre = teclado.nextLine();
        Producto producto = buscarProducto(nombre,listaDeProductos);
        int opcion = 0;
        do {
            try {
                opcion = menuParaModificar();
                opcion = teclado.nextInt();
                if(opcion < 1 || opcion > 5){
                    teclado.nextLine();
                    System.err.println("Opción ingresada no valida, por favor intente de nuevo");
                }
                switch (opcion){
                    case 1:{
                        System.out.println("Ingrese el nuevo nombre del producto: ");
                        String nombreNuevo = teclado.nextLine();
                        producto.setNombre(nombreNuevo);
                        System.out.println("El nombre ha sido cambiado correctamente.");
                    }
                    case 2:{
                        System.out.println("Ingrese la nueva descripción del producto: ");
                        String descripcionNueva = teclado.nextLine();
                        producto.setDescripcion(descripcionNueva);
                        System.out.println("La descripción del producto ha sido cambiada correctamente.");
                    }
                    case 3:{
                        System.out.println("Ingrese el nuevo precio del producto: ");
                        int precioNuevo = teclado.nextInt();
                        producto.setPrecio(precioNuevo);
                        System.out.println("El precio fue cambiado correctamente.");
                    }
                    case 4:{
                        System.out.println("Ingrese el nuevo stock disponible del producto: ");
                        int stockNuevo = teclado.nextInt();
                        producto.setStock(stockNuevo);
                        System.out.println("El stock ha sido cambiado correctamente.");
                    }
                    case 5:{
                        System.out.println("Ingrese la nueva categoría del producto: ");
                        String categoriaNueva = teclado.nextLine();
                        producto.setCategoria(categoriaNueva);
                        System.out.println("La categoría ha sido cambiada correctamente.");
                    }
                }
            } catch (InputMismatchException e) {
                teclado.nextLine();
                System.err.println("Opción ingresada no valida, por favor intente de nuevo.");
            }
        }while (opcion != 5) ;
        salirMenu();
    }
    public void salirMenu(){
        System.out.println("\n Ha salido del menu");
    }

    public int menuParaModificar(){
        Scanner teclado = new Scanner(System.in);
        System.out.println("1) Nombre");
        System.out.println("2) Descripción");
        System.out.println("3) Precio");
        System.out.println("4) Stock");
        System.out.println("5) Categoría");
        System.out.println("¿Que desa modificar?");
        int opcion = teclado.nextInt();
        return opcion;
    }
    public Producto buscarProducto(String nombre, ArrayList<Producto> listaDeProductos) {
        for(Producto producto : listaDeProductos){
            if(producto.getNombre().equals(nombre)){
                return producto;
            }
        }
        return null;
    }

    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(ArrayList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }
}
