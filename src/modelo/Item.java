package modelo;

public class Item {
    private String nombre;

    private int cantidad ;

    // Constructor
    public Item(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad ;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    // objetos que tienen
    
}