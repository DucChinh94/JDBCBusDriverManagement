package Util.DBUtil;

import Entity.Driver;
import Util.file.DataReadable;
import Util.file.DataWriteable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriverDBUtil implements DataReadable<Driver>, DataWriteable<Driver> {

    private final static String url = "jdbc:oracle:thin:@localhost:1521:orcl";
    private final static String user = "BUSDATABASE";
    private final static String password = "M@isoan130219";
    @Override
    public List<Driver> readDataFromDB() {
        List<Driver> driverList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement st = connection.createStatement();
            String sql = "Select ID, NAME, ADDRESS, PHONE_NUMBER, DRIVER_LEVEL from DRIVER ORDER BY ID";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String fullname = rs.getString(2);
                String address = rs.getString(3);
                String phoneNumber = rs.getString(4);
                String driveLevel = rs.getString(5);
                Driver driver = new Driver(fullname, address, phoneNumber, id, driveLevel);
                driverList.add(driver);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return driverList;
    }

    @Override
    public void writeDataToDB(Driver driver) {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String sql = "INSERT INTO DRIVER (ID, NAME, ADDRESS, PHONE_NUMBER, DRIVER_LEVEL) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, driver.getDriverId());
            pstmt.setString(2, driver.getFullName());
            pstmt.setString(3, driver.getAddress());
            pstmt.setString(4, driver.getPhoneNumber());
            pstmt.setString(5, driver.getLevel());
            pstmt.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
