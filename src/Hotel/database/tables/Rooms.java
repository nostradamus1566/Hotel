package Hotel.database.tables;

import Hotel.database.RoomType;
import Hotel.database.Table;
import Hotel.database.rows.Room;

import java.sql.*;

public class Rooms extends Table {
    public Rooms(Connection conn) {
        super(conn);
    }

    @Override
    public void setup() {
    	 try {
             Statement statement = this.getConnection().createStatement();

             statement.executeUpdate(
                 "CREATE TABLE IF NOT EXISTS room(" +
                     "id INTEGER PRIMARY KEY," +
                     "roomType INTEGER," +
                     "roomCost INTEGER," +
                     "status BOOLEAN" +
                 ")"
             );

             System.out.println("== Rooms (if any) ==");

             ResultSet r = statement.executeQuery("SELECT * FROM room");

             Integer rooms = 0;

             while (r.next()) {
                 rooms += 1;

                 Room row = new Room();
                 row.setData(r);
                 System.out.println(row);
             }

             // Do cleanup
             r.close();
             statement.close();

             if (rooms < 102) {
                 // Not enough rooms, get insertin'

                 int i;

                 for (i = 0; i < 2; i += 1) {
                     // Penthouse (400)
                     // Prefix: 500

                     this.upsert(
                             500 + i, RoomType.PENTHOUSE.getValue(), 400, false
                     );
                 }

                 for (i = 0; i < 20; i += 1) {
                     // Family (180)
                     // Prefix: 400

                     this.upsert(
                             400 + i, RoomType.FAMILY.getValue(), 180, false
                     );
                 }

                 for (i = 0; i < 50; i += 1) {
                     // Double (120)
                     // Prefix: 300

                     this.upsert(
                             300 + i, RoomType.DOUBLE.getValue(), 120, false
                     );
                 }

                 for (i = 0; i < 20; i += 1) {
                     // Twin (120)
                     // Prefix: 200

                     this.upsert(
                             200 + i, RoomType.TWIN.getValue(), 120, false
                     );
                 }

                 for (i = 0; i < 10; i += 1) {
                     // Single (90)
                     // Prefix: 100

                     this.upsert(
                             100 + i, RoomType.SINGLE.getValue(), 90, false
                     );
                 }

                 System.out.println("Completed rooms table.");
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
    }

    public Room getRoom(Integer id) {
        try {
            PreparedStatement statement = this.getConnection().prepareStatement(
                    "SELECT * FROM room WHERE id=? LIMIT 1"
            );

            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                Room customer = new Room();
                result.first();
                customer.setData(result);

                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    void upsert(
        Integer id,
        Integer roomType,
        Integer roomCost,
        boolean status
    ) {
        try {
            PreparedStatement statement = this.getConnection().prepareStatement(
                "INSERT INTO room (id, roomType, roomCost, status) " +
                        "VALUES (?, ?, ?, ?) " +
                        "ON DUPLICATE KEY UPDATE " +
                        "roomType=VALUES(roomType), roomCost=VALUES(roomCost), " +
                        "status=VALUES(status)",
                Statement.RETURN_GENERATED_KEYS
            );

            statement.setInt(1, id);
            statement.setInt(2, roomType);
            statement.setInt(3, roomCost);
            statement.setBoolean(4, status);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
