package Hotel;

import Hotel.database.Connection;

import java.sql.SQLException;

public class Main {

    public static void main(String ... args) {
        Connection conn;
        try {
            conn = new Connection();
            Home home = new Home(conn);
            home.show();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
