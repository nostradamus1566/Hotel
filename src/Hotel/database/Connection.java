package Hotel.database;

import Hotel.database.tables.Bookings;
import Hotel.database.tables.Customers;
import Hotel.database.tables.Rooms;

import java.sql.*;

public class Connection {
    private final java.sql.Connection connection;
    public static Table customer;
    public static Table booking;
    public static Table room;

    public Connection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");  // Load JDBC driver

        final String hostname = "localhost";
        final Integer port = 3306;
        final String username = "root";
        final String password = "fastralee";

        this.connection = DriverManager.getConnection(
                String.format("jdbc:mysql://%s:%s/", hostname, port),
                username, password
        );

        Statement statement = this.connection.createStatement();
        statement.execute("CREATE DATABASE IF NOT EXISTS hotel");
        statement.execute("USE hotel");

        Connection.booking = new Bookings(this.connection);
        Connection.booking.setup();

        Connection.customer = new Customers(this.connection);
        Connection.customer.setup();

        Connection.room = new Rooms(this.connection);
        Connection.room.setup();
    }
}
