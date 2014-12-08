package Hotel.database.tables;

import Hotel.database.Table;
import Hotel.database.rows.Booking;
import Hotel.database.rows.Customer;
import Hotel.database.rows.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Bookings extends Table {
    public Bookings(Connection conn) {
        super(conn);
    }

    @Override
    public void setup() {
        try {
            Statement statement = this.getConnection().createStatement();

            statement.executeUpdate(
                "CREATE TABLE IF NOT EXISTS booking(" +
                    "id INTEGER PRIMARY KEY AUTO_INCREMENT," +
                    "cid INTEGER," +
                    "rid INTEGER," +
                    "arrival DATE NOT NULL," +
                    "departure DATE NOT NULL" +
                ")"
            );

            System.out.println("== Bookings (if any) ==");

            ResultSet r = statement.executeQuery("SELECT * FROM booking");

            while (r.next()) {
                Booking row = new Booking();
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

    /**
     * Get a booking by its ID. Returns null if the booking wasn't found.
     *
     * @param id The booking ID to look for
     * @return The Booking if it was found, otherwise null
     */
    public Booking getBooking(
            Integer id  // Argument may not be null
    ) {
        try {
            PreparedStatement statement = this.getConnection().prepareStatement(  // Create a prepared statement
                    "SELECT * FROM booking WHERE id=? LIMIT 1"  // The "?" is replaced later with the ID
            );
            statement.setInt(1, id);  // Replaces the first "?". This is to avoid SQL injection.
            ResultSet set = statement.executeQuery();  // Run the query

            if (set.next()) {  // If there are bookings..
                Booking booking = new Booking();  // Create the Booking object..
                set.first();  // ..Put the result set's pointer at the first row..
                booking.setData(set);  // ..and set the data in the Booking object.

                return booking;  // Return the new Booking object
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Output an error, if there is one
        }

        return null;  // Return null if there was an error or no bookings found
    }

    /**
     * Get a List containing all the bookings in the database.
     *
     * @return All the bookings in the database
     */
    public List<Booking> getBookings() {
        try {
            PreparedStatement statement = this.getConnection().prepareStatement(
                    "SELECT * FROM booking"
            );

            ResultSet set = statement.executeQuery();

            List<Booking> bookings = new ArrayList<>();

            while (set.next()) {
                Booking b = new Booking();
                b.setData(set);
                bookings.add(b);
            }

            return bookings;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();  // No bookings (or an error). The default case.
    }

    /**
     * Get all bookings within a date range. This is an inclusive range, so it
     * will also include bookings that are at the extreme ends of the range.
     *
     * @param arrival Arrival (starting) date, use null to ignore
     * @param departure Departure (ending) date, use null to ignore
     * @return List of Bookings that match
     */
    public List<Booking> getBookingsRange(Date arrival, Date departure) {
        PreparedStatement statement;

        try {
            if (arrival != null) {  // If we have an arrival date..
                if (departure != null) {  // If we also have a departure date..
                    // Then we'll check both
                    statement = this.getConnection().prepareStatement(
                            "SELECT * FROM booking WHERE arrival >= ? AND departure <= ?"
                    );
                    statement.setDate(1, arrival);
                    statement.setDate(2, departure);
                } else {
                    // Otherwise, just check the arrival date
                    statement = this.getConnection().prepareStatement(
                            "SELECT * FROM booking WHERE arrival >= ?"
                    );
                    statement.setDate(1, arrival);
                }
            } else if (departure != null) {
                // If not, and we have a departure date, let's use that instead
                statement = this.getConnection().prepareStatement(
                        "SELECT * FROM booking WHERE departure >= ?"
                );
                statement.setDate(1, departure);
            } else {
                // If we don't have either, well.. Just return.
                return new ArrayList<>();
            }

            // OK, now the actual logic..

            ResultSet set = statement.executeQuery();
            ArrayList<Booking> bookings = new ArrayList<>();

            while(set.next()) {
                Booking b = new Booking();
                b.setData(set);
                bookings.add(b);
            }

            return bookings;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();  // No bookings (or an error)
    }

    /**
     * Get a booking by using a Customer to look it up. Returns null if the booking wasn't found.
     *
     * @param customer The Customer object to look up with
     * @return The Booking if it was found, otherwise null
     */
    public Booking getBookingByCustomer(
            Customer customer
    ) {
        return this.getBookingByCustomer(customer.getCid());
    }

    /**
     * Get a booking by using a Customer ID to look it up. Returns null if the booking wasn't found.
     *
     * @param id The Customer ID to look up with
     * @return The Booking if it was found, otherwise null
     */
    public Booking getBookingByCustomer(
            Integer id
    ) {
        try {
            PreparedStatement statement = this.getConnection().prepareStatement(  // Create a prepared statement
                    "SELECT * FROM booking WHERE cid=? LIMIT 1"  // The "?" is replaced later with the ID
            );
            statement.setInt(1, id);  // Replaces the first "?". This is to avoid SQL injection.
            ResultSet set = statement.executeQuery();  // Run the query

            if (set.next()) {  // If there are bookings..
                Booking booking = new Booking();  // Create the Booking object..
                set.first();  // ..Put the result set's pointer at the first row..
                booking.setData(set);  // ..and set the data in the Booking object.

                return booking;  // Return the new Booking object
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Output an error, if there is one
        }

        return null;  // Return null if there was an error or no bookings found
    }


    /**
     * Get a booking by using a Room object to look it up. Returns null if the booking wasn't found.
     *
     * @param room The Room to look up with
     * @return The Booking if it was found, otherwise null
     */
    public Booking getBookingByRoom(
            Room room
    ) {
        return this.getBookingByRoom(room.getId());
    }


    /**
     * Get a booking by using a Room ID to look it up. Returns null if the booking wasn't found.
     *
     * @param id The Room ID to look up with
     * @return The Booking if it was found, otherwise null
     */
    public Booking getBookingByRoom(
            Integer id
    ) {
        try {
            PreparedStatement statement = this.getConnection().prepareStatement(  // Create a prepared statement
                    "SELECT * FROM booking WHERE rid=? LIMIT 1"  // The "?" is replaced later with the ID
            );
            statement.setInt(1, id);  // Replaces the first "?". This is to avoid SQL injection.
            ResultSet set = statement.executeQuery();  // Run the query

            if (set.next()) {  // If there are bookings..
                Booking booking = new Booking();  // Create the Booking object..
                set.first();  // ..Put the result set's pointer at the first row..
                booking.setData(set);  // ..and set the data in the Booking object.

                return booking;  // Return the new Booking object
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Output an error, if there is one
        }

        return null;  // Return null if there was an error or no bookings found
    }

    public boolean deleteBooking(
            Booking booking
    ) {
        return this.deleteBooking(booking.getID());
    }

    public boolean deleteBooking(
            Integer id
    ) {
        try {
            PreparedStatement statement = this.getConnection().prepareStatement(
                    "DELETE FROM bookings WHERE id=?"
            );
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;  // Returns the number of rows removed
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Insert or update a Booking into the database. If a booking with this ID exists, we'll update it,
     * otherwise we'll add a new one.
     *
     * @param booking The Booking object to insert or update
     */
    Integer upsert(
            Booking booking
    ) {
        Integer newId = this.upsert(
                booking.getID(), booking.getCustomerID(), booking.getRoomID(),
                booking.getArrivalDate(), booking.getDepartureDate()
        );

        booking.setId(newId);
        return newId;
    }

    /**
     * Insert or update a Booking into the database. If a booking with this ID exists, we'll update it,
     * otherwise we'll add a new one.
     *
     * @param id The booking ID - use null if you're making a new booking
     * @param customer The Customer object that this booking refers to
     * @param room The Room object that this booking refers to
     * @param arrival The arrival java.sql.Date object
     * @param departure The departure java.sql.Date object
     */
    Integer upsert(
            Integer id,  // This may be null
            Customer customer,
            Room room,
            Date arrival,
            Date departure
    ) {
        if (id == null) {
            id = 0;  // There should never be an ID of 0 in the database
        }

        return this.upsert(id, customer.getCid(), room.getId(), arrival, departure);
    }

    /**
     * Insert or update a Booking into the database. If a booking with this ID exists, we'll update it,
     * otherwise we'll add a new one.
     *
     * @param id The booking ID - use null if you're making a new booking
     * @param cid The customer ID that this booking refers to
     * @param rid The room ID that this booking refers to
     * @param arrival The arrival java.sql.Date object
     * @param departure The departure java.sql.Date object
     */
    Integer upsert(
            Integer id,  // This may be null
            Integer cid,
            Integer rid,
            Date arrival,
            Date departure
    ) {
        if (id == null) {
            id = 0;  // There should never be an ID of 0 in the database
        }

        try {
            PreparedStatement statement = this.getConnection().prepareStatement(
                "INSERT INTO booking (id, cid, rid, arrival, departure) " +
                "VALUES (?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE " +
                        "cid=VALUES(cid), rid=VALUES(rid), " +
                        "arrival=VALUES(arrival), departure=VALUES(departure)",
                Statement.RETURN_GENERATED_KEYS
            );

            statement.setInt(1, id);
            statement.setInt(2, cid);
            statement.setInt(3, rid);
            statement.setDate(4, arrival);
            statement.setDate(5, departure);

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
