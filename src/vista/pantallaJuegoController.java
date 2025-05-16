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
        if (jugador.getPosicion() == 49) {
            mostrarMensajeVictoria();
        } else {
            // Actualizar el texto del evento y efectos visuales basados en el tipo de casilla
            actualizarEfectosCasilla(casillaActual);
        }
        actualizarTextoInventario();

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
            eventos.setText("¡Cayó en un agujero!\n Retrocede una casilla.\n (Posición actual: " + jugador.getPosicion() + ")");

        } else if (casilla instanceof CasillaEvento) {
            System.out.println("Inventario actual: " + inventario.getLista().size() + " items");
            eventos.setText("¡Evento especial activado!\n (Posición actual: " + jugador.getPosicion() + ")");

        } else if (casilla instanceof CasillaOso) {
            if (jugador.getPosicion() == 0) {
                eventos.setText("¡Un oso ha aparecido!\n No tenías peces y has vuelto al inicio.\n (Posición actual: " + jugador.getPosicion() + ")");
            } else {
                eventos.setText("¡Un oso ha aparecido!\n Has usado un pez para escapar.\n (Posición actual: " + jugador.getPosicion() + ")");
            }

        } else if (casilla instanceof CasillaNormal) {
            eventos.setText("Casilla Normal.\n (Posición actual: " + jugador.getPosicion() + ")");

        } else if (casilla instanceof CasillaTrineo) {
            eventos.setText("¡El jugador ha caído en un trineo y\n avanza más rápido!\n(Posición actual: " + jugador.getPosicion() + ")");
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

        System.out.println("Inventario actualizado - Peces: " + peces + ", Nieve: " + nieve +
                ", Rápido: " + rapido + ", Lento: " + lento);
    }

    @FXML
    private void handleRapido() {
        // Verificar si hay dados rápidos disponibles en el inventario
        Item dadoRapido = buscarItemEnInventario("dado_rapido");

        if (dadoRapido != null && dadoRapido.getCantidad() > 0) {
            // Descontar un dado rápido del inventario
            dadoRapido.setCantidad(dadoRapido.getCantidad() - 1);

            // Lanzar el dado rápido
            Random rand = new Random();
            int diceResult = rand.nextInt(6) + 5;  // Valores entre 5 y 10
            dadoResultText.setText("Ha salido: " + diceResult);

            // Actualizar la interfaz gráfica del inventario
            actualizarTextoInventario();

            // Mover al jugador con el número de pasos que salió en el dado
            moverJugador(diceResult);
        } else {
            // No hay dados rápidos disponibles
            eventos.setText("No tienes dados rápidos disponibles");
        }
    }

    @FXML
    private void handleLento() {
        // Verificar si hay dados lentos disponibles en el inventario
        Item dadoLento = buscarItemEnInventario("dado_lento");

        if (dadoLento != null && dadoLento.getCantidad() > 0) {
            // Descontar un dado lento del inventario
            dadoLento.setCantidad(dadoLento.getCantidad() - 1);

            // Lanzar el dado lento
            Random rand = new Random();
            int diceResult = rand.nextInt(4);  // Valores entre 0 y 3
            dadoResultText.setText("Ha salido: " + diceResult);

            // Actualizar la interfaz gráfica del inventario
            actualizarTextoInventario();

            // Mover al jugador con el número de pasos que salió en el dado
            moverJugador(diceResult);
        } else {
            // No hay dados lentos disponibles
            eventos.setText("No tienes dados lentos disponibles");
        }
    }

    // Método auxiliar para buscar un item específico en el inventario
    private Item buscarItemEnInventario(String nombreItem) {
        for (Item item : inventario.getLista()) {
            if (item.getNombre().equalsIgnoreCase(nombreItem)) {
                return item;
            }
        }
        return null;
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

    private void mostrarMensajeVictoria() {
        // Cambiar el texto del evento para mostrar mensaje de victoria
        eventos.setText("¡FELICIDADES! ¡Has llegado al final del tablero y has ganado el juego!");
        eventos.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-fill: green;");

        // Desactivar el botón del dado para evitar seguir jugando
        dado.setDisable(true);
        rapido.setDisable(true);
        lento.setDisable(true);
        peces.setDisable(true);
        nieve.setDisable(true);

        // También podríamos mostrar un diálogo de victoria
        Alert alertaVictoria = new Alert(Alert.AlertType.INFORMATION);
        alertaVictoria.setTitle("¡Victoria!");
        alertaVictoria.setHeaderText("¡Has ganado!");
        alertaVictoria.setContentText("Has llegado al final del tablero. ¡Felicidades por completar el juego!");
        alertaVictoria.show();
    }
    @FXML
    private void handleNewGame() {
        System.out.println("New Game clicked");

        // Reiniciar el juego llamando al método initialize
        initialize();

        // Habilitar todos los botones que pudieran estar deshabilitados
        dado.setDisable(false);
        rapido.setDisable(false);
        lento.setDisable(false);
        peces.setDisable(false);
        nieve.setDisable(false);

        // Restablecer el estilo del texto de eventos a su formato original
        eventos.setStyle("");

        // Mantener la estructura del texto del dado pero sin valor
        dadoResultText.setText("Ha salido: -");
        // Alternativa: dadoResultText.setText("Tira el dado para comenzar");

        System.out.println("Nueva partida iniciada");
    }

    @FXML
    private void handleSaveGame() {
        System.out.println("Save Game clicked");
        initialize();
    }

    @FXML
    private void handleLoadGame() {
        System.out.println("Load Game clicked");
        // TODO
    }

    @FXML
    private void handleQuitGame() {
        System.out.println("Quit Game clicked");
        System.exit(0);
    }
}
