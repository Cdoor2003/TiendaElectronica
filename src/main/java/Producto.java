public class Producto {
    private String nombre;
    private int stock;
    private int precio;
    private String descripcion;
    private String categoria;

    public Producto(String nombre, int stock, int precio, String descripcion, String categoria) {
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
        this.descripcion = descripcion;
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public boolean vendido(int numero) {
        if(numero <= stock){
            numero -= stock;
        }
        else {
            System.out.println("No hay suficientes productos");
            return false;
        }
        return true;
    }
    public String toString(){
        return nombre+","+descripcion+","+precio+","+stock+","+categoria;
    }
}
