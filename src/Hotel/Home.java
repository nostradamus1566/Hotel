package Hotel;

import Hotel.database.Connection;

import java.util.GregorianCalendar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Home {

    private JFrame frame;
    private JTable CustomerTable1;
    private JTextField txtFirstName;
    private JTextField txtLastname;
    private JTable RoomsTable;
    private JTextField txtRoomNumber_2;
    private JTextField txtRoomNumber_1;
    private JTextField txtRoomNumber;
    private JTable CustomerTable;
    private JTextField txtFirstName_1;
    private JTextField txtLastName;
    private JTextField txtId;

    private Connection connection;

    /**
     * Launch the application.
     */
    public Home(Connection connection) {
        this.connection = connection;
        this.initialize();
    }

    public void show() {
        this.frame.setVisible(true);
    }

    /**
     * Initialize the contents of the frame.
     */
    protected void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        GregorianCalendar calendar = new GregorianCalendar();

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(10, 11, 424, 251);
        frame.getContentPane().add(tabbedPane);

        JPanel BookingPane = new JPanel();
        tabbedPane.addTab("Place Booking", null, BookingPane, null);
        BookingPane.setLayout(null);

        CustomerTable1 = new JTable();
        CustomerTable1.setBounds(10, 50, 150, 64);            //this table is to look up existing customers presuming first and last name stored separate so only one book button
        CustomerTable1.setModel(new DefaultTableModel(
                new Object[][]{
                        {null, null},
                        {null, null},
                        {null, null},
                        {null, null},
                },
                new String[]{
                        "id", "Name"
                }
        ) {
            Class[] columnTypes = new Class[]{
                    Integer.class, String.class
            };

            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });
        BookingPane.add(CustomerTable1);

        JLabel lblPlaceBooking = new JLabel("Place Booking");
        lblPlaceBooking.setBounds(167, 11, 65, 14);
        BookingPane.add(lblPlaceBooking);

        JLabel lblId = new JLabel("id");
        lblId.setHorizontalAlignment(SwingConstants.CENTER);
        lblId.setBounds(21, 25, 46, 14);
        BookingPane.add(lblId);

        JLabel lblName = new JLabel("Name");
        lblName.setHorizontalAlignment(SwingConstants.CENTER);
        lblName.setBounds(91, 25, 46, 14);
        BookingPane.add(lblName);

        JComboBox<java.util.Date> checkinBox = new JComboBox<>();
        checkinBox.setBounds(44, 172, 93, 20);
        BookingPane.add(checkinBox);

        for (int i = 0; i < 7; i++) {
            calendar.roll(GregorianCalendar.DAY_OF_MONTH, 1);
            checkinBox.addItem(calendar.getTime());
        }
        JLabel lblCheckIn = new JLabel("check in");
        lblCheckIn.setBounds(140, 175, 46, 14);
        BookingPane.add(lblCheckIn);

        JComboBox checkoutBox = new JComboBox();
        checkoutBox.setBounds(43, 192, 94, 20);
        BookingPane.add(checkoutBox);

        JLabel lblCheckOut = new JLabel("check out");
        lblCheckOut.setBounds(140, 195, 46, 14);
        BookingPane.add(lblCheckOut);

        JButton btnBook = new JButton("Book");        // add code for updating resident
        btnBook.setBounds(43, 128, 89, 23);
        BookingPane.add(btnBook);

        txtFirstName = new JTextField();
        txtFirstName.setText("first name");
        txtFirstName.setBounds(242, 73, 89, 20);
        BookingPane.add(txtFirstName);
        txtFirstName.setColumns(10);

        txtLastname = new JTextField();
        txtLastname.setText("lastname");
        txtLastname.setColumns(10);
        txtLastname.setBounds(242, 94, 89, 20);
        BookingPane.add(txtLastname);

        JLabel lblFirstName = new JLabel("First Name");
        lblFirstName.setBounds(341, 79, 68, 14);
        BookingPane.add(lblFirstName);

        JLabel lblLastName = new JLabel("Last Name");
        lblLastName.setBounds(341, 97, 68, 14);
        BookingPane.add(lblLastName);

        JLabel lblNewCustomers = new JLabel("New Customers");
        lblNewCustomers.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewCustomers.setBounds(242, 48, 89, 14);
        BookingPane.add(lblNewCustomers);

        JComboBox roomtypeBox = new JComboBox();
        roomtypeBox.setBounds(223, 191, 160, 23);
        BookingPane.add(roomtypeBox);

        JLabel lblRoomType = new JLabel("Room Type");
        lblRoomType.setHorizontalAlignment(SwingConstants.CENTER);
        lblRoomType.setBounds(223, 161, 160, 14);
        BookingPane.add(lblRoomType);

        JPanel roomspane = new JPanel();
        tabbedPane.addTab("Rooms", null, roomspane, null);
        roomspane.setLayout(null);

        RoomsTable = new JTable();
        RoomsTable.setModel(new DefaultTableModel(
                new Object[][]{
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                },
                new String[]{
                        "Room", "Booked", "Name", "Check out"
                }
        ) {
            Class[] columnTypes = new Class[]{
                    Integer.class, Boolean.class, String.class, String.class
            };

            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });
        RoomsTable.getColumnModel().getColumn(0).setPreferredWidth(38);
        RoomsTable.getColumnModel().getColumn(1).setPreferredWidth(50);
        RoomsTable.getColumnModel().getColumn(3).setPreferredWidth(60);
        RoomsTable.setBounds(10, 29, 231, 183);
        roomspane.add(RoomsTable);

        JLabel lblRoomNum = new JLabel("Room");
        lblRoomNum.setBounds(10, 11, 46, 14);
        roomspane.add(lblRoomNum);

        JLabel lblBooked = new JLabel("Booked");
        lblBooked.setBounds(50, 11, 46, 14);
        roomspane.add(lblBooked);

        JLabel lblName_1 = new JLabel("Name");
        lblName_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblName_1.setBounds(112, 11, 46, 14);
        roomspane.add(lblName_1);

        JLabel lblCheckOut_1 = new JLabel("Check Out");
        lblCheckOut_1.setBounds(181, 11, 60, 14);
        roomspane.add(lblCheckOut_1);

        JLabel lblSearch = new JLabel("SEARCH");
        lblSearch.setBounds(300, 11, 46, 14);
        roomspane.add(lblSearch);

        txtRoomNumber_2 = new JTextField();
        txtRoomNumber_2.setText("Room Number");
        txtRoomNumber_2.setBounds(276, 36, 86, 20);
        roomspane.add(txtRoomNumber_2);
        txtRoomNumber_2.setColumns(10);

        txtRoomNumber_1 = new JTextField();
        txtRoomNumber_1.setText("Check Out");
        txtRoomNumber_1.setBounds(276, 67, 86, 20);
        roomspane.add(txtRoomNumber_1);
        txtRoomNumber_1.setColumns(10);

        txtRoomNumber = new JTextField();
        txtRoomNumber.setText("Name");
        txtRoomNumber.setBounds(276, 98, 86, 20);
        roomspane.add(txtRoomNumber);
        txtRoomNumber.setColumns(10);

        JButton btnSearch = new JButton("search");
        btnSearch.setBounds(273, 151, 89, 23);
        roomspane.add(btnSearch);


        JPanel Customers = new JPanel();
        tabbedPane.addTab("New tab", null, Customers, null);
        Customers.setLayout(null);

        CustomerTable = new JTable();
        CustomerTable.setBounds(10, 26, 213, 186);
        CustomerTable.setModel(new DefaultTableModel(
                new Object[][]{
                        {null, null, null},
                        {null, null, null},
                        {null, null, null},
                        {null, null, null},
                        {null, null, null},
                        {null, null, null},
                        {null, null, null},
                        {null, null, null},
                        {null, null, null},
                        {null, null, null},
                        {null, null, null},
                        {null, null, null},
                },
                new String[]{
                        "First Name", "Last Name", "id"
                }
        ) {
            Class[] columnTypes = new Class[]{
                    String.class, String.class, Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });
        Customers.add(CustomerTable);

        JLabel lblFirstName_1 = new JLabel("First Name");
        lblFirstName_1.setBounds(10, 11, 68, 14);
        Customers.add(lblFirstName_1);

        JLabel lblLastName_1 = new JLabel("Last Name");
        lblLastName_1.setBounds(88, 11, 71, 14);
        Customers.add(lblLastName_1);

        JLabel lblId_1 = new JLabel("id");
        lblId_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblId_1.setBounds(169, 11, 46, 14);
        Customers.add(lblId_1);

        txtFirstName_1 = new JTextField();
        txtFirstName_1.setText("First Name");
        txtFirstName_1.setHorizontalAlignment(SwingConstants.CENTER);
        txtFirstName_1.setBounds(280, 49, 86, 20);
        Customers.add(txtFirstName_1);
        txtFirstName_1.setColumns(10);

        txtLastName = new JTextField();
        txtLastName.setHorizontalAlignment(SwingConstants.CENTER);
        txtLastName.setText("Last Name");
        txtLastName.setBounds(280, 80, 86, 20);
        Customers.add(txtLastName);
        txtLastName.setColumns(10);

        txtId = new JTextField();
        txtId.setText("id");
        txtId.setHorizontalAlignment(SwingConstants.CENTER);
        txtId.setBounds(280, 111, 86, 20);
        Customers.add(txtId);
        txtId.setColumns(10);

        JButton btnNewButton = new JButton("ADD");
        btnNewButton.setBounds(221, 159, 96, 31);
        Customers.add(btnNewButton);

        JButton btnSearch_1 = new JButton("SEARCH");
        btnSearch_1.setBounds(323, 159, 96, 31);
        Customers.add(btnSearch_1);
    }
}
