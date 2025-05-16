package modelo;

import java.util.ArrayList;

/**
 * Casilla que representa un oso. Envía al pingüino al inicio del tablero,
 * a menos que tenga un pez en el inventario.
 */
public class CasillaOso extends Casilla {

    /**
     * Constructor de CasillaOso.
     *
     * @param idCasilla          ID de la casilla.
     * @param posicion           Posición de la casilla.
     * @param jugadoresActuales  Lista de jugadores actuales.
     */
    public CasillaOso(int idCasilla, int posicion, ArrayList<Jugador> jugadoresActuales) {
        super(idCasilla, posicion, jugadoresActuales);
    }

    /**
     * Revisa si el jugador es un pingüino y si tiene un pez en su inventario.
     * Si tiene un pez, lo consume y no es devuelto al inicio.
     * De lo contrario, lo devuelve al inicio del tablero.
     *
     * @param jugador Jugador afectado.
     * @return La posición actual si tiene un pez o no es pingüino, 0 si es devuelto al inicio.
     */
    @Override
    public int realizarAccion(Jugador jugador) {
        if (jugador instanceof Pinguino) {
            Pinguino pinguino = (Pinguino) jugador;
            Inventario inventario = pinguino.getInventario();

            // Verificar si tiene un pez en el inventario
            Item pez = buscarItemEnInventario(inventario, "pez");

            if (pez != null && pez.getCantidad() > 0) {
                // Tiene un pez, lo consume y no es devuelto al inicio
                pez.setCantidad(pez.getCantidad() - 1);

                // Si después de restar queda en 0, podríamos querer eliminar el item
                if (pez.getCantidad() == 0) {
                    // Buscar el índice del pez en la lista para eliminarlo
                    int indicePez = -1;
                    for (int i = 0; i < inventario.getLista().size(); i++) {
                        if (inventario.getLista().get(i).getNombre().equalsIgnoreCase("pez")) {
                            indicePez = i;
                            break;
                        }
                    }

                    if (indicePez != -1) {
                        inventario.eliminarItem(indicePez);
                    }
                }

                System.out.println("El pingüino usó un pez para escapar del oso!");
                return jugador.getPosicion(); // Mantiene su posición actual
            } else {
                // No tiene pez, es devuelto al inicio
                jugador.setPosicion(0);
                return 0;
            }
        }
        return jugador.getPosicion(); // Si no es pingüino, mantiene su posición
    }

    /**
     * Método auxiliar para buscar un item específico en el inventario
     * @param inventario El inventario donde buscar
     * @param nombreItem El nombre del item a buscar
     * @return El item si se encuentra, null en caso contrario
     */
    private Item buscarItemEnInventario(Inventario inventario, String nombreItem) {
        for (Item item : inventario.getLista()) {
            if (item.getNombre().equalsIgnoreCase(nombreItem)) {
                return item;
            }
        }
        return null;
    }
}