package vista;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import modelo.*;

import java.util.ArrayList;
import java.util.Random;

public class pantallaJuegoController {

    // FXML elementos
    @FXML private MenuItem newGame;
    @FXML private MenuItem saveGame;
    @FXML private MenuItem loadGame;
    @FXML private MenuItem quitGame;

    @FXML private Button dado;
    @FXML private Button rapido;
    @FXML private Button lento;
    @FXML private Button peces;
    @FXML private Button nieve;

    @FXML private Text dadoResultText;
    @FXML private Text rapido_t;
    @FXML private Text lento_t;
    @FXML private Text peces_t;
    @FXML private Text nieve_t;
    @FXML private Text eventos;

    @FXML private GridPane tablero;
    @FXML private Circle P1;
    @FXML private Circle P2;
    @FXML private Circle P3;
    @FXML private Circle P4;

    private final int COLUMNS = 5;  // Definimos las columnas del tablero

    // MODELO
    private Tablero tableroModelo;
    private Jugador jugador;
    private Inventario inventario;

    @FXML
    private void initialize() {
        eventos.setText("¡El juego ha comenzado!");

        // Inicializamos el inventario para el pingüino
        inventario = new Inventario();

        // Establecer el callback para actualizar la UI cuando cambie el inventario
        inventario.setCallback(() -> {
            actualizarTextoInventario();
            System.out.println("Callback ejecutado: Inventario actualizado");
        });

        jugador = new Pinguino(1, "Jugador 1", 0, "blue", inventario);

        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(jugador);

        tableroModelo = new Tablero(1, new ArrayList<>(), jugadores, 0, jugador);
        tableroModelo.inicializarCasillas();  // Inicializa las casillas del tablero
        System.out.println("Tablero y casillas inicializados.");

        // Posicionar inicialmente el jugador en la casilla de inicio
        actualizarPosicionVisual();

        // Asegurarse de que la UI muestre correctamente el inventario inicial
        actualizarTextoInventario();
    }

    @FXML
    private void handleDado(ActionEvent event) {
        Random rand = new Random();
        int diceResult = rand.nextInt(6) + 1;  // Simulamos el lanzamiento del dado
        dadoResultText.setText("Ha salido: " + diceResult);

        // Movemos al jugador con el número de pasos que salió en el dado
        moverJugador(diceResult);
    }

    // Método para mover al jugador y actualizar la UI
    private void moverJugador(int pasos) {
        int posicionActual = jugador.getPosicion();
        int nuevaPosicion = posicionActual + pasos;

        // Limitar la posición a las 50 casillas del tablero
        if (nuevaPosicion >= 50) {
            nuevaPosicion = 49;  // La posición máxima es 49 (la última casilla)
        }

        // Actualizar la posición del jugador en el modelo
        jugador.setPosicion(nuevaPosicion);

        // Actualizar la posición visual antes de la acción
        actualizarPosicionVisual();

        System.out.println("Jugador ahora en posición " + nuevaPosicion);

        // Ejecutar la acción de la casilla
        Casilla casillaActual = tableroModelo.getCasillas().get(nuevaPosicion);

        // Limpiar y añadir el jugador a la casilla actual
        casillaActual.getJugadoresActuales().clear();
        casillaActual.getJugadoresActuales().add(jugador);

        // Guardar la posición actual antes de realizar la acción
        int posAntes = jugador.getPosicion();

        // Realizar la acción de la casilla y obtener la nueva posición
        int posicionDespuesDeAccion = casillaActual.realizarAccion(jugador);

        // Verificar si la posición cambió después de la acción
        if (posicionDespuesDeAccion != posAntes) {
            System.out.println("La posición cambió de " + posAntes + " a " + posicionDespuesDeAccion + " después de la acción.");
            // Actualizar la posición visual después del cambio
            actualizarPosicionVisual();
        }

        // Actualizar el texto del evento y efectos visuales basados en el tipo de casilla
        String tipoCasilla = casillaActual.getClass().getSimpleName();
        actualizarEfectosCasilla(casillaActual);

        // Asegurarse de actualizar el inventario después de cada acción
        actualizarTextoInventario();

        if (jugador instanceof Pinguino) {
            Pinguino p = (Pinguino) jugador;
            System.out.println("Inventario actual:");
            for (Item item : p.getInventario().getLista()) {
                System.out.println("- " + item.getNombre() + " (x" + item.getCantidad() + ")");
            }
        }

        System.out.println("Jugador finalmente en posición " + jugador.getPosicion() + " tras acción de casilla " + tipoCasilla);
    }

    // Método para actualizar la posición visual del jugador
    private void actualizarPosicionVisual() {
        int posicionActual = jugador.getPosicion();
        int fila = posicionActual / COLUMNS;
        int columna = posicionActual % COLUMNS;

        GridPane.setRowIndex(P1, fila);
        GridPane.setColumnIndex(P1, columna);

        System.out.println("Posición visual actualizada a fila " + fila + ", columna " + columna + " (posición " + posicionActual + ")");
    }

    // Método que actualiza los efectos visuales basados en la casilla
    private void actualizarEfectosCasilla(Casilla casilla) {
        if (casilla instanceof CasillaAgujero) {
            eventos.setText("¡Cayó en un agujero! Retrocede una casilla. (Posición actual: " + jugador.getPosicion() + ")");

        } else if (casilla instanceof CasillaEvento) {
            System.out.println("Inventario actual: " + inventario.getLista().size() + " items");
            eventos.setText("¡Evento especial activado! (Posición actual: " + jugador.getPosicion() + ")");

        } else if (casilla instanceof CasillaOso) {
            eventos.setText("¡Un oso ha aparecido! Vuelve al inicio del tablero." +
                    " (Posición actual: " + jugador.getPosicion() + ")");

        } else if (casilla instanceof CasillaSueloQuebradizo) {
            eventos.setText("¡Cuidado! El suelo está quebradizo. (Posición actual: " + jugador.getPosicion() + ")");

        } else if (casilla instanceof CasillaTrineo) {
            eventos.setText("¡El jugador ha caído en un trineo y avanza más rápido! (Posición actual: " + jugador.getPosicion() + ")");
        }
    }

    private void actualizarTextoInventario() {
        int peces = 0, nieve = 0, rapido = 0, lento = 0;

        for (Item item : inventario.getLista()) {
            String nombreItem = item.getNombre().toLowerCase();
            int cantidad = item.getCantidad();

            switch (nombreItem) {
                case "pez":
                    peces += cantidad;
                    break;
                case "bola":
                    nieve += cantidad;
                    break;
                case "dado_rapido":
                    rapido += cantidad;
                    break;
                case "dado_lento":
                    lento += cantidad;
                    break;
            }
        }

        peces_t.setText("Peces: " + peces);
        nieve_t.setText("Bolas de nieve: " + nieve);
        rapido_t.setText("Dado rápido: " + rapido);
        lento_t.setText("Dado lento: " + lento);

        System.out.println("UI actualizada - Peces: " + peces + ", Nieve: " + nieve +
                ", Rápido: " + rapido + ", Lento: " + lento);
    }

    @FXML
    private void handleRapido() {
        System.out.println("Fast.");
        // Implementar la lógica para usar el dado rápido
    }

    @FXML
    private void handleLento() {
        System.out.println("Slow.");
        // Implementar la lógica para usar el dado lento
    }

    @FXML
    private void handlePeces() {
        System.out.println("Fish.");
        // Implementar la lógica para usar los peces
    }

    @FXML
    private void handleNieve() {
        System.out.println("Snow.");
        // Implementar la lógica para usar las bolas de nieve
    }
}