package Hotel.database.tables;

import Hotel.database.Table;
import Hotel.database.rows.Customer;

import java.sql.*;

public class Customers extends Table {
    public Customers(Connection conn) {
        super(conn);
    }

    @Override
    public void setup() {
    	 try {
             Statement statement = this.getConnection().createStatement();

             statement.executeUpdate(
                 "CREATE TABLE IF NOT EXISTS customer(" +
                     "cid INTEGER PRIMARY KEY AUTO_INCREMENT," +
                     "fname VARCHAR(255),"+
                     "lname VARCHAR(255)" +
                 ")"
             );

             System.out.println("== Customers (if any) ==");

             ResultSet r = statement.executeQuery("SELECT * FROM customer");

             while (r.next()) {
                 Customer row = new Customer();
                 row.setData(r);
                 System.out.println(row);
             }

             // Do cleanup
             r.close();
             statement.close();
         } catch (SQLException e) {
             e.printStackTrace();
         }
    }

    public Customer getCustomer(Integer id) {
        try {
            PreparedStatement statement = this.getConnection().prepareStatement(
                    "SELECT * FROM customer WHERE cid=? LIMIT 1"
            );

            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                Customer customer = new Customer();
                result.first();
                customer.setData(result);
                
                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
