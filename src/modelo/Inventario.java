package modelo;

import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private List<Item> lista = new ArrayList<>();
    private Runnable callback; // Esto se ejecutará cada vez que cambie el inventario

    public void setCallback(Runnable callback) {
        this.callback = callback;
    }

    public void agregarItem(Item nuevoItem) {
        boolean itemExistente = false;

        for (Item item : lista) {
            if (item.getNombre().equalsIgnoreCase(nuevoItem.getNombre())) {
                item.setCantidad(item.getCantidad() + nuevoItem.getCantidad());
                itemExistente = true;
                break;
            }
        }

        if (!itemExistente) {
            lista.add(nuevoItem);
        }

        // Ejecutar el callback para actualizar la UI
        if (callback != null) {
            callback.run();
        }
    }

    public void eliminarItem(int index) {
        if (index >= 0 && index < lista.size()) {
            lista.remove(index);

            // Ejecutar el callback para actualizar la UI
            if (callback != null) {
                callback.run();
            }
        }
    }

    public List<Item> getLista() {
        return lista;
    }
}