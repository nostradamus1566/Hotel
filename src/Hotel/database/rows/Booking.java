package Hotel.database.rows;

import Hotel.database.Connection;
import Hotel.database.Row;
import Hotel.database.tables.Customers;
import Hotel.database.tables.Rooms;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class Booking implements Row {
    private Integer id = 0;  // Means that a manually-created Booking will always be a new row in the database
    private Integer cid;
    private Integer rid;
    private Date arrivalDate;
    private Date departureDate;

    @Override
    public void setData(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id");
        this.cid = resultSet.getInt("cid");
        this.rid = resultSet.getInt("rid");
        this.arrivalDate = resultSet.getDate("arrival");
        this.departureDate = resultSet.getDate("departure");
    }

    // region Getters and setters

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getID() {
        return id;
    }

    public Integer getCustomerID() {
        return cid;
    }

    public Customer getCustomer() {
        return ((Customers) Connection.customer).getCustomer(this.getCustomerID());
    }

    public void setCustomerID(Integer cid) {
        this.cid = cid;
    }

    public void setCustomer(Customer customer) {
        this.cid = customer.getCid();
    }

    public Integer getRoomID() {
        return rid;
    }

    public void setRoomID(Integer rid) {
        this.rid = rid;
    }

    public Room getRoom(Integer id) {
        return ((Rooms) Connection.room).getRoom(id);
    }

    public void setRoom(Room room) {
        this.rid = room.getId();
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    // endregion

    public String toString() {
        return String.format(
                "ID: %s | CID: %s | RID: %s | Arrival: %s | Departure: %s",
                this.id, this.cid, this.rid, this.arrivalDate, this.departureDate
        );
    }
}
