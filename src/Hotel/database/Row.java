package Hotel.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Row {
    public void setData(ResultSet resultSet) throws SQLException;
}
