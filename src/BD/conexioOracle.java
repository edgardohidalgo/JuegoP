
package BD;

import java.sql.Connection;
import java.sql.DriverManager;

public class conexioOracle {
    private Connection conn = null;
    private String url ,user,pass;
    public conexioOracle() {
        conectar();
    }
    private void conectar() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            url = "jdbc:oracle:thin:@//oracle.ilerna.com:1521/XEPDB2";
            user = "DM2425_PIN_GRUP07";
            pass = "AAHRT07";
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Se conecto con la base de datos" );;
        } catch (Exception e) {
            System.out.println("Error al desconectar con la base de datos " + e);;
        }
    }
    public void desconectar() {
        try{
            conn.close();
        }catch (Exception e) {
            System.out.println("Error al desconectar con la base de datos" + e);;
        }

    }
    public Connection getConn(){
        return this.conn ;
    }
}
