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

    @FXML
    private void initialize() {
        eventos.setText("¡El juego ha comenzado!");

        // Inicializamos el tablero con casillas y jugador
        jugador = new Jugador(1, "Jugador 1", 0, null);  // Ejemplo de jugador
        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(jugador);

        tableroModelo = new Tablero(1, new ArrayList<>(), jugadores, 0, jugador);
        tableroModelo.inicializarCasillas();  // Inicializa las casillas del tablero
        System.out.println("Tablero y casillas inicializados.");
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

        // Actualizar posición del jugador en el modelo
        jugador.setPosicion(nuevaPosicion);

        // Mover visualmente el círculo del jugador en el tablero
        actualizarPosicionVisual(nuevaPosicion);

        System.out.println("Jugador ahora en posición " + nuevaPosicion);

        // Ejecutar la acción de la casilla y obtener la nueva posición después de la acción
        Casilla casillaActual = tableroModelo.getCasillas().get(nuevaPosicion);

        // Añadir el jugador a la casilla actual
        if (!casillaActual.getJugadoresActuales().contains(jugador)) {
            casillaActual.getJugadoresActuales().clear();
            casillaActual.getJugadoresActuales().add(jugador);
        }

        // Ejecutar la acción de la casilla y obtener la nueva posición
        int posicionDespuesDeAccion = casillaActual.realizarAccion(jugador);

        // Si la posición cambió después de la acción, actualizar visualmente al jugador
        if (posicionDespuesDeAccion != nuevaPosicion) {
            System.out.println("La posición del jugador cambió de " + nuevaPosicion +
                    " a " + posicionDespuesDeAccion + " después de la acción de la casilla");

            // Actualizar la posición visual después de la acción
            actualizarPosicionVisual(posicionDespuesDeAccion);

            // Si la posición cambió, asegurarnos de que el jugador esté asociado a la nueva casilla
            Casilla nuevaCasilla = tableroModelo.getCasillas().get(posicionDespuesDeAccion);

            // Eliminar al jugador de la casilla anterior
            casillaActual.eliminarJugador(jugador);

            // Añadir al jugador a la nueva casilla
            if (!nuevaCasilla.getJugadoresActuales().contains(jugador)) {
                nuevaCasilla.getJugadoresActuales().add(jugador);
            }

            // Actualizar texto de evento basado en la nueva casilla
            actualizarEfectosCasilla(nuevaCasilla);
        } else {
            // Si no cambió la posición, actualizar efectos de la casilla actual
            actualizarEfectosCasilla(casillaActual);
        }
    }

    // Método auxiliar para actualizar la posición visual del jugador
    private void actualizarPosicionVisual(int posicion) {
        int fila = posicion / COLUMNS;
        int columna = posicion % COLUMNS;
        GridPane.setRowIndex(P1, fila);
        GridPane.setColumnIndex(P1, columna);
    }

    // Método que actualiza los efectos visuales basados en la casilla
    private void actualizarEfectosCasilla(Casilla casilla) {
        String mensaje = "";

        if (casilla instanceof CasillaAgujero) {
            mensaje = "¡Cayó en un agujero! Retrocede una casilla.";
            P1.setStyle("-fx-fill: red;");  // Cambiar el color del jugador (ejemplo visual)

        } else if (casilla instanceof CasillaEvento) {
            mensaje = "¡Evento especial activado!";
            P1.setStyle("-fx-fill: green;");  // Cambiar el color del jugador

        } else if (casilla instanceof CasillaOso) {
            mensaje = "¡Un oso ha aparecido! El jugador vuelve al inicio.";
            P1.setStyle("-fx-fill: blue;");  // Cambiar el color del jugador

        } else if (casilla instanceof CasillaSueloQuebradizo) {
            mensaje = "¡Cuidado! El suelo está quebradizo.";
            P1.setStyle("-fx-fill: orange;");  // Cambiar el color del jugador

        } else if (casilla instanceof CasillaTrineo) {
            mensaje = "¡El jugador ha caído en un trineo y avanza más rápido!";
            P1.setStyle("-fx-fill: yellow;");  // Cambiar el color del jugador
        }

        // Añadir la posición actual para más claridad
        eventos.setText(mensaje + " (Casilla " + jugador.getPosicion() + ")");
    }

    // Los otros botones aún están por implementar
    @FXML
    private void handleRapido() {
        System.out.println("Fast.");
    }

    @FXML
    private void handleLento() {
        System.out.println("Slow.");
    }

    @FXML
    private void handlePeces() {
        System.out.println("Fish.");
    }

    @FXML
    private void handleNieve() {
        System.out.println("Snow.");
    }
}