package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un inventario de items para un jugador.
 * Permite agregar o eliminar items, y notifica cambios mediante un callback.
 */
public class Inventario {
    private List<Item> lista = new ArrayList<>();
    private Runnable callback; // Esto se ejecutará cada vez que cambie el inventario

    /**
     * Establece el callback que se ejecutará cuando el inventario cambie.
     * @param callback acción a ejecutar
     */
    public void setCallback(Runnable callback) {
        this.callback = callback;
    }

    /**
     * Agrega un nuevo item al inventario. Si ya existe, aumenta su cantidad.
     * Ejecuta el callback si se ha definido.
     * @param nuevoItem el item a agregar
     */
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

        if (callback != null) {
            callback.run();
        }
    }

    /**
     * Elimina un item del inventario según su índice.
     * Ejecuta el callback si se ha definido.
     * @param index posición del item a eliminar
     */
    public void eliminarItem(int index) {
        if (index >= 0 && index < lista.size()) {
            lista.remove(index);

            if (callback != null) {
                callback.run();
            }
        }
    }

    /**
     * Devuelve la lista de items actuales del inventario.
     * @return lista de items
     */
    public List<Item> getLista() {
        return lista;
    }
}
