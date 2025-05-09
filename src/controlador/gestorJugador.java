package controlador;

import modelo.*;

public class gestorJugador {

    public void jugadorUsaItem(Pinguino p) {
        if (!p.getInventario().getLista().isEmpty()) {
            p.usarObjeto();
            System.out.println("Jugador " + p.getNombre() + " ha usado un objeto.");
        } else {
            System.out.println("El inventario está vacío.");
        }
    }

    public static void jugadorSemueve(Jugador j, int pasos, Tablero tablero) {
        int nuevaPos = Math.min(j.getPosicion() + pasos, 49);
        j.setPosicion(nuevaPos);
        System.out.println("Jugador " + j.getNombre() + " se mueve a la posición " + nuevaPos);

        // Ejecutar acción de la nueva casilla si es un Pinguino
        Casilla casilla = tablero.getCasillas().get(nuevaPos);
        if (j instanceof Pinguino) {
            casilla.realizarAccion(j);
        }
    }

    public void jugadorFinalizaTurno(Jugador j) {
        System.out.println("Turno finalizado para el jugador " + j.getNombre());
        // Aquí puedes añadir lógica para actualizar estadísticas, guardar log, etc.
    }

    public void pinguinoEvento(Pinguino p) {
        // Esta función puede usarse para aplicar efectos de evento activos
        System.out.println("Aplicando efectos de evento a " + p.getNombre());
        // Se puede extender más adelante
    }
}
