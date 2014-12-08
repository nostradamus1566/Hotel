package Hotel.database;

import java.sql.Connection;

public abstract class Table {
    private final Connection connection;

    public Table(Connection conn) {
        this.connection = conn;
    }

    public Connection getConnection() {
        return this.connection;
    }

    public abstract void setup();
}
