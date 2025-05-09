package controlador;

import modelo.Tablero;

public class gestorPartida {
    private static String urlBBDD = "jdbc:oracle:thin:@//oracle.ilerna.com:1521/XEPDB2";
    private static String username = "DM2425_PIN_GRUP07";
    private static String password = "AAHRT07";

    public gestorPartida(String urlBBDD, String username, String password) {
        gestorPartida.urlBBDD=urlBBDD;
        gestorPartida.username=username;
        gestorPartida.password=password;
    }
    public String getUrlBBDD() {
        return urlBBDD;
    }
    public void setUrlBBDD(String linkBBDD) {
        gestorPartida.urlBBDD = linkBBDD;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        gestorPartida.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        gestorPartida.password = password;
    }

    public Tablero cargarPartida() {
        return null;
    }
    public void guardarPartida(Tablero t) {

    }

}
